package net.alminoris.aestheticshelving.menu;


import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

public class OneItemSlot extends Slot
{
    public OneItemSlot(Container p_40223_, int p_40224_, int p_40225_, int p_40226_) {
        super(p_40223_, p_40224_, p_40225_, p_40226_);
    }

    @Override
    public int getMaxStackSize()
    {
        return 1;
    }
}

