package bagu_chan.nillo.client.render;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.client.ModModelLayers;
import bagu_chan.nillo.client.model.GilloModel;
import bagu_chan.nillo.client.render.state.NilloRenderState;
import bagu_chan.nillo.entity.Gillo;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GilloRenderer extends MobRenderer<Gillo, NilloRenderState, GilloModel<NilloRenderState>> {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(NilloCore.MODID, "textures/entity/gillo/gillo.png");

    public GilloRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new GilloModel<>(p_174304_.bakeLayer(ModModelLayers.GILLO)), 0.75F);
    }

    @Override
    public NilloRenderState createRenderState() {
        return new NilloRenderState();
    }

    @Override
    protected void setupRotations(NilloRenderState p_115014_, PoseStack p_115015_, float p_115016_, float p_115017_) {
        super.setupRotations(p_115014_, p_115015_, p_115016_, p_115017_);
        if (!((double) p_115014_.walkAnimationSpeed < 0.01)) {
            float f = 13.0F;
            float f1 = p_115014_.walkAnimationPos + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            p_115015_.mulPose(Axis.ZP.rotationDegrees(6.5F * f2));
        }
    }

    @Override
    public void extractRenderState(Gillo nillo, NilloRenderState nilloState, float p_361157_) {
        super.extractRenderState(nillo, nilloState, p_361157_);
        nilloState.isSitting = nillo.isInSittingPose();
        nilloState.isOnGround = nillo.onGround();
        nilloState.isAggressive = nillo.isAggressive();
        nilloState.attackAnimationState.copyFrom(nillo.attackAnimationState);
    }

    @Override
    public ResourceLocation getTextureLocation(NilloRenderState p_114482_) {
        return TEXTURE;
    }
}
