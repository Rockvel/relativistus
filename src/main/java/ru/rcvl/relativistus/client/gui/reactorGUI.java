package ru.rcvl.relativistus.client.gui;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import ru.rcvl.relativistus.Relativistus;
import ru.rcvl.relativistus.common.tile.tileReactor;

@SideOnly(Side.CLIENT)
public class reactorGUI extends GuiContainer{
    private static final ResourceLocation thisGUI = new ResourceLocation("relativistus:textures/gui/reactorGUI.png");

    private tileReactor reactor;
    int i = 1;

    public reactorGUI(InventoryPlayer player, tileReactor machine){
        super(new containerReactor(player, machine));
        this.xSize=256;
        this.ySize=248;
        reactor = machine;
    }

    protected void drawGuiContainerForegroundLayer(int p1, int p2){
        String name = StatCollector.translateToLocal("relativemechanism.reactor");
        String num = i+"/"+reactor.getValid().size();
        this.fontRendererObj.drawString(name, this.xSize/2 - this.fontRendererObj.getStringWidth(name)/2, 6, 4210752);
        if(!reactor.getValid().isEmpty()){
            if(i>reactor.getValid().size())i=1;
            this.fontRendererObj.drawString(num, 164-this.fontRendererObj.getStringWidth(num)/2, 18, 4210752);
        }
        this.fontRendererObj.drawString(StatCollector.translateToLocal("relativemechanism.stability") + " " + reactor.getStab(), 124, 34, 0);
        this.fontRendererObj.drawString(StatCollector.translateToLocal("relativemechanism.state") + reactor.getState(), 124, 44, 0);
    }

    protected void drawGuiContainerBackgroundLayer(float p1, int p2, int p3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(thisGUI);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k+40, l, 0, 0, 176, 248);
        this.drawTexturedModalRect(k+216, l+21, 176, 21, 50, 118);
    }

    @Override
    public void initGui() {
        super.initGui();
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        buttonList.add(new GuiButton(0, k+144, l+139, 40, 20, StatCollector.translateToLocal("relativemechanism.start")));
        buttonList.add(new GuiButton(1, k+119, l+139, 20, 20, "+"));
        buttonList.add(new GuiButton(2, k+189, l+139, 20, 20, "-"));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if(button.id==0){
            Relativistus.network.sendToServer(new reactorClientPacket(reactor, 0, i));
        }
        if(button.id==1){
            if(reactor.getState()==0){
                if(i<reactor.getValid().size())i++;
            }
            else if (isShiftKeyDown()){
                Relativistus.network.sendToServer(new reactorClientPacket(reactor, 1, 10));
            }
            else Relativistus.network.sendToServer(new reactorClientPacket(reactor, 1, 1));
        }
        if(button.id==2){
            if(reactor.getState()==0){
                if(i>1)i--;
            }
            else if (isShiftKeyDown()){
                Relativistus.network.sendToServer(new reactorClientPacket(reactor, 2, 10));
            }
            else Relativistus.network.sendToServer(new reactorClientPacket(reactor, 2, 1));
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        buttonList.get(0).enabled=reactor.getState()==0;
    }
}