//package com.nali.small.entity.memo.server;
//
//import com.nali.da.IBothDaNe;
//import com.nali.da.IBothDaSn;
//import com.nali.small.entity.EntityE;
//import com.nali.small.entity.IMixE;
//import com.nali.small.entity.memo.server.ai.MixAIE;
//
//public abstract class ServerSe<SD, BD extends IBothDaNe & IBothDaSn, E extends EntityE, I extends IMixE<SD, BD, E>, A extends MixAIE<SD, BD, E, I, ?>> extends ServerE<SD, BD, E, I, MS> implements IServerS
//{
//	public int[] frame_int_array;
//
//	public ServerSe(I i)
//	{
//		super(i);
//		this.frame_int_array = new int[i.getIntegerDataParameterArray().length];
//	}
//
//	@Override
//	public void onUpdate()
//	{
////		this.syncFrame(this.i);
//		super.onUpdate();
//		this.updateFrame();
//	}
//
//	@Override
//	public int[] getFrameIntArray()
//	{
//		return frame_int_array;
//	}
//}
