package net.alminoris.aestheticshelving.block.entity;

import net.alminoris.aestheticshelving.menu.CeilingShelfMenu;
import net.alminoris.aestheticshelving.menu.ShelfMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CeilingShelfBlockEntity extends BaseShelfBlockEntity
{

    public CeilingShelfBlockEntity(BlockPos pos, BlockState state)
    {
        super(pos, state, ModBlockEntities.CEILING_SHELF_BLOCK_ENTITY.get(), 3);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player)
    {
        return new CeilingShelfMenu(containerId, playerInventory, this);
    }
}
