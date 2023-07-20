package bagu_chan.nillo.client.render;

import bagu_chan.nillo.entity.ThrownBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;

public class ThrwonBlockRenderer extends EntityRenderer<ThrownBlockEntity> {

    public ThrwonBlockRenderer(EntityRendererProvider.Context manager) {
        super(manager);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(ThrownBlockEntity entity, float yaw, float partialTicks, PoseStack posestack, MultiBufferSource buffers, int light) {
        BlockState blockstate = entity.getBlockState();
        if (blockstate.getRenderShape() == RenderShape.MODEL) {
            Level world = entity.level();
            if (blockstate.getRenderShape() != RenderShape.INVISIBLE) {
                posestack.pushPose();
                BlockPos blockpos = BlockPos.containing(entity.getX(), entity.getBoundingBox().maxY, entity.getZ());
                posestack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
                posestack.mulPose(Axis.XP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot()) + 0.0F));
                posestack.translate(-0.5D, 0.0D, -0.5D);
                BlockRenderDispatcher dispatcher = Minecraft.getInstance().getBlockRenderer();
                var model = dispatcher.getBlockModel(blockstate);
                for (var renderType : model.getRenderTypes(blockstate, RandomSource.create(blockstate.getSeed(entity.blockPosition())), ModelData.EMPTY))
                    dispatcher.getModelRenderer().tesselateBlock(world, model, blockstate, blockpos, posestack, buffers.getBuffer(renderType), false, RandomSource.create(), 0L, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, renderType);
                posestack.popPose();
                super.render(entity, yaw, partialTicks, posestack, buffers, light);
            }
        }
    }

    @Override
    public ResourceLocation getTextureLocation(ThrownBlockEntity entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}