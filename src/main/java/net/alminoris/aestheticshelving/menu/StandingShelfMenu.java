package net.alminoris.aestheticshelving.menu;

import net.alminoris.aestheticshelving.block.entity.StandingShelfBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class StandingShelfMenu extends AbstractContainerMenu {

    private final StandingShelfBlockEntity blockEntity;
    private final ContainerLevelAccess access;

    public StandingShelfMenu(int containerId, Inventory playerInventory, StandingShelfBlockEntity blockEntity)
    {
        super(ModMenus.STANDING_SHELF_MENU.get(), containerId);
        this.blockEntity = blockEntity;
        this.access = ContainerLevelAccess.create(playerInventory.player.getLevel(), blockEntity.getBlockPos());

        this.addSlot(new OneItemSlot(blockEntity, 0, 71, 21));
        this.addSlot(new OneItemSlot(blockEntity, 1, 89, 21));
        this.addSlot(new OneItemSlot(blockEntity, 2, 71, 48));
        this.addSlot(new OneItemSlot(blockEntity, 3, 89, 48));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    // Client-side constructor (Forge syncs BlockPos automatically)
    public StandingShelfMenu(int containerId, Inventory playerInventory, FriendlyByteBuf friendlyByteBuf)
    {
        this(containerId, playerInventory, (StandingShelfBlockEntity) playerInventory.player.getLevel().getBlockEntity(friendlyByteBuf.readBlockPos()));
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
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}