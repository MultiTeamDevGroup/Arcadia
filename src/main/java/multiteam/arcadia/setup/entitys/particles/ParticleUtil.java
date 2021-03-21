package multiteam.arcadia.setup.entitys.particles;

import multiteam.arcadia.ArcadiaMod;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

@Mod.EventBusSubscriber(modid = ArcadiaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleUtil {

    @SubscribeEvent
    public static void registerParticles(ParticleFactoryRegisterEvent event){

        if (checkForNonNullWithReflection(ParticleList.CLOUD_POOF_PARTICLE)) {
            Minecraft.getInstance().particles.registerFactory(ParticleList.CLOUD_POOF_PARTICLE.get(), CloudPoofParticle.Factory::new);
        }
        if (checkForNonNullWithReflection(ParticleList.STORMY_CLOUD_POOF_PARTICLE)) {
            Minecraft.getInstance().particles.registerFactory(ParticleList.STORMY_CLOUD_POOF_PARTICLE.get(), StormyCloudPoofParticle.Factory::new);
        }
    }

    private static boolean checkForNonNullWithReflection(RegistryObject<BasicParticleType> registryObject) {
        return ObfuscationReflectionHelper.getPrivateValue(RegistryObject.class, registryObject, "value") != null;
    }


}
