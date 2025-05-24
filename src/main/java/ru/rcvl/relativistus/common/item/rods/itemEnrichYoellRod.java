package ru.rcvl.relativistus.common.item.rods;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class itemEnrichYoellRod extends itemRod {
    public itemEnrichYoellRod(){
        setMaxStackSize(1);
        setUnlocalizedName("itemEnrichYoellRod");
        setMaxDamage(1440);
        isDamageable();
        neutronKoef = 5;
    }
    public EnumRarity getRarity(ItemStack stack){return EnumRarity.rare;};
}