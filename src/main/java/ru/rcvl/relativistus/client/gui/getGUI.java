package ru.rcvl.relativistus.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ru.rcvl.relativistus.client.gui.cobble.*;
import ru.rcvl.relativistus.common.tile.tileCobble;
import ru.rcvl.relativistus.common.tile.tileReactor;

public class getGUI implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID==0){
            return new containerReactor(player.inventory, (tileReactor) world.getTileEntity(x, y, z));
        }
        if(ID==1){
            return new containerCobble(player.inventory, (tileCobble) world.getTileEntity(x, y, z));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID==0){
            return new reactorGUI(player.inventory, (tileReactor) world.getTileEntity(x, y, z));
        }
        if(ID==1){
            return new cobbleGUI(player.inventory, (tileCobble) world.getTileEntity(x, y, z));
        }
        return null;
    }
}
