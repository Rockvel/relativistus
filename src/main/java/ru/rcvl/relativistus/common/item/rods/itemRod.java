package ru.rcvl.relativistus.common.item.rods;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.util.List;

public abstract class itemRod extends Item {

    protected int neutronKoef;

    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean forTrue){
        int meta = stack.getItemDamage();
        tooltip.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tooltip.rod0") + " " + neutronKoef);
        tooltip.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tooltip.rod1") + " " + (this.getMaxDamage() - meta)/60 + " " + StatCollector.translateToLocal("tooltip.hours") + " " + (this.getMaxDamage() - meta)%60 + " " + StatCollector.translateToLocal("tooltip.minutes"));
    }

    public int getKoef(){
        return neutronKoef;
    }
}