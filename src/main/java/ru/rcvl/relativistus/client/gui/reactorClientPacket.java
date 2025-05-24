package ru.rcvl.relativistus.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import ru.rcvl.relativistus.common.tile.tileReactor;

public class reactorClientPacket implements IMessage, IMessageHandler<reactorClientPacket, IMessage> {

    int x, y, z, action, index;

    public reactorClientPacket(){}

    public reactorClientPacket(tileReactor reactor, int action){
        x=reactor.xCoord;
        y=reactor.yCoord;
        z=reactor.zCoord;
        this.action=action;
    }

    public reactorClientPacket(tileReactor reactor, int action, int index){
        x=reactor.xCoord;
        y=reactor.yCoord;
        z=reactor.zCoord;
        this.action=action;
        this.index = index;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        action=buf.readInt();
        index=buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(action);
        buf.writeInt(index);
    }

    @Override
    public IMessage onMessage(reactorClientPacket message, MessageContext ctx) {
        TileEntity tile = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
        if(tile instanceof tileReactor){
            switch (message.action){
                case 0:((tileReactor) tile).start(message.index-1);
                case 1:((tileReactor) tile).addDepth(message.index);
                case 2:((tileReactor) tile).removeDepth(message.index);
            }
        }
        ctx.getServerHandler().playerEntity.worldObj.markBlockForUpdate(message.x, message.y, message.z);
        return null;
    }
}
