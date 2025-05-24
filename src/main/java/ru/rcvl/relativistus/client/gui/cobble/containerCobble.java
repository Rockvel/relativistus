package ru.rcvl.relativistus.client.gui.cobble;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import ru.rcvl.relativistus.common.tile.tileCobble;

public class containerCobble extends Container {

    private final tileCobble cobble;

    public containerCobble(InventoryPlayer player, tileCobble machine){
        this.cobble=machine;
        int k = 0;
        for (int i = 0; i < 9; i++){
            this.addSlotToContainer(new Slot(player, k, 8 + 18 * i, 124));
            k++;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++){
                this.addSlotToContainer(new Slot(player, k, 8 + 18 * j, 66 + 18 * i));
                k++;
            }
        }
        this.addSlotToContainer(new SlotFurnace(player.player, cobble, 0, 80, 25));
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.cobble.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        return null;
    }
}
