package multiteam.arcadia.setup.world.dimension;

import multiteam.arcadia.ArcadiaMod;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

public class ModDimensions {

    public static final RegistryKey<DimensionType> CLOUD_REALM_TYPE = RegistryKey.create(net.minecraft.util.registry.Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation(ArcadiaMod.MOD_ID, "cloud_realm"));
    public static final RegistryKey<World> CLOUD_REALM = RegistryKey.create(net.minecraft.util.registry.Registry.DIMENSION_REGISTRY, new ResourceLocation(ArcadiaMod.MOD_ID, "cloud_realm"));

}
