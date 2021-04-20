package multiteam.arcadia.setup.blocks;


import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.Registration;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {


    public static final Material CLOUD = (new Material(MaterialColor.NONE, false, false, false, false, false, false, Material.ICE.getPushReaction()));

    public static final RegistryObject<Block> CLOUD_BLOCK = register("cloud_block", () -> new CloudBlock(AbstractBlock.Properties.of(CLOUD).strength(0, 0).harvestLevel(0).sound(SoundType.WOOL).emissiveRendering(ModBlocks::always).isViewBlocking(ModBlocks::never).noCollission()), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<Block> STORMY_CLOUD_BLOCK = register("stormy_cloud_block", () -> new StormyCloudBlock(AbstractBlock.Properties.of(CLOUD).strength(0, 0).harvestLevel(0).sound(SoundType.WOOL).emissiveRendering(ModBlocks::always).isViewBlocking(ModBlocks::never).noCollission()), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<Block> JELLY_CLOUD_BLOCK = register("jelly_cloud_block", () -> new JellyCloudBlock(AbstractBlock.Properties.of(CLOUD).strength(0, 0).harvestLevel(0).sound(SoundType.WOOL).emissiveRendering(ModBlocks::always).isViewBlocking(ModBlocks::never).noCollission()), ArcadiaMod.ARCADIA_MAIN_TAB, false);

    public static final RegistryObject<LanternBlock> ZAP_LANTERN = registerNoItem("zap_lantern", () -> new LanternBlock(AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel(value -> 10).noOcclusion().emissiveRendering(ModBlocks::always)));

    //DEUS ROCK
    public static final RegistryObject<Block> DEUS_ROCK = register("deus_rock", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<Block> DEUS_ROCK_POLISHED = register("polished_deus_rock", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<StairsBlock> DEUS_ROCK_POLISHED_STAIRS = register("polished_deus_rock_stairs", () -> new StairsBlock(ModBlocks.DEUS_ROCK.get().defaultBlockState(), AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<SlabBlock> DEUS_ROCK_POLISHED_SLAB = register("polished_deus_rock_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<Block> DEUS_ROCK_TILES = register("deus_rock_tiles", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<StairsBlock> DEUS_ROCK_TILES_STAIRS = register("deus_rock_tiles_stairs", () -> new StairsBlock(ModBlocks.DEUS_ROCK.get().defaultBlockState(), AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<SlabBlock> DEUS_ROCK_TILES_SLAB = register("deus_rock_tiles_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<Block> DEUS_ROCK_GILDED_TILES = register("deus_rock_gilded_tiles", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<StairsBlock> DEUS_ROCK_GILDED_TILES_STAIRS = register("deus_rock_gilded_tiles_stairs", () -> new StairsBlock(ModBlocks.DEUS_ROCK.get().defaultBlockState(), AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<SlabBlock> DEUS_ROCK_GILDED_TILES_SLAB = register("deus_rock_gilded_tiles_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<Block> DEUS_ROCK_CADMID_TILES = register("deus_rock_cadmid_tiles", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<StairsBlock> DEUS_ROCK_CADMID_TILES_STAIRS = register("deus_rock_cadmid_tiles_stairs", () -> new StairsBlock(ModBlocks.DEUS_ROCK.get().defaultBlockState(), AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<SlabBlock> DEUS_ROCK_CADMID_TILES_SLAB = register("deus_rock_cadmid_tiles_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);

    //SILVER STONE
    public static final RegistryObject<Block> SILVER_STONE = register("silver_stone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<Block> SILVER_STONE_GRASSY = register("grassy_silver_stone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<Block> SILVER_COBBLESTONE = register("silver_cobblestone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<OreBlock> CADMIUM_ORE = register("cadmium_ore", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(4).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);

    //NIMBUS STONE
    public static final RegistryObject<Block> NIMBUS_STONE = register("nimbus_stone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<Block> NIMBUS_COBBLESTONE = register("nimbus_cobblestone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<Block> NIMBUS_STONEBRICKS = register("nimbus_stonebricks", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<Block> CHISELED_NIMBUS_STONEBRICKS = register("chiseled_nimbus_stonebricks", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<StairsBlock> NIMBUS_STONEBRICKS_STAIRS = register("nimbus_stonebricks_stairs", () -> new StairsBlock(ModBlocks.DEUS_ROCK.get().defaultBlockState(), AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<StairsBlock> CHISELED_NIMBUS_STONEBRICKS_STAIRS = register("chiseled_nimbus_stonebricks_stairs", () -> new StairsBlock(ModBlocks.DEUS_ROCK.get().defaultBlockState(), AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<SlabBlock> NIMBUS_STONEBRICKS_SLAB = register("nimbus_stonebricks_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);
    public static final RegistryObject<SlabBlock> CHISELED_NIMBUS_STONEBRICKS_SLAB = register("chiseled_nimbus_stonebricks_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.5f,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ArcadiaMod.ARCADIA_MAIN_TAB, false);


    public static void register() {

    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block){
        return Registration.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, ItemGroup group, boolean isItemFireResistent){
        RegistryObject<T> ret = registerNoItem(name, block);
        if(isItemFireResistent){
            Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(group).fireResistant()));
        }else{
            Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(group)));
        }

        return ret;
    }

    private static boolean always(BlockState p_235426_0_, IBlockReader p_235426_1_, BlockPos p_235426_2_) {
        return true;
    }

    private static boolean never(BlockState p_235436_0_, IBlockReader p_235436_1_, BlockPos p_235436_2_) {
        return false;
    }
}
