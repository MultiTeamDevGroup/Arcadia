package multiteam.arcadia.setup;

import com.mojang.blaze3d.systems.RenderSystem;
import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.entity.ModEntitys;
import multiteam.arcadia.setup.entity.zap.ZapEntity;
import multiteam.arcadia.setup.world.dimension.ModDimensions;
import multiteam.multicore_lib.setup.utilities.TeleportTool;
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
                    TeleportTool.teleportTo(serverPlayer, world, new BlockPos(currentPlayerPos.getX(), 260, currentPlayerPos.getZ()));
                }
            }
        }
    }

}
