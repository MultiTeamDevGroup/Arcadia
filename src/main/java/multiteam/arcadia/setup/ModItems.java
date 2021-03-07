package multiteam.arcadia.setup;

import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.items.AngelWings;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;
import top.theillusivec4.caelus.api.CaelusApi;


public class ModItems {

    public static final RegistryObject<ElytraItem> ANGEL_WINGS = Registration.ITEMS.register("angel_wings", () -> new AngelWings(new Item.Properties().maxDamage(100).group(ArcadiaMod.ARCADIA_TAB).rarity(Rarity.EPIC)));

    static void register() {}
}
