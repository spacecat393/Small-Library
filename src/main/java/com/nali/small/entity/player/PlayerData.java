package com.nali.small.entity.player;

import com.nali.Nali;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.UUID;

import static com.nali.Nali.warn;

public class PlayerData
{
	public static Map<UUID, Byte> SAKURA_MAP;

	public static void read(File world_file)
	{
		File player_file = new File(world_file, "nali/player");
		player_file.mkdir();

		File[] file_array = player_file.listFiles();
		for (File file : file_array)
		{
			try
			{
				UUID player_uuid = UUID.fromString(file.getName());
				byte[] byte_array = Files.readAllBytes(file.toPath());
				SAKURA_MAP.put(player_uuid, byte_array[0]);
			}
			catch (Exception e)
			{
				warn(e);
				file.delete();
			}
		}
	}

	public static void write(File world_file)
	{
		File player_file = new File(world_file, "nali/player");
//		file.mkdirs();

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
	}
}
