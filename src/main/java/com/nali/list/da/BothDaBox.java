package com.nali.list.da;

import com.nali.da.IBothDaO;

import static com.nali.list.data.SmallData.MODEL_STEP;

public class BothDaBox implements IBothDaO
{
	public static BothDaBox IDA = new BothDaBox();

	@Override
	public int O_StartPart()
	{
		return MODEL_STEP/* + 0*/;
	}

	@Override
	public int O_EndPart()
	{
		return MODEL_STEP + 3;
	}
}