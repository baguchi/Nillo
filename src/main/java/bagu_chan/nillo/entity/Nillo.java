package bagu_chan.nillo.entity;

import bagu_chan.nillo.entity.goal.NilloTargetGoal;
import bagu_chan.nillo.register.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;

//example Entity
public class Nillo extends Animal {
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.CHICKEN, Items.COOKED_CHICKEN);


    private int hungerCooldown;
    public int attackAnimationTick;
    private final int attackAnimationLength = 10;
    private final int attackAnimationLeftActionPoint = 2;
    public final AnimationState attackAnimationState = new AnimationState();
    public Nillo(EntityType<? extends Nillo> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    public static AttributeSupplier.Builder createAttributeMap() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.3F).add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.FOLLOW_RANGE, 18.0D).add(Attributes.ATTACK_DAMAGE, 3.0F);
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

    public int getHungerCooldown() {
        return hungerCooldown;
    }

    public void setHungerCooldown(int hungerCooldown) {
        this.hungerCooldown = hungerCooldown;
    }

    @Override
    public boolean killedEntity(ServerLevel p_216988_, LivingEntity p_216989_) {
        this.hungerCooldown = 1200 + this.random.nextInt(1200);
        this.heal(1);
        return super.killedEntity(p_216988_, p_216989_);
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
        this.goalSelector.addGoal(2, new BreedGoal(this, 0.75D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, this.getFoodItems(), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NilloTargetGoal<>(this, Chicken.class, true));
    }

    public static boolean checkNilloSpawnRules(EntityType<? extends Animal> p_218105_, LevelAccessor p_218106_, MobSpawnType p_218107_, BlockPos p_218108_, RandomSource p_218109_) {
        return (p_218106_.getBlockState(p_218108_.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) || p_218106_.getBlockState(p_218108_.below()).is(Tags.Blocks.SAND)) && isBrightEnoughToSpawn(p_218106_, p_218108_);
    }

    public Ingredient getFoodItems() {
        return FOOD_ITEMS;
    }

    @Override
    public boolean isFood(ItemStack p_27600_) {
        return getFoodItems().test(p_27600_);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return ModEntities.NILLO.get().create(p_146743_);
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        if(this.hungerCooldown > 0){
            this.setHungerCooldown(this.getHungerCooldown() - 1);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_27587_) {
        super.addAdditionalSaveData(p_27587_);
        p_27587_.putInt("HungerCooldown", this.getHungerCooldown());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_27576_) {
        super.readAdditionalSaveData(p_27576_);

        this.setHungerCooldown(p_27576_.getInt("HungerCooldown"));
    }

    public boolean canSpeedUp() {
        return false;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.PHANTOM_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_33152_) {
        return SoundEvents.PHANTOM_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.PHANTOM_DEATH;
    }

    @Override
    public float getVoicePitch() {
        return super.getVoicePitch() + 0.5F;
    }

    @Override
    protected float getStandingEyeHeight(Pose p_21131_, EntityDimensions p_21132_) {
        return p_21132_.height * 0.5F;
    }

    static class AttackGoal extends MeleeAttackGoal {
        private final Nillo nillo;
        private boolean attack;

        public AttackGoal(Nillo nillo) {
            super(nillo, 1.1D, true);
            this.nillo = nillo;
        }

        @Override
        public void stop() {
            super.stop();
            this.attack = false;
            this.nillo.setAggressive(false);
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity p_29589_, double p_29590_) {
            double d0 = this.getAttackReachSqr(p_29589_);
            if (this.getTicksUntilNextAttack() == this.nillo.getAttackAnimationLeftActionPoint()) {

                if (p_29590_ <= d0) {
                    this.mob.doHurtTarget(p_29589_);
                }

                if (this.getTicksUntilNextAttack() == 0) {
                    this.resetAttackCooldown();
                }
                if (this.nillo.canSpeedUp()) {
                    this.nillo.setAggressive(true);
                }
            } else if (p_29590_ <= d0) {
                if (this.getTicksUntilNextAttack() == this.nillo.getAttackAnimationLength()) {
                    this.nillo.level().broadcastEntityEvent(this.nillo, (byte) 4);
                    this.attack = true;
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
            this.ticksUntilNextAttack = this.adjustedTickDelay(11);
        }

        protected double getAttackReachSqr(LivingEntity attackTarget) {
            return (double) (this.nillo.getBbWidth() * 1.6F * this.nillo.getBbWidth() * 1.6F + attackTarget.getBbWidth());
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }
}
