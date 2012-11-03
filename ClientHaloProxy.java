package net.minecraft.src;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientHaloProxy extends ServerHaloProxy
{
	public void init() {
		RenderingRegistry.registerEntityRenderingHandler(net.minecraft.src.EntityGrenade.class, new RenderSnowball(mod_Halo.Grenade.iconIndex));
	}
}
