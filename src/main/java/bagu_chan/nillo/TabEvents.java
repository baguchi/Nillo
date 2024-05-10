package bagu_chan.nillo;

import bagu_chan.nillo.register.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = NilloCore.MODID)
public class TabEvents {

    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ModItems.NILLO_SPAWNEGG.get());
            event.accept(ModItems.WIND_NILLO_SPAWNEGG.get());
            event.accept(ModItems.AQUA_NILLO_SPAWNEGG.get());
            event.accept(ModItems.EARTH_NILLO_SPAWNEGG.get());
            event.accept(ModItems.FIRE_NILLO_SPAWNEGG.get());
            event.accept(ModItems.GILLO_SPAWNEGG.get());
        }
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.AQUA_AMULET.get());
            event.accept(ModItems.FIRE_AMULET.get());
            event.accept(ModItems.WIND_AMULET.get());
            event.accept(ModItems.EARTH_AMULET.get());
            event.accept(ModItems.LEATHER_NILLO_ARMOR.get());
            event.accept(ModItems.ARMADILLO_NILLO_ARMOR.get());
        }
    }
}
