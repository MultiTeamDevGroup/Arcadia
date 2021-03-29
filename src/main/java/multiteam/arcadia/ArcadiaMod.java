package multiteam.arcadia;

import multiteam.arcadia.setup.ModItemGroup;
import multiteam.arcadia.setup.Registration;
import multiteam.arcadia.setup.blocks.ModBlocks;
import multiteam.arcadia.setup.items.AngelWings;
import multiteam.arcadia.setup.items.ModItems;
import multiteam.arcadia.setup.particles.ModParticles;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemStack;
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
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

import java.util.stream.Collectors;

@Mod(ArcadiaMod.MOD_ID)
public class ArcadiaMod
{
    public static final String MOD_ID = "arcadia";
    private static final Logger LOGGER = LogManager.getLogger();

    public static final ModItemGroup ARCADIA_MAIN_TAB = new ModItemGroup("arcadia_main_tab", () -> new ItemStack(ModItems.ANGEL_WINGS.get()));

    public ArcadiaMod() {

        Registration.register();

        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::setup);
        modBus.addListener(this::enqueueIMC);
        modBus.addListener(this::processIMC);
        modBus.addListener(this::doClientStuff);
        modBus.addListener(AngelWings::onClientSetup);
        //modBus.addListener(ModParticles::registerParticles);
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {

        RenderTypeLookup.setRenderLayer(ModBlocks.CLOUD_BLOCK.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.JELLY_CLOUD_BLOCK.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.STORMY_CLOUD_BLOCK.get(), RenderType.translucent());

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
