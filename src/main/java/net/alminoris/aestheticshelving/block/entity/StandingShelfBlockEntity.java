package net.alminoris.aestheticshelving.block.entity;

import net.alminoris.aestheticshelving.menu.StandingShelfMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class StandingShelfBlockEntity extends BaseShelfBlockEntity
{

    public StandingShelfBlockEntity(BlockPos pos, BlockState state)
    {
        super(pos, state, ModBlockEntities.STANDING_SHELF_BLOCK_ENTITY.get(), 4);
    }

    public Dictionary<Integer, List<ItemStack>> getExtraRenderStack()
    {
        Dictionary<Integer, List<ItemStack>> result = new Hashtable<>();
        List<ItemStack> res1 = new ArrayList<>();
        List<ItemStack> res2 = new ArrayList<>();
        for(int i = 0; i < inventory.size(); i++)
        {
            if (i < 2)
            {
                if (!this.getItem(i).isEmpty())
                    res1.add(this.getItem(i));
            }
            else
            {
                if (!this.getItem(i).isEmpty())
                    res2.add(this.getItem(i));
            }
        }

        result.put(0, res1);
        result.put(1, res2);

        return result;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player)
    {
        return new StandingShelfMenu(containerId, playerInventory, this);
    }
}
