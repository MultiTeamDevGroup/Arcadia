package multiteam.arcadia.main.particles;

import multiteam.arcadia.main.Registration;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.RegistryObject;

public class ModParticleRegistry {

    public static final RegistryObject<SimpleParticleType> CLOUD_PARTICLE = Registration.PARTICLE_TYPES.register("cloud_particle", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CLOUD_PARTICLE_STORMY = Registration.PARTICLE_TYPES.register("cloud_particle_stormy", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CLOUD_PARTICLE_JELLY = Registration.PARTICLE_TYPES.register("cloud_particle_jelly", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CLOUD_PARTICLE_PURPLE = Registration.PARTICLE_TYPES.register("cloud_particle_purple", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CLOUD_PARTICLE_SILVER = Registration.PARTICLE_TYPES.register("cloud_particle_silver", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CLOUD_PARTICLE_GOLD = Registration.PARTICLE_TYPES.register("cloud_particle_gold", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CLOUD_PARTICLE_ANGRY = Registration.PARTICLE_TYPES.register("cloud_particle_angry", () -> new SimpleParticleType(true));

    public static void register(){}

    public static void particleRegistry(){
        Minecraft.getInstance().particleEngine.register(ModParticleRegistry.CLOUD_PARTICLE.get(), CloudParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticleRegistry.CLOUD_PARTICLE_STORMY.get(), CloudParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticleRegistry.CLOUD_PARTICLE_JELLY.get(), CloudParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticleRegistry.CLOUD_PARTICLE_PURPLE.get(), CloudParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticleRegistry.CLOUD_PARTICLE_SILVER.get(), CloudParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticleRegistry.CLOUD_PARTICLE_GOLD.get(), CloudParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticleRegistry.CLOUD_PARTICLE_ANGRY.get(), CloudParticle.Provider::new);

    }
}
