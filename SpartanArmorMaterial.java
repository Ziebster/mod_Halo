package net.minecraft.src;
 
import java.util.Random;
 
public enum SpartanArmorMaterial
{
	TITANIUM(10, new int[]{2, 7, 5, 3}, 9);
	private int maxDamageFactor;
	private int damageReductionAmountArray[];
	private int enchantability;


	private SpartanArmorMaterial(int par3, int[] par4ArrayOfInteger, int par5)
	{
		this.maxDamageFactor = par3;
		this.damageReductionAmountArray = par4ArrayOfInteger;
		this.enchantability = par5;
	}

	public int getDurability(int i)
	{
		return ItemArmor.getMaxDamageArray()[i] * maxDamageFactor;
	}
	public int getDamageReductionAmount(int i)
	{
		return damageReductionAmountArray[i];
	}

	public int getEnchantability()
	{
		return enchantability;
	}

}