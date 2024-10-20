package bagu_chan.nillo.client.render.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.item.ItemStack;

public class NilloRenderState extends LivingEntityRenderState {
    public final AnimationState attackAnimationState = new AnimationState();
    public boolean isSitting;
    public ItemStack bodyArmorItem = ItemStack.EMPTY;
    public boolean isAggressive;
    public boolean isOnGround;
}
