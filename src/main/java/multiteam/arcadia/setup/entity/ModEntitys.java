package multiteam.arcadia.setup.entity;

import multiteam.arcadia.setup.Registration;
import multiteam.arcadia.setup.entity.zap.ZapEntity;
import multiteam.arcadia.setup.entity.zap.ZapEntityRenderer;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ModEntitys {

    public static final RegistryObject<EntityType<ZapEntity>> ZAP = Registration.ENTITY_TYPES.register("zap", () -> EntityType.Builder.of(ZapEntity::new, EntityClassification.AMBIENT).sized(0.7f,0.7f).fireImmune().setShouldReceiveVelocityUpdates(false).build("zap"));

    public static void register() {

    }

    public static void clientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntitys.ZAP.get(), ZapEntityRenderer::new);
    }
}
