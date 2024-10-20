package bagu_chan.nillo.client.render;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.client.ModModelLayers;
import bagu_chan.nillo.client.model.WindNilloModel;
import bagu_chan.nillo.client.render.layer.NilloArmorLayer;
import bagu_chan.nillo.client.render.state.NilloRenderState;
import bagu_chan.nillo.entity.WindNillo;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class WindNilloRenderer extends MobRenderer<WindNillo, NilloRenderState, WindNilloModel<NilloRenderState>> {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(NilloCore.MODID, "textures/entity/nillo/wind_nillo.png");

    public WindNilloRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new WindNilloModel<>(p_174304_.bakeLayer(ModModelLayers.WIND_NILLO)), 0.3F);
        this.addLayer(new NilloArmorLayer<>(this, p_174304_.getModelSet(), p_174304_.getEquipmentRenderer()));
    }

    @Override
    public NilloRenderState createRenderState() {
        return new NilloRenderState();
    }

    @Override
    public void extractRenderState(WindNillo nillo, NilloRenderState nilloState, float p_361157_) {
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
