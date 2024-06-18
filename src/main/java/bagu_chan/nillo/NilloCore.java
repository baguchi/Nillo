package bagu_chan.nillo;

import bagu_chan.nillo.register.ModEntities;
import bagu_chan.nillo.register.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NilloCore.MODID)
public class NilloCore {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "nillo";
    public static final String NETWORK_PROTOCOL = "2";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public NilloCore(ModContainer modContainer, IEventBus modBus) {
        IEventBus forgeBus = NeoForge.EVENT_BUS;

        ModItems.ITEMS.register(modBus);
        ModEntities.ENTITIES_REGISTRY.register(modBus);
        // Register the commonSetup method for modloading
        modBus.addListener(this::commonSetup);

    }


    private void commonSetup(final FMLCommonSetupEvent event) {
    }


}
