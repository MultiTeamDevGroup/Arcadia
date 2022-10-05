package multiteam.arcadia.main.block.cloud;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.RegistryObject;

public class PurpleCloudBlock extends CloudBlock{

    private final VoxelShape collisionShape = Block.box(0.4D, 0.4D, 0.4D, 12.0D, 12.0D, 12.0D);

    public PurpleCloudBlock(Properties properties, boolean puffsEasily, RegistryObject<SimpleParticleType> cloudParticle) {
        super(properties, puffsEasily, cloudParticle);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collisionContext) {
        return collisionShape;
    }

    public void entityInside(BlockState p_58180_, Level p_58181_, BlockPos p_58182_, Entity p_58183_) {
        p_58183_.makeStuckInBlock(p_58180_, new Vec3(0.25D, (double)0.05F, 0.25D));
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entityFallenOn, float fallDistance) {
        super.fallOn(level, state, pos, entityFallenOn, fallDistance);
        if(fallDistance >= 1.2f){
            this.poof(level, pos, entityFallenOn);
        }
    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter blockGetter, Entity entity) {
        super.updateEntityAfterFallOn(blockGetter, entity);
    }
}
