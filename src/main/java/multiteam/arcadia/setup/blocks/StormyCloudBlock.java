package multiteam.arcadia.setup.blocks;

import multiteam.arcadia.setup.particles.ModParticles;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StormyCloudBlock extends CloudBlock{

    public StormyCloudBlock(Properties properties) {
        super(properties);
    }

    @Override
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
                    //worldIn.addParticle(ModParticles.STORMY_CLOUD_POOF.get(), posx+x, posy+y, posz+z, spedx, spedy, spedz);
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
