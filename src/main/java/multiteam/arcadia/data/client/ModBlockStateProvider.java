package multiteam.arcadia.data.client;

import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.blocks.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ArcadiaMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.CLOUD_BLOCK.get());
        simpleBlock(ModBlocks.JELLY_CLOUD_BLOCK.get());
        simpleBlock(ModBlocks.STORMY_CLOUD_BLOCK.get());

        simpleBlock(ModBlocks.DEUS_ROCK.get());
        simpleBlock(ModBlocks.DEUS_ROCK_POLISHED.get());
        simpleBlock(ModBlocks.DEUS_ROCK_TILES.get());
        simpleBlock(ModBlocks.DEUS_ROCK_GILDED_TILES.get());
        simpleBlock(ModBlocks.DEUS_ROCK_CADMID_TILES.get());

        simpleBlock(ModBlocks.SILVER_STONE.get());
        simpleBlock(ModBlocks.SILVER_COBBLESTONE.get());
        simpleBlock(ModBlocks.CADMIUM_ORE.get());

        simpleBlock(ModBlocks.NIMBUS_STONE.get());
        simpleBlock(ModBlocks.NIMBUS_COBBLESTONE.get());
        simpleBlock(ModBlocks.NIMBUS_STONEBRICKS.get());
        simpleBlock(ModBlocks.CHISELED_NIMBUS_STONEBRICKS.get());
    }
}
