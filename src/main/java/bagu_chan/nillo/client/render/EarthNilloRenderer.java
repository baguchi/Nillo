package bagu_chan.nillo.client.render;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.client.ModModelLayers;
import bagu_chan.nillo.client.model.EarthNilloModel;
import bagu_chan.nillo.client.render.layer.NilloArmorLayer;
import bagu_chan.nillo.entity.EarthNillo;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EarthNilloRenderer extends MobRenderer<EarthNillo, EarthNilloModel<EarthNillo>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(NilloCore.MODID, "textures/entity/nillo/earth_nillo.png");

    public EarthNilloRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new EarthNilloModel<>(p_174304_.bakeLayer(ModModelLayers.EARTH_NILLO)), 0.3F);
        this.addLayer(new NilloArmorLayer<>(this, p_174304_.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(EarthNillo p_114482_) {
        return TEXTURE;
    }
}
