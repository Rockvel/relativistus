package ru.rcvl.relativistus.common.item.rotors;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.util.List;

public class itemIronRotor extends itemRotor {
    public itemIronRotor(){
        setMaxStackSize(1);
        setUnlocalizedName("itemIronRotor");
        setTextureName("relativistus:itemIronRotor");
        setMaxDamage(720);
        isDamageable();
        outKoef = 85;
    }
}
