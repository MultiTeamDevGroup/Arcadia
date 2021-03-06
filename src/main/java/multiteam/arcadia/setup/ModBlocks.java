package multiteam.arcadia.setup;

import multiteam.arcadia.ArcadiaMod;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    //EXAMPLE BLOCK
    // public static final RegistryObject<Block> MACHINE_BLOCK = register("machine_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(6, 6).harvestLevel(2).sound(SoundType.NETHERITE)));


    static void register() {}

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block){
        return Registration.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(String name_, Supplier<T> block_){
        RegistryObject<T> ret = registerNoItem(name_, block_);
        Registration.ITEMS.register(name_, () -> new BlockItem(ret.get(), new Item.Properties().group(ArcadiaMod.ARCADIA_TAB)));
        return ret;
    }
}


























