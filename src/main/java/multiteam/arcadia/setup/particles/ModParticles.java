package multiteam.arcadia.setup.particles;

import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.Registration;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ArcadiaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticles {

    public static final RegistryObject<BasicParticleType> CLOUD_POOF = Registration.PARTICLES.register("cloud_poof_particle", () -> new BasicParticleType(false));
    //public static final RegistryObject<BasicParticleType> STORMY_CLOUD_POOF = Registration.PARTICLES.register("cloud_poof_particle", () -> new BasicParticleType(false));

    public static void register() {
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerParticles(ParticleFactoryRegisterEvent event){

        Minecraft.getInstance().particleEngine.register(CLOUD_POOF.get(), CloudPoofParticle.Factory::new);
        //Minecraft.getInstance().particleEngine.register(STORMY_CLOUD_POOF.get(), CloudPoofParticle.Factory::new);

    }

}
