package com.nali.list.network.method.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.IBothE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.system.bytes.ByteReader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class CCI
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		long key = ByteReader.getLong(clientmessage.data, 1);
		if (Minecraft.getMinecraft().world.provider.getDimension() == (int)(key >> 32))
		{
			Entity entity = Minecraft.getMinecraft().world.getEntityByID((int)key);
			if (entity instanceof IMixE)
			{
				IBothE b = ((IMixE)entity).getB();
				if (b instanceof ClientE)
				{
					ClientE c = (ClientE)b;
					c.mc.byte_array = clientmessage.data;
					c.mc.call(c.mc.byte_array[1 + 8]);
				}
			}
		}
	}
}
