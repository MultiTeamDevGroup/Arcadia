package multiteam.arcadia.setup.items;

import multiteam.multicore_lib.setup.utilities.*;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.world.dimension.ModDimensions;
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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
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

        /**ItemStack itemStack = new ItemStack(this.getItem());
        CompoundNBT nbtTagCompound = itemStack.getTag();
        if (nbtTagCompound == null){
            nbtTagCompound = new CompoundNBT();
        }
        itemStack.setTag(nbtTagCompound);
        nbtTagCompound.putInt("barCurrentFill", 5); **/
    }

    public boolean isCurioEquipped;

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerEntity, Hand handOfPlayer) {
        ItemStack itemstack = playerEntity.getItemInHand(handOfPlayer);
        EquipmentSlotType equipmentslottype = MobEntity.getEquipmentSlotForItem(itemstack);
        ItemStack itemstack1 = playerEntity.getItemBySlot(equipmentslottype);

        /**if(playerEntity.level.isClientSide){
            CompoundNBT nbtTagCompound = itemstack.getTag();
            if (nbtTagCompound != null){
                itemstack.setTag(nbtTagCompound);
                int currentFill = nbtTagCompound.getInt("barCurrentFill");

                if(currentFill > 0){
                    currentFill--;
                }
                nbtTagCompound.putInt("barCurrentFill", currentFill);
            }
        }**/

        if (itemstack1.isEmpty()) {
            playerEntity.setItemSlot(equipmentslottype, itemstack.copy());
            itemstack.setCount(0);
            return ActionResult.sidedSuccess(itemstack, worldIn.isClientSide());
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

        /**CompoundNBT nbtTagCompound = stack.getTag();

        if (nbtTagCompound == null){
            nbtTagCompound = new CompoundNBT();
        }

        stack.setTag(nbtTagCompound);**/

        //This is how you make a bar, that can be used as a progress bar or something like taht, to display an integer value in a pretty fancy way.
        // it does not work properly yet
        // TooltipItemRenderSetup.makeItemBar(tooltip, nbtTagCompound, Items.FEATHER, Items.APPLE, 5, nbtTagCompound.getInt("barCurrentFill"));

        //This is how you add items that does nothing to the tooltip
        /**Item[] itemsToRender = {ModItems.ANGEL_WINGS.get(), ModItems.ANGEL_WINGS.get(), ModItems.ANGEL_WINGS.get()};
        TooltipItemRenderSetup.putItems(tooltip,nbtTagCompound, itemsToRender);**/

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
            ServerPlayerEntity serverPlayer = null;
            if(playerE instanceof ServerPlayerEntity){
                serverPlayer = (ServerPlayerEntity)playerE;
            }
            if(entity.position().y > 300 && playerE.getItemBySlot(EquipmentSlotType.CHEST).getItem() == ModItems.ANGEL_WINGS.get() && playerE.level.dimension() == World.OVERWORLD){
                // this is how you send something to the player's action bar: AT LEAST IT WAS ON MCP MAPPINGS!
                // playerE.sendStatusMessage(new TranslationTextComponent("message.arcadia.action_bar.entering_dimension"), true);

                if(stack != null){
                    stack.hurtAndBreak(20, entity, e -> e.hasItemInSlot(EquipmentSlotType.CHEST));
                }
                if(serverPlayer !=null){
                    BlockPos currentPlayerPos = serverPlayer.blockPosition();
                    ServerWorld world = serverPlayer.getServer().getLevel(ModDimensions.CLOUD_REALM);
                    TeleportTool.teleportTo(serverPlayer, world, new BlockPos(currentPlayerPos.getX(), 100, currentPlayerPos.getZ()));
                }

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
