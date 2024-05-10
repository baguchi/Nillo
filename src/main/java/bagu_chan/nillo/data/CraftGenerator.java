package bagu_chan.nillo.data;

import bagu_chan.nillo.register.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class CraftGenerator extends RecipeProvider {
    public CraftGenerator(PackOutput generator, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(generator, completableFuture);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.LEATHER_NILLO_ARMOR.get(), 1)
                .pattern("H")
                .pattern("L")
                .pattern("L")
                .define('H', Items.LEATHER_HELMET)
                .define('L', Items.LEATHER)
                .unlockedBy("has_item", has(Items.LEATHER))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.ARMADILLO_NILLO_ARMOR.get(), 1)
                .pattern("AAA")
                .pattern("AAA")
                .pattern(" A ")
                .define('A', Items.ARMADILLO_SCUTE)
                .unlockedBy("has_item", has(Items.ARMADILLO_SCUTE))
                .save(consumer);
    }

}
