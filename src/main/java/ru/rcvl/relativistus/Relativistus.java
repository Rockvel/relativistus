package ru.rcvl.relativistus;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import ru.rcvl.relativistus.client.gui.getGUI;
import ru.rcvl.relativistus.common.common;
import ru.rcvl.relativistus.common.integrations.integrations;

@Mod(modid = "Relativistus", dependencies = "required-after:IC2;")
public class Relativistus {
    @Instance
    public static Relativistus instance;

    public static SimpleNetworkWrapper network = new SimpleNetworkWrapper("rcvl");

    @SidedProxy (
            clientSide = "ru.rcvl.relativistus.client.client",
            serverSide = "ru.rcvl.relativistus.common.common"
    )

    public static common proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        proxy.preInit(event);
        integrations.modScanner();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){proxy.Init(event);
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new getGUI());}

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);
        integrations.integrate();
    }
}