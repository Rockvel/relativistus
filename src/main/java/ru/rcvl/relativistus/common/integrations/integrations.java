package ru.rcvl.relativistus.common.integrations;

import cpw.mods.fml.common.Loader;

public class integrations {

    public static boolean nei = false;
    public static boolean mineTweaker = false;
    public static boolean ic2 = false;

    public static void modScanner(){
        nei = Loader.isModLoaded("NotEnoughItems");
        mineTweaker = Loader.isModLoaded("MineTweaker3");
    }

    public static void integrate(){
        if(nei){
            ru.rcvl.relativistus.common.integrations.nei.nei.register();
        }
        if(mineTweaker){
            ru.rcvl.relativistus.common.integrations.minetweaker.mineTweaker.registerTweaker();
        }
    }
}
