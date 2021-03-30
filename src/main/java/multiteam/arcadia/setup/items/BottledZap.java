package multiteam.arcadia.setup.items;

import multiteam.arcadia.setup.entity.ModEntitys;
import multiteam.arcadia.setup.entity.zap.ZapEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BottledZap extends Item {

    public BottledZap(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerEntity, Hand handOfPlayer) {
        ItemStack itemStack = playerEntity.getItemInHand(handOfPlayer);

        Entity zapEnty = new ZapEntity(ModEntitys.ZAP.get(), playerEntity.level);
        playerEntity.level.addFreshEntity(zapEnty);
        zapEnty.setPos(playerEntity.getX(), playerEntity.getY(), playerEntity.getZ());

        itemStack.shrink(1);
        if(itemStack.isEmpty()){
            playerEntity.inventory.removeItem(itemStack);
        }

        return ActionResult.consume(itemStack);
    }

}
