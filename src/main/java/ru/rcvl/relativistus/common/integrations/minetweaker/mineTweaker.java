package ru.rcvl.relativistus.common.integrations.minetweaker;

import minetweaker.MineTweakerAPI;

public class mineTweaker {
    public static void registerTweaker(){
        MineTweakerAPI.registerClass(reactor.class);
    }
}
