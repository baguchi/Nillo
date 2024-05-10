package bagu_chan.nillo.data;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.register.ModEntities;
import bagu_chan.nillo.register.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class EntityTagGenerator extends EntityTypeTagsProvider {
    public EntityTagGenerator(PackOutput p_256095_, CompletableFuture<HolderLookup.Provider> p_256572_, ExistingFileHelper exFileHelper) {
        super(p_256095_, p_256572_, NilloCore.MODID, exFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider p_255894_) {
        this.tag(EntityTypeTags.CAN_BREATHE_UNDER_WATER).add(ModEntities.AQUA_NILLO.get());
        this.tag(EntityTypeTags.AQUATIC).add(ModEntities.AQUA_NILLO.get());
        this.tag(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS).add(ModEntities.NILLO.get()).add(ModEntities.AQUA_NILLO.get()).add(ModEntities.EARTH_NILLO.get()).add(ModEntities.FIRE_NILLO.get())
                .add(ModEntities.WIND_NILLO.get());
        this.tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(ModEntities.WIND_NILLO.get());
        this.tag(ModTags.EntityTypes.NILLO_HUNT_TARGETS).add(EntityType.CHICKEN);
        this.tag(ModTags.EntityTypes.GILLO_HUNT_TARGETS).add(EntityType.CHICKEN).add(EntityType.PANDA).add(EntityType.WOLF).add(EntityType.POLAR_BEAR);
    }
}
