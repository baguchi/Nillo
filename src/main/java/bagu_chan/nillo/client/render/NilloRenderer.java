package bagu_chan.nillo.client.render;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.client.ModModelLayers;
import bagu_chan.nillo.client.model.NilloModel;
import bagu_chan.nillo.entity.Nillo;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class NilloRenderer extends MobRenderer<Nillo, NilloModel<Nillo>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(NilloCore.MODID, "textures/entity/nillo/nillo.png");

    public NilloRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new NilloModel<>(p_174304_.bakeLayer(ModModelLayers.NILLO)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(Nillo p_114482_) {
        return TEXTURE;
    }
}
