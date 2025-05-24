package ru.rcvl.relativistus.common.recipe;

import net.minecraft.item.ItemStack;

public class reactorRecipe {
    private ItemStack inputBlock;
    private ItemStack inputIngot;
    private ItemStack inputNugget;
    private ItemStack[] output;
    private int inputChance;
    private int[] outputChance;
    private int duration;
    private double koef;
    private int blockKoef;
    private int ingotKoef;
    private int energy;
    public reactorRecipe(ItemStack InputBlock, int BlockKoef, ItemStack InputIngot, int IngotKoef, ItemStack InputNugget, int InputChance, ItemStack[] Output, int[] OutputChance,int Energy,  int Duration, double Koef){
        inputBlock=InputBlock;
        inputIngot=InputIngot;
        inputNugget=InputNugget;
        blockKoef=BlockKoef;
        ingotKoef=IngotKoef;
        inputChance=InputChance;
        outputChance=OutputChance;
        output=Output;
        duration=Duration;
        koef=Koef;
        energy=Energy;
    }
    public ItemStack getInputBlock(){
        return inputBlock;
    }
    public ItemStack getInputIngot(){
        return inputIngot;
    }
    public ItemStack getInputNugget(){
        return inputNugget;
    }
    public ItemStack[] getOutput(){
        return output;
    }
    public int[] getOuputChanse(){return outputChance;}
    public int getInputChance(){return inputChance;}
    public double getKoef(){
        return koef;
    }
    public int getDuration(){
        return duration;
    }
    public int getBlockKoef() {
        return blockKoef;
    }
    public int getIngotKoef(){
        return ingotKoef;
    }
    public int getEnergy(){return energy;}
    public boolean isInputAvailable(ItemStack Input){
        return Input.isItemEqual(inputNugget);
    }
    public boolean isIngotAvailable(ItemStack Ingot){
        if(inputIngot!=null)return Ingot.isItemEqual(inputIngot);
        else return false;
    }
    public boolean isBlockAvailable(ItemStack Block){
        if(inputBlock!=null)return Block.isItemEqual(inputBlock);
        else return false;
    }
}