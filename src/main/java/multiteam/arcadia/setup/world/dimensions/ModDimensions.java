package multiteam.arcadia.setup.world.dimensions;

import multiteam.arcadia.ArcadiaMod;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

public class ModDimensions {
    public static final RegistryKey<DimensionType> CLOUD_REALM_TYPE = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, new ResourceLocation(ArcadiaMod.MOD_ID, "cloud_realm"));
    public static final RegistryKey<World> CLOUD_REALM = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(ArcadiaMod.MOD_ID, "cloud_realm"));
}
