package com.nali.list.da;

import com.nali.da.IBothDaO;

import static com.nali.list.data.SmallData.MODEL_STEP;

public class BothDaStorage implements IBothDaO
{
	public static BothDaStorage IDA = new BothDaStorage();

	@Override
	public int O_StartPart()
	{
		return MODEL_STEP + 3;
	}

	@Override
	public int O_EndPart()
	{
		return MODEL_STEP + 5;
	}
}
