package multiteam.arcadia.setup.entity.zap;

import multiteam.arcadia.setup.entity.ModEntitys;
import multiteam.arcadia.setup.items.ModItems;
import multiteam.arcadia.setup.music.ModMusics;
import multiteam.arcadia.setup.particles.ModParticles;
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
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

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

    boolean DONE = false;
    @Override
    public ActionResultType interactAt(PlayerEntity playerEntity, Vector3d p_184199_2_, Hand handOfPlayer) {
        ItemStack itemstack = playerEntity.getItemInHand(handOfPlayer);

        //SWORD-BOW: STRENGTH
        //PICKAXE-AXE: HASTE
        //EMPTY: JUMP BOOST - SWIFTNESS
        //BOTTLE: TO BOTTLE
        if(!playerEntity.level.isClientSide && DONE == false){
            if(itemstack.getItem().getClass() == Items.STONE_SWORD.getClass() || itemstack.getItem().getClass() == Items.BOW.getClass() && DONE == false){
                playerEntity.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 2400));
                playerEntity.level.playSound((PlayerEntity)null, (double)this.getEntity().getX(), (double)this.getEntity().getY(), (double)this.getEntity().getZ(), ModMusics.ZAP_FUNCTION_ACTIVATE.get(), SoundCategory.NEUTRAL, 0.5F, 1F );
                this.DONE = true;

            }else if(itemstack.getItem().getClass() == Items.STONE_PICKAXE.getClass() || itemstack.getItem().getClass() == Items.STONE_AXE.getClass() && DONE == false){
                playerEntity.addEffect(new EffectInstance(Effects.DIG_SPEED, 2400));
                playerEntity.level.playSound((PlayerEntity)null, (double)this.getEntity().getX(), (double)this.getEntity().getY(), (double)this.getEntity().getZ(), ModMusics.ZAP_FUNCTION_ACTIVATE.get(), SoundCategory.NEUTRAL, 0.5F, 1F );
                this.DONE = true;

            }else if(itemstack.getItem() == Items.GLASS_BOTTLE && DONE == false){

                if(!playerEntity.isCreative()){
                    itemstack.shrink(1);
                    if(itemstack.isEmpty()){
                        playerEntity.inventory.removeItem(itemstack);
                    }
                }

                playerEntity.inventory.add(new ItemStack(ModItems.BOTTLED_ZAP.get()));
                playerEntity.level.playSound((PlayerEntity)null, (double)this.getEntity().getX(), (double)this.getEntity().getY(), (double)this.getEntity().getZ(), ModMusics.ZAP_BOTTLE_CAPTURE.get(), SoundCategory.NEUTRAL, 0.5F, 1F );
                this.DONE = true;

            }else if(itemstack.getItem() == Items.AIR && DONE == false){
                int randomChance = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                if(randomChance >= 50){
                    playerEntity.addEffect(new EffectInstance(Effects.JUMP, 2400));
                    playerEntity.level.playSound((PlayerEntity)null, (double)this.getEntity().getX(), (double)this.getEntity().getY(), (double)this.getEntity().getZ(), ModMusics.ZAP_FUNCTION_ACTIVATE.get(), SoundCategory.NEUTRAL, 0.5F, 1F );
                    this.DONE = true;

                }else{
                    playerEntity.level.playSound((PlayerEntity)null, (double)this.getEntity().getX(), (double)this.getEntity().getY(), (double)this.getEntity().getZ(), ModMusics.ZAP_FUNCTION_ACTIVATE.get(), SoundCategory.NEUTRAL, 0.5F, 1F );
                    playerEntity.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 2400));
                    this.DONE = true;
                }
            }

            if(playerEntity.level.isClientSide){
                summonZapParticles(playerEntity.level, this.getEntity().blockPosition());
            }

            this.getEntity().remove();
        }

        return super.mobInteract(playerEntity, handOfPlayer);

    }

    public static void summonZapParticles(World worldIn, BlockPos pos){
        for (float x = 0; x < 1;){
            for (float y = 0; y < 1;){
                for (float z = 0; z < 1;){
                    worldIn.addAlwaysVisibleParticle(ModParticles.ZAP_BOLT.get(), (double)pos.getX()+x, (double)pos.getY()+y, (double)pos.getZ()+z, 0.2D, 2.0D, 0.2D);
                    //worldIn.addParticle(ModParticles.CLOUD_POOF.get(), (double)pos.getX()+x, (double)pos.getY()+y, (double)pos.getZ()+z,0.2D, 2.0D, 0.2D);
                    z+=0.2f;
                }
                y+=0.2f;
            }
            x+=0.2f;
        }
    }

}
