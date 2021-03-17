package multiteam.arcadia.setup;

import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.blocks.CloudBlock;
import multiteam.arcadia.setup.blocks.StormyCloudBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    //CUSTOM MATERIALS
    public static final Material CLOUD = (new Material(MaterialColor.AIR, false, false, false, false, false, false, Material.ICE.getPushReaction()));

    //CLOUDS
    public static final RegistryObject<Block> CLOUD_BLOCK = register("cloud_block", () -> new CloudBlock(AbstractBlock.Properties.create(CLOUD).hardnessAndResistance(0, 0).harvestLevel(0).sound(SoundType.CLOTH).notSolid().setEmmisiveRendering((p_test_1_, p_test_2_, p_test_3_) -> true)));
    public static final RegistryObject<Block> STORMY_CLOUD_BLOCK = register("stormy_cloud_block", () -> new StormyCloudBlock(AbstractBlock.Properties.create(CLOUD).hardnessAndResistance(0, 0).harvestLevel(0).sound(SoundType.CLOTH).notSolid().setEmmisiveRendering((p_test_1_, p_test_2_, p_test_3_) -> true)));

    //STONES
    public static final RegistryObject<Block> DEUS_ROCK = register("deus_rock", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> DEUS_ROCK_POLISHED = register("polished_deus_rock", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));
    public static final RegistryObject<StairsBlock> DEUS_ROCK_POLISHED_STAIRS = register("polished_deus_rock_stairs", () -> new StairsBlock(ModBlocks.DEUS_ROCK.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));
    public static final RegistryObject<SlabBlock> DEUS_ROCK_POLISHED_SLAB = register("polished_deus_rock_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> DEUS_ROCK_TILES = register("deus_rock_tiles", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));
    public static final RegistryObject<StairsBlock> DEUS_ROCK_TILES_STAIRS = register("deus_rock_tiles_stairs", () -> new StairsBlock(ModBlocks.DEUS_ROCK.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));
    public static final RegistryObject<SlabBlock> DEUS_ROCK_TILES_SLAB = register("deus_rock_tiles_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> DEUS_ROCK_GILDED_TILES = register("deus_rock_gilded_tiles", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));
    public static final RegistryObject<StairsBlock> DEUS_ROCK_GILDED_TILES_STAIRS = register("deus_rock_gilded_tiles_stairs", () -> new StairsBlock(ModBlocks.DEUS_ROCK.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));
    public static final RegistryObject<SlabBlock> DEUS_ROCK_GILDED_TILES_SLAB = register("deus_rock_gilded_tiles_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));


    public static final RegistryObject<Block> SILVER_STONE = register("silver_stone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SILVER_STONE_GRASSY = register("grassy_silver_stone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SILVER_COBBLESTONE = register("silver_cobblestone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));


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


























