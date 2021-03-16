package multiteam.arcadia.setup.blocks;

import multiteam.arcadia.setup.entitys.particles.ParticleList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class StormyCloudBlock extends Block {

    public StormyCloudBlock(Properties properties) {
        super(properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        LightningBoltEntity lightning = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, worldIn);
        lightning.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0.0F);
        worldIn.addEntity(lightning);
        double posx = (double)pos.getX()+0.0D;
        double posy = (double)pos.getY()+0.0D;
        double posz = (double)pos.getZ()+0.0D;
        double spedx = 0.2D;
        double spedy = 2.0D;
        double spedz = 0.2D;
        for (float x = 0; x < 1;){
            for (float y = 0; y < 1;){
                for (float z = 0; z < 1;){
                    worldIn.addParticle(ParticleList.CLOUD_POOF_PARTICLE.get(), posx+x, posy+y, posz+z, spedx, spedy, spedz);
                    z+=0.2f;
                }
                y+=0.2f;
            }
            x+=0.2f;
        }

    }

}