package net.alminoris.aestheticshelving.menu;

import net.alminoris.aestheticshelving.block.entity.LadderShelfBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class LadderShelfMenu extends AbstractContainerMenu {

    private final LadderShelfBlockEntity blockEntity;
    private final ContainerLevelAccess access;

    public LadderShelfMenu(int containerId, Inventory playerInventory, LadderShelfBlockEntity blockEntity)
    {
        super(ModMenus.LADDER_SHELF_MENU.get(), containerId);
        this.blockEntity = blockEntity;
        this.access = ContainerLevelAccess.create(playerInventory.player.getLevel(), blockEntity.getBlockPos());

        int l = 0;
        for (int i = 0; i < 4; i++)
        {
            int base = (80+18*(4 - (i+1)));
            for (int j = 0; j < i+2; j++)
            {
                this.addSlot(new OneItemSlot(blockEntity, l++, base+18*j, 24+18*i));
            }
        }

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    // Client-side constructor (Forge syncs BlockPos automatically)
    public LadderShelfMenu(int containerId, Inventory playerInventory, FriendlyByteBuf friendlyByteBuf)
    {
        this(containerId, playerInventory, (LadderShelfBlockEntity) playerInventory.player.getLevel().getBlockEntity(friendlyByteBuf.readBlockPos()));
    }

    @Override
    public boolean stillValid(Player player) {
        return blockEntity != null && blockEntity.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack original = slot.getItem();
            newStack = original.copy();

            int containerSlots = this.blockEntity.getContainerSize();
            int totalSlots = this.slots.size();

            if (index < containerSlots) {
                if (!this.moveItemStackTo(original, containerSlots, totalSlots, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.moveItemStackTo(original, 0, containerSlots, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (original.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return newStack;
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 100 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 158));
        }
    }
}