package multiteam.arcadia.main.item;

import multiteam.arcadia.Arcadia;
import multiteam.arcadia.main.block.ModBlockRegistry;
import multiteam.multicore_lib.setup.utilities.generic.ItemGroupTool;
import net.minecraft.world.item.ItemStack;

public class ModItemRegistry {

    public static final ItemGroupTool ARCADIA_ITEM_GROUP = new ItemGroupTool(Arcadia.MODID + "_items", () -> new ItemStack(ModBlockRegistry.CLOUD_BLOCK.get().asItem()));

    //public static final RegistryObject<Item> HOVERTEXTITEM_EXAMPLE = Registration.ITEMS.register("hover_text_item_example", () -> new HoverTextItemExampleItem(new Item.Properties().tab(MCL_EXAMPLES_TAB)));

    public static void register(){}
}
