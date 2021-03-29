package multiteam.arcadia.setup;

import multiteam.arcadia.ArcadiaMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ArcadiaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    @SubscribeEvent
    public static void onLightningStrike(EntityJoinWorldEvent event){
        if(event.getEntity().getType() == EntityType.LIGHTNING_BOLT){
            System.out.println("A Lightning Struck and it was detected!!!");
        }
    }
}
