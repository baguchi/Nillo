package bagu_chan.nillo.client.render;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.client.ModModelLayers;
import bagu_chan.nillo.client.model.BooldModel;
import bagu_chan.nillo.entity.Boold;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BooldRenderer extends MobRenderer<Boold, BooldModel<Boold>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(NilloCore.MODID, "textures/entity/boold/boold.png");

    public BooldRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new BooldModel<>(p_174304_.bakeLayer(ModModelLayers.BOOLD)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Boold p_114482_) {
        return TEXTURE;
    }
}
