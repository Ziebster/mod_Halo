package net.minecraft.src;

public class ItemGrenade extends Item
{
	public ItemGrenade(int i)
	{
		super(i);
		
		grenadeID = shiftedIndex;
	}
	public static int grenadeID;
	
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		if(!world.isRemote)
		{
			world.spawnEntityInWorld(new EntityGrenade(world, entityplayer));
		}
		--itemstack.stackSize;
		return itemstack;
		
	}
}
