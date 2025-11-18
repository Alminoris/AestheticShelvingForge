package net.alminoris.aestheticshelving.datagen;

import net.alminoris.aestheticshelving.block.ModBlocks;
import net.alminoris.aestheticshelving.item.ModItemGroups;
import net.alminoris.aestheticshelving.util.helper.BlockSetsHelper;
import net.alminoris.aestheticshelving.util.helper.ModJsonHelper;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder
{
    public ModRecipeProvider(DataGenerator pOutput)
    {
        super(pOutput);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> recipeExporter)
    {
        for(String name : BlockSetsHelper.WOODS)
        {
            String blockName = (name.equals("crimson") || name.equals("warped")) ? "stem" : (name.equals("bamboo") ? "block" : "log");
            Block block = ForgeRegistries.BLOCKS.getValue(ResourceLocation.withDefaultNamespace("stripped_"+name+"_"+blockName));
            Block block1 = ForgeRegistries.BLOCKS.getValue(ResourceLocation.withDefaultNamespace(name+"_"+blockName));
            registerShelf(recipeExporter, ModBlocks.SHELVES.get(name).get(), block1, block);
            registerStandingShelf(recipeExporter, ModBlocks.STANDING_SHELVES.get(name).get(), block1, block);
            registerCornerShelf(recipeExporter, ModBlocks.CORNER_SHELVES.get(name).get(), block1, block);
            registerTowerShelf(recipeExporter, ModBlocks.TOWER_SHELVES.get(name).get(), block1, block);
            registerLadderShelf(recipeExporter, ModBlocks.LADDER_SHELVES.get(name).get(), block1, block);
            registerCeilingShelf(recipeExporter, ModBlocks.CEILING_SHELVES.get(name).get(), block);
        }

        for(String name : ModItemGroups.AN_WOOD_NAMES)
        {
            ModJsonHelper.createShapedRecipe("shelf_" + name, "4", "arborealnature:" + name + "_log", "arborealnature:stripped_" + name + "_log",
                    "\"#/#\"", "", "");

            ModJsonHelper.createShapedRecipe("standing_shelf_" + name, "4", "arborealnature:" + name + "_log", "arborealnature:stripped_" + name + "_log",
                    "\"/#\",", "\"/#\"", "");

            ModJsonHelper.createShapedRecipe("corner_shelf_" + name, "4", "arborealnature:" + name + "_log",
                    "arborealnature:stripped_" + name + "_log",
                    "\"#/\",", "\" #\"", "");

            ModJsonHelper.createShapedRecipe("tower_shelf_" + name, "3", "arborealnature:" + name + "_log",
                    "arborealnature:stripped_" + name + "_log",
                    "\" / \",", "\"/#/\",", "\"/#/\"");

            ModJsonHelper.createShapedRecipe("ladder_shelf_" + name, "2", "arborealnature:" + name + "_log",
                    "arborealnature:stripped_" + name + "_log",
                    "\"#/ \",", "\"#/ \",", "\" #/\"");

            ModJsonHelper.createShapedRecipe("ceiling_shelf_" + name, "4", "minecraft:chain", "arborealnature:stripped_" + name + "_log",
                    "\"# #\",", "\"///\"", "");
        }

        for(String name : ModItemGroups.WF_WOOD_NAMES)
        {
            ModJsonHelper.createShapedRecipe("shelf_" + name, "4", "wildfields:" + name + "_log", "wildfields:stripped_" + name + "_log",
                    "\"#/#\"", "", "");

            ModJsonHelper.createShapedRecipe("standing_shelf_" + name, "4", "wildfields:" + name + "_log", "wildfields:stripped_" + name + "_log",
                    "\"/#\",", "\"/#\"", "");

            ModJsonHelper.createShapedRecipe("corner_shelf_" + name, "4", "wildfields:" + name + "_log",
                    "wildfields:stripped_" + name + "_log",
                    "\"#/\",", "\" #\"", "");

            ModJsonHelper.createShapedRecipe("tower_shelf_" + name, "3", "wildfields:" + name + "_log",
                    "wildfields:stripped_" + name + "_log",
                    "\" / \",", "\"/#/\",", "\"/#/\"");

            ModJsonHelper.createShapedRecipe("ladder_shelf_" + name, "2", "wildfields:" + name + "_log",
                    "wildfields:stripped_" + name + "_log",
                    "\"#/ \",", "\"#/ \",", "\" #/\"");

            ModJsonHelper.createShapedRecipe("ceiling_shelf_" + name, "4", "minecraft:chain", "wildfields:stripped_" + name + "_log",
                    "\"# #\",", "\"///\"", "");
        }

        for(String name : ModItemGroups.WT_WOOD_NAMES)
        {
            ModJsonHelper.createShapedRecipe("shelf_" + name, "4", "whisperleaftrees:" + name + "_log", "whisperleaftrees:stripped_" + name + "_log",
                    "\"#/#\"", "", "");

            ModJsonHelper.createShapedRecipe("standing_shelf_" + name, "4", "whisperleaftrees:" + name + "_log", "whisperleaftrees:stripped_" + name + "_log",
                    "\"/#\",", "\"/#\"", "");

            ModJsonHelper.createShapedRecipe("corner_shelf_" + name, "4", "whisperleaftrees:" + name + "_log",
                    "whisperleaftrees:stripped_" + name + "_log",
                    "\"#/\",", "\" #\"", "");

            ModJsonHelper.createShapedRecipe("tower_shelf_" + name, "3", "whisperleaftrees:" + name + "_log",
                    "whisperleaftrees:stripped_" + name + "_log",
                    "\" / \",", "\"/#/\",", "\"/#/\"");

            ModJsonHelper.createShapedRecipe("ladder_shelf_" + name, "2", "whisperleaftrees:" + name + "_log",
                    "whisperleaftrees:stripped_" + name + "_log",
                    "\"#/ \",", "\"#/ \",", "\" #/\"");

            ModJsonHelper.createShapedRecipe("ceiling_shelf_" + name, "4", "minecraft:chain", "whisperleaftrees:stripped_" + name + "_log",
                    "\"# #\",", "\"///\"", "");
        }

        for(String name : ModItemGroups.ST_WOOD_NAMES)
        {
            ModJsonHelper.createShapedRecipe("shelf_" + name, "4", "silverwoodtrees:" + name + "_log", "silverwoodtrees:stripped_" + name + "_log",
                    "\"#/#\"", "", "");

            ModJsonHelper.createShapedRecipe("standing_shelf_" + name, "4", "silverwoodtrees:" + name + "_log", "silverwoodtrees:stripped_" + name + "_log",
                    "\"/#\",", "\"/#\"", "");

            ModJsonHelper.createShapedRecipe("corner_shelf_" + name, "4", "silverwoodtrees:" + name + "_log",
                    "silverwoodtrees:stripped_" + name + "_log",
                    "\"#/\",", "\" #\"", "");

            ModJsonHelper.createShapedRecipe("tower_shelf_" + name, "3", "silverwoodtrees:" + name + "_log",
                    "silverwoodtrees:stripped_" + name + "_log",
                    "\" / \",", "\"/#/\",", "\"/#/\"");

            ModJsonHelper.createShapedRecipe("ladder_shelf_" + name, "2", "silverwoodtrees:" + name + "_log",
                    "silverwoodtrees:stripped_" + name + "_log",
                    "\"#/ \",", "\"#/ \",", "\" #/\"");

            ModJsonHelper.createShapedRecipe("ceiling_shelf_" + name, "4", "minecraft:chain", "silverwoodtrees:stripped_" + name + "_log",
                    "\"# #\",", "\"///\"", "");
        }

        for(String name : ModItemGroups.MT_WOOD_NAMES)
        {
            ModJsonHelper.createShapedRecipe("shelf_" + name, "4", "missingtrees:" + name + "_log", "missingtrees:stripped_" + name + "_log",
                    "\"#/#\"", "", "");

            ModJsonHelper.createShapedRecipe("standing_shelf_" + name, "4", "missingtrees:" + name + "_log", "missingtrees:stripped_" + name + "_log",
                    "\"/#\",", "\"/#\"", "");

            ModJsonHelper.createShapedRecipe("corner_shelf_" + name, "4", "missingtrees:" + name + "_log",
                    "missingtrees:stripped_" + name + "_log",
                    "\"#/\",", "\" #\"", "");

            ModJsonHelper.createShapedRecipe("tower_shelf_" + name, "3", "missingtrees:" + name + "_log",
                    "missingtrees:stripped_" + name + "_log",
                    "\" / \",", "\"/#/\",", "\"/#/\"");

            ModJsonHelper.createShapedRecipe("ladder_shelf_" + name, "2", "missingtrees:" + name + "_log",
                    "missingtrees:stripped_" + name + "_log",
                    "\"#/ \",", "\"#/ \",", "\" #/\"");

            ModJsonHelper.createShapedRecipe("ceiling_shelf_" + name, "4", "minecraft:chain", "missingtrees:stripped_" + name + "_log",
                    "\"# #\",", "\"///\"", "");
        }

        for(String name : ModItemGroups.NSS_WOOD_NAMES)
        {
            ModJsonHelper.createShapedRecipe("shelf_" + name, "4", "natures_spirit:" + name.replace("_nss", "") + "_log", "natures_spirit:stripped_" + name.replace("_nss", "") + "_log",
                    "\"#/#\"", "", "");

            ModJsonHelper.createShapedRecipe("standing_shelf_" + name, "4", "natures_spirit:" + name.replace("_nss", "") + "_log", "natures_spirit:stripped_" + name.replace("_nss", "") + "_log",
                    "\"/#\",", "\"/#\"", "");

            ModJsonHelper.createShapedRecipe("corner_shelf_" + name, "4", "natures_spirit:" + name.replace("_nss", "") + "_log",
                    "natures_spirit:stripped_" + name.replace("_nss", "") + "_log",
                    "\"#/\",", "\" #\"", "");

            ModJsonHelper.createShapedRecipe("tower_shelf_" + name, "3", "natures_spirit:" + name.replace("_nss", "") + "_log",
                    "natures_spirit:stripped_" + name.replace("_nss", "") + "_log",
                    "\" / \",", "\"/#/\",", "\"/#/\"");

            ModJsonHelper.createShapedRecipe("ladder_shelf_" + name, "2", "natures_spirit:" + name.replace("_nss", "") + "_log",
                    "natures_spirit:stripped_" + name.replace("_nss", "") + "_log",
                    "\"#/ \",", "\"#/ \",", "\" #/\"");

            ModJsonHelper.createShapedRecipe("ceiling_shelf_" + name, "4", "minecraft:chain", "natures_spirit:stripped_" + name.replace("_nss", "") + "_log",
                    "\"# #\",", "\"///\"", "");
        }
    }

    private void registerShelf(Consumer<FinishedRecipe> recipeExporter, Block output, Block ing1, Block ing2)
    {
        ShapedRecipeBuilder.shaped(output, 4)
                .pattern("#/#")
                .define('#', ing1)
                .define('/', ing2)
                .unlockedBy(getHasName(ing1), has(ing1))
                .unlockedBy(getHasName(ing2), has(ing2))
                .save(recipeExporter);
    }

    private void registerStandingShelf(Consumer<FinishedRecipe> recipeExporter, Block output, Block ing1, Block ing2)
    {
        ShapedRecipeBuilder.shaped(output, 4)
                .pattern("/#")
                .pattern("/#")
                .define('#', ing1)
                .define('/', ing2)
                .unlockedBy(getHasName(ing1), has(ing1))
                .unlockedBy(getHasName(ing2), has(ing2))
                .save(recipeExporter);
    }

    private void registerCornerShelf(Consumer<FinishedRecipe> recipeExporter, Block output, Block ing1, Block ing2)
    {
        ShapedRecipeBuilder.shaped(output, 4)
                .pattern("#/")
                .pattern(" #")
                .define('#', ing1)
                .define('/', ing2)
                .unlockedBy(getHasName(ing1), has(ing1))
                .unlockedBy(getHasName(ing2), has(ing2))
                .save(recipeExporter);
    }

    private void registerTowerShelf(Consumer<FinishedRecipe> recipeExporter, Block output, Block ing1, Block ing2)
    {
        ShapedRecipeBuilder.shaped(output, 3)
                .pattern(" / ")
                .pattern("/#/")
                .pattern("/#/")
                .define('#', ing1)
                .define('/', ing2)
                .unlockedBy(getHasName(ing1), has(ing1))
                .unlockedBy(getHasName(ing2), has(ing2))
                .save(recipeExporter);
    }

    private void registerLadderShelf(Consumer<FinishedRecipe> recipeExporter, Block output, Block ing1, Block ing2)
    {
        ShapedRecipeBuilder.shaped(output, 2)
                .pattern("#/ ")
                .pattern("#/ ")
                .pattern(" #/")
                .define('#', ing1)
                .define('/', ing2)
                .unlockedBy(getHasName(ing1), has(ing1))
                .unlockedBy(getHasName(ing2), has(ing2))
                .save(recipeExporter);
    }

    private void registerCeilingShelf(Consumer<FinishedRecipe> recipeExporter, Block output, Block ing2)
    {
        ShapedRecipeBuilder.shaped(output, 4)
                .pattern("# #")
                .pattern("///")
                .define('#', Blocks.CHAIN)
                .define('/', ing2)
                .unlockedBy(getHasName(Blocks.CHAIN), has(Blocks.CHAIN))
                .unlockedBy(getHasName(ing2), has(ing2))
                .save(recipeExporter);
    }
}