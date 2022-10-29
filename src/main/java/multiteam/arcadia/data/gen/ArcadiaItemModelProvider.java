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
        withExistingParent("cloud_block_stormy", modLoc("block/cloud_block_stormy"));
        withExistingParent("cloud_block_jelly", modLoc("block/cloud_block_jelly"));
        withExistingParent("cloud_block_purple", modLoc("block/cloud_block_purple"));
        withExistingParent("cloud_block_silver", modLoc("block/cloud_block_silver"));
        withExistingParent("cloud_block_gold", modLoc("block/cloud_block_gold"));
        withExistingParent("cloud_block_angry", modLoc("block/cloud_block_angry"));


        //Items
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        //Regular Items
        builder(itemGenerated, "cloud_vial");
        builder(itemGenerated, "cloud_vial_stormy");
        builder(itemGenerated, "cloud_vial_jelly");
        builder(itemGenerated, "cloud_vial_purple");
        builder(itemGenerated, "cloud_vial_silver");
        builder(itemGenerated, "cloud_vial_gold");
        builder(itemGenerated, "cloud_vial_angry");

    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }

}
