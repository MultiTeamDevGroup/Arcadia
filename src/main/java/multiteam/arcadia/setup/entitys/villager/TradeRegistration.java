package multiteam.arcadia.setup.entitys.villager;

import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.ModBlocks;
import multiteam.arcadia.setup.ModItems;
import multiteam.arcadia.setup.VillagerProfessions;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = ArcadiaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TradeRegistration {

    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event){
        //if (event.getType() == VillagerProfessions.GARDEN_SOLDIER_COMMANDER.get()) {

            //EXAMPLE TRADE REGISTRATION
            //event.getTrades().get(1).add((new RandomTradeBuilder(16, 10, 0.05F).setEmeraldPrice(1).setForSale(ModItems.SKIN_CARD0.get(), 1, 1).build()));

        //}

    }
}
