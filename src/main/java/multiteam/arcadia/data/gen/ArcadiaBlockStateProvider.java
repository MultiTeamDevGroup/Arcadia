package multiteam.arcadia.data.gen;

import multiteam.arcadia.Arcadia;
import multiteam.arcadia.main.block.ModBlockRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ArcadiaBlockStateProvider extends BlockStateProvider {
    public ArcadiaBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Arcadia.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithRenderType(ModBlockRegistry.CLOUD_BLOCK.get(), "translucent");
        simpleBlockWithRenderType(ModBlockRegistry.CLOUD_BLOCK_STORMY.get(), "translucent");
        simpleBlockWithRenderType(ModBlockRegistry.CLOUD_BLOCK_JELLY.get(), "translucent");
        simpleBlockWithRenderType(ModBlockRegistry.CLOUD_BLOCK_PURPLE.get(), "translucent");
        simpleBlockWithRenderType(ModBlockRegistry.CLOUD_BLOCK_SILVER.get(), "translucent");
        simpleBlockWithRenderType(ModBlockRegistry.CLOUD_BLOCK_GOLD.get(), "translucent");
    }

    public void simpleBlockWithRenderType(Block block, String renderType) {
        //TODO Migrate to MultiCoreLib
        simpleBlock(block, cubeAllWithRenderType(block, renderType));
    }

    public ModelFile cubeAllWithRenderType(Block block, String renderType) {
        return models().cubeAll(name(block), blockTexture(block)).renderType(renderType);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
}

