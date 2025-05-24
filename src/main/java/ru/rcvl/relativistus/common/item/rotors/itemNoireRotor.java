package ru.rcvl.relativistus.common.item.rotors;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.util.List;

public class itemNoireRotor extends itemRotor {

    public itemNoireRotor(){
        setMaxStackSize(1);
        setUnlocalizedName("itemNoireRotor");
        setTextureName("relativistus:itemNoireRotor");
        setMaxDamage(5760);
        isDamageable();
        outKoef = 100;
    }

    public EnumRarity getRarity(ItemStack stack){return EnumRarity.uncommon;};
}