package com.nali.list.capability.factory;

import com.nali.list.capability.type.SmallSakuraType;

public class SmallSakuraFactory implements SmallSakuraType
{
	public byte sakura_byte = 0;

	@Override
	public byte get()
	{
		return this.sakura_byte;
	}

	@Override
	public void set(byte i)
	{
		this.sakura_byte = i;
	}

	@Override
	public void add(byte i)
	{
		if (this.sakura_byte + i < Byte.MAX_VALUE/*Integer.MAX_VALUE*/)
		{
			this.sakura_byte += i;
		}
	}
}
