package bagu_chan.nillo.entity;

import bagu_chan.nillo.entity.goal.NilloTargetGoal;
import bagu_chan.nillo.register.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Gillo extends Nillo{
    private static final UUID SPEED_MODIFIER_ATTACKING_UUID = UUID.fromString("d9c0fd1b-7f7f-1bf1-d6fb-44264f7ec5cd");
    private static final AttributeModifier SPEED_MODIFIER_ATTACKING = new AttributeModifier(SPEED_MODIFIER_ATTACKING_UUID, "Attacking speed boost", 0.05D, AttributeModifier.Operation.ADDITION);


    public Gillo(EntityType<? extends Nillo> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    public float maxUpStep() {
        return 1.0F;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(3, new NilloTargetGoal<>(this, Cow.class, true));
        this.targetSelector.addGoal(4, new NilloTargetGoal<>(this, Wolf.class, true));
    }

    public InteractionResult mobInteract(Player p_30412_, InteractionHand p_30413_) {
        ItemStack itemstack = p_30412_.getItemInHand(p_30413_);
        if ((!p_30412_.isShiftKeyDown()) && this.isTame() && this.isOwnedBy(p_30412_)) {
            p_30412_.startRiding(this);
            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(p_30412_, p_30413_);
        }
    }

    @javax.annotation.Nullable
    public LivingEntity getControllingPassenger() {
        Entity entity = this.getFirstPassenger();
        if (entity instanceof Mob) {
            return (Mob) entity;
        } else {
            entity = this.getFirstPassenger();
            if (entity instanceof Player) {
                return (Player) entity;
            }


            return null;
        }
    }


    protected void tickRidden(Player p_278233_, Vec3 p_275693_) {
        super.tickRidden(p_278233_, p_275693_);
        Vec2 vec2 = this.getRiddenRotation(p_278233_);
        this.setRot(vec2.y, vec2.x);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
    }

    protected Vec2 getRiddenRotation(LivingEntity p_275502_) {
        return new Vec2(p_275502_.getXRot() * 0.5F, p_275502_.getYRot());
    }

    protected Vec3 getRiddenInput(Player p_278278_, Vec3 p_275506_) {
        float f = p_278278_.xxa * 0.5F;
        float f1 = p_278278_.zza;
        if (f1 <= 0.0F) {
            f1 *= 0.25F;
        }
        return new Vec3((double) f, 0.0D, (double) f1);
    }

    protected float getRiddenSpeed(Player p_278336_) {
        return (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED);
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
    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        Nillo nillo = ModEntities.NILLO.get().create(p_146743_);
        if (nillo != null) {
            UUID uuid = this.getOwnerUUID();
            if (uuid != null) {
                nillo.setOwnerUUID(uuid);
                nillo.setTame(true);
            }
        }
        return nillo;
    }
    @Override
    public int getAttackAnimationLength() {
        return isAggressive() ? (int) (super.getAttackAnimationLength() * 0.8F) : super.getAttackAnimationLength();
    }
}
