package ru.rcvl.relativistus.client.render;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.IItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import ru.rcvl.relativistus.common.item.rods.*;

import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class itemRodsRender implements IItemRenderer {
    private final ResourceLocation rodModel = new ResourceLocation( "relativistus:models/rod.obj");

    public static String[] paths = {"relativistus:textures/items/itemBoronSteelRod.png", "relativistus:textures/items/itemCobaltRod.png", "relativistus:textures/items/itemSilverRod.png", "relativistus:textures/items/itemYoellRod.png", "relativistus:textures/items/itemEnrichYoellRod.png", "relativistus:textures/items/itemRelativeRod.png"};
    private final ResourceLocation rodTexturePath;

    private final IModelCustom model;

    public itemRodsRender(int i){
        this.model = AdvancedModelLoader.loadModel(rodModel);
        this.rodTexturePath = new ResourceLocation(paths[i]);
    }
    public boolean handleRenderType(ItemStack item, ItemRenderType type){
        return true;
    }
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper){
        return  true;
    }
    public void renderItem(ItemRenderType type, ItemStack item, Object... data){
        if(type==ItemRenderType.EQUIPPED_FIRST_PERSON){
            Minecraft.getMinecraft().renderEngine.bindTexture(rodTexturePath);
            GL11.glPushMatrix();
            GL11.glTranslatef(0.5F, 0.2F, 0.5F);
            GL11.glScalef(1.5F, 1.5F, 1.5F);
            model.renderAll();
            GL11.glPopMatrix();
        }
        else if(type==ItemRenderType.EQUIPPED){
            Minecraft.getMinecraft().renderEngine.bindTexture(rodTexturePath);
            GL11.glPushMatrix();
            GL11.glRotatef(45F, 0F, 1F, 0F);
            GL11.glRotatef(68F, -1F, 0F, 0F);
            GL11.glTranslatef(0.3F, -1.75F, 0.8F);
            GL11.glScalef(1.7F, 1.7F, 1.7F);
            model.renderAll();
            GL11.glPopMatrix();
        }
        else{
        Minecraft.getMinecraft().renderEngine.bindTexture(rodTexturePath);
        GL11.glPushMatrix();
        GL11.glTranslatef(-0.1F, -0.58F, 0F);
        GL11.glScalef(1.5F, 1.5F, 1.5F);
        model.renderAll();
        GL11.glPopMatrix();
        }
    }
}