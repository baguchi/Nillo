package bagu_chan.nillo.entity.goal;

import bagu_chan.nillo.entity.Nillo;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

public class NilloTargetGoal <T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
    private final Nillo tamableMob;

    public NilloTargetGoal(Nillo p_26097_, Class<T> p_26098_, boolean p_26099_, TargetingConditions.Selector p_376872_) {
        super(p_26097_, p_26098_, p_26099_, p_376872_);
        this.tamableMob = p_26097_;
    }

    public boolean canUse() {
        return this.tamableMob.getHungerCooldown() <= 0 && !this.tamableMob.isTame() && super.canUse();
    }
}