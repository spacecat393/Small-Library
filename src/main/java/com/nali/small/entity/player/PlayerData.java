package com.nali.small.entity.player;

import com.nali.Nali;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static com.nali.Nali.warn;

public class PlayerData
{
	public static Map<UUID, Byte> SAKURA_MAP;
//	public static List<Short> INV_SHORT_LIST;

	public static void read(File world_file)
	{
		PlayerData.SAKURA_MAP = new HashMap();
//		PlayerData.INV_SHORT_LIST = new ArrayList();

		File file = new File(world_file, "nali/player");
		file.mkdir();

		File[] file_array = file.listFiles();
		for (File f : file_array)
		{
			try
			{
				UUID player_uuid = UUID.fromString(f.getName());
				byte[] byte_array = Files.readAllBytes(f.toPath());
				SAKURA_MAP.put(player_uuid, byte_array[0]);
			}
			catch (Exception e)
			{
				warn(e);
				f.delete();
			}
		}

//		file = new File(world_file, "nali/inv");
//		file.mkdir();
//
//		file_array = file.listFiles();
//		for (File f : file_array)
//		{
//			INV_SHORT_LIST.add(Short.parseShort(f.getName()));
//		}
	}

	public static void write(File world_file)
	{
		File player_file = new File(world_file, "nali/player");

		for (File file : player_file.listFiles())
		{
			file.delete();
		}

		for (UUID uuid : SAKURA_MAP.keySet())
		{
			try
			{
				Files.write(new File(player_file, uuid.toString()).toPath(), new byte[]{SAKURA_MAP.get(uuid)});
			}
			catch (IOException e)
			{
				Nali.error(e);
			}
		}

		PlayerData.SAKURA_MAP = null;
//		PlayerData.INV_SHORT_LIST = null;
	}
}
