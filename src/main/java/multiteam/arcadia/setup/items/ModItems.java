package multiteam.arcadia.setup.items;

import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.Registration;
import multiteam.arcadia.setup.music.ModMusics;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {

    public static final RegistryObject<Item> ANGEL_WINGS = Registration.ITEMS.register("angel_wings", () -> new AngelWings(new Item.Properties().tab(ArcadiaMod.ARCADIA_MAIN_TAB).durability(100).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> CADMIUM_INGOT = Registration.ITEMS.register("cadmium_ingot", () -> new Item(new Item.Properties().tab(ArcadiaMod.ARCADIA_MAIN_TAB)));
    public static final RegistryObject<Item> CADMIUM_ORB = Registration.ITEMS.register("cadmium_orb", () -> new Item(new Item.Properties().tab(ArcadiaMod.ARCADIA_MAIN_TAB)));

    public static final RegistryObject<MusicDiscItem> CLOUDY_PARADISE_MUSIC_DISC = Registration.ITEMS.register("cloudy_paradise_disc", () -> new MusicDiscItem(1, ModMusics.CLOUDY_PARADISE_LAZY.get(), new Item.Properties().stacksTo(1).tab(ArcadiaMod.ARCADIA_MAIN_TAB).rarity(Rarity.RARE)));
    public static final RegistryObject<MusicDiscItem> WINGED_MUSIC_DISC = Registration.ITEMS.register("winged_disc", () -> new MusicDiscItem(1, ModMusics.WINGED_LAZY.get(), new Item.Properties().stacksTo(1).tab(ArcadiaMod.ARCADIA_MAIN_TAB).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> TESTCURIO = Registration.ITEMS.register("test_curio", () -> new TestCurio(new Item.Properties().tab(ArcadiaMod.ARCADIA_MAIN_TAB)));

    public static void register() {

    }

}
