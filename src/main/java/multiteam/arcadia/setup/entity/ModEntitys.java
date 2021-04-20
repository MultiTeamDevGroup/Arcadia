package multiteam.arcadia.setup.entity;

import multiteam.arcadia.setup.Registration;
import multiteam.arcadia.setup.entity.GeoExampleEntity.ExampleGeoRenderer;
import multiteam.arcadia.setup.entity.GeoExampleEntity.GeoExampleEntity;
import multiteam.arcadia.setup.entity.zap.ZapEntity;
import multiteam.arcadia.setup.entity.zap.ZapEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ModEntitys {

    public static final RegistryObject<EntityType<ZapEntity>> ZAP = Registration.ENTITY_TYPES.register("zap", () -> EntityType.Builder.of(ZapEntity::new, EntityClassification.AMBIENT).sized(0.7f,0.7f).fireImmune().setShouldReceiveVelocityUpdates(false).build("zap"));
    //public static final RegistryObject<EntityType<AerogelEntity>> AEROGEL = Registration.ENTITY_TYPES.register("aerogel", () -> EntityType.Builder.of(AerogelEntity::new, EntityClassification.CREATURE).sized(1.0f, 1.0f).fireImmune().setShouldReceiveVelocityUpdates(false).build("aerogel"));
    //public static final RegistryObject<EntityType<AerogelEntity>> AEROGEL = buildEntity(AerogelEntity::new, AerogelEntity.class, 1.0F, 1.0F);
    public static final RegistryObject<EntityType<GeoExampleEntity>> GEO_EXAMPLE_ENTITY = buildEntity(GeoExampleEntity::new, GeoExampleEntity.class, .7F, 1.3F);


    public static void register() {

    }

    public static void clientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntitys.ZAP.get(), ZapEntityRenderer::new);
        //RenderingRegistry.registerEntityRenderingHandler(ModEntitys.AEROGEL.get(), AerogelEntityRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntitys.GEO_EXAMPLE_ENTITY.get(), ExampleGeoRenderer::new);
    }

    public static <T extends Entity> RegistryObject<EntityType<T>> buildEntity(EntityType.IFactory<T> entity, Class<T> entityClass, float width, float height) {
        String name = entityClass.getSimpleName().toLowerCase();
        return Registration.ENTITY_TYPES.register(name, () -> EntityType.Builder.of(entity, EntityClassification.CREATURE).sized(width, height).build(name));
    }



}
