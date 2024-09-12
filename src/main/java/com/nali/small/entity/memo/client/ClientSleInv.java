//package com.nali.small.entity.memo.client;
//
//import com.nali.da.IBothDaNe;
//import com.nali.da.IBothDaSn;
//import com.nali.da.client.IClientDaS;
//import com.nali.render.RenderS;
//import com.nali.small.entity.IMixE;
//import com.nali.small.entity.Inventory;
//import com.nali.small.entity.memo.IBothLeInv;
//import com.nali.small.entity.memo.client.box.mix.MixBoxSle;
//import com.nali.small.entity.memo.client.render.mix.MixRenderSleInv;
//import com.nali.sound.ISoundDaLe;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//@SideOnly(Side.CLIENT)
//public abstract class ClientSleInv<RC extends IClientDaS, R extends RenderS<BD, RC>, SD extends ISoundDaLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLivingBase, I extends IMixE<SD, BD, E>, MB extends MixBoxSle<RC, R, SD, BD, E, I, MR, ?>, MR extends MixRenderSleInv<RC, R, SD, BD, E, I, MB, ?>> extends ClientSle<RC, R, SD, BD, E, I, MB, MR> implements IBothLeInv<SD, BD, E, I>
//{
//	public Inventory inventory;
//
//	public ClientSleInv(I i, R r, Inventory inventory)
//	{
//		super(i, r);
//		this.inventory = inventory;
//	}
//
//	public Inventory getInventory()
//	{
//		return this.inventory;
//	}
//}
