package multiteam.arcadia.main.block.cloud;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.RegistryObject;

public class JellyCloudBlock extends CloudBlock{

    public JellyCloudBlock(Properties properties, boolean puffsEasily, RegistryObject<SimpleParticleType> cloudParticle) {
        super(properties, puffsEasily, cloudParticle);
    }

    @Override
    public void specialPuff(Level level, BlockPos currentPos, Entity entitySteppingOnTop) {
        this.bounceUp(entitySteppingOnTop);
    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter blockGetter, Entity entity) {
        this.bounceUp(entity);
    }

    private void bounceUp(Entity entity) {
        Vec3 vec3 = entity.getDeltaMovement();
        entity.setDeltaMovement(vec3.x, 1.0D, vec3.z);
    }
}
