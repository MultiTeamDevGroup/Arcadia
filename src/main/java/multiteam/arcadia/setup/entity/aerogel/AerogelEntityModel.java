package multiteam.arcadia.setup.entity.aerogel;

import multiteam.arcadia.ArcadiaMod;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class AerogelEntityModel extends AnimatedGeoModel<AerogelEntity> implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    @Override
    public ResourceLocation getModelLocation(AerogelEntity aerogelEntity) {
        return new ResourceLocation(ArcadiaMod.MOD_ID, "geo/entity/aero_gel.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AerogelEntity aerogelEntity) {
        return new ResourceLocation(ArcadiaMod.MOD_ID, "textures/entity/aero_gel.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AerogelEntity aerogelEntity) {
        return new ResourceLocation(ArcadiaMod.MOD_ID, "animations/entity/aero_gel.animation.json");
    }

    @Override
    public void registerControllers(AnimationData animationData) {

    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

}
