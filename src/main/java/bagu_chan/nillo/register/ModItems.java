package bagu_chan.nillo.register;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.item.AmuletItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NilloCore.MODID);

    public static final RegistryObject<Item> NILLO_SPAWNEGG = ITEMS.register("nillo_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.NILLO, 0x40302A, 0x92B843, (new Item.Properties())));
    public static final RegistryObject<Item> WIND_NILLO_SPAWNEGG = ITEMS.register("wind_nillo_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.WIND_NILLO, 0xDDE563, 0xEBE8D4, (new Item.Properties())));
    public static final RegistryObject<Item> AQUA_NILLO_SPAWNEGG = ITEMS.register("aqua_nillo_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.AQUA_NILLO, 0x7E766E, 0x5C76C2, (new Item.Properties())));
    public static final RegistryObject<Item> EARTH_NILLO_SPAWNEGG = ITEMS.register("earth_nillo_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.EARTH_NILLO, 0x6E7961, 0x2F3332, (new Item.Properties())));
    public static final RegistryObject<Item> FIRE_NILLO_SPAWNEGG = ITEMS.register("fire_nillo_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.FIRE_NILLO, 0x693B3A, 0xE17E31, (new Item.Properties())));
    public static final RegistryObject<Item> GILLO_SPAWNEGG = ITEMS.register("gillo_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.GILLO, 0x40302A, 0xEB8800, (new Item.Properties())));
    public static final RegistryObject<Item> AQUA_AMULET = ITEMS.register("aqua_amulet", () -> new AmuletItem((new Item.Properties())));
    public static final RegistryObject<Item> FIRE_AMULET = ITEMS.register("fire_amulet", () -> new AmuletItem((new Item.Properties())));
    public static final RegistryObject<Item> EARTH_AMULET = ITEMS.register("earth_amulet", () -> new AmuletItem((new Item.Properties())));
    public static final RegistryObject<Item> WIND_AMULET = ITEMS.register("wind_amulet", () -> new AmuletItem((new Item.Properties())));
}
