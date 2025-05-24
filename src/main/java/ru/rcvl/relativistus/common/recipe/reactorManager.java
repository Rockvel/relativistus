package ru.rcvl.relativistus.common.recipe;

import net.minecraft.item.ItemStack;
import java.util.ArrayList;

public class reactorManager {
    private static ArrayList<reactorRecipe> recipes = new ArrayList<reactorRecipe>();

    public static void addRecipe(ItemStack inputNugget, int inputChance, ItemStack[] output, int[] outputChance, int energy, int duration, double koef){
        recipes.add(new reactorRecipe(null, 0, null, 0, inputNugget, inputChance, output, outputChance, energy, duration, koef));
    }

    public static void addRecipe(ItemStack inputIngot, int ingotKoef, ItemStack inputNugget, int inputChance, ItemStack[] output, int[] outputChance,int energy,  int duration, double koef){
        recipes.add(new reactorRecipe(null, 0, inputIngot, ingotKoef, inputNugget, inputChance, output, outputChance, energy, duration, koef));
    }

    public static void addRecipe(ItemStack inputBlock, ItemStack inputIngot, ItemStack inputNugget, int inputChance, ItemStack[] output, int[] outputChance, int energy, int duration, double koef){
        recipes.add(new reactorRecipe(inputBlock, 0, inputIngot, 0, inputNugget, inputChance, output, outputChance, energy, duration, koef));
    }
    public static ArrayList<reactorRecipe> getRecipes(){
        return recipes;
    }

    public static ArrayList <reactorRecipe> getRecipeFromNugget(ItemStack stack){
        ArrayList <reactorRecipe> returnRecipes = new ArrayList<reactorRecipe>();
        for (reactorRecipe recipe : recipes){
            if(recipe.isInputAvailable(stack))returnRecipes.add(recipe);
        }
        return returnRecipes;
    }

    public static ArrayList <reactorRecipe> getRecipeFromIngot(ItemStack stack){
        ArrayList <reactorRecipe> returnRecipes = new ArrayList<reactorRecipe>();
        for (reactorRecipe recipe : recipes){
            if(recipe.isIngotAvailable(stack))returnRecipes.add(recipe);
        }
        return returnRecipes;
    }

    public static ArrayList <reactorRecipe>  getRecipeFromBlock(ItemStack stack){
        ArrayList <reactorRecipe> returnRecipes = new ArrayList<reactorRecipe>();
        for (reactorRecipe recipe : recipes){
            if(recipe.isBlockAvailable(stack))returnRecipes.add(recipe);
        }
        return returnRecipes;
    }
}