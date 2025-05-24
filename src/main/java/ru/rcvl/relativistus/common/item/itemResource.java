package ru.rcvl.relativistus.common.item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.IIcon;
import java.util.List;


public class itemResource extends Item {
    public itemResource() {
        setHasSubtypes(true);
        setMaxStackSize(64);
        setUnlocalizedName("itemResource.");
    }
    public IIcon[] icons;
    public static String[] types = {"noire", "yoell", "enrichyoell"};

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int i = MathHelper.clamp_int(stack.getItemDamage(), 0, types.length);
        return super.getUnlocalizedName() + types[i];
    }

    @Override
    public void registerIcons(IIconRegister ir) {
        icons = new IIcon[types.length];
        for (int x = 0; x < types.length; x++) {
            icons[x] = ir.registerIcon("relativistus:itemResource." + types[x]);
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
    public EnumRarity getRarity(ItemStack stack){
        if (stack.getItemDamage() == 0) {
            return EnumRarity.uncommon;
        }
        return EnumRarity.common;
    }
}