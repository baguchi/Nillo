package bagu_chan.nillo.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.equipment.ArmorMaterial;

public class NilloArmorItem extends AnimalArmorItem {
    private final ResourceLocation textureLocation;
    @javax.annotation.Nullable
    private final ResourceLocation overlayTextureLocation;

    public NilloArmorItem(ArmorMaterial armorMaterial, AnimalArmorItem.BodyType p_324315_, boolean p_331679_, Properties p_316341_) {
        super(armorMaterial, p_324315_, p_316341_);
        ResourceLocation resourcelocation = armorMaterial.modelId().withPath((p_323717_ -> "textures/entity/nillo/armor/nillo_armor_" + p_323717_));
        this.textureLocation = resourcelocation.withSuffix(".png");
        if (p_331679_) {
            this.overlayTextureLocation = resourcelocation.withSuffix("_overlay.png");
        } else {
            this.overlayTextureLocation = null;
        }
        //rewrite armor valve
    }


    public ResourceLocation getTexture() {
        return this.textureLocation;
    }


    public ResourceLocation getOverlayTexture() {
        return this.overlayTextureLocation;
    }
}