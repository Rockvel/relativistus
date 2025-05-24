package ru.rcvl.relativistus.common.item.rotors;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.util.List;

public class itemCarbonRotor extends itemRotor{

    public itemCarbonRotor(){
        setMaxStackSize(1);
        setUnlocalizedName("itemCarbonRotor");
        setTextureName("relativistus:itemCarbonRotor");
        setMaxDamage(1080);
        isDamageable();
        outKoef = 100;
    }
}