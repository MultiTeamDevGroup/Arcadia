package multiteam.arcadia.main;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = multiteam.arcadia.Arcadia.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    @SubscribeEvent
    public static void onBonemealUsed(PlayerInteractEvent event){
        Level level = event.getLevel();
        Block block = level.getBlockState(event.getPos()).getBlock();
        ItemStack handStack = event.getItemStack();


    }
}
