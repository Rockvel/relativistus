package ru.rcvl.relativistus.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import ru.rcvl.relativistus.common.tile.tileReactor;
import ru.rcvl.relativistus.common.item.rods.itemRod;
import ru.rcvl.relativistus.common.item.rotors.itemRotor;

public class containerReactor extends Container {
    private final tileReactor reactor;

    public containerReactor(InventoryPlayer player, tileReactor machine) {
        this.reactor = machine;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 3; j++){
                this.addSlotToContainer(new Slot(reactor, i+j*2, 48 + j*18, 30+i*18));
                this.addSlotToContainer(new SlotFurnace(player.player, machine, j+3*i+6, 48 + j*18, 98+i*18));
            }
        }
        this.addSlotToContainer(new rotorSlot(reactor, 12, 224, 29));
        this.addSlotToContainer(new rodSlot(reactor,13, 242, 29));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(player, j + 9*i + 9, 48 + 18 * j, 166 + 18 * i));
            }
        }
        for (int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(player, i, 48 + 18 * i, 224));
        }
    }

    public boolean canInteractWith(EntityPlayer player) {
        return this.reactor.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        return null;
    }

    static class rotorSlot extends Slot{
        public rotorSlot(IInventory inv, int id, int x, int y) {
            super(inv, id, x, y);
        }

        public boolean isItemValid(ItemStack stack) {
            return stack.getItem() instanceof itemRotor;
        }
    }

    static class rodSlot extends Slot{
        public rodSlot(IInventory inv, int id, int x, int y) {
            super(inv, id, x, y);
        }

        public boolean isItemValid(ItemStack stack) {
            return stack.getItem() instanceof itemRod;
        }
    }
}