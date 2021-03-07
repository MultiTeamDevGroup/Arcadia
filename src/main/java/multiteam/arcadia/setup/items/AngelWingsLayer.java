package multiteam.arcadia.setup.items;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.model.ElytraModel;
import net.minecraft.util.ResourceLocation;

public class AngelWingsLayer extends ElytraLayer {
    private static final ResourceLocation TEXTURE_ELYTRA = new ResourceLocation("textures/entity/angel_wings.png");
    private final ElytraModel modelElytra = new ElytraModel<>();

    public AngelWingsLayer(IEntityRenderer rendererIn) {
        super(rendererIn);
    }

}
