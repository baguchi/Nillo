package bagu_chan.nillo.entity;

import bagu_chan.nillo.register.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class Boold extends Animal {
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT);
    public static int TICK_BE_BIGGER = Math.abs(24000);
    public int attackAnimationTick;
    private final int attackAnimationLength = 35;
    private final int attackAnimationLeftActionPoint = 32;
    public final AnimationState attackAnimationState = new AnimationState();

    private int biggerAge;

    public Boold(EntityType<? extends Boold> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }

    public static AttributeSupplier.Builder createAttributeMap() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.22F).add(Attributes.MAX_HEALTH, 14.0D).add(Attributes.FOLLOW_RANGE, 18.0D).add(Attributes.ATTACK_DAMAGE, 4.0F);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.LLAMA_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_28306_) {
        return SoundEvents.LLAMA_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.LLAMA_DEATH;
    }

    protected void playStepSound(BlockPos p_28301_, BlockState p_28302_) {
        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    public float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 2F;
    }


    @Override
    public void baseTick() {
        super.baseTick();
        if (this.level().isClientSide) {
            if (this.attackAnimationTick < this.getAttackAnimationLength()) {
                this.attackAnimationTick++;
            }

            if (this.attackAnimationTick >= this.getAttackAnimationLength()) {
                this.attackAnimationState.stop();
            }
        }
    }

    public InteractionResult mobInteract(Player p_27584_, InteractionHand p_27585_) {
        ItemStack itemstack = p_27584_.getItemInHand(p_27585_);
        if (this.isFood(itemstack) && !this.isFullBigger()) {
            this.usePlayerItem(p_27584_, p_27585_, itemstack);
            biggerAge += 200;
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        return super.mobInteract(p_27584_, p_27585_);
    }

    @Override
    public boolean isFood(ItemStack p_27600_) {
        return getFoodItems().test(p_27600_);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide) {
            if (!this.isBaby() && this.isFullBigger()) {
                this.biggerAge += 1;
                if (biggerAge >= TICK_BE_BIGGER) {
                    this.biggerBoold();
                }
            }
        }
    }

    public boolean isFullBigger() {
        return false;
    }

    public void setBiggerAge(int biggerAge) {
        this.biggerAge = biggerAge;
    }

    public int getBiggerAge() {
        return biggerAge;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_27587_) {
        super.addAdditionalSaveData(p_27587_);
        p_27587_.putInt("BiggerAge", this.getBiggerAge());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_27576_) {
        super.readAdditionalSaveData(p_27576_);
        this.setBiggerAge(p_27576_.getInt("BiggerAge"));
    }

    private void biggerBoold() {
        Level $$1 = this.level();
        if ($$1 instanceof ServerLevel serverlevel) {
            HornedBoold horned = ModEntities.HORNED_BOOLD.get().create(this.level());
            if (horned != null) {
                horned.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
                horned.finalizeSpawn(serverlevel, this.level().getCurrentDifficultyAt(horned.blockPosition()), MobSpawnType.CONVERSION, (SpawnGroupData) null, (CompoundTag) null);
                horned.setNoAi(this.isNoAi());
                if (this.hasCustomName()) {
                    horned.setCustomName(this.getCustomName());
                    horned.setCustomNameVisible(this.isCustomNameVisible());
                }

                horned.setPersistenceRequired();
                serverlevel.addFreshEntityWithPassengers(horned);
                this.discard();
            }
        }

    }

    public int getAttackAnimationLength() {
        return attackAnimationLength;
    }

    public int getAttackAnimationLeftActionPoint() {
        return attackAnimationLeftActionPoint;
    }

    @Override
    public void handleEntityEvent(byte p_21375_) {
        if (p_21375_ == 4) {
            this.attackAnimationState.start(this.tickCount);
            this.attackAnimationTick = 0;
        } else {
            super.handleEntityEvent(p_21375_);
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BooldAttackGoal(this));
        this.goalSelector.addGoal(2, new BreedGoal(this, 0.85D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, this.getFoodItems(), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public Ingredient getFoodItems() {
        return FOOD_ITEMS;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return ModEntities.BOOLD.get().create(p_146743_);
    }

    @Override
    protected float getStandingEyeHeight(Pose p_21131_, EntityDimensions p_21132_) {
        return p_21132_.height * 0.65F;
    }

    public static class BooldAttackGoal extends MeleeAttackGoal {
        private final Boold boold;
        private boolean attack;
        private BlockPos targetPos;
        private int rushCooldowmTick;

        public BooldAttackGoal(Boold nillo) {
            super(nillo, 1.15D, true);
            this.boold = nillo;
        }

        @Override
        public void start() {
            super.start();
            this.rushCooldowmTick = 40;
        }

        @Override
        public void stop() {
            super.stop();
            this.attack = false;
        }

        public void tick() {
            LivingEntity livingentity = this.mob.getTarget();

            if (livingentity != null) {
                double d0 = this.mob.getPerceivedTargetDistanceSquareForMeleeAttack(livingentity);
                this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);

                if (--this.rushCooldowmTick < 0) {

                    targetPos = livingentity.blockPosition();

                    this.mob.getLookControl().setLookAt(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ(), 30.0F, 30.0F);

                    this.mob.getNavigation().moveTo(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ(), 2.25F);
                    this.rushCooldowmTick = 80;
                }
                this.checkAndPerformAttack(livingentity, d0);

            }
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity p_29589_, double p_29590_) {
            double d0 = this.getAttackReachSqr(p_29589_);
            if (p_29590_ <= d0 && this.getTicksUntilNextAttack() == this.boold.getAttackAnimationLeftActionPoint()) {

                this.mob.doHurtTarget(p_29589_);
                this.attack = true;
                if (this.getTicksUntilNextAttack() == 0) {
                    this.resetAttackCooldown();
                }
            } else if (p_29590_ <= d0) {
                if (this.getTicksUntilNextAttack() == this.boold.getAttackAnimationLength()) {
                    this.boold.level().broadcastEntityEvent(this.boold, (byte) 4);
                }
                if (this.getTicksUntilNextAttack() == 0) {
                    this.resetAttackCooldown();
                }
            } else {
                if (this.getTicksUntilNextAttack() == 0 || !this.attack) {
                    this.resetAttackCooldown();
                }
            }

        }

        protected void resetAttackCooldown() {
            this.ticksUntilNextAttack = this.adjustedTickDelay(36);
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }
}
