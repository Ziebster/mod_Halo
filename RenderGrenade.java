package net.minecraft.src;

import net.minecraft.src.ModelBase;
import net.minecraft.src.RenderLiving;

public class RenderGrenade extends RenderLiving {

	private ModelGrenade grenadeModel;

	public RenderGrenade(int i, Class<ModelGrenade> class1) {
		super(new ModelGrenade(), 0.5f);
		this.grenadeModel = (ModelGrenade) super.mainModel;
	}

}