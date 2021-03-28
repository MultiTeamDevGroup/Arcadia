package multiteam.arcadia.setup;

import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.blocks.ModBlocks;
import multiteam.arcadia.setup.items.ModItems;
import multiteam.arcadia.setup.music.ModMusics;
import multiteam.arcadia.setup.particles.ModParticles;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ArcadiaMod.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ArcadiaMod.MOD_ID);
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ArcadiaMod.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ArcadiaMod.MOD_ID);

    public static void register() {
        IEventBus modeEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register(modeEventBus);
        ITEMS.register(modeEventBus);
        PARTICLES.register(modeEventBus);
        SOUND_EVENTS.register(modeEventBus);

        //clalling empty classes, to classload the classes, hence loading the stuff in them to registry.
        ModItems.register();
        ModBlocks.register();
        ModParticles.register();
        ModMusics.register();

    }


}
