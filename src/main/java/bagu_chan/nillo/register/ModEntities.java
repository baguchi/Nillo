package bagu_chan.nillo.register;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.entity.Boold;
import bagu_chan.nillo.entity.Gillo;
import bagu_chan.nillo.entity.Nillo;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
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
    public static final RegistryObject<EntityType<Gillo>> GILLO = ENTITIES_REGISTRY.register("gillo", () -> EntityType.Builder.of(Gillo::new, MobCategory.CREATURE).sized(1.0F, 1.0F).build(prefix("gillo")));
    public static final RegistryObject<EntityType<Boold>> BOOLD = ENTITIES_REGISTRY.register("boold", () -> EntityType.Builder.of(Boold::new, MobCategory.CREATURE).sized(1.0F, 1.0F).build(prefix("boold")));

    private static String prefix(String path) {
        return NilloCore.MODID + "." + path;
    }

    @SubscribeEvent
    public static void registerEntity(EntityAttributeCreationEvent event) {
        event.put(NILLO.get(), Nillo.createAttributeMap().build());
        event.put(GILLO.get(), Gillo.createAttributeMap().build());
        event.put(BOOLD.get(), Boold.createAttributeMap().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event) {
        event.register(NILLO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Nillo::checkNilloSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(GILLO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Nillo::checkNilloSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(BOOLD.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
