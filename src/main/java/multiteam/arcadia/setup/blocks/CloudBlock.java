package multiteam.arcadia.setup.blocks;

import multiteam.arcadia.setup.particles.ModParticles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CloudBlock extends Block {

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);

    public CloudBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState, IBlockReader iBlockReader, BlockPos blockPos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState blockState, IBlockReader iBlockReader, BlockPos blockPos) {
        return VoxelShapes.block();
    }

    @Override
    public VoxelShape getShape(BlockState  blockState, IBlockReader iBlockReader, BlockPos blockPos, ISelectionContext context) {
        return VoxelShapes.block();
    }

    @Override
    public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity playerEntity) {
        poofOutOfExistence(worldIn, pos, false, playerEntity);
    }

    @Override
    public void fallOn(World worldIn, BlockPos pos, Entity entity, float fallDistance) {
        if(entity.getType() == EntityType.SLIME){
            if(!worldIn.isClientSide){
                worldIn.setBlockAndUpdate(pos, ModBlocks.JELLY_CLOUD_BLOCK.get().defaultBlockState());
            }
        }else{
            poofOutOfExistence(worldIn, pos, true, entity);
        }
    }

    public void poofOutOfExistence(World worldIn, BlockPos pos, Boolean causedbyfall, Entity entity){
        worldIn.playSound((PlayerEntity)null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.WOOL_BREAK, SoundCategory.BLOCKS, 0.5F, 0.4F );
        for (float x = 0; x < 1;){
            for (float y = 0; y < 1;){
                for (float z = 0; z < 1;){
                    worldIn.addParticle(ModParticles.CLOUD_POOF.get(), (double)pos.getX()+x, (double)pos.getY()+y, (double)pos.getZ()+z,0.2D, 2.0D, 0.2D);
                    z+=0.2f;
                }
                y+=0.2f;
            }
            x+=0.2f;
        }

        if(causedbyfall && !worldIn.isClientSide){
            worldIn.destroyBlock(pos, false, null, 0);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public boolean skipRendering(BlockState state, BlockState state2, Direction direction) {
        return state2.is(this) ? true : super.skipRendering(state, state2, direction);
    }

}
