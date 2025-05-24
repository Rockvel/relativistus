package ru.rcvl.relativistus.common.item.rods;

public class itemBoronSteelRod extends itemRod {

    public itemBoronSteelRod(){
        setMaxStackSize(1);
        setUnlocalizedName("itemBoronSteelRod");
        setMaxDamage(300);
        isDamageable();
        neutronKoef = 1;
    }
}
