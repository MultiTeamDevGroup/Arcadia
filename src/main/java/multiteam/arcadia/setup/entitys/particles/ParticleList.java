package multiteam.arcadia.setup.entitys.particles;

import multiteam.arcadia.setup.Registration;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.fml.RegistryObject;

public class ParticleList {

    public static final RegistryObject<BasicParticleType> CLOUD_POOF_PARTICLE = Registration.PARTICLES.register("cloud_poof_particle", () -> new BasicParticleType(true));

    public static void register() {}
}
