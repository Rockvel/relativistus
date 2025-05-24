package ru.rcvl.relativistus.common.item.rotors;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.util.List;

public abstract class itemRotor extends Item {

    itemRotor(){
        isDamageable();
        setMaxStackSize(1);
    }

    protected int outKoef;

    public int getKoef(){
        return outKoef;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean forTrue){
        int meta = stack.getItemDamage();
        tooltip.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tooltip.rotor0") + " " + outKoef + "%");
        tooltip.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tooltip.rotor1") + " " + (this.getMaxDamage()-meta)/60 + " " + StatCollector.translateToLocal("tooltip.hours") + " " + (this.getMaxDamage()-meta)%60 + " " + StatCollector.translateToLocal("tooltip.minutes"));
    }
}
