package net.alminoris.aestheticshelving.block.entity;

import net.alminoris.aestheticshelving.menu.LadderShelfMenu;
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

public class LadderShelfBlockEntity extends BaseShelfBlockEntity
{
    public LadderShelfBlockEntity(BlockPos pos, BlockState state)
    {
        super(pos, state, ModBlockEntities.LADDER_SHELF_BLOCK_ENTITY.get(), 14);
    }

    public TreeMap<Integer, List<ItemStack>> getRenderTreeMapStack()
    {
        TreeMap<Integer, List<ItemStack>> result = new TreeMap<>();

        List<ItemStack> res1 = new ArrayList<>();
        List<ItemStack> res2 = new ArrayList<>();
        List<ItemStack> res3 = new ArrayList<>();
        List<ItemStack> res4 = new ArrayList<>();

        for (int i = 0; i < inventory.size(); i++)
        {
            ItemStack stack = this.getItem(i);
            if (stack.isEmpty()) continue;

            if (i < 2) res1.add(stack);
            else if (i < 5) res2.add(stack);
            else if (i < 9) res3.add(stack);
            else res4.add(stack);
        }

        result.put(0, res1);
        result.put(1, res2);
        result.put(2, res3);
        result.put(3, res4);

        return result;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player)
    {
        return new LadderShelfMenu(containerId, playerInventory, this);
    }
}