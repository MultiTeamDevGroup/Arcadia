package multiteam.arcadia.main.block.cloud;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;

public class StormyCloudBlock extends CloudBlock{

    public StormyCloudBlock(Properties properties, boolean puffsEasily) {
        super(properties, puffsEasily);
    }

    @Override
    public void specialPuff(Level level, BlockPos currentPos, Entity entitySteppingOnTop) {
        if(!level.isClientSide){
            LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
            lightning.absMoveTo(currentPos.getX() + 0.5, currentPos.getY(), currentPos.getZ() + 0.5, 0f, 0f); // Fix lightning position.
            level.addFreshEntity(lightning);
        }
    }
}
