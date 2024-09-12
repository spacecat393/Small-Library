package com.nali.small.entity.memo.server.si.path;

public class PathMath
{
	public static byte[] PATH_BYTE_ARRAY = { -1, 0, 1 };

	public static byte getIndex(byte x, byte y, byte z)
	{
		byte index = 0;

		for (byte xi : PATH_BYTE_ARRAY)
		{
			for (byte yi : PATH_BYTE_ARRAY)
			{
				for (byte zi : PATH_BYTE_ARRAY)
				{
					if (!(xi == 0 && yi == 0 && zi == 0))
					{
						if (xi == x && yi == y && zi == z)
						{
							return index;
						}

						++index;
					}
				}
			}
		}

		return -1;
	}

	public static byte signum(double i)
	{
		if (i > 0.0D)
		{
			return (byte)1;
		}
		else
		{
			return i < 0.0D ? (byte)-1 : (byte)0;
		}
	}
}
