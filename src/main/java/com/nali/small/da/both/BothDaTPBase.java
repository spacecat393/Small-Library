package com.nali.small.da.both;

import com.nali.da.IBothDaSn;

public class BothDaTPBase implements IBothDaSn
{
	public static byte MAX_FRAME = 1;

	@Override
	public byte MaxFrame()
	{
		return MAX_FRAME;
	}
}
