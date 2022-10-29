package multiteam.arcadia.main.item;

import multiteam.arcadia.Arcadia;
import multiteam.arcadia.main.Registration;
import multiteam.arcadia.main.block.ModBlockRegistry;
import multiteam.multicore_lib.setup.utilities.generic.ItemGroupTool;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

public class ModItemRegistry {

    public static final ItemGroupTool ARCADIA_ITEM_GROUP = new ItemGroupTool(Arcadia.MODID + "_items", () -> new ItemStack(ModBlockRegistry.CLOUD_BLOCK.get().asItem()));

    public static final RegistryObject<Item> CLOUD_VIAL = Registration.ITEMS.register("cloud_vial", () -> new CloudVial(new Item.Properties().tab(ARCADIA_ITEM_GROUP), 12966903, "cloud_vial"));
    public static final RegistryObject<Item> CLOUD_VIAL_STORMY = Registration.ITEMS.register("cloud_vial_stormy", () -> new CloudVial(new Item.Properties().tab(ARCADIA_ITEM_GROUP), 4344175, "cloud_vial_stormy"));
    public static final RegistryObject<Item> CLOUD_VIAL_JELLY = Registration.ITEMS.register("cloud_vial_jelly", () -> new CloudVial(new Item.Properties().tab(ARCADIA_ITEM_GROUP), 7335371, "cloud_vial_jelly"));
    public static final RegistryObject<Item> CLOUD_VIAL_PURPLE = Registration.ITEMS.register("cloud_vial_purple", () -> new CloudVial(new Item.Properties().tab(ARCADIA_ITEM_GROUP), 12222191, "cloud_vial_purple"));
    public static final RegistryObject<Item> CLOUD_VIAL_SILVER = Registration.ITEMS.register("cloud_vial_silver", () -> new CloudVial(new Item.Properties().tab(ARCADIA_ITEM_GROUP), 13160161, "cloud_vial_silver"));
    public static final RegistryObject<Item> CLOUD_VIAL_GOLD = Registration.ITEMS.register("cloud_vial_gold", () -> new CloudVial(new Item.Properties().tab(ARCADIA_ITEM_GROUP), 15522560, "cloud_vial_gold"));
    public static final RegistryObject<Item> CLOUD_VIAL_ANGRY = Registration.ITEMS.register("cloud_vial_angry", () -> new CloudVial(new Item.Properties().tab(ARCADIA_ITEM_GROUP), 10818608, "cloud_vial_angry"));

    public static void register(){}
}
