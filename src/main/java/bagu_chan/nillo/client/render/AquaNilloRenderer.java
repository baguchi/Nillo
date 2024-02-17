package bagu_chan.nillo.client.render;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.client.ModModelLayers;
import bagu_chan.nillo.client.model.AquaNilloModel;
import bagu_chan.nillo.entity.AquaNillo;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AquaNilloRenderer extends MobRenderer<AquaNillo, AquaNilloModel<AquaNillo>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(NilloCore.MODID, "textures/entity/nillo/aqua_nillo.png");

    public AquaNilloRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new AquaNilloModel<>(p_174304_.bakeLayer(ModModelLayers.AQUA_NILLO)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(AquaNillo p_114482_) {
        return TEXTURE;
    }
}
