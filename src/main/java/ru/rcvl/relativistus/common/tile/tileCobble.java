package ru.rcvl.relativistus.common.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class tileCobble extends TileEntity implements ISidedInventory {

    private ItemStack cobble;

    public void updateEntity(){
        if(cobble==null|| cobble.stackSize<64){
            cobble = new ItemStack(Blocks.cobblestone, 64);
        }
    }

    public void writeCustomNBT(NBTTagCompound nbt){
        if(this.cobble!=null){
            NBTTagCompound item = new NBTTagCompound();
            this.cobble.writeToNBT(item);
            nbt.setTag("cobble", item);
        }
        else nbt.removeTag("cobble");
    }

    public void readCustomNBT(NBTTagCompound nbt){
        this.cobble=ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("cobble"));
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        writeCustomNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        readCustomNBT(nbt);
    }
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeCustomNBT(nbt);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 812, nbt);
    }
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        super.onDataPacket(net, packet);
        readCustomNBT(packet.func_148857_g());
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int slot) {
        return new int[]{0};
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return false;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return true;
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        if(slot==0)return cobble;
        return null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int decrement) {
        if(slot==0){
            if(cobble==null)return null;
            else{
                if(decrement<cobble.stackSize){
                    ItemStack split = cobble.splitStack(decrement);
                    if(cobble.stackSize<=0)cobble=null;
                    return split;
                }
                else{
                    ItemStack split = cobble;
                    cobble=null;
                    return split;
                }
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
    }

    @Override
    public String getInventoryName() {
        return "relativemechanism.cobble";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return false;
    }
}
