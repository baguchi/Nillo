package bagu_chan.nillo.client;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.client.model.BooldModel;
import bagu_chan.nillo.client.model.GilloModel;
import bagu_chan.nillo.client.model.HornedBooldModel;
import bagu_chan.nillo.client.model.NilloModel;
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
        event.registerEntityRenderer(ModEntities.GILLO.get(), GilloRenderer::new);
        event.registerEntityRenderer(ModEntities.BOOLD.get(), BooldRenderer::new);
        event.registerEntityRenderer(ModEntities.HORNED_BOOLD.get(), HornedBooldRenderer::new);
        event.registerEntityRenderer(ModEntities.THROWN_BLOCK.get(), ThrwonBlockRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.NILLO, NilloModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.GILLO, GilloModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.BOOLD, BooldModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.HORNED_BOOLD, HornedBooldModel::createBodyLayer);
    }

}
