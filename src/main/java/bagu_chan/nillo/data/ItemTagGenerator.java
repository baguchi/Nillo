package bagu_chan.nillo.data;

import bagu_chan.nillo.NilloCore;
import bagu_chan.nillo.register.ModEntities;
import bagu_chan.nillo.register.ModItems;
import bagu_chan.nillo.register.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends ItemTagsProvider {
    public ItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> provider, ExistingFileHelper exFileHelper) {
        super(packOutput, lookupProvider, provider, NilloCore.MODID, exFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        this.tag(ItemTags.DYEABLE).add(ModItems.LEATHER_NILLO_ARMOR.get());
        this.tag(ItemTags.FREEZE_IMMUNE_WEARABLES).add(ModItems.LEATHER_NILLO_ARMOR.get());
        this.tag(ModTags.Items.AMULETS).add(ModItems.AQUA_AMULET.get()).add(ModItems.FIRE_AMULET.get()).add(ModItems.WIND_AMULET.get()).add(ModItems.EARTH_AMULET.get());
    }
}
