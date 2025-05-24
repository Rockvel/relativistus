package ru.rcvl.relativistus.common.item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.IIcon;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.util.List;

public class itemRelative extends Item {

    public itemRelative(){
        setMaxStackSize(64);
        setUnlocalizedName("itemRelative.");
        setHasSubtypes(true);
    }

    public IIcon[] icons;

    public String[] types = {"shard" , "largeShard", "chunk", "slab"};

    @Override
    public String getUnlocalizedName(ItemStack stack){
        int i = MathHelper.clamp_int(stack.getItemDamage(), 0, types.length);
        return super.getUnlocalizedName() + types[i];
    }

    @Override
    public void registerIcons(IIconRegister ir) {
        icons = new IIcon[types.length];
        for (int x = 0; x < types.length; x++) {
            icons[x] = ir.registerIcon("relativistus:itemRelative." + types[x]);
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int dam) {
        return this.icons[dam % icons.length];
    }


    @SuppressWarnings("unchecked")
    @SideOnly (Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int j = 0; j < types.length; ++j) {
            list.add(new ItemStack(item, 1, j));
        }
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
       switch (stack.getItemDamage()){
           case 0:
               return EnumRarity.uncommon;
           case 1:
           case 2:
               return  EnumRarity.rare;
           case 3:
               return EnumRarity.epic;
           default: return EnumRarity.common;
       }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean forTrue){
        int meta = stack.getItemDamage();
        tooltip.add(EnumChatFormatting.DARK_GRAY + "" + EnumChatFormatting.ITALIC + StatCollector.translateToLocal("tooltip." + types[meta]));
    }
}