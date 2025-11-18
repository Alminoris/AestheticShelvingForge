package net.alminoris.aestheticshelving.datagen.loot;

import net.alminoris.aestheticshelving.block.ModBlocks;
import net.alminoris.aestheticshelving.util.helper.BlockSetsHelper;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLoot
{
    @Override
    protected void addTables()
    {
        for(String name : BlockSetsHelper.getWoods())
        {
            dropSelf(ModBlocks.SHELVES.get(name).get());
            dropSelf(ModBlocks.CEILING_SHELVES.get(name).get());
            dropSelf(ModBlocks.STANDING_SHELVES.get(name).get());
            dropSelf(ModBlocks.CORNER_SHELVES.get(name).get());
            dropSelf(ModBlocks.TOWER_SHELVES.get(name).get());
            dropSelf(ModBlocks.LADDER_SHELVES.get(name).get());
        }
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}