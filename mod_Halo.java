package net.minecraft.src;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.SidedProxy;
import net.minecraft.src.EntityGrenade;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.src.RenderGrenade;
import java.util.*;
import java.lang.reflect.Method;
import net.minecraft.src.ModelGrenade;

/**
 * 
 * @author Joseph
 *
 */
@Mod(modid = "HaloMod", name = "HaloMod", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class mod_Halo extends BaseMod
{	
	public static Block Titanium = (new BlockTitanium(192, 0)).setHardness(20.0F).setResistance(2000.0F).setStepSound(Block.soundStoneFootstep).setBlockName("Titanium");
	public static Block TitaniumTwo = (new BlockTitaniumTwo(193, 0)).setHardness(20.0F).setResistance(2000.0F).setStepSound(Block.soundStoneFootstep).setBlockName("TitaniumTwo");
	public static Item TitaniumIngot = (new ItemTitanium(3000)).setItemName("TitaniumIngot");
	public static Item SpartanHelm = (new ItemSpartan(3001, SpartanArmorMaterial.TITANIUM, ModLoader.addArmor("Titanium"), 0)).setItemName("SpartanHelm");
	public static Item SpartanChest = (new ItemSpartan(3002, SpartanArmorMaterial.TITANIUM, ModLoader.addArmor("Titanium"), 1)).setItemName("SpartanChest");
	public static Item SpartanPants = (new ItemSpartan(3003, SpartanArmorMaterial.TITANIUM, ModLoader.addArmor("Titanium"), 2)).setItemName("SpartanPants");
	public static Item SpartanFoot = (new ItemSpartan(3004, SpartanArmorMaterial.TITANIUM, ModLoader.addArmor("Titanium"), 3)).setItemName("SpartanFoot");
	public static Item Grenade = (new ItemGrenade(3005)).setItemName("Grenade");
	public static Item Magnum = (new ItemMagnum(3007)).setItemName("Magnum");

	@SidedProxy(serverSide = "ServerHaloProxy", clientSide = "ClientHaloProxy")
	public static ClientHaloProxy proxy;
	
	
	@Init
	public void init(FMLInitializationEvent event) {
		EntityRegistry.registerModEntity(EntityGrenade.class, "Grenade", 3005, this, 80, 3, true);
		
		proxy.init();
	}
	
	public mod_Halo()
	{
		//Primary Titanium Block
		ModLoader.registerBlock(Titanium);
		ModLoader.addName(Titanium, "Titanium Ore");
		Titanium.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/Halo/Titanium.png");
		//Secondary Titanium Block
		ModLoader.registerBlock(TitaniumTwo);
		ModLoader.addName(TitaniumTwo, "Worn Titanium Ore");
		TitaniumTwo.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/Halo/TitaniumTwo.png");
		//Titanium Ingot
		ModLoader.addName(TitaniumIngot, "Titanium Ingot");
		TitaniumIngot.iconIndex = ModLoader.addOverride("/gui/items.png", "/Halo/TitaniumIngot.png");
		//Grenades
		ModLoader.addName(Grenade, "Frag Grenade");
		Grenade.iconIndex = ModLoader.addOverride("/gui/items.png", "/Halo/Grenade.png");
		//Guns
		ModLoader.addName(Magnum, "Magnum");
		Magnum.iconIndex = ModLoader.addOverride("/gui/items.png", "/Halo/Magnum.png");
		
		ModLoader.addSmelting(mod_Halo.TitaniumTwo.blockID, new ItemStack(Titanium, 1), 1.0F);
		ModLoader.addSmelting(mod_Halo.Titanium.blockID, new ItemStack(TitaniumIngot, 1), 1.0F);
	
		ModLoader.addRecipe(new ItemStack(Grenade, 1), new Object[]{"X",Character.valueOf('X'),Block.dirt});
		ModLoader.addRecipe(new ItemStack(Titanium, 1), new Object[]{"X","X",Character.valueOf('X'),Block.dirt});
		ModLoader.addRecipe(new ItemStack(Magnum, 1), new Object[]{"XXX","  X",Character.valueOf('X'),TitaniumIngot});
	}
	
	public void load() {}
	
	public String getVersion()
	{
		return "1.0";
	}
}