package multiteam.arcadia.main.particles;

import multiteam.arcadia.main.Registration;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.RegistryObject;

public class ModParticleRegistry {

    public static final RegistryObject<SimpleParticleType> CLOUD_PARTICLE = Registration.PARTICLE_TYPES.register("cloud_particle", () -> new SimpleParticleType(true));

    public static void register(){}

    public static void particleRegistry(){
        Minecraft.getInstance().particleEngine.register(ModParticleRegistry.CLOUD_PARTICLE.get(), CloudParticle.Provider::new);

    }
}
