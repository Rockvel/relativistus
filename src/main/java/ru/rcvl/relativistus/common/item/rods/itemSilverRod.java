package ru.rcvl.relativistus.common.item.rods;

public class itemSilverRod extends itemRod {
    public itemSilverRod(){
        setMaxStackSize(1);
        setUnlocalizedName("itemSilverRod");
        setMaxDamage(720);
        isDamageable();
        neutronKoef = 3;
    }
}