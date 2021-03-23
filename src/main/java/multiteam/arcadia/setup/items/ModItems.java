package multiteam.arcadia.setup.items;

import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.Registration;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {

    public static final RegistryObject<Item> ANGEL_WINGS = Registration.ITEMS.register("angel_wings", () -> new AngelWings(new Item.Properties().tab(ArcadiaMod.ARCADIA_MAIN_TAB).durability(100)));
    public static final RegistryObject<Item> CADMIUM_INGOT = Registration.ITEMS.register("cadmium_ingot", () -> new Item(new Item.Properties().tab(ArcadiaMod.ARCADIA_MAIN_TAB)));
    public static final RegistryObject<Item> CADMIUM_ORB = Registration.ITEMS.register("cadmium_orb", () -> new Item(new Item.Properties().tab(ArcadiaMod.ARCADIA_MAIN_TAB)));

    public static void register() {

    }

}
