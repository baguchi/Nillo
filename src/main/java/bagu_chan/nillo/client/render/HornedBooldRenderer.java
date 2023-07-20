package bagu_chan.nillo.client.render;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.client.ModModelLayers;
import bagu_chan.nillo.client.model.HornedBooldModel;
import bagu_chan.nillo.entity.HornedBoold;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HornedBooldRenderer extends MobRenderer<HornedBoold, HornedBooldModel<HornedBoold>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(NilloCore.MODID, "textures/entity/boold/horned_boold.png");

    public HornedBooldRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new HornedBooldModel<>(p_174304_.bakeLayer(ModModelLayers.HORNED_BOOLD)), 0.75F);
    }

    @Override
    public ResourceLocation getTextureLocation(HornedBoold p_114482_) {
        return TEXTURE;
    }
}
