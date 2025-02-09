package com.nali.list.gui.da.server;

import com.nali.Nali;
import com.nali.list.gui.da.client.CDaInvSelect;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CPageDa;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class SDaInvSelect
{
	public static byte ID;
	public static byte STATE;//delete/add/move
	public static List<Runnable> RUNNABLE_LIST;

	public final static byte MAX_SIZE = 117;
	public final static byte B_MORE = 0;
	public final static byte B_LESS = 1;
	public final static byte B_FETCH = 2;
	public final static byte B_DELETE = 3;
	public final static byte B_MOVE = 4;

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		//get item from file | 119
		int inv = ByteReader.getInt(servermessage.data, 3+4);
		File inv_file = new File(entityplayermp.world.getSaveHandler().getWorldDirectory(), "nali/inv/" + inv);
//		String[] inv_string_array = null;
//		String[] inv_string_array = inv_file.list();
		long max_inv_file = 0;
		try (Stream<Path> path_stream = Files.list(inv_file.toPath()))
		{
			max_inv_file = path_stream.count() - 1;
		}
		catch (IOException e)
		{
			Nali.warn(e);
		}
		int page = ByteReader.getInt(servermessage.data, 3);

		switch (servermessage.data[2])
		{
			case B_MORE:
				if (((long)(page + 1) * MAX_SIZE) < max_inv_file)
				{
					++page;
					servermessage.data[2] = B_FETCH;
				}
				break;
			case B_LESS:
				int new_page = page - 1;
				if (new_page != -1)
				{
					if (((long)new_page * MAX_SIZE) < max_inv_file)
					{
						--page;
						servermessage.data[2] = B_FETCH;
					}
				}
				break;
			case B_DELETE:
				if ((STATE & 1) == 0)
				{
					STATE |= 1;
					int delete_item_id = ByteReader.getInt(servermessage.data, 3+4+4);
					File file = new File(inv_file, "" + delete_item_id);
					File nbt_n_file = new File(inv_file, "nbt/" + delete_item_id);
					File[] nbt_n_file_array =  nbt_n_file.listFiles();
					if (nbt_n_file_array != null)
					{
						for (File nbt_i_file : nbt_n_file_array)
						{
							nbt_i_file.delete();
						}
					}
					nbt_n_file.delete();
					file.delete();

//			servermessage.data[2] = B_FETCH;
					STATE &= 255-1;
				}
				break;
			case B_MOVE:
				RUNNABLE_LIST.add(() ->
				{
					if ((STATE & 1) == 0)
					{
						STATE |= 1;
						int move_item_id = ByteReader.getInt(servermessage.data, 3+4+4);
						long nbt = ByteReader.getLong(servermessage.data, 3+4+4+4);
						File item_file = new File(inv_file, "" + move_item_id);
						try
						{
							ItemStack itemstack = new ItemStack(Item.getItemById(move_item_id));
							byte[] byte_array = Files.readAllBytes(item_file.toPath());
							long count = ByteReader.getLong(byte_array, 0);
							int will_take_count;
							long take_count;
							int final_take;

							NBTTagCompound nbttagcompound = null;
							if (nbt == -1)
							{
								File nbt_n_file = new File(inv_file, "nbt/" + move_item_id);
								File[] nbt_n_file_array = nbt_n_file.listFiles();
								if (nbt_n_file_array != null)
								{
									for (File nbt_i_file : nbt_n_file_array)
									{
										nbttagcompound = ByteReader.deserializeNBT(Files.readAllBytes(nbt_i_file.toPath()));
										nbt_i_file.delete();
										nbt = 0;
										break;
									}
								}
							}
							if (nbt != -1)
							{
								will_take_count = 1;
								take_count = count - 1;

								final_take = 1;
							}
							else
							{
								will_take_count = itemstack.getMaxStackSize();
								take_count = count - will_take_count;

								if (take_count < 0)
								{
									final_take = (int)count;
								}
								else
								{
									final_take = will_take_count;
								}
							}

							itemstack.setCount(final_take);
							if (nbttagcompound != null)
							{
								itemstack.setTagCompound(nbttagcompound);
							}

							if (final_take == will_take_count && take_count != 0)
							{
								ByteWriter.set(byte_array, take_count, 0);
								Files.write(item_file.toPath(), byte_array);
							}
							else
							{
								item_file.delete();
							}

							if (!entityplayermp.inventory.addItemStackToInventory(itemstack))
							{
								entityplayermp.world.spawnEntity(new EntityItem(entityplayermp.world, entityplayermp.posX, entityplayermp.posY, entityplayermp.posZ, itemstack));
							}
						}
						catch (IOException e)
						{
							Nali.warn(e);
							item_file.delete();
						}

						STATE &= 255-1;
					}
				});
		}

		if (servermessage.data[2] == B_FETCH)
		{
			String[] inv_string_array = inv_file.list();
			int max_mix_page = (int)Math.ceil(max_inv_file / (float)MAX_SIZE);
			byte max_page;

			if (max_mix_page > 0)
			{
				max_mix_page -= 1;
			}

			if (page == max_mix_page)
			{
				byte left = (byte)(max_inv_file % MAX_SIZE);
				if (left == 0 && max_inv_file > 0)
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
			int i_page = new_page + max_page;

			byte[] byte_array = new byte[1 + 1 + max_page * 4 + 4 + 1 + 4];
			byte_array[0] = CPageDa.ID;
			byte_array[1] = CDaInvSelect.ID;

			for (int i = new_page; i < i_page; ++i)
			{
				try
				{
					ByteWriter.set(byte_array, Integer.parseInt(inv_string_array[i]), byte_array_index);
					byte_array_index += 4;
				}
				catch (Exception e)
				{
					++i_page;
				}
			}
			ByteWriter.set(byte_array, page, byte_array_index);
			byte_array_index += 4;
			byte_array[byte_array_index++] = max_page;//max_page
			ByteWriter.set(byte_array, max_mix_page, byte_array_index);
			NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
		}
	}
}
