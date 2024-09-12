//package com.nali.small.entity.memo.server;
//
//import com.nali.da.IBothDaNe;
//import com.nali.da.IBothDaSn;
//import com.nali.small.entity.EntityLeInv;
//import com.nali.small.entity.IMixE;
//import com.nali.small.entity.Inventory;
//import com.nali.small.entity.memo.IBothLeInv;
//import com.nali.small.entity.memo.server.ai.MixAIE;
//import com.nali.sound.ISoundDaLe;
//import net.minecraft.item.ItemStack;
//import net.minecraft.network.datasync.EntityDataManager;
//
//import static com.nali.small.entity.EntityLeInv.MOUTH_ITEMSTACK_DATAPARAMETER;
//
//public abstract class ServerSleInv<SD extends ISoundDaLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLeInv, I extends IMixE<SD, BD, E>, A extends MixAIE<SD, BD, E, I, ?>> extends ServerSle<SD, BD, E, I, MS> implements IBothLeInv<SD, BD, E, I>
//{
//	public Inventory inventory;
//
//	public ServerSleInv(I i, Inventory inventory)
//	{
//		super(i);
//		this.inventory = inventory;
//	}
//
////	@Override
////	public void onUpdate()
////	{
////		super.onUpdate();
////		ItemStack mouth_itemstack = this.inventory.offset_itemstack_nonnulllist.get(0);
////		EntityDataManager entitydatamanager = this.i.getE().getDataManager();
////		if (mouth_itemstack.isEmpty())
////		{
////			entitydatamanager.set(MOUTH_ITEMSTACK_DATAPARAMETER, ItemStack.EMPTY);
////		}
////		else
////		{
////			entitydatamanager.set(MOUTH_ITEMSTACK_DATAPARAMETER, mouth_itemstack);
////		}
////	}
//
//	@Override
//	public Inventory getInventory()
//	{
//		return this.inventory;
//	}
//}
