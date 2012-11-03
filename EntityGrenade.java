package net.minecraft.src;
import cpw.mods.fml.common.registry.IThrowableEntity;
import net.minecraft.src.mod_Halo;

public class EntityGrenade extends EntityThrowable
{
	public EntityGrenade(World world)
	{
		super(world);
    	this.setSize(0.5F, 0.5F);
        this.exploded = false;
        this.ticksExisted = 0;
        this.bounceFactor = 0.6;
        this.stopped = false;
	}
	
	public EntityGrenade(World world, EntityLiving entityliving)
	{
		super(world);
        this.thrower = entityliving;
        this.setSize(0.5F, 0.5F);
        this.setLocationAndAngles(entityliving.posX, entityliving.posY + (double)entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
        this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.posY -= 0.10000000149011612D;
        this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        float var3 = 0.4F;
        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * var3);
        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * var3);
        this.motionY = (double)(-MathHelper.sin((this.rotationPitch + this.func_70183_g()) / 180.0F * (float)Math.PI) * var3);
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, this.func_70182_d(), 1.0F);
	}

	public EntityGrenade(World world, double par2, double par4, double par6)
	{
		super(world);
        this.setSize(0.25F, 0.25F);
        this.setPosition(par2, par4, par6);
        this.yOffset = height / 2.0F;
	}

	protected void onImpact(MovingObjectPosition position)
	{
		if(onGround && (motionX*motionX+motionY*motionY+motionZ*motionZ)<0.02)
        {
			stopped = true;
			motionX = 0;
        	motionY = 0;
        	motionZ = 0;
        }
		else
		{
        	motionX *= bounceFactor;
        	motionY *= bounceFactor;
        	motionZ *= bounceFactor;
		}
	}
	
	protected float getGravityVelocity()
	{
		return 0.07F;
	}

	protected float func_70182_d()
	{
    	return 0.5F;
	}

	protected float func_70183_g()
	{
    	return -20.0F;
    }
	
	protected boolean canTriggerWalking()
	{
		return false;
	}


	public void onUpdate()
	{
		if(stopped)
		{
			return;
		}
		double prevVelX = motionX;
		double prevVelY = motionY;
    	double prevVelZ = motionZ;
    	prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        moveEntity(motionX, motionY, motionZ);

        // Take into account bouncing (normal displacement just sets them to 0)
        if(motionX!=prevVelX)
        {
        	motionX = -bounceFactor*prevVelX;
        }

        if(motionY!=prevVelY)
        {
        	motionY = -bounceFactor*prevVelY;
        }
        else
        {
        	motionY -= 0.04; //Apply gravity.
        }
        
        if(motionZ!=prevVelZ)
        {
        	motionZ = -bounceFactor*prevVelZ;
        }

        // Air friction
        motionX *= 0.99;
        motionY *= 0.99;
        motionZ *= 0.99;
 
        // Are we going to explode?
        if(ticksExisted++ >= 50)
        {
            explode();
        }
	}

	private void explode()
    {
    	if(!exploded)
    	{
	    	exploded = true;
	        worldObj.createExplosion(null, posX, posY, posZ, 4F, false);
	        setDead();
    	}
    }

	public void setDead()
	{
		this.isDead = true;
	}

	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setByte("Fuse", (byte)ticksExisted);
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);
		ticksExisted = nbttagcompound.getByte("Fuse");
	}

	public void onCollideWithPlayer(EntityPlayer entityplayer)
	{
		// Have to override the item's function
	}
 
	double bounceFactor;
	boolean exploded;
	boolean stopped;
}