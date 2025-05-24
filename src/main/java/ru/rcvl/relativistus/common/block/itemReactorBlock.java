package ru.rcvl.relativistus.common.block;

import net.minecraft.item.ItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class itemReactorBlock extends ItemBlock {
    public itemReactorBlock(Block block){
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta){
    return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack){
        return "tile.reactorBlock."+reactorBlock.types[stack.getItemDamage()%reactorBlock.types.length];
    }
}