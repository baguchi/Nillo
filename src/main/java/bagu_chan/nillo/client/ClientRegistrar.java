package bagu_chan.nillo.client;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.client.model.*;
import bagu_chan.nillo.client.render.*;
import bagu_chan.nillo.register.ModEntities;
import bagu_chan.nillo.register.ModItems;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = NilloCore.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
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
        event.registerLayerDefinition(ModModelLayers.NILLO_ARMOR, () -> NilloModel.createBodyLayer(new CubeDeformation(0.15F)));
        event.registerLayerDefinition(ModModelLayers.WIND_NILLO, WindNilloModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.AQUA_NILLO, AquaNilloModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.EARTH_NILLO, EarthNilloModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.GILLO, GilloModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerColor(RegisterColorHandlersEvent.Item event) {
        event.register(
                (p_329705_, p_329706_) -> p_329706_ > 0 ? -1 : DyedItemColor.getOrDefault(p_329705_, -6265536),
                ModItems.LEATHER_NILLO_ARMOR.get()
        );
    }
}
