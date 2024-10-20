package bagu_chan.nillo.register;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.item.AmuletItem;
import bagu_chan.nillo.item.NilloArmorItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
 public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NilloCore.MODID);

 public static final DeferredItem<Item> AQUA_AMULET = ITEMS.registerItem("aqua_amulet", AmuletItem::new);
 public static final DeferredItem<Item> FIRE_AMULET = ITEMS.registerItem("fire_amulet", AmuletItem::new);
 public static final DeferredItem<Item> EARTH_AMULET = ITEMS.registerItem("earth_amulet", AmuletItem::new);
 public static final DeferredItem<Item> WIND_AMULET = ITEMS.registerItem("wind_amulet", AmuletItem::new);
 public static final DeferredItem<Item> LEATHER_NILLO_ARMOR = ITEMS.registerItem("leather_nillo_armor", properties -> new NilloArmorItem(ArmorMaterials.LEATHER, AnimalArmorItem.BodyType.CANINE, true, properties.stacksTo(1).durability(86).enchantable(5)));
 public static final DeferredItem<Item> ARMADILLO_NILLO_ARMOR = ITEMS.registerItem("armadillo_nillo_armor", properties -> new NilloArmorItem(ArmorMaterials.ARMADILLO_SCUTE, AnimalArmorItem.BodyType.CANINE, true, properties.stacksTo(1).durability(64)));
 public static final DeferredItem<Item> NILLO_SPAWNEGG = ITEMS.registerItem("nillo_spawn_egg", properties -> new DeferredSpawnEggItem(ModEntities.NILLO, 0x40302A, 0x92B843, (properties)));
 public static final DeferredItem<Item> WIND_NILLO_SPAWNEGG = ITEMS.registerItem("wind_nillo_spawn_egg", properties -> new DeferredSpawnEggItem(ModEntities.WIND_NILLO, 0xDDE563, 0xEBE8D4, (properties)));
 public static final DeferredItem<Item> AQUA_NILLO_SPAWNEGG = ITEMS.registerItem("aqua_nillo_spawn_egg", properties -> new DeferredSpawnEggItem(ModEntities.AQUA_NILLO, 0x7E766E, 0x5C76C2, (properties)));
 public static final DeferredItem<Item> EARTH_NILLO_SPAWNEGG = ITEMS.registerItem("earth_nillo_spawn_egg", properties -> new DeferredSpawnEggItem(ModEntities.EARTH_NILLO, 0x6E7961, 0x2F3332, (properties)));
 public static final DeferredItem<Item> FIRE_NILLO_SPAWNEGG = ITEMS.registerItem("fire_nillo_spawn_egg", properties -> new DeferredSpawnEggItem(ModEntities.FIRE_NILLO, 0x693B3A, 0xE17E31, (properties)));
 public static final DeferredItem<Item> GILLO_SPAWNEGG = ITEMS.registerItem("gillo_spawn_egg", properties -> new DeferredSpawnEggItem(ModEntities.GILLO, 0x40302A, 0xEB8800, (properties)));

 private static ResourceKey<Item> prefix(String path) {
  return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(NilloCore.MODID, path));
 }

}
