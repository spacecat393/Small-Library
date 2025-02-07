package com.nali.list.gui.da.server;

import com.nali.Nali;
import com.nali.list.gui.da.client.CDaInvSelectItem;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CPageDa;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.player.EntityPlayerMP;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SDaInvSelectItem
{
	public static byte ID;

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		if ((SDaInvSelect.STATE & 1) == 0)
		{
			SDaInvSelect.STATE |= 1;
			int inv = ByteReader.getInt(servermessage.data, 2);
			int item_id = ByteReader.getInt(servermessage.data, 2+4);

			File inv_file = new File(entityplayermp.world.getSaveHandler().getWorldDirectory(), "nali/inv/" + inv);
			File inv_n_file = new File(inv_file, "" + item_id);

			try
			{
				byte[] byte_array = new byte[1 + 1 + 8];
				byte_array[0] = CPageDa.ID;
				byte_array[1] = CDaInvSelectItem.ID;
				ByteWriter.set(byte_array, ByteReader.getLong(Files.readAllBytes(inv_n_file.toPath()), 0), 2);
				NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
			}
			catch (IOException e)
			{
				Nali.warn(e);
			}
			SDaInvSelect.STATE &= 255-1;
		}
	}
}
