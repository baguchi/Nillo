package bagu_chan.nillo.client;

import bagu_chan.nillo.NilloCore;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static ModelLayerLocation NILLO = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(NilloCore.MODID, "nillo"), "main");
    public static ModelLayerLocation WIND_NILLO = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(NilloCore.MODID, "wind_nillo"), "main");
    public static ModelLayerLocation EARTH_NILLO = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(NilloCore.MODID, "earth_nillo"), "main");
    public static ModelLayerLocation AQUA_NILLO = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(NilloCore.MODID, "aqua_nillo"), "main");
    public static ModelLayerLocation GILLO = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(NilloCore.MODID, "gillo"), "main");
    public static ModelLayerLocation NILLO_ARMOR = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(NilloCore.MODID, "nillo_armor"), "main");
}
