package ru.rcvl.relativistus.common.item.rods;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import ru.rcvl.relativistus.common.item.rotors.itemRelativeRotor;
import java.util.List;


public class itemRelativeRod extends itemRod {
    public itemRelativeRod(){
        setMaxStackSize(1);
        setUnlocalizedName("itemRelativeRod");
    }

    public EnumRarity getRarity(ItemStack stack){return EnumRarity.epic;}

    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean forTrue){
        tooltip.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tooltip.relativeRod"));
        tooltip.add(itemRelativeRotor.relativeText(StatCollector.translateToLocal("tooltip.relative0")) + " " + EnumChatFormatting.GRAY + StatCollector.translateToLocal("tooltip.relative1"));
    }

    @Override
    public int getKoef() {
        return 6;
    }
}