package com.nali.small.entity.memo;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.render.FRenderE;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IBothE<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>>
{
	default boolean processInitialInteract(EntityPlayer entityplayer, EnumHand enumhand)
	{
		entityplayer.swingArm(enumhand);
		return true;
	}

	default boolean isMove(E e)
	{
		return !e.isDead;
	}

	void onUpdate();
	//server
	void writeFile();
	void readFile();
	//
//	void writeEntityToNBT(NBTTagCompound nbttagcompound);
//	void readEntityFromNBT(NBTTagCompound nbttagcompound);

//	void add();
//	void remove();

	//	@SideOnly(Side.CLIENT)
//	void setGlowing(boolean b);
	@SideOnly(Side.CLIENT)
	void onReadNBT();//need check again
	@SideOnly(Side.CLIENT)
	void setShouldRender(boolean result);
	@SideOnly(Side.CLIENT)
	void doRender(FRenderE<E> rendere, double ox, double oy, double oz, float partialTicks);
//	@SideOnly(Side.CLIENT)
//	void doContainer();

//	@SideOnly(Side.CLIENT)//will remove later
//	void setUUID(UUID uuid);
//	@SideOnly(Side.CLIENT)
//	void playSound(int i);

//	I getI();
}
