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
        //withExistingParent("cabbage_bush", modLoc("block/cabbage_bush"));


        //Items
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        //Regular Items
        //builder(itemGenerated, "icon_soldier_modifier_list");

    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }

}
