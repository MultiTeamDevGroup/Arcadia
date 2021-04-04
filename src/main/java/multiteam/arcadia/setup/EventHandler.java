package multiteam.arcadia.setup;

import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.entity.ModEntitys;
import multiteam.arcadia.setup.entity.zap.ZapEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.ThreadLocalRandom;


@Mod.EventBusSubscriber(modid = ArcadiaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    @SubscribeEvent
    public static void onEntityAddedToWorld(EntityJoinWorldEvent event){
        if(event.getEntity().getType() == EntityType.LIGHTNING_BOLT && !event.getWorld().isClientSide){
            int randomChance = ThreadLocalRandom.current().nextInt(0, 100 + 1);
            if(randomChance > 96){
                Entity zapEnty = new ZapEntity(ModEntitys.ZAP.get(),event.getWorld());
                event.getWorld().addFreshEntity(zapEnty);
                zapEnty.setPos(event.getEntity().getX()+ThreadLocalRandom.current().nextInt(2, 5 + 1), event.getEntity().getY()+ThreadLocalRandom.current().nextInt(2, 5 + 1), event.getEntity().getZ()+ThreadLocalRandom.current().nextInt(2, 5 + 1));
            }
        }
    }

    //@SubscribeEvent
    //public static void onPlayerRightClick

}
