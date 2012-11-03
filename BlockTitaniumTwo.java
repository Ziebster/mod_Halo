package net.minecraft.src;

import java.util.Random;

public class BlockTitaniumTwo extends Block
{
    public BlockTitaniumTwo(int par1, int par2)
    {
        super(par1, par2, Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Block.stone.blockID;
    }
}
