package multiteam.arcadia.main;

import multiteam.arcadia.Arcadia;
import multiteam.arcadia.main.block.ModBlockRegistry;
import multiteam.arcadia.main.item.ModItemRegistry;
import multiteam.multicore_lib.MultiCoreLib;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class Registration {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Arcadia.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Arcadia.MODID);
    //public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, MultiCoreLib.MOD_ID);
    //public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MultiCoreLib.MOD_ID);

    public static void register(){
        IEventBus modeEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modeEventBus);
        ITEMS.register(modeEventBus);
        //POTIONS.register(modeEventBus);
        //ENTITY_TYPES.register(modeEventBus);

        ModBlockRegistry.register();
        ModItemRegistry.register();
        //ModPotions.register();
        //ModEntities.register();
    }
}
