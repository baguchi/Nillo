package bagu_chan.nillo.entity;

import bagu_chan.nillo.entity.goal.FollowOwnerWaterGoal;
import bagu_chan.nillo.entity.goal.NilloTargetGoal;
import bagu_chan.nillo.entity.goal.SitWaterWhenOrderedToGoal;
import bagu_chan.nillo.register.ModEntities;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Map;

public class AquaNillo extends Nillo {
    private final Map<String, Vector3f> modelRotationValues = Maps.newHashMap();
    public AquaNillo(EntityType<? extends AquaNillo> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);

        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.25F, 1.0F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    public Map<String, Vector3f> getModelRotationValues() {
        return this.modelRotationValues;
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SitWaterWhenOrderedToGoal(this));

        this.goalSelector.addGoal(2, new AttackGoal(this));
        this.goalSelector.addGoal(3, new FollowOwnerWaterGoal(this, 1.0, 10.0F, 2.0F, false));

        this.goalSelector.addGoal(4, new BreedGoal(this, 0.75D));
        this.goalSelector.addGoal(5, new TemptGoal(this, 1.1D, this.getFoodItems(), false));
        this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new RandomSwimmingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));

        this.targetSelector.addGoal(2, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(4, new NilloTargetGoal<>(this, Chicken.class, true));
    }

    public static AttributeSupplier.Builder createAttributeMap() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.3F).add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.FOLLOW_RANGE, 18.0D).add(Attributes.ATTACK_DAMAGE, 2.0F);
    }

    public boolean checkSpawnObstruction(LevelReader p_32829_) {
        return p_32829_.isUnobstructed(this);
    }


    public static boolean checkAquaNilloSpawnRules(EntityType<? extends AquaNillo> p_218991_, LevelAccessor p_218992_, MobSpawnType p_218993_, BlockPos p_218994_, RandomSource p_218995_) {
        return (p_218992_.getDifficulty() != Difficulty.PEACEFUL && (p_218993_ == MobSpawnType.SPAWNER || p_218992_.getFluidState(p_218994_).is(FluidTags.WATER)) && p_218992_.getFluidState(p_218994_.below()).is(FluidTags.WATER));
    }

    public void baseTick() {
        int i = this.getAirSupply();
        super.baseTick();
        if (!this.isNoAi()) {
            this.handleAirSupply(i);
        }

    }

    protected void handleAirSupply(int p_149194_) {
        if (this.isAlive() && !this.isInWaterRainOrBubble()) {
            this.setAirSupply(p_149194_ - 1);
            if (this.getAirSupply() == -20) {
                this.setAirSupply(0);
                this.hurt(this.damageSources().dryOut(), 2.0F);
            }
        } else {
            this.setAirSupply(this.getMaxAirSupply());
        }
    }

    public int getMaxAirSupply() {
        return 200;
    }

    protected int increaseAirSupply(int p_28389_) {
        return this.getMaxAirSupply();
    }

    @Override
    public boolean canDrownInFluidType(FluidType type) {
        return false;
    }

    protected PathNavigation createNavigation(Level p_28362_) {
        return new WaterBoundPathNavigation(this, p_28362_);
    }

    public void travel(Vec3 p_28383_) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), p_28383_);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(p_28383_);
        }

    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return ModEntities.AQUA_NILLO.get().create(p_146743_);
    }
}
