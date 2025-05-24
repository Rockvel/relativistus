package ru.rcvl.relativistus.common.item.rotors;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.util.List;
public class itemRelativeRotor extends itemRotor {

    public itemRelativeRotor(){
        setMaxStackSize(1);
        setUnlocalizedName("itemRelativeRotor");
        setTextureName("relativistus:itemRelativeRotor");
        outKoef=125;
    }

    public EnumRarity getRarity(ItemStack stack){return EnumRarity.epic;};
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean forTrue){
        tooltip.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tooltip.rotor0") + " " + outKoef + "%");
        tooltip.add(relativeText(StatCollector.translateToLocal("tooltip.relative0")) + EnumChatFormatting.GRAY + " " + StatCollector.translateToLocal("tooltip.relative1"));
    }

    public static final EnumChatFormatting[] colors = {EnumChatFormatting.LIGHT_PURPLE, EnumChatFormatting.BLUE, EnumChatFormatting.AQUA, EnumChatFormatting.GREEN, EnumChatFormatting.YELLOW, EnumChatFormatting.GOLD, EnumChatFormatting.RED};

    @SideOnly(Side.CLIENT)
    public static String relativeText(String text){
        StringBuilder relativatedText = new StringBuilder();
        for (int i = 0; i<text.length(); i++){
            char letter = text.charAt(i);
            int pos = (int) ((i+Minecraft.getSystemTime()/60)%colors.length);
            relativatedText.append(colors[pos]);
            relativatedText.append(letter);
        }
        return relativatedText.toString();
    }
}