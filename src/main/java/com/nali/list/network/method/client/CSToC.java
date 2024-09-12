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
import java.util.UUID;

import static com.nali.Nali.warn;
import static com.nali.small.entity.memo.client.ClientE.C_MAP;

public class CSToC
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		ClientE.UUID_MAP.clear();
		C_MAP.clear();

		for (int i = 1; i < clientmessage.data.length; i += 4)
		{
			UUID uuid = ByteReader.getUUID(clientmessage.data, i);
			i += 16;

			int list_id = ByteReader.getInt(clientmessage.data, i);
			i += 4;

			World world = Minecraft.getMinecraft().player.world;
//			Entity entity = world.getEntityByID(list_id);
//			ClientE cliente = C_MAP.get(uuid);
			ClientE cliente;
//			if (cliente == null)
//			{
			Entity entity = world.getEntityByID(list_id);
//			if (entity == null)
//			{
//				try
//				{
//					int entity_id = ByteReader.getInt(clientmessage.data, i);
//					entity = EntityList.getClassFromID(entity_id).getConstructor(World.class).newInstance(world);
//				}
//				catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
//				{
//					Nali.I.warn(e);
//				}
//			}
//			int dimension = ByteReader.getInt(clientmessage.data, i);
//			i += 4;

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
					cliente.uuid = uuid;
					cliente.onReadNBT();
				}
				catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
				{
					warn(e);
					continue;
				}
			}

//			cliente.dimension = dimension;
//			}
//			else
//			{
//			int entity_id = ByteReader.getInt(clientmessage.data, i);
			ClientE.UUID_MAP.put(list_id, uuid);
//			try
//			{
//				entity = EntityList.getClassFromID(entity_id).getConstructor(World.class).newInstance(world);
//				IMixE imixe = (IMixE)entity;
//				cliente = imixe.getB();
//			cliente.fake = true;
//			cliente.uuid = uuid;
//							NBTTagCompound nbttagcompound = new NBTTagCompound();
//							skinningentities.initWriteEntityToNBT(nbttagcompound);
//							for (int ii = 0; ii < skinningentities.bothdata.MaxPart(); ++ii)
//							{
//								((ObjectRender)skinningentities.client_object).texture_index_int_array[ii] = nbttagcompound.getInteger("int_" + ii);
//							}
//							skinningentities.initWriteEntityToNBT(nbttagcompound);
//			cliente.initFakeFrame();
//			}
//			catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
//			{
//				Nali.I.warn(e);
//			}
//		}
			C_MAP.put(uuid, cliente);
		}
	}
}
