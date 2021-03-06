package multiteam.arcadia.data.client;

import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ArcadiaMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void  registerStatesAndModels() {
        //EXAMPLE GENERATED BLOCK STATE
        //simpleBlock(ModBlocks.MACHINE_BLOCK.get());
    }
}
