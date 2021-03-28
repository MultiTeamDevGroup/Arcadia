package multiteam.arcadia.setup.blocks;


import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.Registration;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {


    public static final Material CLOUD = (new Material(MaterialColor.NONE, false, false, false, false, false, false, Material.ICE.getPushReaction()));

    public static final RegistryObject<Block> CLOUD_BLOCK = register("cloud_block", () -> new CloudBlock(AbstractBlock.Properties.of(CLOUD).strength(0, 0).harvestLevel(0).sound(SoundType.WOOL).emissiveRendering((p_test_1_, p_test_2_, p_test_3_) -> true)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<Block> STORMY_CLOUD_BLOCK = register("stormy_cloud_block", () -> new StormyCloudBlock(AbstractBlock.Properties.of(CLOUD).strength(0, 0).harvestLevel(0).sound(SoundType.WOOL).emissiveRendering((p_test_1_, p_test_2_, p_test_3_) -> true)), ArcadiaMod.ARCADIA_MAIN_TAB);

    //DEUS ROCK
    public static final RegistryObject<Block> DEUS_ROCK = register("deus_rock", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<Block> DEUS_ROCK_POLISHED = register("polished_deus_rock", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<StairsBlock> DEUS_ROCK_POLISHED_STAIRS = register("polished_deus_rock_stairs", () -> new StairsBlock(ModBlocks.DEUS_ROCK.get().defaultBlockState(), AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<SlabBlock> DEUS_ROCK_POLISHED_SLAB = register("polished_deus_rock_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<Block> DEUS_ROCK_TILES = register("deus_rock_tiles", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<StairsBlock> DEUS_ROCK_TILES_STAIRS = register("deus_rock_tiles_stairs", () -> new StairsBlock(ModBlocks.DEUS_ROCK.get().defaultBlockState(), AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<SlabBlock> DEUS_ROCK_TILES_SLAB = register("deus_rock_tiles_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<Block> DEUS_ROCK_GILDED_TILES = register("deus_rock_gilded_tiles", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<StairsBlock> DEUS_ROCK_GILDED_TILES_STAIRS = register("deus_rock_gilded_tiles_stairs", () -> new StairsBlock(ModBlocks.DEUS_ROCK.get().defaultBlockState(), AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<SlabBlock> DEUS_ROCK_GILDED_TILES_SLAB = register("deus_rock_gilded_tiles_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<Block> DEUS_ROCK_CADMID_TILES = register("deus_rock_cadmid_tiles", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<StairsBlock> DEUS_ROCK_CADMID_TILES_STAIRS = register("deus_rock_cadmid_tiles_stairs", () -> new StairsBlock(ModBlocks.DEUS_ROCK.get().defaultBlockState(), AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<SlabBlock> DEUS_ROCK_CADMID_TILES_SLAB = register("deus_rock_cadmid_tiles_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);

    //SILVER STONE
    public static final RegistryObject<Block> SILVER_STONE = register("silver_stone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<Block> SILVER_STONE_GRASSY = register("grassy_silver_stone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<Block> SILVER_COBBLESTONE = register("silver_cobblestone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<OreBlock> CADMIUM_ORE = register("cadmium_ore", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(4).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);

    //NIMBUS STONE
    public static final RegistryObject<Block> NIMBUS_STONE = register("nimbus_stone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<Block> NIMBUS_COBBLESTONE = register("nimbus_cobblestone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<Block> NIMBUS_STONEBRICKS = register("nimbus_stonebricks", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<Block> CHISELED_NIMBUS_STONEBRICKS = register("chiseled_nimbus_stonebricks", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<StairsBlock> NIMBUS_STONEBRICKS_STAIRS = register("nimbus_stonebricks_stairs", () -> new StairsBlock(ModBlocks.DEUS_ROCK.get().defaultBlockState(), AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<StairsBlock> CHISELED_NIMBUS_STONEBRICKS_STAIRS = register("chiseled_nimbus_stonebricks_stairs", () -> new StairsBlock(ModBlocks.DEUS_ROCK.get().defaultBlockState(), AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<SlabBlock> NIMBUS_STONEBRICKS_SLAB = register("nimbus_stonebricks_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);
    public static final RegistryObject<SlabBlock> CHISELED_NIMBUS_STONEBRICKS_SLAB = register("chiseled_nimbus_stonebricks_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB);


    public static void register() {

    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block){
        return Registration.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, ItemGroup group){
        RegistryObject<T> ret = registerNoItem(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(group)));
        return ret;
    }
}
