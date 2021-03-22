package multiteam.arcadia.setup.items;

import multiteam.arcadia.ArcadiaMod;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import javax.annotation.Nullable;
import java.util.List;

public class AngelWings extends Item {

    public AngelWings(Properties properties) {
        super(properties);
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("tooltip.arcadia.angel_wing_description").append("").withStyle(TextFormatting.YELLOW));
    }

    @Nullable
    @Override
    public EquipmentSlotType getEquipmentSlot(ItemStack stack)
    {
        return EquipmentSlotType.CHEST;
    }

    @Override
    public boolean canElytraFly(ItemStack stack, LivingEntity entity){
        return true;
    }

    int cooldown;

    @Override
    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks){
        if (!entity.level.isClientSide && (flightTicks + 1) % 20 == 0)
        {
            if(entity instanceof PlayerEntity){
                PlayerEntity playerEntity = (PlayerEntity) entity.getEntity();
                if(!playerEntity.isCreative()){
                    stack.hurtAndBreak(1, entity, e -> e.hasItemInSlot(EquipmentSlotType.CHEST));
                }
            }
        }

        if(cooldown >= 0){
            cooldown--;
        }

        if(entity instanceof PlayerEntity && cooldown <= 0){
            PlayerEntity playerE = (PlayerEntity) entity.getEntity();
            if(entity.position().y >= 300 && playerE.getItemBySlot(EquipmentSlotType.CHEST).getItem() == ModItems.ANGEL_WINGS.get() && playerE.level.dimension() == World.OVERWORLD){
                // this is how you send something to the plaayer's action bar: AT LEAST IT WAS ON THE PREVIOUS VERSION!
                // playerE.sendStatusMessage(new TranslationTextComponent("message.arcadia.action_bar.entering_dimension"), true);

                //ServerWorld destWorld = playerE.getServer().overworld().getWorldServer(ModDimensions.CLOUD_REALM);
                //ServerPlayerEntity serverPlayer = (ServerPlayerEntity) playerE;
                //TeleportationTools.teleport(serverPlayer, destWorld, new BlockPos(serverPlayer.getPosition().getX(), 30, serverPlayer.getPosition().getY()));
                cooldown = 100;
            }
        }

        return true;
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

    public static void onClientSetup(FMLClientSetupEvent event)
    {
        registerElytraLayer();
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerElytraLayer()
    {
        Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap().values().forEach(player -> player.addLayer(new CustomElytraLayer(player)));
    }


}
