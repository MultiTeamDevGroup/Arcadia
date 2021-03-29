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
        for (float x = 0; x < 1;){
            for (float y = 0; y < 1;){
                for (float z = 0; z < 1;){
                    worldIn.addParticle(ModParticles.JELLY_CLOUD_POOF.get(), pos.getX()+x, pos.getY()+y, pos.getZ()+z,0.2D, 2.0D, 0.2D);
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
    public void updateEntityAfterFallOn(IBlockReader blockReader, Entity entity) {
        if (entity.isSuppressingBounce()) {
            super.updateEntityAfterFallOn(blockReader, entity);
        } else {
            this.bounceUp(entity);
        }

    }

    private void bounceUp(Entity entity) {
        Vector3d vector3d = entity.getDeltaMovement();
        if (vector3d.y < 0.0D) {
            entity.setDeltaMovement(vector3d.x, -vector3d.y * 1.6D, vector3d.z);
        }

    }

}
