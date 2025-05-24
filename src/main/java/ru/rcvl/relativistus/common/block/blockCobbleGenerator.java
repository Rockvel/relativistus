package ru.rcvl.relativistus.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import ru.rcvl.relativistus.Relativistus;
import ru.rcvl.relativistus.common.tile.tileCobble;

public class blockCobbleGenerator extends BlockContainer {

    public blockCobbleGenerator(){
        super(Material.iron);
        setBlockName("blockCobbleGenerator");
        setStepSound(Block.soundTypeMetal);
        setHarvestLevel("pickaxe", 2);
    }

    private IIcon[] icons;
    public String[] sides = {"top", "sides", "front"};

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir){
        this.icons = new IIcon[sides.length];
        for(int i =0; i<sides.length; i++){
            this.icons[i] = ir.registerIcon("relativistus:"+"blockCobbleGenerator."+sides[i]);
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        if (side==0 || side==1) return icons [0];
        if (side==2 || side==3) return icons[1];
        if (side==4 || side==5) return icons[2];
        return null;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new tileCobble();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par0, float par1, float par2, float par3){
        if (!world.isRemote){
            player.openGui(Relativistus.instance, 1, world, x, y, z);
        }
        return true;
    }
}
