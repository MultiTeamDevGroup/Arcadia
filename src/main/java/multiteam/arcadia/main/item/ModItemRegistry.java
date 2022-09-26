package multiteam.arcadia.main.item;

import multiteam.arcadia.Arcadia;
import multiteam.multicore_lib.setup.utilities.generic.ItemGroupTool;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModItemRegistry {

    public static final ItemGroupTool ARCADIA_ITEM_GROUP = new ItemGroupTool(Arcadia.MODID + "_items", () -> new ItemStack(Items.WHITE_WOOL));

    //public static final RegistryObject<Item> HOVERTEXTITEM_EXAMPLE = Registration.ITEMS.register("hover_text_item_example", () -> new HoverTextItemExampleItem(new Item.Properties().tab(MCL_EXAMPLES_TAB)));

    public static void register(){}
}
