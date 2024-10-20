package bagu_chan.nillo.client.model;// Made with Blockbench 4.7.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import bagu_chan.nillo.client.animation.NilloAnimations;
import bagu_chan.nillo.client.render.state.NilloRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class NilloModel<T extends NilloRenderState> extends EntityModel<T> {
    public final ModelPart realRoot;
    public final ModelPart root;
    public final ModelPart body;
    public final ModelPart tail;
    public final ModelPart head;

    public NilloModel(ModelPart root) {
        super(root);
        this.realRoot = root;
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.head = this.body.getChild("head");
        this.tail = this.body.getChild("tail");
    }

    public static LayerDefinition createBodyLayer(CubeDeformation cubeDeformation) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 22).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 4.0F, 8.0F, cubeDeformation), PartPose.offset(0.0F, -2.0F, -1.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 34).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 3.0F, 8.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, 1.0F, 7.0F, 0.48F, 0.0F, 0.0F));

        PartDefinition bone = body.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -5.0F, 8.0F, 6.0F, 5.0F, cubeDeformation), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 11).addBox(-4.0F, -6.0F, -5.0F, 8.0F, 6.0F, 5.0F, cubeDeformation)
                .texOffs(21, 11).addBox(-4.0F, -0.01F, -5.0F, 8.0F, 0.0F, 5.0F, cubeDeformation), PartPose.offset(0.0F, 2.0F, 0.0F));

        PartDefinition Jaw_r1 = jaw.addOrReplaceChild("Jaw_r1", CubeListBuilder.create().texOffs(16, 0).addBox(-2.5F, 0.0F, -6.0F, 5.0F, 0.0F, 5.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -0.3054F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 22).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -1.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 34).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 7.0F, 0.48F, 0.0F, 0.0F));

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
        this.head.xRot = entity.xRot * ((float) Math.PI / 180F);
        this.head.yRot = entity.yRot * ((float) Math.PI / 180F);
        this.applyStatic(NilloAnimations.scaled);
        if (entity.isBaby) {
            this.applyStatic(NilloAnimations.baby);
        }
        if (entity.isSitting) {
            this.applyStatic(NilloAnimations.sit);
        } else {
            this.animateWalk(NilloAnimations.walk, entity.walkAnimationPos, entity.walkAnimationSpeed, 1.0F, 1.5F);
        }
        this.animate(entity.attackAnimationState, NilloAnimations.attack, entity.ageInTicks);
    }


    public void copyPropertiesTo(NilloModel<T> p_102873_) {
        p_102873_.head.copyFrom(this.head);
        p_102873_.body.copyFrom(this.body);
        p_102873_.root.copyFrom(this.root);
        p_102873_.tail.copyFrom(this.tail);
    }
}