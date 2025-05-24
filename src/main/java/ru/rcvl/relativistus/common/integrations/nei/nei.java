package ru.rcvl.relativistus.common.integrations.nei;
import codechicken.nei.api.API;

public class nei {
    public static void register(){
        reactorNEI reactorNEI = new reactorNEI();
        API.registerRecipeHandler(reactorNEI);
        API.registerUsageHandler(reactorNEI);
    }
}
