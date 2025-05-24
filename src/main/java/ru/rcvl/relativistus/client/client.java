package ru.rcvl.relativistus.client;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import ru.rcvl.relativistus.common.common;
import ru.rcvl.relativistus.common.handler.register;

public class client extends common{
    @Override public void preInit(FMLPreInitializationEvent event) {super.preInit(event);
    }

    @Override public void Init(FMLInitializationEvent event) { super.Init(event);
        register.registerRender();
    }

    @Override public void postInit(FMLPostInitializationEvent event) {super.postInit(event);
    }
}
