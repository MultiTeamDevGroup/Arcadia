package multiteam.arcadia.main.block.cloud;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

public class GoldCloudBlock extends CloudBlock{

    public GoldCloudBlock(Properties properties, boolean puffsEasily, RegistryObject<SimpleParticleType> cloudParticle) {
        super(properties, puffsEasily, cloudParticle);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entitySteppingOnTop) {
        super.stepOn(level, pos, state, entitySteppingOnTop);
        if(entitySteppingOnTop instanceof Player player && !level.isClientSide){
            if(player.isCrouching()){
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 50, 2, false, false, false, (MobEffectInstance)null, MobEffects.MOVEMENT_SPEED.createFactorData()));
            }
        }
    }
}
