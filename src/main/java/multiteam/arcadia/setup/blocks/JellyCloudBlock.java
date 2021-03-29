package multiteam.arcadia.setup.blocks;

import multiteam.arcadia.setup.particles.ModParticles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class JellyCloudBlock extends CloudBlock{

    public JellyCloudBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void poofOutOfExistence(World worldIn, BlockPos pos, Boolean causedbyfall, Entity entity){
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
                    worldIn.addParticle(ModParticles.JELLY_CLOUD_POOF.get(), posx+x, posy+y, posz+z, spedx, spedy, spedz);
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

    @Override
    public void updateEntityAfterFallOn(IBlockReader p_176216_1_, Entity p_176216_2_) {
        if (p_176216_2_.isSuppressingBounce()) {
            super.updateEntityAfterFallOn(p_176216_1_, p_176216_2_);
        } else {
            this.bounceUp(p_176216_2_);
        }

    }

    private void bounceUp(Entity p_226946_1_) {
        Vector3d vector3d = p_226946_1_.getDeltaMovement();
        if (vector3d.y < 0.0D) {
            double d0 = p_226946_1_ instanceof LivingEntity ? 1.1D : 1.1D;
            p_226946_1_.setDeltaMovement(vector3d.x, -vector3d.y * d0, vector3d.z);
        }

    }

}
