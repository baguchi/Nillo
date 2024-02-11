package bagu_chan.nillo.client;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.client.model.*;
import bagu_chan.nillo.client.render.*;
import bagu_chan.nillo.register.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = NilloCore.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistrar {
    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.NILLO.get(), NilloRenderer::new);
        event.registerEntityRenderer(ModEntities.WIND_NILLO.get(), WindNilloRenderer::new);
        event.registerEntityRenderer(ModEntities.AQUA_NILLO.get(), AquaNilloRenderer::new);
        event.registerEntityRenderer(ModEntities.EARTH_NILLO.get(), EarthNilloRenderer::new);
        event.registerEntityRenderer(ModEntities.FIRE_NILLO.get(), FireNilloRenderer::new);
        event.registerEntityRenderer(ModEntities.GILLO.get(), GilloRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.NILLO, NilloModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.WIND_NILLO, WindNilloModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.AQUA_NILLO, AquaNilloModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.EARTH_NILLO, EarthNilloModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.GILLO, GilloModel::createBodyLayer);
    }
}
