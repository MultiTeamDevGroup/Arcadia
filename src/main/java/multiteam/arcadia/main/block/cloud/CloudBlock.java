package multiteam.arcadia.main.block.cloud;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CloudBlock extends Block {
    public final boolean puffsEasily;

    private final VoxelShape collisionShape = Block.box(0.1D, 0.1D, 0.1D, 15.9D, 15.9D, 15.9D);

    public CloudBlock(Properties properties, boolean puffsEasily) {
        super(properties);
        this.puffsEasily = puffsEasily;
    }

    @Override
    public Object getRenderPropertiesInternal() {
        return super.getRenderPropertiesInternal();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collisionContext) {
        return collisionShape;
    }

    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entitySteppingOnTop) {
        if(puffsEasily){
            this.puff(level, pos);
        }
    }

    public void puff(Level level, BlockPos currentPos) {
        //TODO puff

        //Client side:
        //spawn particles
        //play sound

        //Server side:
        //remove block
    }
}