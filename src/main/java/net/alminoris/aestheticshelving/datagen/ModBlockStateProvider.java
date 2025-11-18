package net.alminoris.aestheticshelving.datagen;

import net.alminoris.aestheticshelving.AestheticShelving;
import net.alminoris.aestheticshelving.block.ModBlocks;
import net.alminoris.aestheticshelving.util.helper.BlockSetsHelper;
import net.alminoris.aestheticshelving.util.helper.ModJsonHelper;
import net.alminoris.aestheticshelving.util.helper.ModJsonTemplates;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;

public class ModBlockStateProvider extends BlockStateProvider
{
    public ModBlockStateProvider(DataGenerator output, ExistingFileHelper exFileHelper)
    {
        super(output, AestheticShelving.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        for(String name : BlockSetsHelper.getWoods())
        {
            if (!Arrays.asList(BlockSetsHelper.WOODS).contains(name))
            {
                registerShelf(ModBlocks.SHELVES.get(name),"aestheticshelving:block/",
                        "shelf_"+name, "stripped_"+name+"_log", name+"_log");
                registerStandingShelf(ModBlocks.STANDING_SHELVES.get(name),"aestheticshelving:block/",
                        "standing_shelf_"+name, "stripped_"+name+"_log", name+"_log");
                registerCornerShelf(ModBlocks.CORNER_SHELVES.get(name),"aestheticshelving:block/",
                        "corner_shelf_"+name, "stripped_"+name+"_log", name+"_log");
                registerTowerShelf(ModBlocks.TOWER_SHELVES.get(name),"aestheticshelving:block/",
                        "tower_shelf_"+name, "stripped_"+name+"_log", name+"_log");
                registerLadderShelf(ModBlocks.LADDER_SHELVES.get(name),"aestheticshelving:block/",
                        "ladder_shelf_"+name, "stripped_"+name+"_log", name+"_log");
                registerCeilingShelf(ModBlocks.CEILING_SHELVES.get(name),"aestheticshelving:block/",
                        "ceiling_shelf_"+name, "stripped_"+name+"_log");
            }
            else
            {
                String logName = (name.equals("crimson") || name.equals("warped")) ? "stem" : (name.equals("bamboo") ? "block" : "log");
                registerShelf(ModBlocks.SHELVES.get(name),"minecraft:block/",
                        "shelf_"+name, "stripped_"+name+"_"+logName, name+"_"+logName);
                registerStandingShelf(ModBlocks.STANDING_SHELVES.get(name),"minecraft:block/",
                        "standing_shelf_"+name, "stripped_"+name+"_"+logName, name+"_"+logName);
                registerCornerShelf(ModBlocks.CORNER_SHELVES.get(name),"minecraft:block/",
                        "corner_shelf_"+name, "stripped_"+name+"_"+logName, name+"_"+logName);
                registerTowerShelf(ModBlocks.TOWER_SHELVES.get(name),"minecraft:block/",
                        "tower_shelf_"+name, "stripped_"+name+"_"+logName, name+"_"+logName);
                registerLadderShelf(ModBlocks.LADDER_SHELVES.get(name),"minecraft:block/",
                        "ladder_shelf_"+name, "stripped_"+name+"_"+logName, name+"_"+logName);
                registerCeilingShelf(ModBlocks.CEILING_SHELVES.get(name),"minecraft:block/",
                        "ceiling_shelf_"+name, "stripped_"+name+"_"+logName);
            }
        }
    }

    public void registerShelf(RegistryObject<Block> block, String modId, String name, String baseName, String legName)
    {
        ModJsonHelper.registerShelfBlockModel(ModJsonTemplates.SHELF, name, modId+baseName, modId+legName, "normal");
        ModJsonHelper.registerShelfBlockModel(ModJsonTemplates.SHELF_CENTER, name, modId+baseName, modId+legName, "center");
        ModJsonHelper.registerShelfBlockModel(ModJsonTemplates.SHELF_LEFT, name, modId+baseName, modId+legName, "left");
        ModJsonHelper.registerShelfBlockModel(ModJsonTemplates.SHELF_RIGHT, name, modId+baseName, modId+legName, "right");
        ModJsonHelper.createBlockstate(ModJsonTemplates.SHELF_BLOCKSTATE, name);
        blockItem(block, "block/"+ name);
    }

    public void registerStandingShelf(RegistryObject<Block> block, String modId, String name, String baseName, String legName)
    {
        ModJsonHelper.registerStandingShelfBlockModel(ModJsonTemplates.STANDING_SHELF, name, modId+baseName, modId+legName);
        ModJsonHelper.createBlockstate(ModJsonTemplates.BLOCKSTATE, name);
        blockItem(block, "block/"+ name);
    }

    public void registerCornerShelf(RegistryObject<Block> block, String modId, String name, String baseName, String legName)
    {
        ModJsonHelper.registerStandingShelfBlockModel(ModJsonTemplates.CORNER_SHELF, name, modId+baseName, modId+legName);
        ModJsonHelper.createBlockstate(ModJsonTemplates.BLOCKSTATE, name);
        blockItem(block, "block/"+ name);
    }

    public void registerTowerShelf(RegistryObject<Block> block, String modId, String name, String baseName, String legName)
    {
        ModJsonHelper.registerStandingShelfBlockModel(ModJsonTemplates.TOWER_SHELF, name, modId+baseName, modId+legName);
        ModJsonHelper.createBlockstate(ModJsonTemplates.BLOCKSTATE, name);
        blockItem(block, "block/"+ name);
    }

    public void registerLadderShelf(RegistryObject<Block> block, String modId, String name, String baseName, String legName)
    {
        ModJsonHelper.registerStandingShelfBlockModel(ModJsonTemplates.LADDER_SHELF, name, modId+baseName, modId+legName);
        ModJsonHelper.createBlockstate(ModJsonTemplates.BLOCKSTATE, name);
        blockItem(block, "block/"+ name);
    }

    public void registerCeilingShelf(RegistryObject<Block> block, String modId, String name, String baseName)
    {
        ModJsonHelper.registerCeilingShelfBlockModel(ModJsonTemplates.CEILING_SHELF, name, modId+baseName);
        ModJsonHelper.createBlockstate(ModJsonTemplates.BLOCKSTATE, name);
        blockItem(block, "block/"+ name);
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject, String name)
    {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("aestheticshelving:" + name));
    }
}