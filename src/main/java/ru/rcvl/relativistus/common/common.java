package ru.rcvl.relativistus.common;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import ru.rcvl.relativistus.Relativistus;
import ru.rcvl.relativistus.common.handler.register;
import ru.rcvl.relativistus.common.recipe.recipes;
import ru.rcvl.relativistus.client.gui.reactorClientPacket;

public class common {
    public void preInit(FMLPreInitializationEvent event) {
        register.registration();
        Relativistus.network.registerMessage(new reactorClientPacket(), reactorClientPacket.class, 0, Side.SERVER);
    }

    public void Init(FMLInitializationEvent event) {
        recipes.recipesRegistration();
    }

    public void postInit(FMLPostInitializationEvent event) {
    }
}