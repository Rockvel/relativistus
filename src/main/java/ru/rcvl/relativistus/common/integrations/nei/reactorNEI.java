package ru.rcvl.relativistus.common.integrations.nei;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;
import ru.rcvl.relativistus.client.gui.reactorGUI;
import ru.rcvl.relativistus.common.recipe.reactorRecipe;
import ru.rcvl.relativistus.common.recipe.reactorManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class reactorNEI extends TemplateRecipeHandler {
    @SideOnly(Side.CLIENT)
    private FontRenderer fontRender = Minecraft.getMinecraft().fontRenderer;

    @Override
    public String getGuiTexture() {
        return "relativistus:textures/gui/reactorNEI.png";
    }

    @Override
    public String getRecipeName() {
        return StatCollector.translateToLocal("relativemechanism.reactorCrafting");
    }

    public void loadCraftingRecipes(ItemStack result){
        for (reactorRecipe recipe : reactorManager.getRecipes()){
            for(int i = 0; i<recipe.getOutput().length; i++){
                if(result.isItemEqual(recipe.getOutput()[i])){
                    cachedReactions r = new cachedReactions(recipe);
                    arecipes.add(r);
                }
            }
        }
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if(outputId.equals("reactions")){
            for(reactorRecipe recipe : reactorManager.getRecipes()){
                cachedReactions r = new cachedReactions(recipe);
                arecipes.add(r);
            }
        }
    }

    public void loadUsageRecipes(ItemStack ingredient){
        for (reactorRecipe recipe : reactorManager.getRecipes()){
           if(recipe.isBlockAvailable(ingredient) || recipe.isIngotAvailable(ingredient) || recipe.isInputAvailable(ingredient)){
               cachedReactions r = new cachedReactions(recipe);
               arecipes.add(r);
           }
        }
    }

    @Override
    public void drawBackground(int recipe) {
        GL11.glColor4f(1, 1, 1, 1);
        GuiDraw.changeTexture(getGuiTexture());
        GuiDraw.drawTexturedModalRect(0, 0, 0, 0, 178, 104);
    }

    @Override
    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(19, 42, 16, 21), "reactions"));
    }

    public Class<? extends GuiContainer> getGuiClass(){
        return reactorGUI.class;
    }

    @Override
    public String getOverlayIdentifier() {
        return "reactions";
    }

    @Override
    public void drawForeground(int recipe) {
        fontRender.drawString(StatCollector.translateToLocal("relativemechanism.stability") + ((cachedReactions)arecipes.get(recipe)).koef, 77, 5, 0);
        fontRender.drawString(StatCollector.translateToLocal("relativemechanism.duration") + ((cachedReactions)arecipes.get(recipe)).duration, 77, 14, 0);
        fontRender.drawString(StatCollector.translateToLocal("relativemechanism.output"), 77, 33, 0);
        fontRender.drawString(((cachedReactions)arecipes.get(recipe)).energy + "eU/t", 77, 42, 0);
        fontRender.drawString(((cachedReactions)arecipes.get(recipe)).inputChance + "%", 55, 23, 4210752);
        for(int i = 0; i<((cachedReactions)arecipes.get(recipe)).output.size(); i++){
            fontRender.drawString(((cachedReactions)arecipes.get(recipe)).outputChance[i] + "%", 9 + i*18 - fontRender.getStringWidth("%"+((cachedReactions)arecipes.get(recipe)).outputChance[i])/2, 88, 4210752);
        }
    }

    @Override
    public int recipiesPerPage() {
        return 1;
    }

    private class cachedReactions extends CachedRecipe {
        private ArrayList<PositionedStack> input = new ArrayList<PositionedStack>();
        private ArrayList<PositionedStack> output = new ArrayList<PositionedStack>();
        private int inputChance;
        private int[] outputChance;
        private int duration;
        private double koef;
        private int blockKoef;
        private int ingotKoef;
        private int energy;

        public cachedReactions(reactorRecipe recipe){
            input.add(new PositionedStack(recipe.getInputNugget(), 37, 19));
            if(recipe.getInputIngot()!=null)input.add(new PositionedStack(recipe.getInputIngot(), 19, 19));
            if(recipe.getInputBlock()!=null)input.add(new PositionedStack(recipe.getInputBlock(), 1, 19));
            if(recipe.getOutput()!=null){
                for(int i = 0; i<recipe.getOutput().length; i++){
                    output.add(new PositionedStack(recipe.getOutput()[i], 1+18*i, 69));
                }
            }
            this.inputChance=recipe.getInputChance();
            this.outputChance=recipe.getOuputChanse();
            this.duration=recipe.getDuration();
            this.koef=recipe.getKoef();
            this.blockKoef=recipe.getBlockKoef();
            this.ingotKoef=recipe.getIngotKoef();
            this.energy=recipe.getEnergy();
        }

        @Override
        public PositionedStack getResult() {
            if(!output.isEmpty()) return output.get(0);
            return null;
        }

        @Override
        public ArrayList<PositionedStack> getIngredients() {
            return (ArrayList<PositionedStack>) getCycledIngredients(reactorNEI.this.cycleticks / 20, input);
        }

        @Override
        public List<PositionedStack> getOtherStacks() {
            if(output.size()>1){
                ArrayList<PositionedStack> list = new ArrayList<PositionedStack>(output);
                list.remove(0);
                return list;
            }
            return new ArrayList<>();
        }
    }
}