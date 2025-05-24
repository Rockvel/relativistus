package ru.rcvl.relativistus.common.handler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.MinecraftForgeClient;
import ru.rcvl.relativistus.common.block.blockCobbleGenerator;
import ru.rcvl.relativistus.common.item.itemResource;
import ru.rcvl.relativistus.common.item.itemRelative;
import ru.rcvl.relativistus.common.block.itemReactorBlock;
import ru.rcvl.relativistus.common.block.reactorBlock;
import ru.rcvl.relativistus.common.item.rotors.*;
import ru.rcvl.relativistus.common.item.rods.*;
import ru.rcvl.relativistus.client.render.itemRodsRender;
import ru.rcvl.relativistus.common.item.itemUpgrade;
import ru.rcvl.relativistus.common.tile.tileCobble;
import ru.rcvl.relativistus.common.tile.tileReactor;

public class register {
    public static final reactorBlock reactorBlock = new reactorBlock();
    public static final blockCobbleGenerator blockCobbleGenerator = new blockCobbleGenerator();

    public static final itemResource itemResource = new itemResource();
    public static final itemRelative itemRelative = new itemRelative();
    public static final itemUpgrade itemUpgrade = new itemUpgrade();
    public static final itemIronRotor itemIronRotor = new itemIronRotor();
    public static final itemCarbonRotor itemCarbonRotor = new itemCarbonRotor();
    public static final itemNoireRotor itemNoireRotor = new itemNoireRotor();
    public static final itemYoellRotor itemYoellRotor = new itemYoellRotor();
    public static final itemEnrichYoellRotor itemEnrichYoellRotor = new itemEnrichYoellRotor();
    public static final itemRelativeRotor itemRelativeRotor = new itemRelativeRotor();
    public static final itemBoronSteelRod itemBoronSteelRod = new itemBoronSteelRod();
    public static final itemCobaltRod itemCobaltRod = new itemCobaltRod();
    public static final itemSilverRod itemSilverRod = new itemSilverRod();
    public static final itemYoellRod itemYoellRod = new itemYoellRod();
    public static final itemEnrichYoellRod itemEnrichYoellRod = new itemEnrichYoellRod();
    public static final itemRelativeRod itemRelativeRod = new itemRelativeRod();

    public static void registration(){
        GameRegistry.registerBlock(reactorBlock, itemReactorBlock.class, "reactorBlock");
        GameRegistry.registerBlock(blockCobbleGenerator, "blockCobbleGenerator");

        GameRegistry.registerItem(itemResource, "itemResource");
        GameRegistry.registerItem(itemRelative, "itemRelative");
        GameRegistry.registerItem(itemUpgrade, "itemUpgrade");
        GameRegistry.registerItem(itemIronRotor, "itemIronRotor");
        GameRegistry.registerItem(itemCarbonRotor, "itemCarbonRotor");
        GameRegistry.registerItem(itemNoireRotor, "itemNoireRotor");
        GameRegistry.registerItem(itemYoellRotor, "itemYoellRotor");
        GameRegistry.registerItem(itemEnrichYoellRotor, "itemEnrichYoellRotor");
        GameRegistry.registerItem(itemRelativeRotor, "itemRelativeRotor");
        GameRegistry.registerItem(itemBoronSteelRod, "itemBoronSteelRod");
        GameRegistry.registerItem(itemCobaltRod, "itemCobaltRod");
        GameRegistry.registerItem(itemSilverRod, "itemSilverRod");
        GameRegistry.registerItem(itemYoellRod, "itemYoellRod");
        GameRegistry.registerItem(itemEnrichYoellRod, "itemEnrichYoellRod");
        GameRegistry.registerItem(itemRelativeRod, "itemRelativeRod");

        GameRegistry.registerTileEntity(tileReactor.class, "tileReactor");
        GameRegistry.registerTileEntity(tileCobble.class, "tileCobble");
    }
    @SideOnly(Side.CLIENT)
    public static void registerRender(){
        MinecraftForgeClient.registerItemRenderer(itemBoronSteelRod,  new itemRodsRender(0));
        MinecraftForgeClient.registerItemRenderer(itemCobaltRod, new itemRodsRender(1));
        MinecraftForgeClient.registerItemRenderer(itemSilverRod, new itemRodsRender(2));
        MinecraftForgeClient.registerItemRenderer(itemYoellRod, new itemRodsRender(3));
        MinecraftForgeClient.registerItemRenderer(itemEnrichYoellRod, new itemRodsRender(4));
        MinecraftForgeClient.registerItemRenderer(itemRelativeRod, new itemRodsRender(5));
    }
}
