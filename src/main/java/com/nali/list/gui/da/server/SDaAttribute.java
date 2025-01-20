package com.nali.list.gui.da.server;

import com.nali.list.gui.da.client.CDaAttribute;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CPage;
import com.nali.network.NetworkRegistry;
import com.nali.small.SmallAttribute;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.ArrayList;
import java.util.List;

public class SDaAttribute
{
	public static byte ID;

	public final static byte MAX_SIZE = 120;
	public final static byte I_MORE = 0;
	public final static byte I_LESS = 1;
	public final static byte I_FETCH = 2;

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		byte page = servermessage.data[3];
		int attribute_size;

		ServerE s = ServerE.S_MAP.get(ByteReader.getLong(servermessage.data, 4));
		Entity e = s.i.getE();

		List<IAttributeInstance> list = null;
		if (e instanceof EntityLivingBase)
		{
			EntityLivingBase le = (EntityLivingBase)e;
			list = new ArrayList(le.getAttributeMap().getAllAttributes());
			attribute_size = list.size();
		}
		else
		{
			attribute_size = 0;
		}

		if (servermessage.data[2] == I_MORE)
		{
			if (((page + 1) * MAX_SIZE) < attribute_size)
			{
				++page;
				servermessage.data[2] = I_FETCH;
			}
		}
		else if (servermessage.data[2] == I_LESS)
		{
			byte new_page = (byte)(page - 1);
			if (new_page != -1)
			{
				if ((new_page * MAX_SIZE) < attribute_size)
				{
					--page;
					servermessage.data[2] = I_FETCH;
				}
			}
		}

		if (servermessage.data[2] == I_FETCH)
		{
			byte max_mix_page = (byte)Math.ceil(attribute_size / (float)MAX_SIZE);
			byte max_page;

			if (max_mix_page > 0)
			{
				max_mix_page -= 1;
			}

			if (page == max_mix_page)
			{
				byte left = (byte)(attribute_size % MAX_SIZE);
				if (left == 0 && attribute_size > 0)
				{
					max_page = MAX_SIZE;
				}
				else
				{
					max_page = left;
				}
			}
			else
			{
				max_page = MAX_SIZE;
			}

			short byte_array_index = 2;
			int new_page = page * MAX_SIZE;
			byte[] byte_array = new byte[1 + 1 + max_page * 4 + 1 + 1 + 1];
			byte_array[0] = CPage.ID;
			byte_array[1] = CDaAttribute.ID;
//			if (list != null)
//			{
			for (int i = new_page; i < new_page + max_page; ++i)
			{
				ByteWriter.set(byte_array, SmallAttribute.IATTRIBUTE_INDEX_MAP.get(list.get(i).getAttribute()), byte_array_index);
				byte_array_index += 4;
			}
//			}
			byte_array[byte_array_index++] = page;//page
			byte_array[byte_array_index++] = max_page;//max_page
			byte_array[byte_array_index] = max_mix_page;//max_mix_page
			NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
		}
	}
}
