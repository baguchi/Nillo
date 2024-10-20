package bagu_chan.nillo.client.model;// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import bagu_chan.nillo.client.animation.NilloAnimations;
import bagu_chan.nillo.client.render.state.NilloRenderState;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class WindNilloModel<T extends NilloRenderState> extends NilloModel<T> {

    public WindNilloModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 22).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -1.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 34).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.0F));

        PartDefinition right_wing = body.addOrReplaceChild("right_wing", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.0F, 4.0F));

        PartDefinition cube_r1 = right_wing.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 37).addBox(0.0F, 0.0F, -4.0F, 0.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.7453F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -5.0F, 8.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 11).addBox(-4.0F, -6.0F, -5.0F, 8.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(21, 11).addBox(-4.0F, -0.01F, -5.0F, 8.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

        PartDefinition Jaw_r1 = jaw.addOrReplaceChild("Jaw_r1", CubeListBuilder.create().texOffs(16, 0).addBox(-2.5F, 0.0F, -6.0F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -0.3054F, 0.0F, 0.0F));

        PartDefinition left_wing = body.addOrReplaceChild("left_wing", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 4.0F));

        PartDefinition cube_r2 = left_wing.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 37).addBox(0.0F, 0.0F, -4.0F, 0.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.7453F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.head.xRot = entity.xRot * ((float) Math.PI / 180F);
        this.head.yRot = entity.yRot * ((float) Math.PI / 180F);
        this.applyStatic(NilloAnimations.scaled);
        if (entity.isBaby) {
            this.applyStatic(NilloAnimations.baby);
        }
        if (entity.isOnGround) {
            if (entity.isSitting) {
                this.applyStatic(NilloAnimations.sit);
            } else {
                this.animateWalk(NilloAnimations.walk, entity.walkAnimationPos, entity.walkAnimationSpeed, 1.0F, 1.5F);
            }
        } else {
            this.animateWalk(NilloAnimations.flying, entity.ageInTicks, 1.0F, 1.0F, 1.5F);
        }
        this.animate(entity.attackAnimationState, NilloAnimations.attack, entity.ageInTicks);
    }
}