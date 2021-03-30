package multiteam.arcadia.setup.entity.zap;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class ZapEntity extends AnimalEntity implements IFlyingAnimal {

    public ZapEntity(EntityType<? extends ZapEntity> type, World worldIn) {
        super(type, worldIn);
        this.moveControl = new FlyingMovementController(this, 20, true);
        this.lookControl = new ZapEntity.ZapLookController(this);
    }

    @Override
    public CreatureAttribute getMobType() {
        return CreatureAttribute.UNDEFINED;
    }

    @Override
    public boolean causeFallDamage(float p_225503_1_, float p_225503_2_) {
        return false;
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld serverWorld, AgeableEntity ageableEntity) {
        return null;
    }

    class ZapLookController extends LookController {
        ZapLookController(MobEntity mobEntity) {
            super(mobEntity);
        }

        public void tick() {
            super.tick();
        }

        protected boolean resetXRotOnTick() {
            return true;
        }
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(8, new ZapEntity.WanderGoal());
    }

    class WanderGoal extends Goal {
        WanderGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            return ZapEntity.this.navigation.isDone() && ZapEntity.this.random.nextInt(10) == 0;
        }

        public boolean canContinueToUse() {
            return ZapEntity.this.navigation.isInProgress();
        }

        public void start() {
            Vector3d vector3d = this.findPos();
            if (vector3d != null) {
                ZapEntity.this.navigation.moveTo(ZapEntity.this.navigation.createPath(new BlockPos(vector3d), 1), 1.0D);
            }

        }

        @Nullable
        private Vector3d findPos() {
            Vector3d vector3d = ZapEntity.this.getViewVector(0.0F);
            Vector3d vector3d2 = RandomPositionGenerator.getAboveLandPos(ZapEntity.this, 8, 7, vector3d, ((float)Math.PI / 2F), 2, 1);
            return vector3d2 != null ? vector3d2 : RandomPositionGenerator.getAirPos(ZapEntity.this, 8, 4, -2, vector3d, (double)((float)Math.PI / 2F));
        }
    }

    @Override
    public void tick() {
        super.tick();
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.FLYING_SPEED, (double)2F).add(Attributes.MOVEMENT_SPEED, (double)1.5F);
    }

    protected PathNavigator createNavigation(World worldIn) {
        FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, worldIn) {
            public boolean isStableDestination(BlockPos blockPos) {
                return !this.level.getBlockState(blockPos.below()).isAir();
            }

            public void tick() {
                super.tick();
            }
        };
        flyingpathnavigator.setCanOpenDoors(false);
        flyingpathnavigator.setCanFloat(true);
        flyingpathnavigator.setCanPassDoors(true);
        return flyingpathnavigator;
    }

    protected SoundEvent getAmbientSound() {
        //make something like, buzzing? idk
        return null;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        //uhh, ELECTRIC ZAP SOUND PLEASE
        return SoundEvents.GENERIC_HURT;
    }

    protected float getStandingEyeHeight(Pose pose, EntitySize entitySize) {
        return entitySize.height * 0.7F;
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity playerEntity, Hand handOfPlayer) {
        ItemStack itemstack = playerEntity.getItemInHand(handOfPlayer);
        if(itemstack.getItem().getClass() == Items.STONE_PICKAXE.getClass()){
            System.out.println("Item: " + itemstack.getItem());
        }

        return super.mobInteract(playerEntity, handOfPlayer);
    }

}
