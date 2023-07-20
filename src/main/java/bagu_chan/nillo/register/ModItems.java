package bagu_chan.nillo.register;

import bagu_chan.nillo.NilloCore;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NilloCore.MODID);

    public static final RegistryObject<Item> NILLO_SPAWNEGG = ITEMS.register("nillo_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.NILLO, 0x40302A, 0x92B843, (new Item.Properties())));
    public static final RegistryObject<Item> GILLO_SPAWNEGG = ITEMS.register("gillo_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.GILLO, 0x40302A, 0x92B843, (new Item.Properties())));
    public static final RegistryObject<Item> BOOLD_SPAWNEGG = ITEMS.register("boold_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.BOOLD, 0x6C4631, 0xCABDA0, (new Item.Properties())));
    public static final RegistryObject<Item> HORNED_BOOLD_SPAWNEGG = ITEMS.register("horned_boold_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.HORNED_BOOLD, 0x6C4631, 0xCABDA0, (new Item.Properties())));

}
