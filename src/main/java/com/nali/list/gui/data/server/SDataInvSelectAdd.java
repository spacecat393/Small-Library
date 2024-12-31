package com.nali.list.gui.data.server;

import com.nali.Nali;
import com.nali.NaliConfig;
import com.nali.list.gui.data.client.CDataInvSelectAdd;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CPage;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class SDataInvSelectAdd
{
	public static byte ID;

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		//need mc thread
		if ((SDataInvSelect.STATE & 1) == 0)
		{
			SDataInvSelect.STATE |= 1;
			short inv = ByteReader.getShort(servermessage.data, 2);
			File inv_file = new File(entityplayermp.world.getSaveHandler().getWorldDirectory(), "nali/inv/" + inv);

			byte item_slot = servermessage.data[2+2];
			File inv_n_file = new File(inv_file, "" + item_slot);
	//		inv_n_file.mkdir();

			ItemStack itemstack = entityplayermp.inventory.getStackInSlot(item_slot);
			int count = itemstack.getCount();
			byte[] count_byte_array = new byte[];

			if ()
			{
				count +=;
			}
			try
			{
				Files.write(Paths.get(inv_n_file, );
			}
			catch (IOException e)
			{
				Nali.warn(e);
			}

			NBTTagCompound nbttagcompound = itemstack.getTagCompound();
			if (nbttagcompound != null)
			{
				byte[] nbt_byte_array = ByteWriter.serializeNBT(nbttagcompound);

				File nbt_n_file = new File(inv_file, "nbt/" + item_slot);
				nbt_n_file.mkdir();
				File[] nbt_i_file_array = nbt_n_file.listFiles();
				for (File nbt_i_file : nbt_i_file_array)
				{
					try
					{
						byte[] byte_array = Files.readAllBytes(nbt_i_file.toPath());
						if (Arrays.equals(nbt_byte_array, byte_array))
						{
							File nbt_n_size_file = new File(nbt_i_file + "s");
							break;
						}
					}
					catch (Exception e)
					{
						Nali.warn(e);
						nbt_i_file.delete();
					}
				}
			}
			entityplayermp.inventory.removeStackFromSlot(item_slot);

			NetworkRegistry.I.sendTo(new ClientMessage(new byte[]
			{
				CPage.ID,
				CDataInvSelectAdd.ID
			}), entityplayermp);
			SDataInvSelect.STATE &= 255-1;
		}
	}
}
