package bagu_chan.nillo.client.render;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.client.ModModelLayers;
import bagu_chan.nillo.client.model.AquaNilloModel;
import bagu_chan.nillo.entity.AquaNillo;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AquaNilloRenderer extends MobRenderer<AquaNillo, AquaNilloModel<AquaNillo>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(NilloCore.MODID, "textures/entity/nillo/aqua_nillo.png");

    public AquaNilloRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new AquaNilloModel<>(p_174304_.bakeLayer(ModModelLayers.AQUA_NILLO)), 0.3F);
    }

    @Override
    protected void setupRotations(AquaNillo p_115317_, PoseStack p_115318_, float p_115319_, float p_115320_, float p_115321_) {
        super.setupRotations(p_115317_, p_115318_, p_115319_, p_115320_, p_115321_);
        p_115318_.mulPose(Axis.XP.rotationDegrees(p_115317_.getXRot()));
    }

    @Override
    public ResourceLocation getTextureLocation(AquaNillo p_114482_) {
        return TEXTURE;
    }
}
