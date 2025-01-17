//package com.nali.list.gui.data.server;
//
//import com.nali.Nali;
//import com.nali.list.gui.data.client.CDataInvSelect;
//import com.nali.list.network.message.ClientMessage;
//import com.nali.list.network.message.ServerMessage;
//import com.nali.list.network.method.client.CPage;
//import com.nali.network.NetworkRegistry;
//import com.nali.system.bytes.ByteReader;
//import com.nali.system.bytes.ByteWriter;
//import net.minecraft.entity.player.EntityPlayerMP;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.stream.Stream;
//
//public class SDataInvSelectItem
//{
//	public static byte ID;
//	public static byte STATE;//delete/add/move
//
//	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
//	{
//		//get item from file | 119
//		short item_id = ByteReader.getShort(servermessage.data, 4);
//		File inv_file = new File(entityplayermp.world.getSaveHandler().getWorldDirectory(), "nali/inv/" + item_id);
////		String[] inv_string_array = null;
////		String[] inv_string_array = inv_file.list();
//		long max_inv_file = 0;
//		try (Stream<Path> path_stream = Files.list(inv_file.toPath()))
//		{
//			max_inv_file = path_stream.count();
//		}
//		catch (IOException e)
//		{
//			Nali.warn(e);
//		}
//		byte page = servermessage.data[3];
//
//		if (servermessage.data[2] == 0)//more
//		{
//			if (((page + 1) * 119) < max_inv_file)
//			{
//				++page;
//				servermessage.data[2] = 2;
//			}
//		}
//		else if (servermessage.data[2] == 1)//less
//		{
//			byte new_page = (byte)(page - 1);
//			if (new_page != -1)
//			{
//				if ((new_page * 119) < max_inv_file)
//				{
//					--page;
//					servermessage.data[2] = 2;
//				}
//			}
//		}
//		else if (servermessage.data[2] == 3 && (STATE & 1) == 0)//delete
//		{
//			STATE |= 1;
//			short delete_item_id = ByteReader.getShort(servermessage.data, 4+2);
//
//			STATE &= 255-1;
//		}
////		else if (servermessage.data[2] == 4)//add
////		{
////			short add_item_id = ByteReader.getShort(servermessage.data, 4+2);
////
////		}
//		else if (servermessage.data[2] == 5 && (STATE & 1) == 0)//move
//		{
//			STATE |= 1;
//			short move_item_id = ByteReader.getShort(servermessage.data, 4+2);
//
//			STATE &= 255-1;
//		}
//		String[] inv_string_array = inv_file.list();
//
//		if (servermessage.data[2] == 2)//fetch
//		{
//			byte max_mix_page = (byte)Math.ceil(max_inv_file / 119.0F);
//			byte max_page;
//
//			if (max_mix_page > 0)
//			{
//				max_mix_page -= 1;
//			}
//
//			if (page == max_mix_page)
//			{
//				byte left = (byte)(max_inv_file % 119);
//				if (left == 0 && max_inv_file > 0)
//				{
//					max_page = 119;
//				}
//				else
//				{
//					max_page = left;
//				}
//			}
//			else
//			{
//				max_page = 119;
//			}
//
//			short byte_array_index = 2;
//			int new_page = page * 119;
//			int i_page = new_page + max_page;
//
//			byte[] byte_array = new byte[1 + 1 + max_page * 2 + 1 + 1 + 1];
//			byte_array[0] = CPage.ID;
//			byte_array[1] = CDataInvSelect.ID;
//			for (int i = new_page; i < i_page; ++i)
//			{
//				ByteWriter.set(byte_array, Short.parseShort(inv_string_array[i]), byte_array_index);
//				byte_array_index += 2;
//			}
//			byte_array[byte_array_index++] = page;//page
//			byte_array[byte_array_index++] = max_page;//max_page
//			byte_array[byte_array_index] = max_mix_page;//max_mix_page
//			NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
//		}
//	}
//}
