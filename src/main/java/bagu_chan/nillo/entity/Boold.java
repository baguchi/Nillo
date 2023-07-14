package bagu_chan.nillo.entity;

import bagu_chan.nillo.register.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class Boold extends Animal {

    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT);

    public int attackAnimationTick;
    private final int attackAnimationLength = 35;
    private final int attackAnimationLeftActionPoint = 32;
    public final AnimationState attackAnimationState = new AnimationState();

    public Boold(EntityType<? extends Boold> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }

    public static AttributeSupplier.Builder createAttributeMap() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.22F).add(Attributes.MAX_HEALTH, 14.0D).add(Attributes.FOLLOW_RANGE, 18.0D).add(Attributes.ATTACK_DAMAGE, 4.0F);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.COW_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_28306_) {
        return SoundEvents.COW_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COW_DEATH;
    }

    protected void playStepSound(BlockPos p_28301_, BlockState p_28302_) {
        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }

    protected float getSoundVolume() {
        return 0.4F;
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
        this.goalSelector.addGoal(1, new AttackGoal(this));
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

    static class AttackGoal extends MeleeAttackGoal {
        private final Boold boold;
        private boolean attack;

        public AttackGoal(Boold nillo) {
            super(nillo, 1.1D, true);
            this.boold = nillo;
        }

        @Override
        public void stop() {
            super.stop();
            this.attack = false;
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
