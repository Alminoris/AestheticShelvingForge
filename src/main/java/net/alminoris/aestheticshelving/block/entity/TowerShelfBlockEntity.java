package net.alminoris.aestheticshelving.block.entity;

import net.alminoris.aestheticshelving.menu.TowerShelfMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class TowerShelfBlockEntity extends BaseShelfBlockEntity
{

    public TowerShelfBlockEntity(BlockPos pos, BlockState state)
    {
        super(pos, state, ModBlockEntities.TOWER_SHELF_BLOCK_ENTITY.get(), 12);
    }

    public TreeMap<Integer, List<ItemStack>> getRenderTreeMapStack()
    {
        java.util.TreeMap<Integer, List<ItemStack>> result = new TreeMap<>();

        List<ItemStack> res1 = new ArrayList<>();
        List<ItemStack> res2 = new ArrayList<>();
        List<ItemStack> res3 = new ArrayList<>();

        for (int i = 0; i < inventory.size(); i++)
        {
            ItemStack stack = this.getItem(i);
            if (stack.isEmpty()) continue;

            if (i < 4) res1.add(stack);
            else if (i < 8) res2.add(stack);
            else res3.add(stack);
        }

        result.put(0, res1);
        result.put(1, res2);
        result.put(2, res3);

        return result;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player)
    {
        return new TowerShelfMenu(containerId, playerInventory, this);
    }
}
