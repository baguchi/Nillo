package bagu_chan.nillo.data;

import bagu_chan.nillo.register.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class CraftGenerator extends RecipeProvider {
    protected CraftGenerator(HolderLookup.Provider p_360573_, RecipeOutput p_360872_) {
        super(p_360573_, p_360872_);
    }

    @Override
    protected void buildRecipes() {
        HolderLookup<Item> itemLookup = this.registries.lookupOrThrow(Registries.ITEM);
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.COMBAT, new ItemStack(ModItems.LEATHER_NILLO_ARMOR.get()))
                .pattern("H")
                .pattern("L")
                .pattern("L")
                .define('H', Items.LEATHER_HELMET)
                .define('L', Items.LEATHER)
                .unlockedBy("has_item", has(Items.LEATHER))
                .save(this.output);
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.COMBAT, new ItemStack(ModItems.ARMADILLO_NILLO_ARMOR.get()))
                .pattern("AAA")
                .pattern("AAA")
                .pattern(" A ")
                .define('A', Items.ARMADILLO_SCUTE)
                .unlockedBy("has_item", has(Items.ARMADILLO_SCUTE))
                .save(this.output);
    }
}
