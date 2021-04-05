package multiteam.arcadia.setup.items;

import multiteam.arcadia.setup.blocks.ModBlocks;
import multiteam.arcadia.setup.entity.ModEntitys;
import multiteam.arcadia.setup.entity.zap.ZapEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BottledZap extends Item {

    public BottledZap(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerEntity, Hand handOfPlayer) {
        ItemStack itemStack = playerEntity.getItemInHand(handOfPlayer);
        Minecraft instance = Minecraft.getInstance();

        if(!playerEntity.level.isClientSide){
            if(instance.hitResult.getType() == RayTraceResult.Type.BLOCK){
                BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult)instance.hitResult;
                BlockPos blockpos = blockraytraceresult.getBlockPos();
                BlockItemUseContext blockItemUseContext = new BlockItemUseContext(playerEntity, handOfPlayer, itemStack, blockraytraceresult);
                if(playerEntity.isCrouching()){
                    if(ModBlocks.ZAP_LANTERN.get().getStateForPlacement(blockItemUseContext) != null){
                        worldIn.setBlockAndUpdate(blockpos.offset(blockItemUseContext.getClickedFace().getNormal()), ModBlocks.ZAP_LANTERN.get().getStateForPlacement(blockItemUseContext));
                    }else{
                        worldIn.setBlockAndUpdate(blockpos.offset(blockItemUseContext.getClickedFace().getNormal()), ModBlocks.ZAP_LANTERN.get().defaultBlockState());
                    }
                }else{
                    spawnZap(worldIn, blockpos.offset(blockItemUseContext.getClickedFace().getNormal()));
                }
            }else if(instance.hitResult.getType() == RayTraceResult.Type.ENTITY){
                //System.out.println("found entity");
            }else if(instance.hitResult.getType() == RayTraceResult.Type.MISS){
                spawnZap(worldIn, playerEntity.blockPosition());
            }
        }

        if(!playerEntity.isCreative()){
            itemStack.shrink(1);
            if(itemStack.isEmpty()){
                playerEntity.inventory.removeItem(itemStack);
            }
        }

        return ActionResult.consume(itemStack);
    }

    public void spawnZap(World worldIn, BlockPos pos){
        Entity zapEnty = new ZapEntity(ModEntitys.ZAP.get(), worldIn);
        worldIn.addFreshEntity(zapEnty);
        zapEnty.setPos(pos.getX(), pos.getY(), pos.getZ());
    }

}
