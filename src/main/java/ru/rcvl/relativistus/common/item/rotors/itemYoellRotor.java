package ru.rcvl.relativistus.common.item.rotors;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.util.List;

public class itemYoellRotor extends itemRotor {

    public itemYoellRotor(){
        setMaxStackSize(1);
        setUnlocalizedName("itemYoellRotor");
        setTextureName("relativistus:itemYoellRotor");
        setMaxDamage(1440);
        isDamageable();
        outKoef = 110;
    }
    public EnumRarity getRarity(ItemStack stack){return EnumRarity.rare;};
}