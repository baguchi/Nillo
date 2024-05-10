package bagu_chan.nillo.register;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.item.AmuletItem;
import bagu_chan.nillo.item.NilloArmorItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, NilloCore.MODID);

    public static final Supplier<Item> NILLO_SPAWNEGG = ITEMS.register("nillo_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.NILLO, 0x40302A, 0x92B843, (new Item.Properties())));
    public static final Supplier<Item> WIND_NILLO_SPAWNEGG = ITEMS.register("wind_nillo_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.WIND_NILLO, 0xDDE563, 0xEBE8D4, (new Item.Properties())));
    public static final Supplier<Item> AQUA_NILLO_SPAWNEGG = ITEMS.register("aqua_nillo_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.AQUA_NILLO, 0x7E766E, 0x5C76C2, (new Item.Properties())));
    public static final Supplier<Item> EARTH_NILLO_SPAWNEGG = ITEMS.register("earth_nillo_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.EARTH_NILLO, 0x6E7961, 0x2F3332, (new Item.Properties())));
    public static final Supplier<Item> FIRE_NILLO_SPAWNEGG = ITEMS.register("fire_nillo_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.FIRE_NILLO, 0x693B3A, 0xE17E31, (new Item.Properties())));
    public static final Supplier<Item> GILLO_SPAWNEGG = ITEMS.register("gillo_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.GILLO, 0x40302A, 0xEB8800, (new Item.Properties())));
    public static final Supplier<Item> AQUA_AMULET = ITEMS.register("aqua_amulet", () -> new AmuletItem((new Item.Properties())));
    public static final Supplier<Item> FIRE_AMULET = ITEMS.register("fire_amulet", () -> new AmuletItem((new Item.Properties())));
    public static final Supplier<Item> EARTH_AMULET = ITEMS.register("earth_amulet", () -> new AmuletItem((new Item.Properties())));
    public static final Supplier<Item> WIND_AMULET = ITEMS.register("wind_amulet", () -> new AmuletItem((new Item.Properties())));
    public static final Supplier<Item> LEATHER_NILLO_ARMOR = ITEMS.register("leather_nillo_armor", () -> new NilloArmorItem(ArmorMaterials.LEATHER, AnimalArmorItem.BodyType.CANINE, true, new Item.Properties().stacksTo(1).durability(86)));
}
