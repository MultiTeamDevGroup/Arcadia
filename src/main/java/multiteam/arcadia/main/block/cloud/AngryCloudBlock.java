package multiteam.arcadia.main.block.cloud;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;

public class AngryCloudBlock extends CloudBlock{

    public AngryCloudBlock(Properties properties, boolean poofsEasily, RegistryObject<SimpleParticleType> cloudParticle) {
        super(properties, poofsEasily, cloudParticle);
    }

    @Override
    public void specialPuff(Level level, BlockPos currentPos, Entity entitySteppingOnTop) {
        if (!level.isClientSide) {
            float f = 4.0F;
            level.explode(entitySteppingOnTop, currentPos.getX(), currentPos.getY(), currentPos.getZ(), 4.0F, Explosion.BlockInteraction.BREAK);
        }
    }
}
