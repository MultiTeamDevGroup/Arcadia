package multiteam.arcadia.setup.world.biomes;

import com.mojang.serialization.Codec;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ModBiomeProvider extends BiomeProvider {

    public static final Codec<ModBiomeProvider> CODEC = RegistryLookupCodec.create(Registry.BIOME_REGISTRY).xmap(ModBiomeProvider::new, ModBiomeProvider::getBiomeRegistry).codec();

    private final Biome biome;
    private final Registry<Biome> biomeRegistry;
    private static final List<RegistryKey<Biome>> SPAWN = Collections.singletonList(Biomes.PLAINS);

    public ModBiomeProvider(Registry<Biome> biomeRegistry) {
        super(getStartBiomes(biomeRegistry));
        this.biomeRegistry = biomeRegistry;
        biome = biomeRegistry.get(Biomes.PLAINS.location());
    }

    private static List<Biome> getStartBiomes(Registry<Biome> registry) {
        return SPAWN.stream().map(s -> registry.get(s.location())).collect(Collectors.toList());
    }

    public Registry<Biome> getBiomeRegistry() {
        return biomeRegistry;
    }

    @Override
    public boolean canGenerateStructure(Structure<?> structure) {
        return false;
    }

    @Override
    protected Codec<? extends BiomeProvider> codec() {
        return CODEC;
    }

    @Override
    public BiomeProvider withSeed(long seed) {
        return this;
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return biome;
    }
}