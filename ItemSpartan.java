package net.minecraft.src;
 
public class ItemSpartan extends ItemArmor
{
 
    private static final int maxDamageArray[] = {
        11, 16, 15, 13
    };
    public final int armorType;
    public final int damageReduceAmount;
    public final int renderIndex;
    private final SpartanArmorMaterial material;
 
    public ItemSpartan(int i, SpartanArmorMaterial enumarmormaterial, int j, int k)
    {
        super(i, EnumArmorMaterial.DIAMOND, j, k);
        material = enumarmormaterial;
        armorType = k;
        renderIndex = j;
        damageReduceAmount = enumarmormaterial.getDamageReductionAmount(k);
        setMaxDamage(enumarmormaterial.getDurability(k));
        maxStackSize = 1;
    }
 
    public int getItemEnchantability()
    {
        return material.getEnchantability();
    }
 
    static int[] getMaxDamageArray()
    {
        return maxDamageArray;
    }
 
}