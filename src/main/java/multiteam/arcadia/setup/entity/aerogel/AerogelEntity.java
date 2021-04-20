package multiteam.arcadia.setup.entity.aerogel;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.EnumSet;
import java.util.stream.Stream;

public class AerogelEntity extends SlimeEntity implements IAnimatable {
    private static final DataParameter<Integer> ID_SIZE = EntityDataManager.defineId(SlimeEntity.class, DataSerializers.INT);
    private boolean wasOnGround;
    private boolean shouldDisassemble;

    private AnimationFactory factory = new AnimationFactory(this);

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(checkIfSHouldDisassemble()){
            event.getController().setAnimation(new AnimationBuilder().clearAnimations());
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.aerogel.disassemble", false));
            if(event.getController().getAnimationState() == AnimationState.Stopped){
                event.getController().setAnimation(new AnimationBuilder().clearAnimations());
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.aerogel.tornado", true));
            }
        }else{
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.aerogel.idle", true));
        }

        System.out.println("animationupdate: " + checkIfSHouldDisassemble());
        return PlayState.CONTINUE;
    }

    public AerogelEntity(EntityType<? extends SlimeEntity> type, World worldIn) {
        super(type, worldIn);
        this.moveControl = new AerogelEntity.MoveHelperController(this);
        this.shouldDisassemble = checkIfSHouldDisassemble();
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }


    @Override
    public CreatureAttribute getMobType() {
        return CreatureAttribute.UNDEFINED;
    }

    @Override
    public boolean causeFallDamage(float p_225503_1_, float p_225503_2_) {
        return false;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new AerogelEntity.FloatGoal(this));
        this.goalSelector.addGoal(2, new AerogelEntity.AttackGoal(this));
        this.goalSelector.addGoal(3, new AerogelEntity.FaceRandomGoal(this));
        this.goalSelector.addGoal(4, new AerogelEntity.HopGoal(this));

        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, (p_213811_1_) -> {
            return Math.abs(p_213811_1_.getY() - this.getY()) <= 4.0D;
        }));
    }

    public boolean isTiny() {
        return this.getSize() <= 1;
    }

    @Override
    protected IParticleData getParticleType() {
        return ParticleTypes.ASH;
    }

    protected boolean shouldDespawnInPeaceful() {
        return this.getSize() > 0;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_SIZE, 1);
    }

    @Override
    public int getSize() {
        return this.entityData.get(ID_SIZE);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        p_213281_1_.putInt("Size", this.getSize() - 1);
        p_213281_1_.putBoolean("wasOnGround", this.wasOnGround);
        p_213281_1_.putBoolean("shouldDisassemble", this.shouldDisassemble);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        int i = p_70037_1_.getInt("Size");
        if (i < 0) {
            i = 0;
        }

        this.setSize(i + 1, false);
        super.readAdditionalSaveData(p_70037_1_);
        this.wasOnGround = p_70037_1_.getBoolean("wasOnGround");
        this.shouldDisassemble = p_70037_1_.getBoolean("shouldDisassemble");
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, (double)1.5F).add(Attributes.ATTACK_DAMAGE, 5.0D);
    }

    @Override
    protected void setSize(int p_70799_1_, boolean p_70799_2_) {
        this.entityData.set(ID_SIZE, p_70799_1_);
        this.reapplyPosition();
        this.refreshDimensions();
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double)(p_70799_1_ * p_70799_1_));
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)(0.2F + 0.1F * (float)p_70799_1_));
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue((double)p_70799_1_);
        if (p_70799_2_) {
            this.setHealth(this.getMaxHealth());
        }

        this.xpReward = p_70799_1_;
    }

    public boolean checkIfSHouldDisassemble(){
        LivingEntity livingentity = this.getTarget();
        if (livingentity == null) {
            return false;
        } else if (!livingentity.isAlive()) {
            return false;
        } else {
            return livingentity instanceof PlayerEntity && ((PlayerEntity)livingentity).abilities.invulnerable ? false : this.getMoveControl() instanceof AerogelEntity.MoveHelperController;
        }
    }

    @Override
    public void tick() {

        super.tick();

        this.shouldDisassemble = checkIfSHouldDisassemble();

        if (this.onGround && !this.wasOnGround) {
            int i = this.getSize();

            if (spawnCustomParticles()) i = 0; // don't spawn particles if it's handled by the implementation itself
            for(int j = 0; j < i * 8; ++j) {
                float f = this.random.nextFloat() * ((float)Math.PI * 2F);
                float f1 = this.random.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * (float)i * 0.5F * f1;
                float f3 = MathHelper.cos(f) * (float)i * 0.5F * f1;
                this.level.addParticle(this.getParticleType(), this.getX() + (double)f2, this.getY(), this.getZ() + (double)f3, 0.0D, 0.0D, 0.0D);
            }

            this.playSound(this.getSquishSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);

        }

        this.wasOnGround = this.onGround;

    }

    private float getSoundPitch() {
        float f = this.isTiny() ? 1.4F : 0.8F;
        return ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * f;
    }

    static class FloatGoal extends Goal {
        private final AerogelEntity aerogelEntity;

        public FloatGoal(AerogelEntity p_i45823_1_) {
            this.aerogelEntity = p_i45823_1_;
            this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
            p_i45823_1_.getNavigation().setCanFloat(true);
        }

        public boolean canUse() {
            return (this.aerogelEntity.isInWater() || this.aerogelEntity.isInLava()) && this.aerogelEntity.getMoveControl() instanceof AerogelEntity.MoveHelperController;
        }

        public void tick() {
            if (this.aerogelEntity.getRandom().nextFloat() < 0.8F) {
                this.aerogelEntity.getJumpControl().jump();
            }

            ((AerogelEntity.MoveHelperController)this.aerogelEntity.getMoveControl()).setWantedMovement(1.2D);
        }
    }

    static class AttackGoal extends Goal {
        private final AerogelEntity aerogelEntity;
        private int growTiredTimer;

        public AttackGoal(AerogelEntity aerogelEntity) {
            this.aerogelEntity = aerogelEntity;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        public boolean canUse() {
            LivingEntity livingentity = this.aerogelEntity.getTarget();
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else {
                return livingentity instanceof PlayerEntity && ((PlayerEntity)livingentity).abilities.invulnerable ? false : this.aerogelEntity.getMoveControl() instanceof AerogelEntity.MoveHelperController;
            }
        }

        public void start() {
            this.growTiredTimer = 300;
            this.aerogelEntity.shouldDisassemble = true;
            super.start();
        }

        public boolean canContinueToUse() {
            LivingEntity livingentity = this.aerogelEntity.getTarget();
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else if (livingentity instanceof PlayerEntity && ((PlayerEntity)livingentity).abilities.invulnerable) {
                return false;
            } else {
                return --this.growTiredTimer > 0;
            }
        }

        public boolean checkIfSHouldDisassemble(){
            LivingEntity livingentity = this.aerogelEntity.getTarget();
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else {
                return livingentity instanceof PlayerEntity && ((PlayerEntity)livingentity).abilities.invulnerable ? false : this.aerogelEntity.getMoveControl() instanceof AerogelEntity.MoveHelperController;
            }
        }

        public void tick() {
            this.aerogelEntity.lookAt(this.aerogelEntity.getTarget(), 10.0F, 10.0F);
            this.aerogelEntity.shouldDisassemble = checkIfSHouldDisassemble();
            ((AerogelEntity.MoveHelperController)this.aerogelEntity.getMoveControl()).setDirection(this.aerogelEntity.yRot, this.aerogelEntity.isDealsDamage());
        }
    }

    static class FaceRandomGoal extends Goal {
        private final AerogelEntity aerogelEntity;
        private float chosenDegrees;
        private int nextRandomizeTime;

        public FaceRandomGoal(AerogelEntity p_i45820_1_) {
            this.aerogelEntity = p_i45820_1_;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        public boolean canUse() {
            return this.aerogelEntity.getTarget() == null && (this.aerogelEntity.onGround || this.aerogelEntity.isInWater() || this.aerogelEntity.isInLava() || this.aerogelEntity.hasEffect(Effects.LEVITATION)) && this.aerogelEntity.getMoveControl() instanceof AerogelEntity.MoveHelperController;
        }

        public void tick() {
            if (--this.nextRandomizeTime <= 0) {
                this.nextRandomizeTime = 40 + this.aerogelEntity.getRandom().nextInt(60);
                this.chosenDegrees = (float)this.aerogelEntity.getRandom().nextInt(360);
            }

            ((AerogelEntity.MoveHelperController)this.aerogelEntity.getMoveControl()).setDirection(this.chosenDegrees, false);
        }
    }

    static class HopGoal extends Goal {
        private final AerogelEntity aerogelEntity;

        public HopGoal(AerogelEntity p_i45822_1_) {
            this.aerogelEntity = p_i45822_1_;
            this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        public boolean canUse() {
            return !this.aerogelEntity.isPassenger();
        }

        public void tick() {
            ((AerogelEntity.MoveHelperController)this.aerogelEntity.getMoveControl()).setWantedMovement(1.0D);
        }
    }


    static class MoveHelperController extends MovementController {
        private float yRot;
        private int jumpDelay;
        private final AerogelEntity aerogelEntity;
        private boolean isAggressive;

        public MoveHelperController(AerogelEntity aerogelEntity) {
            super(aerogelEntity);
            this.aerogelEntity = aerogelEntity;
            this.yRot = 180.0F * aerogelEntity.yRot / (float)Math.PI;
        }

        public void setDirection(float p_179920_1_, boolean p_179920_2_) {
            this.yRot = p_179920_1_;
            this.isAggressive = p_179920_2_;
        }

        public void setWantedMovement(double p_179921_1_) {
            this.speedModifier = p_179921_1_;
            this.operation = MovementController.Action.MOVE_TO;
        }

        public void tick() {
            this.mob.yRot = this.rotlerp(this.mob.yRot, this.yRot, 90.0F);
            this.mob.yHeadRot = this.mob.yRot;
            this.mob.yBodyRot = this.mob.yRot;
            if (this.operation != MovementController.Action.MOVE_TO) {
                this.mob.setZza(0.0F);
            } else {
                this.operation = MovementController.Action.WAIT;
                if (this.mob.isOnGround()) {
                    this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                    if (this.jumpDelay-- <= 0) {
                        this.jumpDelay = this.aerogelEntity.getJumpDelay();
                        if (this.isAggressive) {
                            this.jumpDelay /= 3;
                        }

                        this.aerogelEntity.getJumpControl().jump();
                        if (this.aerogelEntity.doPlayJumpSound()) {
                            this.aerogelEntity.playSound(this.aerogelEntity.getJumpSound(), this.aerogelEntity.getSoundVolume(), this.aerogelEntity.getSoundPitch());
                        }
                    } else {
                        this.aerogelEntity.xxa = 0.0F;
                        this.aerogelEntity.zza = 0.0F;
                        this.mob.setSpeed(0.0F);
                    }
                } else {
                    this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                }

            }
        }

    }

}
