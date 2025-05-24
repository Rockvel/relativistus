package ru.rcvl.relativistus.common.item.rods;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class itemYoellRod extends itemRod {
    public itemYoellRod(){
        setMaxStackSize(1);
        setUnlocalizedName("itemYoellRod");
        setMaxDamage(900);
        isDamageable();
        neutronKoef = 4;
    }

    public EnumRarity getRarity(ItemStack stack){return EnumRarity.rare;};
}