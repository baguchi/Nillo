package bagu_chan.nillo.client.render;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.client.ModModelLayers;
import bagu_chan.nillo.client.model.NilloModel;
import bagu_chan.nillo.entity.FireNillo;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FireNilloRenderer extends MobRenderer<FireNillo, NilloModel<FireNillo>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(NilloCore.MODID, "textures/entity/nillo/fire_nillo.png");

    public FireNilloRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new NilloModel<>(p_174304_.bakeLayer(ModModelLayers.NILLO)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(FireNillo p_114482_) {
        return TEXTURE;
    }
}
