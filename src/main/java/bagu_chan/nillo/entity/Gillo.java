package bagu_chan.nillo.entity;

import bagu_chan.nillo.entity.goal.NilloTargetGoal;
import bagu_chan.nillo.register.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Gillo extends Nillo{
    private static final UUID SPEED_MODIFIER_ATTACKING_UUID = UUID.fromString("d9c0fd1b-7f7f-1bf1-d6fb-44264f7ec5cd");
    private static final AttributeModifier SPEED_MODIFIER_ATTACKING = new AttributeModifier(SPEED_MODIFIER_ATTACKING_UUID, "Attacking speed boost", 0.05D, AttributeModifier.Operation.ADDITION);


    public Gillo(EntityType<? extends Nillo> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(3, new NilloTargetGoal<>(this, Cow.class, true));
        this.targetSelector.addGoal(4, new NilloTargetGoal<>(this, Wolf.class, true));
    }

    protected void customServerAiStep() {
        AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
        if (this.isAggressive()) {
            if (!attributeinstance.hasModifier(SPEED_MODIFIER_ATTACKING)) {
                attributeinstance.addTransientModifier(SPEED_MODIFIER_ATTACKING);
            }
        } else if (attributeinstance.hasModifier(SPEED_MODIFIER_ATTACKING)) {
            attributeinstance.removeModifier(SPEED_MODIFIER_ATTACKING);
        }

        super.customServerAiStep();
    }

    public static AttributeSupplier.Builder createAttributeMap() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.23F).add(Attributes.MAX_HEALTH, 30.0D).add(Attributes.FOLLOW_RANGE, 20.0D).add(Attributes.ATTACK_DAMAGE, 7.0F).add(Attributes.KNOCKBACK_RESISTANCE, 0.5F);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return ModEntities.NILLO.get().create(p_146743_);
    }
    @Override
    public int getAttackAnimationLength() {
        return isAggressive() ? (int) (super.getAttackAnimationLength() * 0.8F) : super.getAttackAnimationLength();
    }
}
