package net.alminoris.aestheticshelving.block.entity;

import net.alminoris.aestheticshelving.AestheticShelving;
import net.alminoris.aestheticshelving.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ModBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AestheticShelving.MOD_ID);

    public static final RegistryObject<BlockEntityType<ShelfBlockEntity>> SHELF_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("shelf_be",() ->
                    BlockEntityType.Builder.of(ShelfBlockEntity::new,
                            toBlockArray(ModBlocks.SHELVES.elements())).build(null));

    public static final RegistryObject<BlockEntityType<StandingShelfBlockEntity>> STANDING_SHELF_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("standing_shelf_be",() ->
                    BlockEntityType.Builder.of(StandingShelfBlockEntity::new,
                            toBlockArray(ModBlocks.STANDING_SHELVES.elements())).build(null));

    public static final RegistryObject<BlockEntityType<CeilingShelfBlockEntity>> CEILING_SHELF_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("ceiling_shelf_be",() ->
                    BlockEntityType.Builder.of(CeilingShelfBlockEntity::new,
                            toBlockArray(ModBlocks.CEILING_SHELVES.elements())).build(null));

    public static final RegistryObject<BlockEntityType<TowerShelfBlockEntity>> TOWER_SHELF_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("tower_shelf_be",() ->
                    BlockEntityType.Builder.of(TowerShelfBlockEntity::new,
                            toBlockArray(ModBlocks.TOWER_SHELVES.elements())).build(null));

    public static final RegistryObject<BlockEntityType<CornerShelfBlockEntity>> CORNER_SHELF_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("corner_shelf_be",() ->
                    BlockEntityType.Builder.of(CornerShelfBlockEntity::new,
                            toBlockArray(ModBlocks.CORNER_SHELVES.elements())).build(null));

    public static final RegistryObject<BlockEntityType<LadderShelfBlockEntity>> LADDER_SHELF_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("ladder_shelf_be",() ->
                    BlockEntityType.Builder.of(LadderShelfBlockEntity::new,
                            toBlockArray(ModBlocks.LADDER_SHELVES.elements())).build(null));

    public static void register(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }

    private static Block[] toBlockArray(Enumeration<RegistryObject<Block>>... enumerations)
    {
        List<Block> blocks = new ArrayList<>();
        for(var enumeration : enumerations)
        {
            while (enumeration.hasMoreElements())
            {
                blocks.add(enumeration.nextElement().get());
            }
        }

        return blocks.toArray(new Block[0]);
    }
}
