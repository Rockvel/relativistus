package ru.rcvl.relativistus.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.rcvl.relativistus.Relativistus;
import ru.rcvl.relativistus.common.tile.tileReactor;

import java.util.List;

public class reactorBlock extends BlockContainer {

    public reactorBlock(){
        super(Material.iron);
        setBlockName("reactorBlock");
        setStepSound(Block.soundTypeMetal);
        setHarvestLevel("pickaxe", 2);
    }

    public static String[] sides = {"reactor", "rodFront", "rodTop", "rodSide", "reactorNode"};
    public static String[] types = {"reactor", "rod"};
    private IIcon[] icons;

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        if (metadata == 0) return icons[0];
        if (metadata==1 && side==2 || side==3 || side==4 || side==5) return icons [3];
        if (metadata==1 && side==0) return icons[1];
        if (metadata==1 && side==1) return icons[2];
        return null;
    }

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        if(world.getBlockMetadata(x, y, z)==0){
            tileReactor reactor = (tileReactor)world.getTileEntity(x, y, z);
            if(reactor.isMain())return  icons[4];
        }
        return getIcon(side, world.getBlockMetadata(x, y, z));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir){
        this.icons = new IIcon[sides.length+1];
        for(int i =0; i<sides.length; i++){
            this.icons[i] = ir.registerIcon("relativistus:"+"reactorBlock."+sides[i]);
        }
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list){
        for (int j=0; j<types.length; j++){
            list.add(new ItemStack(item, 1, j));
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase base, ItemStack stack){
        if(world.getBlockMetadata(x, y, z)==0){
            for(int i = x-1; i<=x+1; i++){
                for(int j = y-1; j<=y+1; j++){
                    for(int k = z-1; k<=z+1; k++){
                        if(world.getBlock(i, j, k).equals(this) && world.getBlockMetadata(i, j, k)==0){
                            if(checkMultiblockFromCenter(world, i, j, k)){
                                setTileToFormed(world, x, y, z, i, j, k);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par0, float par1, float par2, float par3){
        if(!world.isRemote && world.getBlockMetadata(x, y, z)==0){
            tileReactor reactor = (tileReactor) world.getTileEntity(x, y, z);
            if(reactor.isFormed()){
                player.openGui(Relativistus.instance, 0, world, reactor.getMainX(), reactor.getMainY(), reactor.getMainZ());
                return true;
            }
        }
        return false;
    }

    public TileEntity createNewTileEntity(World world, int metadata){
        if(metadata==0) return new tileReactor();
        else return null;
    }

    private boolean checkMultiblockFromCenter(World world, int x, int y, int z){
        for(int i = x-1; i<=x+1; i++){
            for(int j = y-1; j<=y+1; j++){
                for(int k = z-1; k<=z+1; k++){
                    if(!world.getBlock(i, j, k).equals(this) || world.getBlockMetadata(i, j, k)==1){
                        return false;
                    }
                    else {
                        tileReactor reactor = (tileReactor) world.getTileEntity(i, j, k);
                        if(reactor.isFormed()){
                            return false;
                        }
                    }
                }
            }
        }
        for(int i = x-1; i<=x+1; i++){
            for(int k = z-1; k<=z+1; k++){
                if(!(world.getBlock(i, y+2, k).equals(this)) || world.getBlockMetadata(i, y+2, k)==0){
                    return false;
                }
            }
        }
        return true;
    }

    private void setTileToFormed(World world, int x, int y, int z, int x1, int y1, int z1){
        for(int i = x1-1; i<=x1+1; i++){
            for(int j = y1-1; j<=y1+1; j++){
                for(int k = z1-1; k<=z1+1; k++){
                    tileReactor reactor = (tileReactor) world.getTileEntity(i, j, k);
                    reactor.setToFormed(x, y, z);
                }
            }
        }
    }
}