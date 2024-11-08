//package com.nali.list.container.gui;
//
//import com.nali.list.container.PlayerContainer;
//import com.nali.list.network.message.ServerMessage;
//import com.nali.list.network.method.server.SEMapToClient;
//import com.nali.network.NetworkRegistry;
//import com.nali.small.entity.EntityLeInv;
//import com.nali.small.entity.memo.client.render.RenderSleInv;
//import com.nali.small.gui.MixGui;
//import com.nali.small.gui.features.messages.player.*;
//import com.nali.system.bytes.ByteWriter;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.ScaledResolution;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.inventory.Container;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import org.lwjgl.opengl.GL11;
//
//import java.util.UUID;
//
//import static com.nali.key.KeyHelper.getTextFromClipboard;
//import static com.nali.key.KeyHelper.isValidUUIDString;
//
//@SideOnly(Side.CLIENT)
//public class PlayerGui extends MixGui
//{
//	public static MixButton[] MIXBUTTON_ARRAY;
//	public static int CURRENT_INDEX;
////	public SakuraRender sakurarender = new SakuraRender();
////	public BoxRender boxrender = new BoxRender();
////	public DrawScreen drawscreen = new DrawScreen();
//	public static byte PAGE;
//
//	public static int MAX_NEXT;
//	public static int NEXT;
//
//	public PlayerGui(Container container)
//	{
//		super(container);
//		this.mc = Minecraft.getMinecraft();
//		ScaledResolution scaledresolution = new ScaledResolution(this.mc);
//		this.setWorldAndResolution(Minecraft.getMinecraft(), scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight());
//
////		byte[] byte_array = new byte[4];
////		BytesWriter.set(byte_array, 0, 0);
////		NetworksRegistry.I.sendToServer(new CapabilitiesServerMessage(byte_array));
////		NetworksRegistry.I.sendToServer(new CapabilitiesServerMessage(new byte[4]));
//		this.xSize = 256;
//		this.ySize = 256;
//
////		this.boxrender.objectscreendraw.rz = 45.0F * 3.0F;
////		float s = -3.5F;
////		this.boxrender.objectscreendraw.sx = s;
////		this.boxrender.objectscreendraw.sy = s;
////		this.boxrender.objectscreendraw.sz = s;
////		this.boxrender.sa = 0.9F;
//	}
//
//	public static PlayerGui get(EntityPlayer entityplayer, World world, int x, int y, int z)
//	{
//		return new PlayerGui(new PlayerContainer());
//	}
//
//	@Override
//	public void drawScreen(int mouseX, int mouseY, float partialTicks)
//	{
//		this.state &= 255-1;
//		this.state &= 255-2;
//		//
////		if (this.mouse_clicked == 0)
////		{
////			++ItemLayerRender.DEBUG_V;
////		}
////		else if (this.mouse_clicked == 1)
////		{
////			--ItemLayerRender.DEBUG_V;
////		}
////		if (!(GUIFEATURESLOADER instanceof DebugGUIFeatures))
////		{
////			GUIFEATURESLOADER = new DebugGUIFeatures(this);
////		}
////		this.state |= 2;
//		//
//
//		super.drawScreen(mouseX, mouseY, partialTicks);
//		if (PAGE == 0)
//		{
//			this.setMessage();
//
//			float x = this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F, y = this.guiTop + 256/2.0F - 19/2.0F, width = 18, height = 19;
//			if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
//			{
////				if (this.mouse_released == 0)
////				{
////					this.sendPacket1(X12.ID);
////				}
//
//				if (!(GUIFEATURESLOADER instanceof RandomAGUIFeatures))
//				{
//					GUIFEATURESLOADER = new RandomAGUIFeatures(this);
//				}
//				this.state |= 2;
//			}
//
//			x = this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1);// y = this.guiTop + 118; width = 18; height = 19;
//			if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
//			{
////				if (this.mouse_released == 0)
////				{
////					this.sendPacket1(X64.ID);
////				}
//
//				if (!(GUIFEATURESLOADER instanceof RandomBGUIFeatures))
//				{
//					GUIFEATURESLOADER = new RandomBGUIFeatures(this);
//				}
//				this.state |= 2;
//			}
//
//			x = this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*2;// y = this.guiTop + 118; width = 18; height = 19;
//			if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
//			{
//				if (this.mouse_released == 0)
//				{
////					byte[] byte_array = new byte[1];
////					byte_array[0] = 5;
////					NetworksRegistry.I.sendToServer(new SkinningEntitiesServerMessage(byte_array));
////
////					this.initEntities();
//
//					PAGE = 1;
//				}
//
//				if (!(GUIFEATURESLOADER instanceof MimiTalkGUIFeatures))
//				{
//					GUIFEATURESLOADER = new MimiTalkGUIFeatures(this);
//				}
//				this.state |= 2;
//			}
//
//			x = this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*3;// y = this.guiTop + 118; width = 18; height = 19;
//			if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
//			{
//				this.state |= 1;
//
//				if (!(GUIFEATURESLOADER instanceof DropToSakuraGUIFeatures))
//				{
//					GUIFEATURESLOADER = new DropToSakuraGUIFeatures(this);
//				}
//				this.state |= 2;
//			}
//		}
//		else
//		{
//			if (MIXBUTTON_ARRAY != null)
//			{
//				int img = 4 + (MIXBUTTON_ARRAY.length - (CURRENT_INDEX + 4));
//				if (img > 4)
//				{
//					img = 4;
//				}
//				int max = 0;
//				float x = 256/2.0F - (56 + 1)*img/2.0F, y = 256/2.0F - 60/2.0F;
//				for (int i = CURRENT_INDEX; i < MIXBUTTON_ARRAY.length; ++i)
//				{
//					MIXBUTTON_ARRAY[i].renderTooltip(this, this.guiLeft + x, this.guiTop + y, mouseX, mouseY/*, this.guiLeft, this.guiTop, this.width, this.height*/);
//
//					if (++max == 4)
//					{
//						break;
//					}
//					x += (56 + 1);
//				}
//			}
//
//			MAX_NEXT = 0;
//			NEXT = (CURRENT_INDEX / 4);
//			if (MIXBUTTON_ARRAY != null)
//			{
//				MAX_NEXT = (int)Math.ceil(MIXBUTTON_ARRAY.length / 4.0F) - 1;
//				if (MAX_NEXT == -1)
//				{
//					MAX_NEXT = 0;
//				}
//				if (CURRENT_INDEX > MIXBUTTON_ARRAY.length)
//				{
//					CURRENT_INDEX = MIXBUTTON_ARRAY.length - 4;
//				}
//				if (CURRENT_INDEX < 0)
//				{
//					CURRENT_INDEX = 0;
//				}
//			}
//
//			float x = this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F, y = this.guiTop + 256/2.0F - 19/2.0F + 61, width = 18, height = 19;
//			if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
//			{
//				if (this.mouse_released == 0)
//				{
//					if (NEXT > 0)
//					{
//						CURRENT_INDEX -= 4;
//					}
//				}
//
//				if (!(GUIFEATURESLOADER instanceof BackGUIFeatures))
//				{
//					GUIFEATURESLOADER = new BackGUIFeatures(this);
//				}
//				this.state |= 2;
//			}
//
//			x = this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1);
//			if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
//			{
//				if (this.mouse_released == 0)
//				{
//					this.initEntities();
//				}
//				else if (this.mouse_released == 1)
//				{
//					this.sendPacket1(SEMapToClient.ID);
//				}
//
//				if (!(GUIFEATURESLOADER instanceof FetchGUIFeatures))
//				{
//					GUIFEATURESLOADER = new FetchGUIFeatures(this);
//				}
//				this.state |= 2;
//			}
//
//			x = this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*2;
//			if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
//			{
//				if (this.mouse_released == 0)
//				{
//					if (NEXT < MAX_NEXT)
//					{
//						CURRENT_INDEX += 4;
//					}
//				}
//
//				if (!(GUIFEATURESLOADER instanceof NextGUIFeatures))
//				{
//					GUIFEATURESLOADER = new NextGUIFeatures(this);
//				}
//				this.state |= 2;
//			}
//
//			x = this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*3;
//			if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
//			{
//				if (this.mouse_released == 0)
//				{
//					String uuid_string = getTextFromClipboard();
//					if (isValidUUIDString(uuid_string))
//					{
//						this.sendPacketUUID(SOpenInvGUI.ID, UUID.fromString(uuid_string));
//					}
//				}
//
//				if (!(GUIFEATURESLOADER instanceof EnterGUIFeatures))
//				{
//					GUIFEATURESLOADER = new EnterGUIFeatures(this);
//				}
//				this.state |= 2;
//			}
//		}
//
//		if ((this.state & 2) == 2)
//		{
//			GUIFEATURESLOADER.drawText(mouseX, mouseY);
//		}
//
//		this.mouse_released = -1;
//	}
//
//	@Override
//	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
//	{
//		boolean gl_blend = GL11.glIsEnabled(GL11.GL_BLEND);
//		GL11.glEnable(GL11.GL_BLEND);
//
//		this.mc.getTextureManager().bindTexture(InventoryGui.GUI_RESOURCELOCATION);
//
////		float ry = this.drawscreen.ry;
//		if (PAGE == 0)
//		{
//			this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F, this.guiTop + 256/2.0F - 19/2.0F, 106, 50, 18, 19);
//			this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + 2, this.guiTop + 256/2.0F - 14/2.0F, 16, 0, 14, 14);
//			this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1), this.guiTop + 256/2.0F - 19/2.0F, 106, 50, 18, 19);
//			this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1) + 2, this.guiTop + 256/2.0F - 14/2.0F, 238, 0, 14, 14);
//			this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*2, this.guiTop + 256/2.0F - 19/2.0F, 106, 50, 18, 19);
//			this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*2 + 2, this.guiTop + 256/2.0F - 12/2.0F, 224, 0, 14, 12);
//			this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*3, this.guiTop + 256/2.0F - 19/2.0F, 106, 50, 18, 19);
//
////			this.drawscreen.rz = 45.0F * 3.0F;
//////			this.drawscreen.ry = 0;
////			this.drawscreen.scale(-3.5F);
////
////			this.drawscreen.x = this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*3 + (18 + 1)/2.0F - 0.5F;
////			this.drawscreen.y = this.guiTop + 256/2.0F - 19/2.0F + 19/2.0F + 2.5F/2.0F;
//////			GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT);
//////			OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
//////			GL11.glGetFloat(GL11.GL_CURRENT_COLOR, OPENGL_FIXED_PIPE_FLOATBUFFER);
//////			GL_CURRENT_COLOR[0] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(0);
//////			GL_CURRENT_COLOR[1] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(1);
//////			GL_CURRENT_COLOR[2] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(2);
//////			GL_CURRENT_COLOR[3] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(3);
//////			GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.9F);
////			this.drawscreen.render(/*this.boxrender*/);
//////			GL11.glColor4f(GL_CURRENT_COLOR[0], GL_CURRENT_COLOR[1], GL_CURRENT_COLOR[2], GL_CURRENT_COLOR[3]);
//////			GL11.glPopAttrib();
//		}
//		else
//		{
//			this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F, this.guiTop + 256/2.0F - 19/2.0F + 61, 106, 50, 18, 19);
//			this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18-8)/2.0F, this.guiTop + 256/2.0F - 6/2.0F + 61, 30, 0, 8, 6);
//			this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1), this.guiTop + 256/2.0F - 19/2.0F + 61, 106, 50, 18, 19);
//			this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1) + (18-12)/2.0F, this.guiTop + 256/2.0F - 12/2.0F + 61, 52, 14, 12, 12);
//			this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*2, this.guiTop + 256/2.0F - 19/2.0F + 61, 106, 50, 18, 19);
//			this.drawTextureRightToLeft(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*2 + (18-8)/2.0F, this.guiTop + 256/2.0F - 6/2.0F + 61, 30, 0, 8, 6);
//			this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*3, this.guiTop + 256/2.0F - 19/2.0F + 61, 106, 50, 18, 19);
//			this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*3 + (18-14)/2.0F, this.guiTop + 256/2.0F - 8/2.0F + 61, 38, 14, 14, 8);
//
//			if (MIXBUTTON_ARRAY != null)
//			{
//				int img = 4 + (MIXBUTTON_ARRAY.length - (CURRENT_INDEX + 4));
//				if (img > 4)
//				{
//					img = 4;
//				}
//				int max = 0;
//				float x = 256/2.0F - (56 + 1)*img/2.0F, y = 256/2.0F - 60/2.0F;
//				for (int i = CURRENT_INDEX; i < MIXBUTTON_ARRAY.length; ++i)
//				{
//					MIXBUTTON_ARRAY[i].render(this, this.guiLeft + x, this.guiTop + y/*, mouseX, mouseY*//*, this.guiLeft, this.guiTop, this.width, this.height*/);
//
//					if (++max == 4)
//					{
//						break;
//					}
//					x += (56 + 1);
//				}
//			}
//		}
//
//		if (gl_blend)
//		{
//			GL11.glEnable(GL11.GL_BLEND);
//		}
//		else
//		{
//			GL11.glDisable(GL11.GL_BLEND);
//		}
//
//////		this.sakura_objectrender.width = this.width;
//////		this.sakura_objectrender.height = this.height;
////		this.drawscreen.rz = 0.0F;
////		this.drawscreen.scale(-3.5F);
////
////		this.drawscreen.x = 15.0F;
////		this.drawscreen.y = 15.0F;
//////		ry += 2.0F * Timing.TD;
//////		this.drawscreen.ry = ry;
//////		this.pyroxene_objectrender.z = -1.0F;
//////		this.pyroxene_objectdata.screen_float_array[2] = this.width / 2.0F;
//////		this.pyroxene_objectdata.screen_float_array[3] = this.guiTop + 72.0F;
////		this.drawscreen.render(/*this.sakurarender*/);
////
//////		if (CapabilitiesRegistryHelper.CLIENT_CAPABILITY_OBJECT_LIST.size() > 0)
//////		{
//		Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("" + this.mc.player.getEntityData().getInteger("Nali_sakura"), 25, 11, 0xFFFFACDF/*getRainbowColor4()*/);
////		}
//	}
//
//	public static class MixButton
//	{
//		public EntityLeInv skinningentities;
//		public RenderSleInv skinningentitiesrender;
//		public int /*x, y, */tx, ty, width, height;
//		public UUID uuid;
////		public DrawScreen drawscreen = new DrawScreen();
//
//		public MixButton(EntityLeInv skinningentities, UUID uuid/*, int x, int y*/, int tx, int ty, int width, int height)
//		{
//			this.skinningentities = skinningentities;
////			if (this.skinningentities != null)
////			{
//			this.skinningentitiesrender = (RenderSleInv)Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(skinningentities);
////				SkinningRender skinningrender = (SkinningRender)skinningentities.client_object;
////				skinningrender.screen_float_array[3] = 0.0F;
////				skinningrender.screen_float_array[4] = -10.0F;
////		this.rx = -1.57079632679F;
////				skinningrender.screen_float_array[5] = -1.57079632679F;
////		skinningdata.screen_float_array[6] = 0.0F;
////				skinningrender.screen_float_array[7] = 0.0F;
////			}
//
////			this.x = x;
////			this.y = y;
//			this.tx = tx;
//			this.ty = ty;
//			this.width = width;
//			this.height = height;
//			this.uuid = uuid;
//		}
//
//		public void render(MixGui mixgui, float x, float y/*, int mouseX, int mouseY*//*, int left, int top, int sys_width, int sys_height*/)
//		{
//			mixgui.drawTexturedModalRect(x, y, this.tx, this.ty, this.width, this.height);
//
////			if (this.skinningentities != null)
////			{
////				Minecraft mc = Minecraft.getMinecraft();
////				ClientLe cliententitiesmemory = (ClientLe)this.skinningentities.bothentitiesmemory;
////				SkinningRender skinningrender = (SkinningRender)cliententitiesmemory.objectrender;
////				if (!skinningrender.entitiesrendermemory.should_render)
////				{
////					this.skinningentitiesrender.updateData(skinningentities, mc.getRenderPartialTicks());
////				}
////
//////				ScaledResolution scaledresolution = new ScaledResolution(mc);
//////				float s = 0.2F;
//////				if (scaledresolution.getScaleFactor() <= 1)//this.mc.gameSettings.guiScale == 0//if ((this.width / (double)this.height) <= 1.3325D || (this.height / (double)this.width) <= 0.5608D)
//////				{
//////					s = 0.1F;
//////				}
//////				float s = -25.0F;
////
////				this.drawscreen.x = x + 28;
////				this.drawscreen.y = y + 47;
//////				skinningrender.objectworlddraw.lig_b = 208.0F;
//////				skinningrender.objectworlddraw.lig_s = 240.0F;
////				skinningrender.lig_b = -1.0F;
//////				skinningrender.sx = s;
//////				skinningrender.sy = s;
//////				skinningrender.sz = s;
////				this.drawscreen.render(skinningrender);
////			}
//		}
//
//		public void renderTooltip(MixGui mixgui, float x, float y, int mouseX, int mouseY/*, int left, int top, int sys_width, int sys_height*/)
//		{
////			if (this.skinningentities != null)
////			{
//			mixgui.renderEntitiesName(this.skinningentities, x, y + 49, 56, 11, mouseX, mouseY);
////			}
////			else
////			{
////				mixgui.renderEntitiesUUID(this.uuid, x, y + 49, 56, 11, mouseX, mouseY);
////			}
//
//			if (mouseX >= x && mouseY >= y && mouseX < x + this.width && mouseY < y + 49)
//			{
//				if (mixgui.mouse_released == 0)
//				{
//					byte[] byte_array = new byte[17];
//					byte_array[0] = SOpenInvGUI.ID;
//					ByteWriter.set(byte_array, this.uuid, 1);
//					NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
//				}
//			}
//		}
//	}
//
//	public void initEntities()
//	{
////		int index = 0;//, x = 10, y = 10;
//////		Set<UUID> keys_set = new HashSet(ENTITIES_MAP.keySet());
////		MIXBUTTON_ARRAY = new MixButton[keys_set.size()];
////		for (UUID uuid : keys_set)
////		{
////			EntityLeInv skinningentities = ENTITIES_MAP.get(uuid);
//////					if (skinningentities == null)
//////					{
//////						this.mixbutton_array = null;
//////						break;
//////					}
////			MIXBUTTON_ARRAY[index++] = new MixButton(skinningentities, uuid/*, 0, 0*/, 143, 51, 56, 60);
//////			this.mixbutton_array[index++] = new MixButton(skinningentities, uuid, x, y, 143, 51, 56, 60);
//////					this.mixbutton_array[index++] = new MixButton(SkinningEntities.ENTITIES_MAP.get(uuid), this.guiLeft + x, this.guiTop + y, 143, 51, 56, 60);
////
//////			if (x >= this.width)
//////			{
//////				y += 60 + 10;
//////				x = 10;
//////			}
//////			else
//////			{
//////				x += 56 + 10;
//////			}
////		}
//	}
//}
