package ru.rcvl.relativistus.common.tile;
import ic2.api.energy.tile.IEnergySource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import ru.rcvl.relativistus.common.item.rods.itemRod;
import ru.rcvl.relativistus.common.recipe.reactorManager;
import ru.rcvl.relativistus.common.recipe.reactorRecipe;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;

public class tileReactor extends TileEntity implements ISidedInventory, IEnergySource {
    private ItemStack[] input = new ItemStack[6];
    private ItemStack[] output = new ItemStack[6];
    private ItemStack rod;
    private ItemStack rotor;
    private boolean isFormed = false;
    private int mainX;
    private int mainY;
    private int mainZ;
    private double koef;
    private ItemStack rb, ri, rn;
    private ItemStack[] ro = new ItemStack[3];
    private int re, rd, ic;
    private int[] oc = new int[3];
    private double rk;
    int depth=0;
    int state;
    int tick, progress;

    @Override
    public boolean canUpdate() {
        return isFormed && state!=0;
    }

    @Override
    public void updateEntity() {
        if(state==1){
            if(progress++>200){
                state=2;
                progress=0;
            }
        }
        if(state==2){
            getInput();
            if(progress==0){
                if(input[4]!=null && input[4].isItemEqual(rn)) {
                    if(input[4].stackSize>1)input[4].stackSize--;
                    else input[4]=null;
                }
                else if(input[5]!=null && input[5].isItemEqual(rn)) {
                    if(input[5].stackSize>1)input[5].stackSize--;
                    else input[5]=null;
                }
                else state = 3;
            }
            if(progress++>rd){
                getOutput();
                progress=0;
            }
            if(tick++>1200){
                damageComps();
                tick=0;
            }
        }
        if(state==3){
            if(progress++>200){state=0;
            progress=0;}
        }
    }

    void getOutput(){
        for(int i = 0; i<3;i++){
            if(ro[i]==null)break;
            else{
                Random r = new Random();
                if(r.nextInt(101)<oc[i]){
                    for(int j = 0; j<6;j++){
                        if(output[j]!=null && output[j].isItemEqual(ro[i]) && output[j].stackSize+ro[i].stackSize<=output[j].getMaxStackSize()){
                            output[j].stackSize+=ro[i].stackSize;
                            break;
                        }
                        if(output[j]==null){
                            output[j]=ro[i].copy();
                            break;
                        }
                    }
                }
            }
        }
    }

    void damageComps(){
        if(rotor!=null && rotor.getItemDamage()!=rotor.getMaxDamage())rotor.setItemDamage(rotor.getItemDamage()+1);
        else rotor=null;
        if(rod!=null && rod.getItemDamage()!=rod.getMaxDamage())rod.setItemDamage(rod.getItemDamage()+1);
        else rod=null;
    }

    public void writeCustomNBT(NBTTagCompound nbt) {
        for(int i =0; i<6; i++){
          if(this.input[i]!=null){
              NBTTagCompound item = new NBTTagCompound();
              this.input[i].writeToNBT(item);
              nbt.setTag("input"+i, item);
          }
          else nbt.removeTag("input"+i);
          if(this.output[i]!=null){
              NBTTagCompound item = new NBTTagCompound();
              this.output[i].writeToNBT(item);
              nbt.setTag("output"+i, item);
          }
          else nbt.removeTag("output"+i);
        }
        if(rotor!=null){
            NBTTagCompound item = new NBTTagCompound();
            rotor.writeToNBT(item);
            nbt.setTag("rotor", item);}
        else nbt.removeTag("rotor");
        if(rod!=null){
            NBTTagCompound item = new NBTTagCompound();
            rod.writeToNBT(item);
            nbt.setTag("rod", item);}
        else nbt.removeTag("rod");
        if(rb!=null){
            NBTTagCompound item = new NBTTagCompound();
            rb.writeToNBT(item);
            nbt.setTag("rb", item);}
        else nbt.removeTag("rb");
        if(ri!=null){
            NBTTagCompound item = new NBTTagCompound();
            ri.writeToNBT(item);
            nbt.setTag("ri", item);}
        else nbt.removeTag("ri");
        if(rn!=null){
            NBTTagCompound item = new NBTTagCompound();
            rn.writeToNBT(item);
            nbt.setTag("rn", item);}
        else nbt.removeTag("rn");
        for(int i = 0; i<3; i++){
            nbt.setInteger("oc"+i, oc[i]);
            if(ro[i]!=null){
                NBTTagCompound item = new NBTTagCompound();
                ro[i].writeToNBT(item);
                nbt.setTag("ro" + i, item);}
            else nbt.removeTag("ro" + i);
        }
        nbt.setInteger("mainX", mainX);
        nbt.setInteger("mainY", mainY);
        nbt.setInteger("mainZ", mainZ);
        nbt.setBoolean("isFormed", isFormed);
        nbt.setInteger("re", re);
        nbt.setInteger("rd", rd);
        nbt.setInteger("ic", ic);
        nbt.setDouble("koef", koef);
        nbt.setDouble("rk", rk);
        nbt.setInteger("depth", depth);
        nbt.setInteger("state", state);
        nbt.setInteger("tick", tick);
        nbt.setInteger("progress", progress);
    }

    public void readCustomNBT(NBTTagCompound nbt) {
        for (int i = 0; i < 6; i++) {
            this.input[i] = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("input" + i));
            this.output[i] = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("output" + i));
        }
        this.rotor=ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("rotor"));
        this.rod=ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("rod"));
        rb=ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("rb"));
        ri=ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("ri"));
        rn=ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("rn"));
        for(int i = 0; i<3; i++){
            ro[i]=ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("ro"+i));
            oc[i]=nbt.getInteger("oc"+i);
        }
        rd=nbt.getInteger("rd");
        re=nbt.getInteger("re");
        ic=nbt.getInteger("ic");
        rk=nbt.getDouble("rk");
        koef=nbt.getDouble("koef");
        mainX=nbt.getInteger("mainX");
        mainY=nbt.getInteger("mainY");
        mainZ=nbt.getInteger("mainZ");
        isFormed=nbt.getBoolean("isFormed");
        depth=nbt.getInteger("depth");
        state=nbt.getInteger("state");
        tick=nbt.getInteger("tick");
        progress=nbt.getInteger("progress");
    }

    @Override
    public int getSizeInventory() {return 14;}

    @Override
    public ItemStack getStackInSlot(int slot) {
        for(int i =0; i<6; i++){
            if(slot==i)return input[i];
            if(slot==i+6)return output[i];
        }
        if (slot==12)return rotor;
        if (slot==13)return rod;
        return null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int decrement){
        for (int i=0; i<6; i++){
            if(slot==i){
                if(input[i]==null)return null;
                else{
                    if(decrement<input[i].stackSize){
                        ItemStack split = input[i].splitStack(decrement);
                        if(input[i].stackSize<=0)input[i]=null;
                        return split;
                    }
                    else{
                        ItemStack split = input[i];
                        input[i]=null;
                        return split;
                    }
                }
            }
            if(slot==i+6){if(output[i]==null)return null;
            else{
                if(decrement<output[i].stackSize){
                    ItemStack split = output[i].splitStack(decrement);
                    if(output[i].stackSize<=0)output[i]=null;
                    return split;
                }
                else{
                    ItemStack split = output[i];
                    output[i]=null;
                    return split;
                }
            }}
        }
        if(slot==12){if(rotor==null)return null;
        else{
            if(decrement<rotor.stackSize){
                ItemStack split = rotor.splitStack(decrement);
                if(rotor.stackSize<=0)rotor=null;
                return split;
            }
            else{
                ItemStack split = rotor;
                rotor=null;
                return split;
            }
        }}
        if(slot==13){if(rod==null)return null;
        else{
            if(decrement<rod.stackSize){
                ItemStack split = rod.splitStack(decrement);
                if(rod.stackSize<=0)rod=null;
                setStab();
                return split;
            }
            else{
                ItemStack split = rod;
                rod=null;
                return split;
            }
        }}
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        for(int i =0; i<6; i++){
            if(slot==i)input[i]=stack;
            if(slot==i+6)output[i]=stack;
        }
        if(slot==12)rotor=stack;
        if(slot==13)rod=stack;
    }

    @Override
    public String getInventoryName() {
        return "relativemechanism.reactor";
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
        return true;
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
    public int[] getAccessibleSlotsFromSide(int side) {
        return null;
    }
    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return true;
    }
    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return true;
    }

    public ArrayList<reactorRecipe> getValid(){
        ArrayList<reactorRecipe> recipes = new ArrayList<reactorRecipe>();
        if(input[4]!=null)recipes.addAll(reactorManager.getRecipeFromNugget(input[4]));
        if(input[5]!=null)recipes.addAll(reactorManager.getRecipeFromNugget(input[5]));
        if(input[4]==null&&input[5]==null){
            if(input[2]!=null)recipes.addAll(reactorManager.getRecipeFromIngot(input[2]));
            if(input[3]!=null)recipes.addAll(reactorManager.getRecipeFromIngot(input[3]));
            if(input[2]==null&&input[3]==null){
                if(input[0]!=null)recipes.addAll(reactorManager.getRecipeFromIngot(input[2]));
                if(input[1]!=null)recipes.addAll(reactorManager.getRecipeFromIngot(input[3]));
            }
        }
        LinkedHashSet<reactorRecipe> r = new LinkedHashSet<reactorRecipe>(recipes);
        recipes = new ArrayList<reactorRecipe>(r);
        return  recipes;
    }

    public void start(int i){
        reactorRecipe recipe = getValid().get(i);
        if(rb!=null)rb=recipe.getInputBlock().copy();
        if(ri!=null)ri=recipe.getInputIngot().copy();
        rn=recipe.getInputNugget().copy();
        if(recipe.getOutput()!=null){
            ro[0]=null;
            ro[1]=null;
            ro[2]=null;
            for(int j = 0; j<recipe.getOutput().length; j++){
                ro[j]=recipe.getOutput()[j].copy();
                oc[j]=recipe.getOuputChanse()[j];
            }
        }
        ic=recipe.getInputChance();
        rd=recipe.getDuration();
        re=recipe.getEnergy();
        rk=recipe.getKoef();
        state=1;
        canUpdate();
    }

    public void getInput(){
        if((input[5]==null||!input[5].isItemEqual(rn))&&(input[4]==null||!input[4].isItemEqual(rn))){
            if(input[2]!=null && input[2].isItemEqual(ri)) {
                if(input[4]==null){
                    input[2].stackSize--;
                    input[4]=rn.copy();
                    return;
                }
                else if(input[5]==null){
                    input[2].stackSize--;
                    input[5]=rn.copy();
                    return;
                }
            }
            if(input[3]!=null && input[3].isItemEqual(ri)) {
                if(input[4]==null){
                    input[3].stackSize--;
                    input[4]=rn.copy();
                    return;
                }
                else if(input[5]==null){
                    input[3].stackSize--;
                    input[5]=rn.copy();
                    return;
                }
            }
            if(input[0]!=null && input[0].isItemEqual(rb)) {
                if(input[2]==null){
                    input[0].stackSize--;
                    input[2]=ri.copy();
                    getInput();
                    return;
                }
                else if(input[3]==null){
                    input[0].stackSize--;
                    input[3]=ri.copy();
                    getInput();
                    return;
                }
            }
            if(input[1]!=null && input[1].isItemEqual(rb)) {
                if(input[2]==null){
                    input[1].stackSize--;
                    input[2]=ri.copy();
                    getInput();
                    return;
                }
                else if(input[3]==null){
                    input[1].stackSize--;
                    input[3]=ri.copy();
                    getInput();
                    return;
                }
            }
            state=3;
        }
    }

    public void setToFormed(int x, int y, int z){
        mainX = x;
        mainY = y;
        mainZ = z;
        isFormed = true;
    }

    public boolean isMain(){
        return xCoord==mainX && yCoord==mainY && zCoord==mainZ;
    }

    public int getMainX() {
        return mainX;
    }

    public int getMainY() {
        return mainY;
    }

    public int getMainZ() {
        return mainZ;
    }

    public int getState(){
        return state;
    }

    public boolean isFormed(){return isFormed;}

    @Override
    public double getOfferedEnergy() {
        return 0;
    }

    @Override
    public void drawEnergy(double v) {

    }

    @Override
    public int getSourceTier() {
        return 14;
    }

    public void addDepth(int d){
        System.out.println("Try " + depth + ' ' + d);
        if(depth+d<=100)depth+=d;
        setStab();
    }

    public void removeDepth(int d){
        if(depth-d>=0)depth-=d;
        setStab();
    }

    void setStab(){
        int k;
        if(rod!=null)k=((itemRod)rod.getItem()).getKoef();
        else k = 0;
        if(k==6)koef=1;
        else koef = Math.max(rk- (double) (k * depth) /100, 0);
    }

    public double getStab(){
        return koef;
    }

    @Override
    public boolean emitsEnergyTo(TileEntity tileEntity, ForgeDirection forgeDirection) {
        return false;
    }
}