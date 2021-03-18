package multiteam.arcadia.setup.items;

import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.ModItems;
import multiteam.arcadia.setup.util.TeleportationTools;
import multiteam.arcadia.setup.world.dimensions.ModDimensions;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;
import java.util.List;

@Mod(ArcadiaMod.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ArcadiaMod.MOD_ID)
public class AngelWings {

    public static void onClientSetup(FMLClientSetupEvent event)
    {
        registerElytraLayer();
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerElytraLayer()
    {
        Minecraft.getInstance().getRenderManager().getSkinMap().values().forEach(player -> player.addLayer(new CustomElytraLayer(player)));
    }

    public static class AngelWings_ extends Item {

        public AngelWings_(Properties properties)
        {
            super(properties);
            DispenserBlock.registerDispenseBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
        }

        PlayerEntity playerE;

        public int getHarvestLevel(ItemStack stack, net.minecraftforge.common.ToolType tool, @Nullable PlayerEntity player, @Nullable BlockState blockState) {
            playerE = player;
            return -1;
        }

        public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
            super.addInformation(stack, worldIn, tooltip, flagIn);
            tooltip.add(new TranslationTextComponent("tooltip.arcadia.angel_wing_description").copyRaw().mergeStyle(TextFormatting.YELLOW));
        }

        @Nullable
        @Override
        public EquipmentSlotType getEquipmentSlot(ItemStack stack)
        {
            return EquipmentSlotType.CHEST; //Or you could just extend ItemArmor
        }

        @Override
        public boolean canElytraFly(ItemStack stack, LivingEntity entity)
        {
            return true;
        }


        @Override
        public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks)
        {
            //Adding 1 to ticksElytraFlying prevents damage on the very first tick.
            if (!entity.world.isRemote && (flightTicks + 1) % 20 == 0)
            {
                if(entity instanceof PlayerEntity){
                    PlayerEntity playerEntity = (PlayerEntity) entity.getEntity();
                    if(!playerEntity.isCreative()){
                        stack.damageItem(1, entity, e -> e.sendBreakAnimation(EquipmentSlotType.CHEST));
                    }
                }
            }

            if(entity instanceof PlayerEntity){
                PlayerEntity playerE = (PlayerEntity) entity.getEntity();
                if(entity.getPosition().getY() >= 300 && playerE.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ModItems.ANGEL_WINGS.get() && playerE.world.getDimensionKey() == World.OVERWORLD){
                    // this is how you send something to the plaayer's eaction bar:
                    // playerE.sendStatusMessage(new TranslationTextComponent("message.arcadia.action_bar.entering_dimension"), true);

                    ServerWorld destWorld = playerE.getServer().getWorld(ModDimensions.CLOUD_REALM);
                    ServerPlayerEntity serverPlayer = (ServerPlayerEntity) playerE;
                    TeleportationTools.teleport(serverPlayer, destWorld, new BlockPos(serverPlayer.getPosition().getX(), 30, serverPlayer.getPosition().getY()));
                }
            }

            return true;
        }

    }

    @OnlyIn(Dist.CLIENT)
    public static class CustomElytraLayer extends ElytraLayer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>>
    {
        public static final ResourceLocation TEXTURE_ELYTRA = new ResourceLocation(ArcadiaMod.MOD_ID, "textures/entity/angel_wings.png");

        public CustomElytraLayer(IEntityRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> rendererIn)
        {
            super(rendererIn);
        }

        @Override
        public boolean shouldRender(ItemStack stack, AbstractClientPlayerEntity entity)
        {

            return stack.getItem() == ModItems.ANGEL_WINGS.get();
        }

        @Override
        public ResourceLocation getElytraTexture(ItemStack stack, AbstractClientPlayerEntity entity)
        {
            return TEXTURE_ELYTRA;
        }
    }

}
