package multiteam.arcadia.data;

import multiteam.arcadia.Arcadia;
import multiteam.arcadia.data.gen.ArcadiaBlockStateProvider;
import multiteam.arcadia.data.gen.ArcadiaItemModelProvider;
import multiteam.arcadia.data.gen.ArcadiaLootTableProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Arcadia.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    private DataGenerators() {
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        gen.addProvider(true, new ArcadiaBlockStateProvider(gen, existingFileHelper));
        gen.addProvider(true, new ArcadiaItemModelProvider(gen, existingFileHelper));
        gen.addProvider(true, new ArcadiaLootTableProvider(gen));
    }
}
