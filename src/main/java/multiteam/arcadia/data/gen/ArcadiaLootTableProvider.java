package multiteam.arcadia.data.gen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import multiteam.arcadia.main.Registration;
import multiteam.arcadia.main.block.ModBlockRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ArcadiaLootTableProvider extends LootTableProvider {

    public ArcadiaLootTableProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return ImmutableList.of(
                Pair.of(ModBlockLootTables::new, LootContextParamSets.BLOCK)
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
        map.forEach((resourceLocation, lootTable) -> LootTables.validate(validationtracker, resourceLocation, lootTable));
    }

    public static class ModBlockLootTables extends BlockLoot {
        @Override
        protected void addTables() {
            dropOther(ModBlockRegistry.CLOUD_BLOCK.get(), Blocks.AIR);
            dropOther(ModBlockRegistry.CLOUD_BLOCK_STORMY.get(), Blocks.AIR);
            dropOther(ModBlockRegistry.CLOUD_BLOCK_JELLY.get(), Blocks.AIR);
            dropOther(ModBlockRegistry.CLOUD_BLOCK_PURPLE.get(), Blocks.AIR);
            dropOther(ModBlockRegistry.CLOUD_BLOCK_SILVER.get(), Blocks.AIR);
            dropOther(ModBlockRegistry.CLOUD_BLOCK_GOLD.get(), Blocks.AIR);
            dropOther(ModBlockRegistry.CLOUD_BLOCK_ANGRY.get(), Blocks.AIR);
            //dropSelf(ModBlocks.CARDBOARD_BOX.get());
            //dropSelf(ModBlocks.WITHER_CABBAGE.get());
            //dropSelf(ModBlocks.ICEY_CABBAGE.get());
            //dropSelf(ModBlocks.SUPER_CABBAGE.get());
            //dropOther(ModBlocks.CABBAGE_BUSH.get(), ModItems.CABBAGE.get());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return Registration.BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
        }
    }
}

