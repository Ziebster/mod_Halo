package net.minecraft.src;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemPlasmaPistol extends Item
{
	public ItemPlasmaPistol(int i)
	{
		super(i);
		maxStackSize = 1;
	}
	
	public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityplayer, int par4)
    {
        int var6 = this.getMaxItemUseDuration(itemstack) - par4;
        
        ArrowLooseEvent event = new ArrowLooseEvent(entityplayer, itemstack, var6);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return;
        }
        var6 = event.charge;
        
        boolean var5 = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, itemstack) > 0;

        if (var5 || entityplayer.inventory.hasItem(Item.arrow.shiftedIndex))
        {
            float F = (float)var6 / 20.0F;
            F = (F * F + F * 2.0F) / 3.0F;

            if ((double)F < 0.1D)
            {
                return;
            }

            if (F > 1.0F)
            {
                F = 1.0F;
            }

            EntityArrow entityarrow = new EntityArrow(world, entityplayer, 100);

            int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, itemstack);

            if (var9 > 0)
            {
                entityarrow.setDamage(entityarrow.getDamage() + (double)var9 * 0.5D + 0.5D);
            }

            int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, itemstack);

            if (var10 > 0)
            {
                entityarrow.setKnockbackStrength(var10);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, itemstack) > 0)
            {
                entityarrow.setFire(100);
            }

            itemstack.damageItem(1, entityplayer);
            world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + F * 0.5F);

            if (var5)
            {
                entityarrow.canBePickedUp = 2;
            }
            else
            {
                entityplayer.inventory.consumeInventoryItem(Item.arrow.shiftedIndex);
            }

            if (!world.isRemote)
            {
                world.spawnEntityInWorld(entityarrow);
            }
        }
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack itemstack)
    {
        return 25;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        ArrowNockEvent event = new ArrowNockEvent(entityplayer, itemstack);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return event.result;
        }
        
        if (entityplayer.capabilities.isCreativeMode || entityplayer.inventory.hasItem(Item.arrow.shiftedIndex))
        {
            entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        }

        return itemstack;
    }
}
