package multiteam.arcadia.setup.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import multiteam.arcadia.ArcadiaMod;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.world.NoteBlockEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class AngelWings extends Item {

    public AngelWings(Properties properties) {
        super(properties);
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
    }

    public boolean isCurioEquipped;

    @Override
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
        ItemStack itemstack = p_77659_2_.getItemInHand(p_77659_3_);
        EquipmentSlotType equipmentslottype = MobEntity.getEquipmentSlotForItem(itemstack);
        ItemStack itemstack1 = p_77659_2_.getItemBySlot(equipmentslottype);
        if (itemstack1.isEmpty()) {
            p_77659_2_.setItemSlot(equipmentslottype, itemstack.copy());
            itemstack.setCount(0);
            return ActionResult.sidedSuccess(itemstack, p_77659_1_.isClientSide());
        } else {
            return ActionResult.fail(itemstack);
        }
    }

    @Override
    public ICapabilityProvider initCapabilities(final ItemStack stack, CompoundNBT unused) {
        ICurio curio = new ICurio() {

            public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid) {
                Multimap<Attribute, AttributeModifier> atts = HashMultimap.create();
                //atts.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "arcadia:attack_dmg_bonus", 4.0D, AttributeModifier.Operation.ADDITION));
                //atts.put(Attributes.LUCK, new AttributeModifier(uuid, "arcadia:lucky_modifier", 4.0D, AttributeModifier.Operation.ADDITION));
                return atts;
            }

            @Override
            public boolean canEquipFromUse(SlotContext slotContext) {
                return true;
            }

            @Override
            public boolean canEquip(String identifier, LivingEntity entityLivingBase) {
                return !CuriosApi.getCuriosHelper().findEquippedCurio(ModItems.ANGEL_WINGS.get(), entityLivingBase)
                        .isPresent();
            }

            @Override
            public void onEquip(String identifier, int index, LivingEntity livingEntity) {
                if (livingEntity instanceof PlayerEntity) {

                }
            }

            @Override
            public void onUnequip(String identifier, int index, LivingEntity livingEntity) {
                if (livingEntity instanceof PlayerEntity) {

                }
            }
        };

        return new ICapabilityProvider() {
            private final LazyOptional<ICurio> curioOpt = LazyOptional.of(() -> curio);

            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {

                return CuriosCapability.ITEM.orEmpty(cap, curioOpt);
            }
        };
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
            if(stack.getItem() == ModItems.ANGEL_WINGS.get()){
                return true;
            }else if(CuriosApi.getCuriosHelper().findEquippedCurio(ModItems.ANGEL_WINGS.get(), (LivingEntity)entity).isPresent()){
                return true;
            }else{
                return false;
            }
            //return stack.getItem() == ModItems.ANGEL_WINGS.get();
            //mess with this if you want to get the curious stuff to render and work when equipped in a curio slot.

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
