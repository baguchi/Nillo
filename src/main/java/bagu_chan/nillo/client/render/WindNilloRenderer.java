package bagu_chan.nillo.client.render;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.client.ModModelLayers;
import bagu_chan.nillo.client.model.WindNilloModel;
import bagu_chan.nillo.client.render.layer.NilloArmorLayer;
import bagu_chan.nillo.entity.WindNillo;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class WindNilloRenderer extends MobRenderer<WindNillo, WindNilloModel<WindNillo>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(NilloCore.MODID, "textures/entity/nillo/wind_nillo.png");

    public WindNilloRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new WindNilloModel<>(p_174304_.bakeLayer(ModModelLayers.WIND_NILLO)), 0.3F);
        this.addLayer(new NilloArmorLayer<>(this, p_174304_.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(WindNillo p_114482_) {
        return TEXTURE;
    }
}
