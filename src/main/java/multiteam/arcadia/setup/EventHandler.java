package multiteam.arcadia.setup;

import com.mojang.blaze3d.systems.RenderSystem;
import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.entity.ModEntitys;
import multiteam.arcadia.setup.entity.zap.ZapEntity;
import multiteam.arcadia.setup.util.TeleportationTools;
import multiteam.arcadia.setup.world.dimension.ModDimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Mod.EventBusSubscriber(modid = ArcadiaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    @SubscribeEvent
    public static void onEntityAddedToWorld(EntityJoinWorldEvent event){
        if(event.getEntity().getType() == EntityType.LIGHTNING_BOLT && !event.getWorld().isClientSide){
            int randomChance = ThreadLocalRandom.current().nextInt(0, 100 + 1);
            if(randomChance > 96){
                Entity zapEnty = new ZapEntity(ModEntitys.ZAP.get(),event.getWorld());
                event.getWorld().addFreshEntity(zapEnty);
                zapEnty.setPos(event.getEntity().getX()+ThreadLocalRandom.current().nextInt(2, 5 + 1), event.getEntity().getY()+ThreadLocalRandom.current().nextInt(2, 5 + 1), event.getEntity().getZ()+ThreadLocalRandom.current().nextInt(2, 5 + 1));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerFallFromDimension(TickEvent.PlayerTickEvent event){
        if(event.player instanceof PlayerEntity){
            PlayerEntity playerEntity = event.player;
            if(playerEntity.level.dimension() == ModDimensions.CLOUD_REALM && playerEntity.position().y < -1 ){
                ServerPlayerEntity serverPlayer = null;
                if(playerEntity instanceof ServerPlayerEntity){
                    serverPlayer = (ServerPlayerEntity)playerEntity;
                }
                if(serverPlayer !=null){
                    BlockPos currentPlayerPos = serverPlayer.blockPosition();
                    ServerWorld world = serverPlayer.getServer().getLevel(World.OVERWORLD);
                    TeleportationTools.teleport(serverPlayer, world, new BlockPos(currentPlayerPos.getX(), 260, currentPlayerPos.getZ()));
                }
            }
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void renderTooltip(RenderTooltipEvent.PostText event) {
        ItemStack stack = event.getStack();

        RenderSystem.pushMatrix();
        RenderSystem.translatef(event.getX(), event.getY() + 12, 500);
        RenderSystem.scalef(0.5f, 0.5f, 1.0f);
        Minecraft mc = Minecraft.getInstance();
        List<? extends ITextProperties> tooltip = event.getLines();

        CompoundNBT nbtTagCompound = stack.getTag();

        if(nbtTagCompound != null){

            if(nbtTagCompound.getBoolean("hasTooltipItem")){
                for (int i = 0; i < nbtTagCompound.getIntArray("itemsToRender").length; i++){
                    ItemStack displayStack = new ItemStack(Item.byId(nbtTagCompound.getIntArray("itemsToRender")[i]));
                    if(displayStack != null){
                        mc.getItemRenderer().renderGuiItem(displayStack, i * 17, nbtTagCompound.getInt("lineToRender") + 20);
                    }
                }
            }

            if(nbtTagCompound.getBoolean("hasItemBar")){
                int barLength = nbtTagCompound.getInt("barLenght");
                int barFillAmount = nbtTagCompound.getInt("barFillAmountDisplay");
                Item[] itemsToRender = new Item[nbtTagCompound.getInt("barLenght")];

                for (int i = 0; i < barLength; i++) {
                    if(barFillAmount == 0){
                        itemsToRender[i] = Item.byId(nbtTagCompound.getInt("barItemEmpty"));
                    }else if(i < barFillAmount){
                        itemsToRender[i] = Item.byId(nbtTagCompound.getInt("barItem"));
                    }else{
                        itemsToRender[i] = Item.byId(nbtTagCompound.getInt("barItemEmpty"));
                    }
                }

                for (int i = 0; i < itemsToRender.length; i++){
                    ItemStack displayStack = new ItemStack(itemsToRender[i]);
                    if(displayStack != null){
                        mc.getItemRenderer().renderGuiItem(displayStack, i * 17, nbtTagCompound.getInt("lineToRender") + 20);
                    }
                }
            }

        }

        RenderSystem.popMatrix();
    }

}
