//package com.nali.small.entity.memo.server;
//
//import com.nali.da.IBothDaNe;
//import com.nali.da.IBothDaSn;
//import com.nali.small.entity.EntityE;
//import com.nali.small.entity.IMixE;
//import com.nali.small.entity.Inventory;
//import com.nali.small.entity.memo.IBothEInv;
//import com.nali.small.entity.memo.server.ai.MixAIE;
//
//public abstract class ServerSeInv<SD, BD extends IBothDaNe & IBothDaSn, E extends EntityE, I extends IMixE<SD, BD, E>, A extends MixAIE<SD, BD, E, I, ?>> extends ServerSe<SD, BD, E, I, MS> implements IBothEInv<SD, BD, E, I>
//{
//	public Inventory inventory;
//
//	public ServerSeInv(I i, Inventory inventory)
//	{
//		super(i);
//		this.inventory = inventory;
//	}
//
//	@Override
//	public Inventory getInventory()
//	{
//		return this.inventory;
//	}
//}
