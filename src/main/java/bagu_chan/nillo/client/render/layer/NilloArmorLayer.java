package bagu_chan.nillo.client.render.layer;

import bagu_chan.nillo.client.ModModelLayers;
import bagu_chan.nillo.client.model.NilloModel;
import bagu_chan.nillo.client.render.state.NilloRenderState;
import bagu_chan.nillo.item.NilloArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Crackiness;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;

import java.util.Map;

public class NilloArmorLayer<T extends NilloRenderState, M extends NilloModel<T>> extends RenderLayer<T, M> {
    private final NilloModel adultModel;
    private final EquipmentLayerRenderer equipmentRenderer;
    private static final Map<Crackiness.Level, ResourceLocation> ARMOR_CRACK_LOCATIONS = Map.of(
            Crackiness.Level.LOW,
            ResourceLocation.parse("textures/entity/nillo/nillo_armor_crackiness_low.png"),
            Crackiness.Level.MEDIUM,
            ResourceLocation.parse("textures/entity/nillo/nillo_armor_crackiness_medium.png"),
            Crackiness.Level.HIGH,
            ResourceLocation.parse("textures/entity/nillo/nillo_armor_crackiness_high.png")
    );

    public NilloArmorLayer(RenderLayerParent<T, M> p_316639_, EntityModelSet p_316756_, EquipmentLayerRenderer p_371602_) {
        super(p_316639_);
        this.adultModel = new NilloModel(p_316756_.bakeLayer(ModModelLayers.NILLO));
        this.equipmentRenderer = p_371602_;
    }

    public void render(PoseStack p_316890_, MultiBufferSource p_316537_, int p_316674_, NilloRenderState p_360943_, float p_316775_, float p_316264_) {
        ItemStack itemstack = p_360943_.bodyArmorItem;
        Equippable equippable = itemstack.get(DataComponents.EQUIPPABLE);
        if (equippable != null && !equippable.model().isEmpty() && itemstack.getItem() instanceof NilloArmorItem nilloArmorItem) {
            net.neoforged.neoforge.client.extensions.common.IClientItemExtensions extensions = net.neoforged.neoforge.client.extensions.common.IClientItemExtensions.of(itemstack);
            int i = extensions.getDefaultDyeColor(itemstack);
            boolean flag = itemstack.hasFoil();
            NilloModel nillo = this.adultModel;
            nillo.setupAnim(p_360943_);
            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(p_316537_, RenderType.armorCutoutNoCull(nilloArmorItem.getTexture()), flag);
            adultModel.renderToBuffer(p_316890_, vertexconsumer, p_316674_, OverlayTexture.NO_OVERLAY);
            this.maybeRenderCracks(p_316890_, p_316537_, p_316674_, itemstack, nillo);
            if (i != 0) {
                NilloModel nillo2 = this.adultModel;
                nillo2.setupAnim(p_360943_);
                VertexConsumer vertexconsumer2 = ItemRenderer.getArmorFoilBuffer(p_316537_, RenderType.armorCutoutNoCull(nilloArmorItem.getOverlayTexture()), flag);
                adultModel.renderToBuffer(p_316890_, vertexconsumer2, p_316674_, OverlayTexture.NO_OVERLAY, i);
                this.maybeRenderCracks(p_316890_, p_316537_, p_316674_, itemstack, nillo2);
            }
        }
    }

    private void maybeRenderCracks(PoseStack p_331222_, MultiBufferSource p_331637_, int p_330931_, ItemStack p_331187_, Model p_364428_) {
        Crackiness.Level crackiness$level = Crackiness.WOLF_ARMOR.byDamage(p_331187_);
        if (crackiness$level != Crackiness.Level.NONE) {
            ResourceLocation resourcelocation = ARMOR_CRACK_LOCATIONS.get(crackiness$level);
            VertexConsumer vertexconsumer = p_331637_.getBuffer(RenderType.entityTranslucent(resourcelocation));
            p_364428_.renderToBuffer(p_331222_, vertexconsumer, p_330931_, OverlayTexture.NO_OVERLAY);
        }
    }
}
