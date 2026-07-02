package io.hwfg.kriscraft.datagen

import io.hwfg.kriscraft.Core
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.core.HolderLookup
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items
import java.util.concurrent.CompletableFuture

class Recipe(
    val output : FabricDataOutput,
    val registryLookup : CompletableFuture<HolderLookup.Provider>
) : FabricRecipeProvider(output,registryLookup) {
    override fun createRecipeProvider(
        registryLookup: HolderLookup.Provider,
        exporter: RecipeOutput
    ): RecipeProvider = object : RecipeProvider(registryLookup,exporter) {
        override fun buildRecipes() {
            this.shaped(RecipeCategory.TOOLS, Core.mossBedBlock.asItem(),1)
                .pattern("   ")
                .pattern("AAA")
                .pattern("BBB")
                .define('A', Items.MOSS_BLOCK)
                .define('B', ItemTags.PLANKS)
                .group("moss_bed")
                .unlockedBy(getHasName(Items.MOSS_BLOCK),has(Items.MOSS_BLOCK))
                .save(exporter)
            this.shapeless(RecipeCategory.FOOD, Core.mossBread,8)
                .requires(Items.BREAD,8)
                .requires(Items.MOSS_BLOCK)
                .group("moss_bread")
                .unlockedBy(getHasName(Items.MOSS_BLOCK),has(Items.MOSS_BLOCK))
                .save(exporter)
            this.shapeless(RecipeCategory.FOOD, Core.mossBurger,1)
                .requires(Core.mossBread,2)
                .requires(ItemTags.MEAT)
                .group("moss_burger")
                .unlockedBy(getHasName(Core.mossBread),has(Core.mossBread))
                .save(exporter)
        }
    }

    override fun getName(): String = "kriscraft_recipe"
}