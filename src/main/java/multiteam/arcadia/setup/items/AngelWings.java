package multiteam.arcadia.setup.items;

import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.ModItems;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import javax.annotation.Nullable;

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

    public static class AngelWings_ extends Item
    {

        public AngelWings_(Properties properties)
        {
            super(properties);
            DispenserBlock.registerDispenseBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
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
                stack.damageItem(1, entity, e -> e.sendBreakAnimation(EquipmentSlotType.CHEST));
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
