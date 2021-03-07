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
    }
}
