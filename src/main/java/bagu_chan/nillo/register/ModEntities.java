package bagu_chan.nillo.register;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.entity.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@EventBusSubscriber(modid = NilloCore.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES_REGISTRY = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, NilloCore.MODID);

    public static final Supplier<EntityType<Nillo>> NILLO = ENTITIES_REGISTRY.register("nillo", () -> EntityType.Builder.of(Nillo::new, MobCategory.CREATURE).sized(0.6F, 0.45F).eyeHeight(0.45F * 0.5F).build(prefix("nillo")));
    public static final Supplier<EntityType<WindNillo>> WIND_NILLO = ENTITIES_REGISTRY.register("wind_nillo", () -> EntityType.Builder.of(WindNillo::new, MobCategory.CREATURE).sized(0.6F, 0.45F).eyeHeight(0.45F * 0.5F).build(prefix("wind_nillo")));
    public static final Supplier<EntityType<AquaNillo>> AQUA_NILLO = ENTITIES_REGISTRY.register("aqua_nillo", () -> EntityType.Builder.of(AquaNillo::new, MobCategory.WATER_CREATURE).sized(0.6F, 0.45F).eyeHeight(0.45F * 0.5F).build(prefix("aqua_nillo")));
    public static final Supplier<EntityType<FireNillo>> FIRE_NILLO = ENTITIES_REGISTRY.register("fire_nillo", () -> EntityType.Builder.of(FireNillo::new, MobCategory.CREATURE).sized(0.6F, 0.45F).eyeHeight(0.45F * 0.5F).build(prefix("fire_nillo")));
    public static final Supplier<EntityType<EarthNillo>> EARTH_NILLO = ENTITIES_REGISTRY.register("earth_nillo", () -> EntityType.Builder.of(EarthNillo::new, MobCategory.CREATURE).sized(0.6F, 0.45F).eyeHeight(0.45F * 0.5F).build(prefix("earth_nillo")));
    public static final Supplier<EntityType<Gillo>> GILLO = ENTITIES_REGISTRY.register("gillo", () -> EntityType.Builder.of(Gillo::new, MobCategory.CREATURE).sized(1.0F, 1.0F).eyeHeight(0.5F).build(prefix("gillo")));

    private static ResourceKey<EntityType<?>> prefix(String path) {
        return ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(NilloCore.MODID, path));
    }

    @SubscribeEvent
    public static void registerEntity(EntityAttributeCreationEvent event) {
        event.put(NILLO.get(), Nillo.createAttributeMap().build());
        event.put(WIND_NILLO.get(), WindNillo.createAttributeMap().build());
        event.put(AQUA_NILLO.get(), AquaNillo.createAttributeMap().build());
        event.put(FIRE_NILLO.get(), FireNillo.createAttributeMap().build());
        event.put(EARTH_NILLO.get(), EarthNillo.createAttributeMap().build());
        event.put(GILLO.get(), Gillo.createAttributeMap().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacement(RegisterSpawnPlacementsEvent event) {
        event.register(NILLO.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Nillo::checkNilloSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(WIND_NILLO.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Nillo::checkNilloSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(AQUA_NILLO.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AquaNillo::checkAquaNilloSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(FIRE_NILLO.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Nillo::checkNilloSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EARTH_NILLO.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Nillo::checkNilloSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(GILLO.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Nillo::checkNilloSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}
