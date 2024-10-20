package bagu_chan.nillo.client.model;// Made with Blockbench 4.7.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import bagu_chan.nillo.client.animation.NilloAnimations;
import bagu_chan.nillo.client.render.state.NilloRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class GilloModel<T extends NilloRenderState> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart realRoot;
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head;

	public GilloModel(ModelPart root) {
		super(root);
		this.realRoot = root;
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.head = this.body.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 56).addBox(-10.0F, -11.0F, 0.0F, 20.0F, 20.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, -7.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 96).addBox(-8.0F, -20.0F, -6.0F, 16.0F, 20.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 16.0F, -1.0036F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -9.0F, -12.0F, 24.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(47, 0).addBox(-3.0F, 1.0F, -10.0F, 6.0F, 0.0F, 10.0F, new CubeDeformation(0.1F))
				.texOffs(0, 30).addBox(-12.0F, -11.0F, -12.0F, 24.0F, 14.0F, 12.0F, new CubeDeformation(0.1F))
				.texOffs(60, 28).addBox(-12.0F, 2.75F, -12.0F, 24.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition plant = head.addOrReplaceChild("plant", CubeListBuilder.create().texOffs(0, 34).addBox(-2.5F, -7.0F, 0.0F, 6.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.5F, -8.0F, -11.0F, 0.0F, 0.0F, 0.6545F));

		PartDefinition plant2 = plant.addOrReplaceChild("plant2", CubeListBuilder.create().texOffs(0, 34).addBox(-2.5F, -7.0F, 0.0F, 6.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition mushroom = body.addOrReplaceChild("mushroom", CubeListBuilder.create().texOffs(0, 5).addBox(-3.5F, -7.0F, 0.0F, 6.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.5F, -11.0F, 4.0F));

		PartDefinition mushroom2 = mushroom.addOrReplaceChild("mushroom2", CubeListBuilder.create().texOffs(0, 5).addBox(-3.5F, -7.0F, 0.0F, 6.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity) {
		super.setupAnim(entity);
		this.head.xRot = entity.xRot * ((float) Math.PI / 180F);
		this.head.yRot = entity.yRot * ((float) Math.PI / 180F);
		if (entity.isBaby) {
			this.applyStatic(NilloAnimations.baby);
		}
		if (entity.isSitting) {
			this.applyStatic(NilloAnimations.sit);
		} else {
			this.animateWalk(NilloAnimations.walk, entity.walkAnimationPos, entity.walkAnimationPos, 1.0F, 1.5F);
		}
		this.animate(entity.attackAnimationState, NilloAnimations.attack, entity.ageInTicks, entity.isAggressive ? 1.2F : 1F);
	}
}