package multiteam.arcadia.data.gen;

import multiteam.arcadia.Arcadia;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ArcadiaBlockStateProvider extends BlockStateProvider {
    public ArcadiaBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Arcadia.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //simpleBlock(ModBlocks.SILKY_CLOTH_BLOCK.get());
    }
}

