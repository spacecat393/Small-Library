package com.nali.small;

import com.nali.small.chunk.ChunkCallBack;
import com.nali.small.chunk.ChunkData;
import com.nali.small.entity.EntityRegistry;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.mix.block.tile.TileRegistry;
import com.nali.small.world.WorldRegistry;
import com.nali.system.bytes.ByteReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.nali.Nali.warn;
import static com.nali.small.chunk.ChunkCallBack.CHUNK_LIST;

@Mod(modid = Small.ID)
public class Small
{
	public final static String ID = "small";

	@SideOnly(Side.CLIENT)
	public static byte FLAG;//blink

	@Instance
	public static Small I;

//	@EventHandler
//	public void onFMLPreInitializationEvent(FMLPreInitializationEvent event)
//	{
//		if (event.getSide().isClient())
//		{
//			Page.genState();
//		}
//	}

	@EventHandler
	public void onFMLInitializationEvent(FMLInitializationEvent event)
	{
		EntityRegistry.set();
		WorldRegistry.set();
//		CapabilityRegistry.register();
//		NetworkRegistry.INSTANCE.registerGuiHandler(I, new GuiHandler());
	}

	@EventHandler
	public void onFMLPostInitializationEvent(FMLPostInitializationEvent event)
	{
////		EntityRegistry.ENTITY_KEY_ARRAY = new HashSet(ENTITY_CLASS_ENTRIES.keySet()).toArray();
//
//		if (event.getSide().isClient())
//		{
//			byte size = 31;
//			String t_string = "info." + Small.ID + ".t";
//			STRING_ARRAY = new String[size];
////			FontRenderer fontrenderer = Minecraft.getMinecraft().fontRenderer;
//			for (byte i = 0; i < size; ++i)
//			{
////				String string = I18n.translateToLocal(t_string + i);
////				STRING_ARRAY[i] = string;
////				byte tw = (byte)fontrenderer.getStringWidth(string);
////				if (MAX_TW < tw)
////				{
////					MAX_TW = tw;
////				}
//				STRING_ARRAY[i] = I18n.translateToLocal(t_string + i);
//			}
////			RenderHelper.init();
//		}
//
////		EntitiesRegistry.ENTITIES_CLASS_LIST = null;
		TileRegistry.TILES_CLASS_LIST = null;
	}

	@EventHandler
	public void onFMLServerAboutToStartEvent(FMLServerAboutToStartEvent event)
	{
//		SDaInvSelect.RUNNABLE_LIST = new ArrayList();

//		ServerE.S_MAP = new HashMap();
		MixSIE.MS_MAP = new HashMap();
		ChunkCallBack.set();
	}

	@EventHandler
	public void onFMLServerStartedEvent(FMLServerStartedEvent event)
	{
		WorldServer[] worldserver_array = FMLCommonHandler.instance().getMinecraftServerInstance().worlds;
		File world_file = worldserver_array[0].getSaveHandler().getWorldDirectory();
		File file = new File(world_file, "nali");
		file.mkdirs();

		file = new File(file, "entity");
		file.mkdirs();

		//s0-playerdata
//		PlayerData.read(world_file);
		//e0-playerdata

		File[] d_file_array = file.listFiles();
		if (d_file_array != null)
		{
			for (File d_file : d_file_array)
			{
				File[] i_file_array = d_file.listFiles();
				if (i_file_array != null)
				{
					for (File i_file : i_file_array)
					{
						if (i_file.isFile())
						{
							try
							{
								byte[] byte_array = Files.readAllBytes(i_file.toPath());
								WorldServer worldserver = worldserver_array[ByteReader.getInt(byte_array, 0)];
								BlockPos blockpos = BlockPos.fromLong(ByteReader.getLong(byte_array, 4));

								ChunkData chunkdata = new ChunkData();
								chunkdata.world = worldserver;
								chunkdata.chunkpos = new ChunkPos(blockpos);
								chunkdata.ticket = ForgeChunkManager.requestTicket(Small.I, chunkdata.world, ForgeChunkManager.Type.NORMAL);

								if (chunkdata.ticket != null)
								{
									ForgeChunkManager.forceChunk(chunkdata.ticket, chunkdata.chunkpos);
									CHUNK_LIST.add(chunkdata);
								}
							}
							catch (Exception e)
							{
								warn(e);
								i_file.delete();
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onFMLServerStoppingEvent(FMLServerStoppingEvent event)
	{
		File world_file = DimensionManager.getCurrentSaveRootDirectory();
		//s0-playerdata
//		PlayerData.write(world_file);
		//e0-playerdata

//		List<ServerE> s_list = new ArrayList(ServerE.S_MAP.values());
		List<MixSIE> ms_list = new ArrayList(MixSIE.MS_MAP.values());
//		if (!s_list.isEmpty())
		if (!ms_list.isEmpty())
		{
//			File[] d_file_array = new File(s_list.get(0).worldserver.getSaveHandler().getWorldDirectory(), "nali/entity").listFiles();
			File[] d_file_array = new File(world_file, "nali/entity").listFiles();

			if (d_file_array != null)
			{
				for (File d_file : d_file_array)
				{
					File[] i_file_array = d_file.listFiles();
					if (i_file_array != null)
					{
						for (File i_file : i_file_array)
						{
							File[] id_file_array = i_file.listFiles();
							if (id_file_array != null)
							{
								for (File id_file : id_file_array)
								{
									id_file.delete();
								}
							}
							i_file.delete();
						}
					}

					d_file.delete();
				}
			}

//			for (ServerE servere : s_list)
			for (MixSIE ms : ms_list)
			{
//				servere.writeFile();
				ms.s.writeFile();
			}
		}

//		ServerE.S_MAP = null;
		MixSIE.MS_MAP = null;
		ChunkCallBack.free();

//		SDaInvSelect.RUNNABLE_LIST = null;
	}
}
