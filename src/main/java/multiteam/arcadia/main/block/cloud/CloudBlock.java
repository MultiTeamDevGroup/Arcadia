package multiteam.arcadia.main.block.cloud;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class CloudBlock extends Block {
    public final boolean poofsEasily;
    public final RegistryObject<SimpleParticleType> cloudParticle;

    private final VoxelShape collisionShape = Block.box(0.1D, 0.1D, 0.1D, 15.9D, 15.9D, 15.9D);

    public CloudBlock(Properties properties, boolean poofsEasily, RegistryObject<SimpleParticleType> cloudParticle) {
        super(properties);
        this.poofsEasily = poofsEasily;
        this.cloudParticle = cloudParticle;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collisionContext) {
        return collisionShape;
    }

    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entitySteppingOnTop) {
        if(poofsEasily){
            if(entitySteppingOnTop instanceof Player player){
                if(!player.isCrouching()){
                    this.poof(level, pos, entitySteppingOnTop);
                }
            }else{
                this.poof(level, pos, entitySteppingOnTop);
            }

        }
    }

    @Override
    public void fallOn(Level p_152426_, BlockState p_152427_, BlockPos p_152428_, Entity p_152429_, float p_152430_) {
        super.fallOn(p_152426_, p_152427_, p_152428_, p_152429_, p_152430_);
    }

    public void poof(Level level, BlockPos currentPos, Entity entitySteppingOnTop) {
        if(level.isClientSide){
            for (int ix = 0; ix < 5; ix++) {
                for (int iy = 0; iy < 5; iy++) {
                    for (int iz = 0; iz < 5; iz++) {
                        level.addParticle(this.cloudParticle.get(), currentPos.getX()+((ix*2)*0.1), currentPos.getY()+((iy*2)*0.1), currentPos.getZ()+((iz*2)*0.1), (level.random.nextIntBetweenInclusive(-10, 10))*0.1, (level.random.nextIntBetweenInclusive(-10, 10))*0.1, (level.random.nextIntBetweenInclusive(-10, 10))*0.1);
                    }
                }
            }
            level.playSound((Player)null, currentPos.getX(), currentPos.getY(), currentPos.getZ(), SoundEvents.WOOL_BREAK, SoundSource.BLOCKS, 0.5F, 0.4F);
        }else{
            specialPuff(level, currentPos, entitySteppingOnTop);
            level.destroyBlock(currentPos, true);
        }
    }

    public void specialPuff(Level level, BlockPos currentPos, Entity entitySteppingOnTop){

    }

    @OnlyIn(Dist.CLIENT)
    public boolean skipRendering(@NotNull BlockState state, BlockState state2, @NotNull Direction direction) {
        return state2.is(this) || super.skipRendering(state, state2, direction);
    }
}
