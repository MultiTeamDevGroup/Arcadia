package multiteam.arcadia.setup;

import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.entitys.armor.ArmorMaterial;
import multiteam.arcadia.setup.items.AngelWings;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;


public class ModItems {

    public static final RegistryObject<ArmorItem> ANGEL_WINGS = Registration.ITEMS.register("angel_wings", () -> new AngelWings(ArmorMaterial.ANGELIC, EquipmentSlotType.CHEST, new Item.Properties().maxDamage(100).group(ArcadiaMod.ARCADIA_TAB).rarity(Rarity.EPIC)));

    static void register() {}
}
