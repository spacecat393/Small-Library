package com.nali.small;

import com.nali.small.capability.CapabilityRegistry;
import com.nali.small.chunk.ChunkCallBack;
import com.nali.small.chunk.ChunkData;
import com.nali.small.entity.EntityRegistry;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.gui.GuiHandler;
import com.nali.small.tile.TileRegistry;
import com.nali.system.bytes.ByteReader;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.lwjgl.opengl.GL11;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.nali.Nali.warn;
import static com.nali.list.container.gui.SmallGui.*;
import static com.nali.small.chunk.ChunkCallBack.CHUNK_LIST;
import static com.nali.small.gui.page.Page.STRING_ARRAY;

@Mod(modid = Small.ID)
public class Small
{
	public final static String ID = "small";

	@Instance
	public static Small I;

	@EventHandler
	public void onFMLPreInitializationEvent(FMLPreInitializationEvent event)
	{
		if (event.getSide().isClient())
		{
			OFFSET_FRAMEBUFFER = OpenGlHelper.glGenFramebuffers();
			OFFSET_FRAMEBUFFER_0 = OpenGlHelper.glGenFramebuffers();
			OFFSET_FRAMEBUFFER_1 = OpenGlHelper.glGenFramebuffers();
			OFFSET_FRAMEBUFFER_TEXTURE = GL11.glGenTextures();
			OFFSET_FRAMEBUFFER_TEXTURE_0 = GL11.glGenTextures();

			OFFSET_RENDER_BUFFER = OpenGlHelper.glGenRenderbuffers();
//////			DataLoader.setModels(RenderHelper.DATALOADER, Small.ID);
//////			CapabilitiesRegistryHelper.update();
////			OpenGUIHelper.set();
		}
	}

	@EventHandler
	public void onFMLInitializationEvent(FMLInitializationEvent event)
	{
		EntityRegistry.set();
		CapabilityRegistry.register();
		NetworkRegistry.INSTANCE.registerGuiHandler(I, new GuiHandler());
	}

	@EventHandler
	public void onFMLPostInitializationEvent(FMLPostInitializationEvent event)
	{
//		EntityRegistry.ENTITY_KEY_ARRAY = new HashSet(ENTITY_CLASS_ENTRIES.keySet()).toArray();

		if (event.getSide().isClient())
		{
			byte size = 30;
			String t_string = "info." + Small.ID + ".t";
			STRING_ARRAY = new String[size];
//			FontRenderer fontrenderer = Minecraft.getMinecraft().fontRenderer;
			for (byte i = 0; i < size; ++i)
			{
//				String string = I18n.translateToLocal(t_string + i);
//				STRING_ARRAY[i] = string;
//				byte tw = (byte)fontrenderer.getStringWidth(string);
//				if (MAX_TW < tw)
//				{
//					MAX_TW = tw;
//				}
				STRING_ARRAY[i] = I18n.translateToLocal(t_string + i);
			}
//			RenderHelper.init();
		}

//		EntitiesRegistry.ENTITIES_CLASS_LIST = null;
		TileRegistry.TILES_CLASS_LIST = null;
	}

	@EventHandler
	public void onFMLServerAboutToStartEvent(FMLServerAboutToStartEvent event)
	{
		ServerE.S_MAP = new HashMap();
		ChunkCallBack.set();
	}

	@EventHandler
	public void onFMLServerStartedEvent(FMLServerStartedEvent event)
	{
		WorldServer[] worldserver_array = FMLCommonHandler.instance().getMinecraftServerInstance().worlds;
		File file = new File(worldserver_array[0].getSaveHandler().getWorldDirectory() + "/nali");
		file.mkdirs();

		file = new File(file + "/entity");
		file.mkdirs();

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
							catch (IOException e)
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
		List<ServerE> s_list = new ArrayList(ServerE.S_MAP.values());
		if (!s_list.isEmpty())
		{
			File[] d_file_array = new File(s_list.get(0).worldserver.getSaveHandler().getWorldDirectory() + "/nali/entity").listFiles();

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

			for (ServerE servere : s_list)
			{
				servere.writeFile();
			}
		}

		ServerE.S_MAP = null;
	}
}
