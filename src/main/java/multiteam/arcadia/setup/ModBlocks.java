package multiteam.arcadia.setup;

import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.blocks.CloudBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final Material CLOUD = (new Material(MaterialColor.AIR, false, false, false, false, false, false, Material.ICE.getPushReaction()));

    public static final RegistryObject<Block> CLOUD_BLOCK = register("cloud_block", () -> new CloudBlock(AbstractBlock.Properties.create(CLOUD).hardnessAndResistance(0, 0).harvestLevel(2).sound(SoundType.CLOTH).notSolid().setEmmisiveRendering((p_test_1_, p_test_2_, p_test_3_) -> true)));


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


























