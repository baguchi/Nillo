package bagu_chan.nillo.client.model;// Made with Blockbench 4.7.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import bagu_chan.nillo.client.animation.BooldAnimations;
import bagu_chan.nillo.entity.HornedBoold;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class HornedBooldModel<T extends HornedBoold> extends HierarchicalModel<T> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart left_leg_back;
    private final ModelPart body;
    private final ModelPart right_leg;
    private final ModelPart right_leg_back;
    private final ModelPart left_leg;

    public HornedBooldModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.left_leg_back = root.getChild("left_leg_back");
        this.body = root.getChild("body");
        this.right_leg = root.getChild("right_leg");
        this.right_leg_back = root.getChild("right_leg_back");
        this.left_leg = root.getChild("left_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -9.0F, -12.0F, 16.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, -10.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition right_horn = head.addOrReplaceChild("right_horn", CubeListBuilder.create().texOffs(0, 28).addBox(-13.0F, -11.0F, -2.0F, 16.0F, 16.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 46).mirror().addBox(-13.0F, -17.0F, -2.0F, 16.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.0F, -8.0F, -6.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition left_horn = head.addOrReplaceChild("left_horn", CubeListBuilder.create().texOffs(0, 28).mirror().addBox(-4.0F, -10.5F, -1.0F, 16.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 46).addBox(-4.0F, -16.5F, -1.0F, 16.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -8.0F, -7.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition left_leg_back = partdefinition.addOrReplaceChild("left_leg_back", CubeListBuilder.create().texOffs(0, 58).addBox(-3.0F, 1.0F, -4.0F, 8.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 9.0F, 12.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 48).addBox(-16.0F, -36.0F, -16.0F, 32.0F, 36.0F, 32.0F, new CubeDeformation(0.0F))
                .texOffs(0, 84).addBox(16.0F, 0.0F, -16.0F, 0.0F, 6.0F, 32.0F, new CubeDeformation(0.0F))
                .texOffs(0, 84).addBox(-16.0F, 0.0F, -16.0F, 0.0F, 6.0F, 32.0F, new CubeDeformation(0.0F))
                .texOffs(63, 116).addBox(-16.0F, 0.0F, -16.0F, 32.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(63, 116).addBox(-16.0F, 0.0F, 16.0F, 32.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));

        PartDefinition tails = body.addOrReplaceChild("tails", CubeListBuilder.create().texOffs(92, 48).addBox(-3.0F, 0.0F, -1.0F, 6.0F, 0.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 16.0F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 58).addBox(-5.0F, 0.0F, -4.0F, 8.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 10.0F, -5.0F));

        PartDefinition right_leg_back = partdefinition.addOrReplaceChild("right_leg_back", CubeListBuilder.create().texOffs(0, 58).addBox(-5.0F, 0.0F, -4.0F, 8.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 10.0F, 12.0F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 58).addBox(-3.0F, 0.0F, -4.0F, 8.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 10.0F, -2.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        float f = ageInTicks - (float) entity.tickCount;
        this.head.xRot = headPitch * ((float) Math.PI / 180F) + (entity.getAggressiveAnimationScale(f) * 20F + 25F) * ((float) Math.PI / 180F);
        //this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        if (this.young) {
            this.applyStatic(BooldAnimations.BABY);
        }
        this.animateWalk(BooldAnimations.WALK, limbSwing, limbSwingAmount, 1.0F, 1.5F);
        this.animate(entity.attackAnimationState, BooldAnimations.ATTACK, ageInTicks, 1F);
        this.animate(entity.groundAttackAnimationState, BooldAnimations.GROUND_ATTACK, ageInTicks, 1F);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}