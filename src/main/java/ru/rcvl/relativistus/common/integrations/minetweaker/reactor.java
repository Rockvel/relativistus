package ru.rcvl.relativistus.common.integrations.minetweaker;
import minetweaker.IUndoableAction;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import ru.rcvl.relativistus.common.recipe.reactorRecipe;
import ru.rcvl.relativistus.common.recipe.reactorManager;

@ZenClass("mods.Relativistus.Reactor")
public class reactor {

    @ZenMethod
    public static void addRecipe(IItemStack inputNugget, int inputChance, IItemStack[] output, int[] outputChance, int energy, int duration, double koef){
        ItemStack[] result = null;
        if(output!=null){
            result = new ItemStack[output.length];
            for(int i = 0; i<result.length; i++){
                result[i]=(ItemStack) output[i].getInternal();
            }
        }
        reactorRecipe recipe = new reactorRecipe(null, 0, null, 0, (ItemStack) inputNugget.getInternal(), inputChance, result, outputChance, energy, duration, koef);
        MineTweakerAPI.apply(new Add(recipe));
    }

    @ZenMethod
    public static void addRecipe(IItemStack inputIngot, int ingotKoef, IItemStack inputNugget, int inputChance, IItemStack[] output, int[] outputChance,int energy,  int duration, double koef){
        ItemStack[] result = null;
        if(output!=null){
            result = new ItemStack[output.length];
            for(int i = 0; i<result.length; i++){
                result[i]=(ItemStack) output[i].getInternal();
            }
        }
        reactorRecipe recipe = new reactorRecipe(null, 0, (ItemStack) inputIngot.getInternal(), ingotKoef, (ItemStack) inputNugget.getInternal(), inputChance, result, outputChance, energy, duration, koef);
        MineTweakerAPI.apply(new Add(recipe));
    }

    @ZenMethod
    public static void addRecipe(IItemStack inputBlock, int blockKoef, IItemStack inputIngot, int ingotKoef, IItemStack inputNugget, int inputChance, IItemStack[] output, int[] outputChance, int energy, int duration, double koef){
        ItemStack[] result = null;
        if(output!=null){
            result = new ItemStack[output.length];
            for(int i = 0; i<result.length; i++){
                result[i]=(ItemStack) output[i].getInternal();
            }
        }
        reactorRecipe recipe = new reactorRecipe((ItemStack)inputBlock.getInternal(), blockKoef, (ItemStack)inputIngot.getInternal(), ingotKoef, (ItemStack) inputNugget.getInternal(), inputChance, result, outputChance, energy, duration, koef);
        MineTweakerAPI.apply(new Add(recipe));
    }

    private static class Add implements IUndoableAction {
        reactorRecipe recipe;

        public Add(reactorRecipe add) {
            recipe = add;
        }

        @Override
        public void apply() {
            reactorManager.getRecipes().add(recipe);
        }

        @Override
        public boolean canUndo() {
            return true;
        }

        @Override
        public void undo() {
            reactorManager.getRecipes().remove(recipe);
        }

        @Override
        public String describe() {
            return "";
        }

        @Override
        public String describeUndo() {
            return "";
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }
    }
}
