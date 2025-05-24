package ru.rcvl.relativistus.common.item.rotors;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.util.List;

public class itemEnrichYoellRotor extends itemRotor{

    public itemEnrichYoellRotor(){
        setMaxStackSize(1);
        setUnlocalizedName("itemEnrichYoellRotor");
        setTextureName("relativistus:itemEnrichYoellRotor");
        setMaxDamage(8640);
        isDamageable();
        outKoef=110;
    }
    public EnumRarity getRarity(ItemStack stack){return EnumRarity.rare;};
}