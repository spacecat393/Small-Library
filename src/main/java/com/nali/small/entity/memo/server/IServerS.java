package com.nali.small.entity.memo.server;

import com.nali.small.entity.memo.server.si.frame.KeyS;

public interface IServerS
{
//	default void updateFrame()
//	{
//		for (FrameS[] frames_array : this.getKeyS2DArray())
//		{
//			for (FrameS frames : frames_array)
//			{
//				if (frames.onUpdate())
//				{
//					frames.sync();
//					break;
//				}
//			}
//		}
//	}

//	default <E extends Entity, I extends IMixE<?, ?, E>> void syncFrame(I i)
//	{
//		E e = i.getE();
//		EntityDataManager entitydatamanager = e.getDataManager();
//		DataParameter<Integer>[] integer_dataparameter = i.getIntegerDataParameterArray();
//		int[] frame_int_array = this.getFrameIntArray();
//		for (int f = 0; f < frame_int_array.length; ++f)
//		{
//			frame_int_array[f] = entitydatamanager.get(integer_dataparameter[f]);
//		}
//	}

	void initKey();
	KeyS[][] getKeyS2DArray();
//	byte[] getFrameByteArray();
	byte[] getKeyDataByteArray();
//	int[][] getFrame2DIntArray();
//	int[] getFrameIntArray();
}
