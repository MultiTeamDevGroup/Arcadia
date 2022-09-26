package multiteam.arcadia.data.gen;

import multiteam.arcadia.Arcadia;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ArcadiaItemModelProvider extends ItemModelProvider {

    public ArcadiaItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Arcadia.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //Blockitems
        withExistingParent("cloud_block", modLoc("block/cloud_block"));
        withExistingParent("stormy_cloud_block", modLoc("block/stormy_cloud_block"));


        //Items
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        //Regular Items
        //builder(itemGenerated, "cloud_block");

    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }

}
