package multiteam.arcadia;

import multiteam.arcadia.setup.entity.GeoExampleEntity.GeoExampleEntity;
import multiteam.arcadia.setup.entity.aerogel.AerogelEntity;
import multiteam.multicore_lib.setup.utilities.*;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import software.bernie.geckolib3.GeckoLib;

import multiteam.arcadia.setup.Registration;
import multiteam.arcadia.setup.blocks.ModBlocks;
import multiteam.arcadia.setup.entity.ModEntitys;
import multiteam.arcadia.setup.entity.zap.ZapEntity;
import multiteam.arcadia.setup.items.AngelWings;
import multiteam.arcadia.setup.items.ModItems;
import multiteam.arcadia.setup.world.biomes.ModBiomeProvider;
import multiteam.arcadia.setup.world.dimension.CloudRealmChunkGenerator;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

@Mod(ArcadiaMod.MOD_ID)
public class ArcadiaMod {

    public static final String MOD_ID = "arcadia";
    public static final Logger LOGGER = LogManager.getLogger();

    public static final ItemGroupTool ARCADIA_MAIN_TAB = new ItemGroupTool("arcadia_main_tab", () -> new ItemStack(ModItems.ANGEL_WINGS.get()));

    public ArcadiaMod() {

        GeckoLib.initialize();
        Registration.register();

        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::setup);
        modBus.addListener(this::enqueueIMC);
        modBus.addListener(this::processIMC);
        modBus.addListener(this::doClientStuff);
        modBus.addListener(AngelWings::onClientSetup);
        modBus.addListener(ModEntitys::clientSetup);
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(MOD_ID, "chunkgen"), CloudRealmChunkGenerator.CODEC);
        Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(MOD_ID, "biomes"), ModBiomeProvider.CODEC);

    }

    private void setup(final FMLCommonSetupEvent event) {

        GlobalEntityTypeAttributes.put(ModEntitys.ZAP.get(), ZapEntity.createAttributes().build());
        GlobalEntityTypeAttributes.put(ModEntitys.GEO_EXAMPLE_ENTITY.get(), GeoExampleEntity.createAttributes().build());
        //GlobalEntityTypeAttributes.put(ModEntitys.AEROGEL.get(), AerogelEntity.createAttributes().build());

        //GlobalEntityTypeAttributes.put(ModEntitys.GEO_EXAMPLE_ENTITY.get(), MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 1.0D).build());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

        RenderTypeLookup.setRenderLayer(ModBlocks.CLOUD_BLOCK.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.JELLY_CLOUD_BLOCK.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.STORMY_CLOUD_BLOCK.get(), RenderType.translucent());

        RenderTypeLookup.setRenderLayer(ModBlocks.ZAP_LANTERN.get(), RenderType.cutoutMipped());

    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,() -> SlotTypePreset.BACK.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,() -> SlotTypePreset.CURIO.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,() -> SlotTypePreset.RING.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,() -> SlotTypePreset.BELT.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,() -> SlotTypePreset.BODY.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,() -> SlotTypePreset.BRACELET.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,() -> SlotTypePreset.CHARM.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,() -> SlotTypePreset.HANDS.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,() -> SlotTypePreset.HEAD.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,() -> SlotTypePreset.NECKLACE.getMessageBuilder().build());

    }

    private void processIMC(final InterModProcessEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {

        }
    }
}
