package multiteam.arcadia.setup.items;

import multiteam.arcadia.setup.blocks.ModBlocks;
import multiteam.arcadia.setup.entity.ModEntitys;
import multiteam.arcadia.setup.entity.zap.ZapEntity;
import multiteam.arcadia.setup.music.ModMusics;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.network.IPacket;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.world.NoteBlockEvent;

public class BottledZap extends Item {

    public BottledZap(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity playerEntity = context.getPlayer();
        ItemStack itemStack = context.getItemInHand();
        World worldIn = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        Hand handOfPlayer = context.getHand();
        BlockRayTraceResult blockraytraceresult = new BlockRayTraceResult(context.getClickLocation(), context.getClickedFace(), context.getClickedPos(), context.isInside());
        BlockItemUseContext blockItemUseContext = new BlockItemUseContext(playerEntity, handOfPlayer, itemStack, blockraytraceresult);
        boolean shouldConsume = false;

        if(!playerEntity.level.isClientSide){
            if(playerEntity.isCrouching()){
                if(ModBlocks.ZAP_LANTERN.get().getStateForPlacement(blockItemUseContext) != null){
                    shouldConsume = true;
                    worldIn.playSound((PlayerEntity)null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.GLASS_PLACE, SoundCategory.BLOCKS, 0.5F, 1F );
                    worldIn.setBlockAndUpdate(blockPos.offset(blockItemUseContext.getClickedFace().getNormal()), ModBlocks.ZAP_LANTERN.get().getStateForPlacement(blockItemUseContext));
                }else{
                    if(worldIn.getBlockState(blockPos.offset(blockItemUseContext.getClickedFace().getNormal())) == Blocks.AIR.defaultBlockState()){
                        shouldConsume = true;
                        worldIn.playSound((PlayerEntity)null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.GLASS_PLACE, SoundCategory.BLOCKS, 0.5F, 1F );
                        worldIn.setBlockAndUpdate(blockPos.offset(blockItemUseContext.getClickedFace().getNormal()), ModBlocks.ZAP_LANTERN.get().defaultBlockState());
                    }
                }
            }else{
                shouldConsume = true;
                worldIn.playSound((PlayerEntity)null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), ModMusics.ZAP_BOTTLE_RELEASE.get(), SoundCategory.NEUTRAL, 0.5F, 1F );
                spawnZap(worldIn, blockPos.offset(blockItemUseContext.getClickedFace().getNormal()));
            }
        }

        if(!playerEntity.isCreative() && shouldConsume){
            itemStack.shrink(1);
            if(itemStack.isEmpty()){
                playerEntity.inventory.removeItem(itemStack);
            }
        }

        return ActionResultType.PASS;
    }

    public void spawnZap(World worldIn, BlockPos pos){
        Entity zapEnty = new ZapEntity(ModEntitys.ZAP.get(), worldIn);
        worldIn.addFreshEntity(zapEnty);
        zapEnty.setPos(pos.getX(), pos.getY(), pos.getZ());
        if(worldIn.isClientSide){
            ZapEntity.summonZapParticles(worldIn, pos);
        }
    }

}
