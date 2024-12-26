//package com.nali.list.da;
//
//import com.nali.da.IBothDaO;
//import com.nali.da.IBothDaS;
//
//import static com.nali.list.data.SmallData.FRAME_STEP;
//import static com.nali.list.data.SmallData.MODEL_STEP;
//
//public class BothDaTPBase implements IBothDaO, IBothDaS
//{
//	public static BothDaTPBase IDA;
//
//	@Override
//	public int O_StartPart()
//	{
//		return MODEL_STEP + 5;
//	}
//
//	@Override
//	public int O_EndPart()
//	{
//		return MODEL_STEP + 8;
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
//		return FRAME_STEP/* + 0*/;
//	}
//}
