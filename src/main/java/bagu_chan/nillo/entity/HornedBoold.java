package bagu_chan.nillo.entity;

import bagu_chan.nillo.register.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class HornedBoold extends Boold {

    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT);
    public int groundAttackAnimationTick;
    private final int groundAttackAnimationLength = 34;
    private final int groundAttackAnimationLeftActionPoint = (int) (20 * 0.75F);
    public final AnimationState groundAttackAnimationState = new AnimationState();

    public HornedBoold(EntityType<? extends HornedBoold> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
        this.setMaxUpStep(1);
    }

    public static AttributeSupplier.Builder createAttributeMap() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.24F).add(Attributes.MAX_HEALTH, 50.0D).add(Attributes.FOLLOW_RANGE, 28.0D).add(Attributes.KNOCKBACK_RESISTANCE, 1.0F).add(Attributes.ATTACK_DAMAGE, 6.0F);
    }

    protected float getSoundVolume() {
        return 1.25F;
    }

    @Override
    public float getVoicePitch() {
        return this.isBaby() ? (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 2F : (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 0.25F;
    }

    public Ingredient getFoodItems() {
        return FOOD_ITEMS;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (this.level().isClientSide) {
            if (this.groundAttackAnimationTick < this.groundAttackAnimationLength) {
                this.groundAttackAnimationTick++;
            }

            if (this.groundAttackAnimationTick >= this.groundAttackAnimationLength) {
                this.groundAttackAnimationState.stop();
            }
        }
    }

    @Override
    public void handleEntityEvent(byte p_21375_) {
        if (p_21375_ == 61) {
            this.groundAttackAnimationState.start(this.tickCount);
            this.groundAttackAnimationTick = 0;
        } else {
            super.handleEntityEvent(p_21375_);
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BooldGroundAttackGoal(this));
        this.goalSelector.addGoal(2, new BooldAttackGoal(this));
        this.goalSelector.addGoal(3, new BreedGoal(this, 0.8D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.0D, this.getFoodItems(), false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 0.9D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.75D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return ModEntities.BOOLD.get().create(p_146743_);
    }

    protected float getStandingEyeHeight(Pose p_21131_, EntityDimensions p_21132_) {
        return p_21132_.height * 0.85F;
    }

    public static class BooldGroundAttackGoal extends Goal {
        private final HornedBoold boold;
        private int cooldown;
        private int tick;
        private boolean failed;

        public BooldGroundAttackGoal(HornedBoold nillo) {
            super();
            this.boold = nillo;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            if (this.cooldown > 0) {
                --this.cooldown;
            }
            if (this.cooldown <= 0) {
                this.cooldown = 20 * 5 + this.boold.random.nextInt(10) * 20;
                if (this.boold.getTarget() != null && this.boold.distanceToSqr(this.boold.getTarget()) > 46F) {

                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            return this.tick < this.boold.groundAttackAnimationLength && !this.failed;
        }

        @Override
        public void start() {
            super.start();
            this.tick = 0;
            this.boold.level().broadcastEntityEvent(this.boold, (byte) 61);
            this.failed = false;
        }

        @Override
        public void stop() {
            super.stop();
        }

        @Override
        public void tick() {
            super.tick();
            ++this.tick;
            if (this.boold.getTarget() != null) {
                this.boold.lookAt(this.boold.getTarget(), 5.0F, 5.0F);


                if (this.tick == this.boold.groundAttackAnimationLeftActionPoint) {
                    LivingEntity target = this.boold.getTarget();
                    if (!this.boold.level().getBlockState(this.boold.blockPosition().below()).isAir() && this.boold.level().getBlockState(this.boold.blockPosition().below()).getBlock().defaultDestroyTime() < 2.0F) {
                        Vec3 vec3 = target.getDeltaMovement();
                        double d0 = target.getX() + vec3.x - this.boold.getX();
                        double d1 = target.getEyeY() - (double) 1.1F - this.boold.getY();
                        double d2 = target.getZ() + vec3.z - this.boold.getZ();

                        ThrownBlockEntity fallingBlock = new ThrownBlockEntity(this.boold.level(), this.boold, this.boold.level().getBlockState(this.boold.blockPosition().below()));
                        fallingBlock.setCanPlace(true);
                        fallingBlock.setPos(this.boold.position().x(), this.boold.getEyeY(), this.boold.position().z());
                        fallingBlock.setXRot(fallingBlock.getXRot() - -40.0F);
                        fallingBlock.shoot(d0, d1, d2, 0.9F, 18F);
                        this.boold.level().addFreshEntity(fallingBlock);
                        this.boold.playSound(this.boold.level().getBlockState(this.boold.blockPosition().below()).getSoundType().getBreakSound(), 2.0F, 1.0F);
                        this.boold.level().removeBlock(this.boold.blockPosition().below(), false);
                    } else {
                        failed = true;
                    }
                }
            }
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }
}
