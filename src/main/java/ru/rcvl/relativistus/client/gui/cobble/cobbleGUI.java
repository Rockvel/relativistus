package ru.rcvl.relativistus.client.gui.cobble;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import ru.rcvl.relativistus.common.tile.tileCobble;

public class cobbleGUI extends GuiContainer {

    private static final ResourceLocation thisGUI = new ResourceLocation("relativistus:textures/gui/cobbleGUI.png");
    private tileCobble cobble;

    public cobbleGUI(InventoryPlayer player, tileCobble machine){
        super(new containerCobble(player, machine));
        this.xSize=176;
        this.ySize=148;
        cobble=machine;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p1, int p2, int p3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(thisGUI);
        this.xSize=176;
        this.ySize=148;
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, 176, 148);
    }

    protected void drawGuiContainerForegroundLayer(int p1, int p2){
        String name = StatCollector.translateToLocal("relativemechanism.cobble");
        this.fontRendererObj.drawString(name, 176/2 - this.fontRendererObj.getStringWidth(name)/2, 6, 4210752);
    }
}
