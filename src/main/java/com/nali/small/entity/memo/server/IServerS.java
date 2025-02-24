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

//	default <E extends		return (byte)
//		(
//			4 +//scale
//			1 +//inv
//			this.S_MaxFrame() * 2
//				//can't sync
////			this.S_MaxFrame() * 6//frame(byte) time(short) future_frame(byte) future_time(short)
//				//time(byte+short) future_time(byte+short)
////			this.S_MaxFrame() * 4//time(short) future_time(short)
//		);
//	}
//
//	@Override
//	public int O_StartPart()
//	{
//		return MODEL_STEP + 25;
//	}
//
//	@Override
//	public int O_EndPart()
//	{
//		return MODEL_STEP + 34;
//	}
//
//	@Override
//	public byte S_MaxFrame()
//	{
//		return 1;
//	}
//
//	@Override
//	public int S_FrameID()
//	{
//		return FRAME_STEP + 2;
//	}
//
//	@Override
//	public int NE_EAT()
//	{
//		return -1;
//	}
//
//	@Override
//	public int NE_HURT()
//	{
//		return -1;
//	}
//
//	@Override
//	public int NE_DEATH()
//	{
//		return -1;
//	}
//
//	@Override
//	public int NE_PAT()
//	{
//		return -1;
//	}
//
//	@Override
//	public int NE_SOFT_READY()
//	{
//		return -1;
//	}
//
//	@Override
//	public int NE_HARD_READY()
//	{
//		return -1;
//	}
//} Entity, I extends IMixE<?, ?, E>> void syncFrame(I i)
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
//	short[] getFixKeyShortArray();
//	int[][] getFrame2DIntArray();
//	int[] getFrameIntArray();
}
