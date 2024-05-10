package bagu_chan.nillo.entity;

import bagu_chan.bagus_lib.entity.goal.AnimateAttackGoal;
import bagu_chan.nillo.entity.goal.NilloTargetGoal;
import bagu_chan.nillo.item.AmuletItem;
import bagu_chan.nillo.item.NilloArmorItem;
import bagu_chan.nillo.register.ModEntities;
import bagu_chan.nillo.register.ModItems;
import bagu_chan.nillo.register.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

//example Entity
public class Nillo extends TamableAnimal {
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.CHICKEN, Items.COOKED_CHICKEN);
    private static final EntityDataAccessor<ItemStack> DATA_AUMLET_ID = SynchedEntityData.defineId(Nillo.class, EntityDataSerializers.ITEM_STACK);

    private int hungerCooldown;
    public int attackAnimationTick;
    private final int attackAnimationLength = 12;
    private final int attackAnimationActionPoint = 8;
    public final AnimationState attackAnimationState = new AnimationState();
    public Nillo(EntityType<? extends Nillo> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    public static AttributeSupplier.Builder createAttributeMap() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.3F).add(Attributes.ATTACK_KNOCKBACK).add(Attributes.KNOCKBACK_RESISTANCE).add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.FOLLOW_RANGE, 18.0D).add(Attributes.ATTACK_DAMAGE, 3.0F);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_AUMLET_ID, ItemStack.EMPTY);
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

    public int getAttackAnimationActionPoint() {
        return attackAnimationActionPoint;
    }

    public int getHungerCooldown() {
        return hungerCooldown;
    }

    public void setHungerCooldown(int hungerCooldown) {
        this.hungerCooldown = hungerCooldown;
    }


    public void setAmuletItemStack(ItemStack stack) {
        this.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(0.0D);
        this.getAttribute(Attributes.ARMOR).setBaseValue(0.0D);
        this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
        if (stack.is(ModItems.WIND_AMULET.get())) {
            this.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(1.5F);
        } else if (stack.is(ModItems.EARTH_AMULET.get())) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(10.0D);
            this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
        }
        this.entityData.set(DATA_AUMLET_ID, stack);
    }

    public ItemStack getAmuletItemStack() {
        return this.entityData.get(DATA_AUMLET_ID);
    }

    @Override
    public boolean doHurtTarget(Entity p_21372_) {
        if (p_21372_ instanceof LivingEntity living) {
            if (this.getAmuletItemStack().is(ModItems.AQUA_AMULET.get())) {
                living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200));
            } else if (this.getAmuletItemStack().is(ModItems.FIRE_AMULET.get())) {
                living.setRemainingFireTicks(living.getRemainingFireTicks() + 100);
            }
        }
        return super.doHurtTarget(p_21372_);
    }

    @Override
    public boolean fireImmune() {
        return super.fireImmune() || this.getAmuletItemStack().is(ModItems.FIRE_AMULET.get());
    }

    @Override
    protected int calculateFallDamage(float p_21237_, float p_21238_) {
        if (this.getAmuletItemStack().is(ModItems.EARTH_AMULET.get())) {
            return 0;
        }
        return super.calculateFallDamage(p_21237_, p_21238_);
    }

    @Override
    public boolean hurt(DamageSource p_27567_, float p_27568_) {
        return super.hurt(p_27567_, p_27568_);
    }

    @Override
    public boolean canDrownInFluidType(FluidType type) {
        return super.canDrownInFluidType(type) && !this.getAmuletItemStack().is(ModItems.AQUA_AMULET.get());
    }

    @Override
    public boolean killedEntity(ServerLevel p_216988_, LivingEntity p_216989_) {
        this.hungerCooldown = 1200 + this.random.nextInt(1200);
        this.heal(1 + (p_216989_.getMaxHealth() / 4));
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
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new AttackGoal(this, 1.25F, 8, 12));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F, false));

        this.goalSelector.addGoal(4, new BreedGoal(this, 0.75D));
        this.goalSelector.addGoal(5, new TemptGoal(this, 1.1D, this.getFoodItems(), false));
        this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
        this.registerTargetGoals();
    }

    protected void registerTargetGoals() {
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(4, new NilloTargetGoal<>(this, Mob.class, true, living -> {
            return living.getType().is(ModTags.EntityTypes.NILLO_HUNT_TARGETS);
        }));
    }

    public static boolean checkNilloSpawnRules(EntityType<? extends Animal> p_218105_, LevelAccessor p_218106_, MobSpawnType p_218107_, BlockPos p_218108_, RandomSource p_218109_) {
        return (p_218106_.getBlockState(p_218108_.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) || p_218106_.getBlockState(p_218108_.below()).is(Tags.Blocks.SANDS)) && isBrightEnoughToSpawn(p_218106_, p_218108_);
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
        Nillo nillo = ModEntities.NILLO.get().create(p_146743_);
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
        if (!this.getAmuletItemStack().isEmpty()) {
            p_27587_.put("Item", this.getAmuletItemStack().save(this.registryAccess(), new CompoundTag()));

        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_27576_) {
        super.readAdditionalSaveData(p_27576_);

        this.setHungerCooldown(p_27576_.getInt("HungerCooldown"));
        CompoundTag compoundtag = p_27576_.getCompound("Item");
        if (compoundtag != null && !compoundtag.isEmpty()) {
            Optional<ItemStack> itemstack = ItemStack.parse(this.registryAccess(), compoundtag);

            this.setAmuletItemStack(itemstack.orElse(ItemStack.EMPTY));
        }
    }

    public InteractionResult mobInteract(Player p_30412_, InteractionHand p_30413_) {
        ItemStack itemstack = p_30412_.getItemInHand(p_30413_);
        if (itemstack.getItem() instanceof AmuletItem) {
            return InteractionResult.PASS;
        } else if (this.level().isClientSide) {
            boolean flag = this.isOwnedBy(p_30412_) || this.isTame() || this.isFood(itemstack) && !this.isTame();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else if (this.isTame()) {
            if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                this.heal((float) itemstack.getFoodProperties(this).nutrition());
                if (!p_30412_.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }
                this.playSound(SoundEvents.GENERIC_EAT);

                this.gameEvent(GameEvent.EAT, this);
                return InteractionResult.SUCCESS;
            } else {
                if (!(this instanceof Gillo) && itemstack.getItem() instanceof NilloArmorItem && this.isOwnedBy(p_30412_) && !this.hasArmor() && !this.isBaby()) {
                    this.setBodyArmorItem(itemstack.copyWithCount(1));
                    itemstack.consume(1, p_30412_);
                    return InteractionResult.SUCCESS;
                } else if (itemstack.is(Items.SHEARS)
                        && this.isOwnedBy(p_30412_)
                        && this.hasArmor()
                        && (!EnchantmentHelper.hasBindingCurse(this.getBodyArmorItem()) || p_30412_.isCreative())) {
                    itemstack.hurtAndBreak(1, p_30412_, getSlotForHand(p_30413_));
                    this.playSound(SoundEvents.ARMOR_UNEQUIP_WOLF);
                    ItemStack itemstack1 = this.getBodyArmorItem();
                    this.setBodyArmorItem(ItemStack.EMPTY);
                    this.spawnAtLocation(itemstack1);
                    return InteractionResult.SUCCESS;
                }/* else if (ArmorMaterials.ARMADILLO.value().repairIngredient().get().test(itemstack)
                        && this.isInSittingPose()
                        && this.hasArmor()
                        && this.isOwnedBy(p_30412_)
                        && this.getBodyArmorItem().isDamaged()) {
                    itemstack.shrink(1);
                    this.playSound(SoundEvents.WOLF_ARMOR_REPAIR);
                    ItemStack itemstack2 = this.getBodyArmorItem();
                    int i = (int)((float)itemstack2.getMaxDamage() * 0.125F);
                    itemstack2.setDamageValue(Math.max(0, itemstack2.getDamageValue() - i));
                    return InteractionResult.SUCCESS;
                }*/ else {
                    InteractionResult interactionresult = super.mobInteract(p_30412_, p_30413_);
                    if (!interactionresult.consumesAction() && this.isOwnedBy(p_30412_)) {
                        this.setOrderedToSit(!this.isOrderedToSit());
                        if (this.isOrderedToSit()) {
                            p_30412_.displayClientMessage(Component.translatable("nillo.nillo.sit", this.getDisplayName()), true);
                        } else {
                            p_30412_.displayClientMessage(Component.translatable("nillo.nillo.stand", this.getDisplayName()), true);
                        }
                        this.jumping = false;
                        this.navigation.stop();
                        this.setTarget(null);
                        return InteractionResult.SUCCESS_NO_ITEM_USED;
                    } else {
                        return interactionresult;
                    }
                }
            }
        } else if (this.isFood(itemstack)) {
            if (!p_30412_.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            if (this.random.nextInt(3) == 0 && !EventHooks.onAnimalTame(this, p_30412_)) {
                this.tame(p_30412_);
                this.navigation.stop();
                this.setTarget((LivingEntity) null);
                this.setOrderedToSit(true);
                this.level().broadcastEntityEvent(this, (byte) 7);
            } else {
                this.level().broadcastEntityEvent(this, (byte) 6);
            }

            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(p_30412_, p_30413_);
        }
    }

    /*@Override
    protected void actuallyHurt(DamageSource p_331935_, float p_330695_) {
        if (!this.canArmorAbsorb(p_331935_)) {
            super.actuallyHurt(p_331935_, p_330695_);
        } else {
            ItemStack itemstack = this.getBodyArmorItem();
            int i = itemstack.getDamageValue();
            int j = itemstack.getMaxDamage();
            itemstack.hurtAndBreak(Mth.ceil(p_330695_), this, EquipmentSlot.BODY);
            if (Crackiness.WOLF_ARMOR.byDamage(i, j) != Crackiness.WOLF_ARMOR.byDamage(this.getBodyArmorItem())) {
                this.playSound(SoundEvents.WOLF_ARMOR_CRACK);
                if (this.level() instanceof ServerLevel serverlevel) {
                    serverlevel.sendParticles(
                            new ItemParticleOption(ParticleTypes.ITEM, Items.ARMADILLO_SCUTE.getDefaultInstance()),
                            this.getX(),
                            this.getY() + 1.0,
                            this.getZ(),
                            20,
                            0.2,
                            0.1,
                            0.2,
                            0.1
                    );
                }
            }
        }
    }

    private boolean canArmorAbsorb(DamageSource p_331524_) {
        return this.hasArmor() && !p_331524_.is(DamageTypeTags.BYPASSES_WOLF_ARMOR);
    }*/

    public boolean hasArmor() {
        return !this.getBodyArmorItem().isEmpty();
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

    public boolean wantsToAttack(LivingEntity p_30389_, LivingEntity p_30390_) {
        if (!(p_30389_ instanceof Creeper) && !(p_30389_ instanceof Ghast)) {
            if (p_30389_ instanceof Nillo) {
                Nillo wolf = (Nillo) p_30389_;
                return !wolf.isTame() || wolf.getOwner() != p_30390_;
            } else if (p_30389_ instanceof Player && p_30390_ instanceof Player && !((Player) p_30390_).canHarmPlayer((Player) p_30389_)) {
                return false;
            } else if (p_30389_ instanceof AbstractHorse && ((AbstractHorse) p_30389_).isTamed()) {
                return false;
            } else {
                return !(p_30389_ instanceof TamableAnimal) || !((TamableAnimal) p_30389_).isTame();
            }
        } else {
            return false;
        }
    }


    static class AttackGoal extends AnimateAttackGoal {
        private final Nillo nillo;

        public AttackGoal(Nillo attacker, double speed, int actionPoint, int attackLength) {
            super(attacker, speed, actionPoint, attackLength);
            this.nillo = attacker;
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity target) {
            if (this.isTimeToAttack()) {
                if (this.canPerformAttack(target)) {
                    this.doAttack(target);
                }
            } else if (this.attackTicks >= this.nillo.getAttackAnimationLength()) {
                this.resetAttackCooldown();
                this.attack = false;
            } else if (this.attackTicks == 0 || !this.attack) {
                if (!this.canPerformAttack(target)) {
                    this.resetAttackCooldown();
                } else {
                    this.attack = true;
                    this.doTheAnimation();
                }
            }

            if (this.attack) {
                this.attackTicks = Mth.clamp(this.attackTicks + 1, 0, this.attackLength);
            } else {
                this.attackTicks = 0;
            }

        }

        protected boolean isTimeToAttack() {
            return this.attackTicks == this.nillo.getAttackAnimationActionPoint();
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }
}
