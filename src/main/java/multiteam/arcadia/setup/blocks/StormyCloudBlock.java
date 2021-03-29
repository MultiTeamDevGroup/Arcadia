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
import net.minecraft.world.World;

public class StormyCloudBlock extends CloudBlock{

    public StormyCloudBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void poofOutOfExistence(World worldIn, BlockPos pos, Boolean causedbyfall, Entity entity){
        worldIn.playSound((PlayerEntity)null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.WOOL_BREAK, SoundCategory.BLOCKS, 0.5F, 0.4F );
        LightningBoltEntity lightning = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, worldIn);
        lightning.absMoveTo(pos.getX(), pos.getY(), pos.getZ(), 0, 0.0F);
        worldIn.addFreshEntity(lightning);
        for (float x = 0; x < 1;){
            for (float y = 0; y < 1;){
                for (float z = 0; z < 1;){
                    worldIn.addParticle(ModParticles.STORMY_CLOUD_POOF.get(), pos.getX()+x, pos.getY()+y, pos.getZ()+z,0.2D, 2.0D, 0.2D);
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
