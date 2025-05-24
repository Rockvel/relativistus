package ru.rcvl.relativistus.common.item.rods;

public class itemCobaltRod extends itemRod {
    public itemCobaltRod(){
        setMaxStackSize(1);
        setUnlocalizedName("itemCobaltRod");
        setMaxDamage(360);
        isDamageable();
        neutronKoef = 2;
    }
}