package bagu_chan.nillo.client.render;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.client.ModModelLayers;
import bagu_chan.nillo.client.model.GilloModel;
import bagu_chan.nillo.client.model.NilloModel;
import bagu_chan.nillo.entity.Gillo;
import bagu_chan.nillo.entity.Nillo;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GilloRenderer extends MobRenderer<Gillo, GilloModel<Gillo>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(NilloCore.MODID, "textures/entity/gillo/gillo.png");

    public GilloRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new GilloModel<>(p_174304_.bakeLayer(ModModelLayers.GILLO)), 0.75F);
    }

    @Override
    public ResourceLocation getTextureLocation(Gillo p_114482_) {
        return TEXTURE;
    }
}
