package multiteam.arcadia.setup.items;

//i have no idea how this should work or how am i supposed to make it work

/**import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.TransformationMatrix;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.*;
import net.minecraftforge.client.model.geometry.IModelGeometry;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

public final class ZappedMultiLayerItem implements IModelGeometry<ZappedMultiLayerItem>{

    @Nonnull
    private final Item baseItem;

    private final boolean applyOutline;

    public ZappedMultiLayerItem(boolean applyOutline, Item baseItem1){
        this(baseItem1, applyOutline);
    }

    public ZappedMultiLayerItem(Item baseItem, boolean applyOutline){
        this.applyOutline = applyOutline;
        this.baseItem = baseItem;
    }


    @Override
    public IBakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<RenderMaterial, TextureAtlasSprite> spriteGetter, IModelTransform modelTransform, ItemOverrideList overrides, ResourceLocation modelLocation) {
        RenderMaterial baseLocation = owner.isTexturePresent("base") ? owner.resolveTexture("base") : null;
        RenderMaterial outlineLocation = owner.isTexturePresent("outline") ? owner.resolveTexture("outline") : null;

        IModelTransform transformsFromModel = owner.getCombinedTransform();

        TextureAtlasSprite baseSprite = baseItem != Items.AIR ? spriteGetter.apply(ForgeHooksClient.getBlockMaterial(baseItem.getRegistryName())) : null;
        TextureAtlasSprite coverSprite = (outlineLocation != null) ? spriteGetter.apply(outlineLocation) : null;

        ImmutableMap<ItemCameraTransforms.TransformType, TransformationMatrix> transformMap = PerspectiveMapWrapper.getTransforms(new ModelTransformComposition(transformsFromModel, modelTransform));

        TransformationMatrix transform = modelTransform.getRotation();
        ItemMultiLayerBakedModel.Builder builder = ItemMultiLayerBakedModel.builder(owner, baseSprite, new ItemOverrideList(overrides,bakery, owner, this), transformMap);

        ItemMultiLayerBakedModel.Builder builder = ItemMultiLayerBakedModel.builder(owner, baseSprite, new ItemOverrideList(bakery, owner., owner, overrides.getOverrides()), transformMap);

    }

    @Override
    public Collection<RenderMaterial> getTextures(IModelConfiguration owner, Function<ResourceLocation, IUnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
        return null;
    }
}
**/