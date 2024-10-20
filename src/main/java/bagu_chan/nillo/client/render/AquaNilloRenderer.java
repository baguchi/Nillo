package bagu_chan.nillo.client.render;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.client.ModModelLayers;
import bagu_chan.nillo.client.model.AquaNilloModel;
import bagu_chan.nillo.client.render.layer.NilloArmorLayer;
import bagu_chan.nillo.client.render.state.AquaNilloRenderState;
import bagu_chan.nillo.entity.AquaNillo;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AquaNilloRenderer extends MobRenderer<AquaNillo, AquaNilloRenderState, AquaNilloModel<AquaNilloRenderState>> {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(NilloCore.MODID, "textures/entity/nillo/aqua_nillo.png");

    public AquaNilloRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new AquaNilloModel<>(p_174304_.bakeLayer(ModModelLayers.AQUA_NILLO)), 0.3F);
        this.addLayer(new NilloArmorLayer<>(this, p_174304_.getModelSet(), p_174304_.getEquipmentRenderer()));
    }

    @Override
    public AquaNilloRenderState createRenderState() {
        return new AquaNilloRenderState();
    }

    @Override
    public void extractRenderState(AquaNillo nillo, AquaNilloRenderState nilloState, float p_361157_) {
        super.extractRenderState(nillo, nilloState, p_361157_);
        nilloState.isSitting = nillo.isInSittingPose();
        nilloState.isOnGround = nillo.onGround();
        nilloState.isAggressive = nillo.isAggressive();
        nilloState.attackAnimationState.copyFrom(nillo.attackAnimationState);

        nilloState.inWaterFactor = nillo.inWaterAnimator.getFactor(p_361157_);
        nilloState.onGroundFactor = nillo.onGroundAnimator.getFactor(p_361157_);
        nilloState.movingFactor = nillo.movingAnimator.getFactor(p_361157_);
    }

    @Override
    public ResourceLocation getTextureLocation(AquaNilloRenderState p_114482_) {
        return TEXTURE;
    }
}
