package bagu_chan.nillo.entity;

import bagu_chan.nillo.register.ModEntities;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

import javax.annotation.Nullable;

public class ThrownBlockEntity extends ThrowableProjectile {

    private BlockState state = Blocks.STONE.defaultBlockState();
    private boolean canPlace = true;

    public ThrownBlockEntity(EntityType<? extends ThrownBlockEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    public ThrownBlockEntity(Level world, LivingEntity thrower, @Nullable BlockState state) {
        super(ModEntities.THROWN_BLOCK.get(), thrower, world);
        if (state != null) {
            this.state = state;
        }
    }


    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.put("BlockState", NbtUtils.writeBlockState(this.state));
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.state = NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), tag.getCompound("BlockState"));
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            ParticleOptions particle = new BlockParticleOption(ParticleTypes.BLOCK, this.state);
            for (int i = 0; i < 20; i++) {
                this.level().addParticle(particle, false, this.getX(), this.getY(), this.getZ(), this.random.nextGaussian() * 0.05D, this.random.nextDouble() * 0.2D, this.random.nextGaussian() * 0.05D);
            }
        } else {
            super.handleEntityEvent(id);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level().isClientSide()) {
            result.getEntity().hurt(this.damageSources().fallingBlock(this.getOwner()), state.getBlock().defaultDestroyTime() * 6);

            this.level().broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, (byte) 3);
            this.gameEvent(GameEvent.BLOCK_DESTROY, this.getOwner());
            if (this.canPlace) {
                this.level().setBlock(this.blockPosition(), this.state, 2);
            }
            this.discard();
        }
    }

    public BlockState getBlockState() {
        return this.state;
    }

    public void setCanPlace(boolean canPlace) {
        this.canPlace = canPlace;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this, Block.getId(this.getBlockState()));
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket packet) {
        super.recreateFromPacket(packet);
        this.state = Block.stateById(packet.getData());
    }
}