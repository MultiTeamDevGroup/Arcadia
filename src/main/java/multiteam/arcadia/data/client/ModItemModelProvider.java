package multiteam.arcadia.data.client;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import multiteam.arcadia.ArcadiaMod;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper){
        super(generator, ArcadiaMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent("cloud_block", modLoc("block/cloud_block"));
        withExistingParent("stormy_cloud_block", modLoc("block/stormy_cloud_block"));
        withExistingParent("deus_rock", modLoc("block/deus_rock"));
        withExistingParent("polished_deus_rock", modLoc("block/polished_deus_rock"));
        withExistingParent("deus_rock_tiles", modLoc("block/deus_rock_tiles"));
        withExistingParent("deus_rock_gilded_tiles", modLoc("block/deus_rock_gilded_tiles"));
        withExistingParent("silver_stone", modLoc("block/silver_stone"));
        withExistingParent("grassy_silver_stone", modLoc("block/grassy_silver_stone"));
        withExistingParent("silver_cobblestone", modLoc("block/silver_cobblestone"));

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        builder(itemGenerated, "angel_wings");
    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/"+name);
    }
}
