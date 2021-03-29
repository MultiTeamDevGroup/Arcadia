package multiteam.arcadia.setup.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class TestCurio extends Item {

    public TestCurio(Properties properties) {
        super(properties);
    }

    @Override
    public ICapabilityProvider initCapabilities(final ItemStack stack, CompoundNBT unused) {
        ICurio curio = new ICurio() {

            public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid) {
                Multimap<Attribute, AttributeModifier> atts = HashMultimap.create();
                atts.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "arcadia:attack_dmg_bonus", 4.0D, AttributeModifier.Operation.ADDITION));
                atts.put(Attributes.LUCK, new AttributeModifier(uuid, "arcadia:lucky_modifier", 4.0D, AttributeModifier.Operation.ADDITION));
                return atts;
            }

            @Override
            public boolean canEquipFromUse(SlotContext slotContext) {
                return true;
            }

            @Override
            public void onEquip(String identifier, int index, LivingEntity livingEntity) {
                if (livingEntity instanceof PlayerEntity) {
                    startFlying((PlayerEntity) livingEntity);
                }
            }

            @Override
            public void onUnequip(String identifier, int index, LivingEntity livingEntity) {
                if (livingEntity instanceof PlayerEntity) {
                    stopFlying((PlayerEntity) livingEntity);
                }
            }

            private void startFlying(PlayerEntity player) {
                if (!player.isCreative() && !player.isSpectator()) {
                    player.abilities.mayfly = true;
                    player.onUpdateAbilities();
                }
            }

            private void stopFlying(PlayerEntity player) {
                if (!player.isCreative() && !player.isSpectator()) {
                    player.abilities.flying = false;
                    player.abilities.mayfly = false;
                    player.onUpdateAbilities();
                }
            }

            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                if (livingEntity instanceof PlayerEntity) {
                    PlayerEntity player = ((PlayerEntity) livingEntity);
                    if (!player.abilities.mayfly) {
                        startFlying(player);
                    }
                }
            }

            @Override
            public boolean canEquip(String identifier, LivingEntity entityLivingBase) {
                return !CuriosApi.getCuriosHelper().findEquippedCurio(ModItems.TESTCURIO.get(), entityLivingBase)
                        .isPresent();
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

    public static boolean isRingInCuriosSlot(ItemStack ring, LivingEntity player) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(ring.getItem(), player).isPresent();
    }
}
