package multiteam.arcadia.data.client;

import multiteam.arcadia.setup.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import multiteam.arcadia.ArcadiaMod;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ArcadiaMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void  registerStatesAndModels() {
        simpleBlock(ModBlocks.CLOUD_BLOCK.get());
        simpleBlock(ModBlocks.STORMY_CLOUD_BLOCK.get());

        simpleBlock(ModBlocks.DEUS_ROCK.get());
        simpleBlock(ModBlocks.DEUS_ROCK_POLISHED.get());
        simpleBlock(ModBlocks.DEUS_ROCK_TILES.get());
        simpleBlock(ModBlocks.DEUS_ROCK_GILDED_TILES.get());

        simpleBlock(ModBlocks.SILVER_STONE.get());
    }
}
