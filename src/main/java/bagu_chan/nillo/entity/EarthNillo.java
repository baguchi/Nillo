package bagu_chan.nillo.entity;

import bagu_chan.nillo.register.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class EarthNillo extends Nillo {
    public EarthNillo(EntityType<? extends EarthNillo> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);

    }

    public static AttributeSupplier.Builder createAttributeMap() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.3F).add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.FOLLOW_RANGE, 18.0D).add(Attributes.ATTACK_DAMAGE, 2.0F).add(Attributes.ARMOR, 12.0F);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        EarthNillo nillo = ModEntities.EARTH_NILLO.get().create(p_146743_);
        if (nillo != null) {
            UUID uuid = this.getOwnerUUID();
            if (uuid != null) {
                nillo.setOwnerUUID(uuid);
                nillo.setTame(true, true);
            }
        }
        return nillo;
    }

    @Override
    protected int calculateFallDamage(float p_21237_, float p_21238_) {
        return super.calculateFallDamage(p_21237_, p_21238_) - 3;
    }
}
