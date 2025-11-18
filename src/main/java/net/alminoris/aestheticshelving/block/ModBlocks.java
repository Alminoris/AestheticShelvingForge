package net.alminoris.aestheticshelving.block;

import net.alminoris.aestheticshelving.AestheticShelving;
import net.alminoris.aestheticshelving.block.custom.*;
import net.alminoris.aestheticshelving.item.ModItemGroups;
import net.alminoris.aestheticshelving.item.ModItems;
import net.alminoris.aestheticshelving.util.helper.BlockSetsHelper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AestheticShelving.MOD_ID);

    public static final Dictionary<String, RegistryObject<Block>> SHELVES = new Hashtable<>()
    {{
        for(String name : BlockSetsHelper.getWoods())
        {
            put(name, registerBlock("shelf_"+name,
                    () -> new ShelfBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS))));
        }
    }};

    public static final Dictionary<String, RegistryObject<Block>> STANDING_SHELVES = new Hashtable<>()
    {{
        for(String name : BlockSetsHelper.getWoods())
        {
            put(name, registerBlock("standing_shelf_"+name,
                    () -> new StandingShelfBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS))));
        }
    }};

    public static final Dictionary<String, RegistryObject<Block>> CEILING_SHELVES = new Hashtable<>()
    {{
        for(String name : BlockSetsHelper.getWoods())
        {
            put(name, registerBlock("ceiling_shelf_"+name,
                    () -> new CeilingShelfBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS))));
        }
    }};

    public static final Dictionary<String, RegistryObject<Block>> CORNER_SHELVES = new Hashtable<>()
    {{
        for(String name : BlockSetsHelper.getWoods())
        {
            put(name, registerBlock("corner_shelf_"+name,
                    () -> new CornerShelfBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS))));
        }
    }};

    public static final Dictionary<String, RegistryObject<Block>> LADDER_SHELVES = new Hashtable<>()
    {{
        for(String name : BlockSetsHelper.getWoods())
        {
            put(name, registerBlock("ladder_shelf_"+name,
                    () -> new LadderShelfBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS))));
        }
    }};

    public static final Dictionary<String, RegistryObject<Block>> TOWER_SHELVES = new Hashtable<>()
    {{
        for(String name : BlockSetsHelper.getWoods())
        {
            put(name, registerBlock("tower_shelf_"+name,
                    () -> new TowerShelfBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS))));
        }
    }};


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModItemGroups.ASHELF_TAB)));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}