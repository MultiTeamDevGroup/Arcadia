package multiteam.arcadia.setup.world.dimension;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.blocks.ModBlocks;
import multiteam.arcadia.setup.world.biomes.ModBiomeProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.Blockreader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;

import java.util.Random;

public class CloudRealmChunkGenerator extends ChunkGenerator {

    public SimplexOctaveGenerator noiser;

    public final float multiplier = 10f;
    public final float power = 5;
    public final float cutOffSteps = .4f;
    public final int cutOffMid = 140; // heaviest concentration of "clouds"

    private static final Codec<Settings> SETTINGS_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.FLOAT.fieldOf("cutoffSteps").forGetter(Settings::getCutoffSteps),
                    Codec.INT.fieldOf("cutoffMid").forGetter(Settings::getCutoffMid)
            ).apply(instance, Settings::new));

    public static final Codec<CloudRealmChunkGenerator> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter(CloudRealmChunkGenerator::getBiomeRegistry),
                    SETTINGS_CODEC.fieldOf("settings").forGetter(CloudRealmChunkGenerator::getTutorialSettings)
            ).apply(instance, CloudRealmChunkGenerator::new));

    private final Settings settings;

    public CloudRealmChunkGenerator(Registry<Biome> registry, Settings settings) {
        super(new ModBiomeProvider(registry), new DimensionStructuresSettings(false));
        this.settings = settings;
        noiser = new SimplexOctaveGenerator(new Random(1337), 3);
        noiser.setScale(.025);
        ArcadiaMod.LOGGER.info("Chunk generator settings: " + settings.getCutoffSteps() + ", " + settings.getCutoffMid());
    }

    public Settings getTutorialSettings() {
        return settings;
    }

    public Registry<Biome> getBiomeRegistry() {
        return ((ModBiomeProvider)biomeSource).getBiomeRegistry();
    }

    public float getNoise(int x, int y, int z) {
        float simplexNoise = (float) (Math.pow((noiser.noise(x, y, z, 1, 1, true) + 1) / 2, power) * multiplier);
        float cutOff = Math.abs(y - settings.getCutoffMid()) * settings.getCutoffSteps();

        return simplexNoise - cutOff;
    }

    @Override
    public void buildSurfaceAndBedrock(WorldGenRegion region, IChunk chunk) {
        ChunkPos chunkpos = chunk.getPos();
        BlockPos.Mutable pos = new BlockPos.Mutable();

        for (int x = 0; x < 16; x++){
            for (int z = 0; z < 16; z++){
                for (int y = 0; y < 256; y++) {
                    float noise = getNoise(chunkpos.x * 16 + x, y, chunkpos.z * 16 + z);
                    chunk.setBlockState(pos.set(x, y, z), noise > 0.5 ? ModBlocks.CLOUD_BLOCK.get().defaultBlockState() : Blocks.AIR.defaultBlockState(), false);
                }
            }
        }

    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        SimplexOctaveGenerator noise = new SimplexOctaveGenerator(new Random(seed), 3);
        noise.setScale(.025);
        CloudRealmChunkGenerator gen = new CloudRealmChunkGenerator(getBiomeRegistry(), settings);
        gen.noiser = noise;
        return gen;
    }

    @Override
    public void fillFromNoise(IWorld world, StructureManager structureManager, IChunk chunk) {

    }

    @Override
    public int getBaseHeight(int x, int z, Heightmap.Type heightmapType) {
        return 0;
    }

    @Override
    public IBlockReader getBaseColumn(int p_230348_1_, int p_230348_2_) {
        return new Blockreader(new BlockState[0]);
    }



    private static class Settings {
        private final float cutoffSteps;
        public final int cutoffMid;


        public Settings(float cutoffSteps, int cutoffMid) {
            this.cutoffSteps = cutoffSteps;
            this.cutoffMid = cutoffMid;
        }

        public int getCutoffMid() {
            return cutoffMid;
        }

        public float getCutoffSteps() {
            return cutoffSteps;
        }
    }
}