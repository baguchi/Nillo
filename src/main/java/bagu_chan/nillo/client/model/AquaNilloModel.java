package bagu_chan.nillo.client.model;// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import bagu_chan.nillo.client.animation.NilloAnimations;
import bagu_chan.nillo.client.render.state.AquaNilloRenderState;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import org.joml.Vector3f;

public class AquaNilloModel<T extends AquaNilloRenderState> extends NilloModel<T> {

    public AquaNilloModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 22).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 37).addBox(0.0F, -4.0F, 0.0F, 0.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -1.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 34).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 37).addBox(0.0F, -4.0F, 0.0F, 0.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.0F));

        PartDefinition bone = body.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -5.0F, 8.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 11).addBox(-4.0F, -6.0F, -5.0F, 8.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(21, 11).addBox(-4.0F, -0.01F, -5.0F, 8.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

        PartDefinition Jaw_r1 = jaw.addOrReplaceChild("Jaw_r1", CubeListBuilder.create().texOffs(16, 0).addBox(-2.5F, 0.0F, -6.0F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -0.3054F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity) {
        super.setupAnim(entity);
        float f1 = entity.inWaterFactor;
        float f2 = entity.onGroundFactor;
        float f3 = entity.movingFactor;
        float f4 = 1.0F - f3;
        float f5 = 1.0F - Math.min(f2, f3);
        this.body.yRot = this.body.yRot + entity.yRot * (float) (Math.PI / 180.0);
        if (entity.isBaby) {
            this.applyStatic(NilloAnimations.baby);
        }
        if (entity.isSitting) {
            this.applyStatic(NilloAnimations.sit);
        } else {
            this.setupSwimmingAnimation(entity.ageInTicks, entity.xRot, Math.min(f3, f1));
            this.setupWaterHoveringAnimation(entity.ageInTicks, Math.min(f4, f1));
            this.setupGroundCrawlingAnimation(entity.ageInTicks, Math.min(f3, f2));
            this.setupLayStillOnGroundAnimation(entity.ageInTicks, Math.min(f4, f2));
        }
        this.applyStatic(NilloAnimations.scaled);
        this.animate(entity.attackAnimationState, NilloAnimations.attack, entity.ageInTicks);

    }


    private void setupLayStillOnGroundAnimation(float p_170415_, float p_170416_) {
        float f = p_170415_ * 0.09F;
        float f1 = Mth.sin(f);
        float f2 = Mth.cos(f);
        float f3 = f1 * f1 - 2.0F * f1;
        float f4 = f2 * f2 - 3.0F * f1;
        this.head.xRot = this.lerpTo(this.head.xRot, -0.09F * f3);
        this.head.yRot = this.lerpTo(this.head.yRot, 0.0F);
        this.head.zRot = this.lerpTo(this.head.zRot, -0.2F);
        this.body.xRot = this.lerpTo(0.2F, this.body.xRot, 0.0F);
        this.body.yRot = this.lerpTo(this.body.yRot, p_170416_ * ((float) Math.PI / 180F));
        this.body.zRot = this.lerpTo(this.body.zRot, 0.0F);
    }

    private void setupGroundCrawlingAnimation(float p_170419_, float p_170420_) {
        float f = p_170419_ * 0.11F;
        float f1 = Mth.cos(f);
        float f2 = (f1 * f1 - 2.0F * f1) / 5.0F;
        float f3 = 0.7F * f1;
        this.head.xRot = this.lerpTo(this.head.xRot, 0.0F);
        this.head.yRot = this.lerpTo(this.head.yRot, 0.09F * f1);
        this.head.zRot = this.lerpTo(this.head.zRot, 0.0F);
        this.tail.yRot = this.lerpTo(this.tail.yRot, this.head.yRot);
        this.body.xRot = this.lerpTo(0.2F, this.body.xRot, 0.0F);
        this.body.yRot = this.lerpTo(this.body.yRot, p_170420_ * ((float) Math.PI / 180F));
        this.body.zRot = this.lerpTo(this.body.zRot, 0.0F);
    }

    private void setupWaterHoveringAnimation(float p_170373_, float p_365132_) {
        if (!(p_365132_ <= 1.0E-5F)) {
            float f = p_170373_ * 0.075F;
            float f1 = Mth.cos(f);
            float f2 = Mth.sin(f) * 0.15F;
            float f3 = (-0.15F + 0.075F * f1) * p_365132_;
            this.body.xRot += f3;
            this.body.y -= f2 * p_365132_;
            this.head.xRot -= f3;
            this.tail.yRot += 0.5F * f1 * p_365132_;
        }
    }

    private void setupSwimmingAnimation(float p_170423_, float p_170424_, float p_361875_) {
        if (!(p_361875_ <= 1.0E-5F)) {
            float f = p_170423_ * 0.33F;
            float f1 = Mth.sin(f);
            float f2 = Mth.cos(f);
            float f3 = 0.13F * f1;
            this.body.xRot += (p_170424_ * (float) (Math.PI / 180.0) + f3) * p_361875_;
            this.head.xRot -= f3 * 1.8F * p_361875_;
            this.body.y -= 0.45F * f2 * p_361875_;
            this.tail.yRot = this.tail.yRot + 0.3F * Mth.cos(f * 0.9F) * p_361875_;
        }
    }

    private Vector3f getRotationVector(ModelPart p_254355_) {
        return new Vector3f(p_254355_.xRot, p_254355_.yRot, p_254355_.zRot);
    }

    private void setRotationFromVector(ModelPart p_254301_, Vector3f p_253783_) {
        p_254301_.setRotation(p_253783_.x(), p_253783_.y(), p_253783_.z());
    }

    private float lerpTo(float p_170375_, float p_170376_) {
        return this.lerpTo(0.05F, p_170375_, p_170376_);
    }


    private float lerpTo(float p_170378_, float p_170379_, float p_170380_) {
        return Mth.rotLerp(p_170378_, p_170379_, p_170380_);
    }

    private void lerpPart(ModelPart p_170404_, float p_170405_, float p_170406_, float p_170407_) {
        p_170404_.setRotation(this.lerpTo(p_170404_.xRot, p_170405_), this.lerpTo(p_170404_.yRot, p_170406_), this.lerpTo(p_170404_.zRot, p_170407_));
    }
}