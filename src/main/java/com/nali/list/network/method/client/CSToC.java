package com.nali.list.network.method.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.system.bytes.ByteReader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.world.World;

import java.lang.reflect.InvocationTargetException;

import static com.nali.Nali.warn;
import static com.nali.small.entity.memo.client.ClientE.C_MAP;

public class CSToC
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
//		ClientE.UUID_MAP.clear();
		C_MAP.clear();

		for (int i = 1; i < clientmessage.data.length; i += 4)
		{
//			UUID uuid = ByteReader.getUUID(clientmessage.data, i);
//			i += 16;
			long key = ByteReader.getLong(clientmessage.data, i);
			i += 8;

			int list_id = ByteReader.getInt(clientmessage.data, i);
			i += 4;

			World world = Minecraft.getMinecraft().player.world;

			ClientE cliente;
			Entity entity = world.getEntityByID(list_id);

			if (entity instanceof IMixE)
			{
				IMixE imixe = (IMixE)entity;
				cliente = (ClientE)imixe.getB();
			}
			else
			{
				try
				{
					int entity_id = ByteReader.getInt(clientmessage.data, i);
					cliente = (ClientE)EntityList.getClassFromID(entity_id).getMethod("getC").invoke(null);
					cliente.mc.fake = true;
//					cliente.uuid = uuid;
					cliente.key = key;
					cliente.onReadNBT();
				}
				catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
				{
					warn(e);
					continue;
				}
			}

//			ClientE.UUID_MAP.put(list_id, uuid);

//			C_MAP.put(uuid, cliente);
			C_MAP.put(key, cliente);
		}
	}
}
