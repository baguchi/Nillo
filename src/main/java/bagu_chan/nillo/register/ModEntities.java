package bagu_chan.nillo.register;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.entity.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = NilloCore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES_REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, NilloCore.MODID);

    public static final RegistryObject<EntityType<Nillo>> NILLO = ENTITIES_REGISTRY.register("nillo", () -> EntityType.Builder.of(Nillo::new, MobCategory.CREATURE).sized(0.6F, 0.45F).build(prefix("nillo")));
    public static final RegistryObject<EntityType<WindNillo>> WIND_NILLO = ENTITIES_REGISTRY.register("wind_nillo", () -> EntityType.Builder.of(WindNillo::new, MobCategory.CREATURE).sized(0.6F, 0.45F).build(prefix("wind_nillo")));
    public static final RegistryObject<EntityType<AquaNillo>> AQUA_NILLO = ENTITIES_REGISTRY.register("aqua_nillo", () -> EntityType.Builder.of(AquaNillo::new, MobCategory.WATER_CREATURE).sized(0.6F, 0.45F).build(prefix("aqua_nillo")));
    public static final RegistryObject<EntityType<FireNillo>> FIRE_NILLO = ENTITIES_REGISTRY.register("fire_nillo", () -> EntityType.Builder.of(FireNillo::new, MobCategory.CREATURE).sized(0.6F, 0.45F).build(prefix("fire_nillo")));
    public static final RegistryObject<EntityType<EarthNillo>> EARTH_NILLO = ENTITIES_REGISTRY.register("earth_nillo", () -> EntityType.Builder.of(EarthNillo::new, MobCategory.CREATURE).sized(0.6F, 0.45F).build(prefix("earth_nillo")));
    public static final RegistryObject<EntityType<Gillo>> GILLO = ENTITIES_REGISTRY.register("gillo", () -> EntityType.Builder.of(Gillo::new, MobCategory.CREATURE).sized(1.0F, 1.0F).build(prefix("gillo")));
    private static String prefix(String path) {
        return NilloCore.MODID + "." + path;
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
    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event) {
        event.register(NILLO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Nillo::checkNilloSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(WIND_NILLO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Nillo::checkNilloSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(AQUA_NILLO.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AquaNillo::checkAquaNilloSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(FIRE_NILLO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Nillo::checkNilloSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(EARTH_NILLO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Nillo::checkNilloSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(GILLO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Nillo::checkNilloSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
