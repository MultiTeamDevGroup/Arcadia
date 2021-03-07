package multiteam.arcadia.setup.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import top.theillusivec4.caelus.Caelus;
import top.theillusivec4.caelus.api.CaelusApi;

import javax.annotation.Nullable;
import java.util.List;

public class AngelWings extends ElytraItem {

    public AngelWings(Properties builder) {
        super(builder);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("tooltip.arcadia.angel_wing_description").copyRaw().mergeStyle(TextFormatting.YELLOW));
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.FEATHER;
    }
/**
    public void onEquip(String identifier, int index, LivingEntity entityLivingBase) {
        ModifiableAttributeInstance attributeInstance = entityLivingBase.getAttribute(CaelusApi.ELYTRA_FLIGHT.get());

        if (attributeInstance != null && !attributeInstance.hasModifier(ELYTRA_CURIO_MODIFIER) && CaelusApi.canElytraFly(entityLivingBase, this.stack)) {
            attributeInstance.applyNonPersistentModifier(ELYTRA_CURIO_MODIFIER);
        }
    }**/

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        EquipmentSlotType equipmentslottype = MobEntity.getSlotForItemStack(itemstack);
        ItemStack itemstack1 = playerIn.getItemStackFromSlot(equipmentslottype);
        if (itemstack1.isEmpty()) {
            playerIn.setItemStackToSlot(equipmentslottype, itemstack.copy());
            itemstack.setCount(0);
            ModifiableAttributeInstance attributeInstance = playerIn.getAttribute(CaelusApi.ELYTRA_FLIGHT.get());
            attributeInstance.applyNonPersistentModifier(CaelusApi.ELYTRA_MODIFIER);
            return ActionResult.func_233538_a_(itemstack, worldIn.isRemote());
        } else {
            return ActionResult.resultFail(itemstack);
        }
    }

    @Override
    public boolean canElytraFly(ItemStack stack, net.minecraft.entity.LivingEntity entity) {
        return ElytraItem.isUsable(stack);
    }

    @Override
    public boolean elytraFlightTick(ItemStack stack, net.minecraft.entity.LivingEntity entity, int flightTicks) {
        if (!entity.world.isRemote && (flightTicks + 1) % 20 == 0) {
            stack.damageItem(1, entity, e -> e.sendBreakAnimation(net.minecraft.inventory.EquipmentSlotType.CHEST));
        }
        return true;
    }

}
