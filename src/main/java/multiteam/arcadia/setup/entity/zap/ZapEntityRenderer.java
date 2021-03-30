package multiteam.arcadia.setup.entity.zap;

import multiteam.arcadia.ArcadiaMod;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class ZapEntityRenderer extends MobRenderer<ZapEntity, ZapEntityModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(ArcadiaMod.MOD_ID, "textures/entity/zap.png");


    public ZapEntityRenderer(EntityRendererManager entityRendererManager) {
        super(entityRendererManager, new ZapEntityModel(), 0.5f);
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(ZapEntity zapEntity) {
        return TEXTURE;
    }
}
