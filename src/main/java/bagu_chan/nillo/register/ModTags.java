package bagu_chan.nillo.register;

import bagu_chan.nillo.NilloCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class EntityTypes {
        public static final TagKey<EntityType<?>> NILLO_HUNT_TARGETS = create("nillo_hunt_targets");
        public static final TagKey<EntityType<?>> GILLO_HUNT_TARGETS = create("gillo_hunt_targets");

        private static TagKey<EntityType<?>> create(String p_203849_) {
            return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(NilloCore.MODID, p_203849_));
        }
    }

    public static class Items {
        public static final TagKey<Item> AMULETS = create("amulets");
        private static TagKey<Item> create(String p_203849_) {
            return TagKey.create(Registries.ITEM, new ResourceLocation(NilloCore.MODID, p_203849_));
        }
    }
}
