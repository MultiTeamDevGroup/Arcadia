package multiteam.arcadia.setup.blocks;

import multiteam.arcadia.setup.particles.ModParticles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

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
    public void playerWillDestroy(World worldIn, BlockPos pos, BlockState p_176208_3_, PlayerEntity p_176208_4_) {
        poofOutOfExistence(worldIn, pos, false);

    }

    @Override
    public void fallOn(World worldIn, BlockPos pos, Entity entity, float fallDistance) {
        poofOutOfExistence(worldIn, pos, true);
    }

    public void poofOutOfExistence(World worldIn, BlockPos pos, Boolean causedbyfall){
        worldIn.playSound((PlayerEntity)null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.WOOL_BREAK, SoundCategory.BLOCKS, 0.5F, 0.4F );
        double posx = (double)pos.getX()+0.0D;
        double posy = (double)pos.getY()+0.0D;
        double posz = (double)pos.getZ()+0.0D;
        double spedx = 0.2D;
        double spedy = 2.0D;
        double spedz = 0.2D;
        for (float x = 0; x < 1;){
            for (float y = 0; y < 1;){
                for (float z = 0; z < 1;){
                    worldIn.addParticle(ModParticles.CLOUD_POOF.get(), posx+x, posy+y, posz+z, spedx, spedy, spedz);
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

}
