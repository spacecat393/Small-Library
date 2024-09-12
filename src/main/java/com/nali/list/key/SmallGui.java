package com.nali.list.key;

import com.nali.key.MixKeyBinding;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SOpenSmallGui;
import com.nali.network.NetworkRegistry;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class SmallGui extends MixKeyBinding
{
	public static int ID;
	public SmallGui(String[] string_array, Integer key)
	{
		super(string_array, key == null ? Keyboard.KEY_P : key);
//		this.setKeyConflictContext(KeyConflictContext.IN_GAME);
	}

	public static void detect()
	{
		if (Minecraft.getMinecraft().currentScreen == null)
		{
//			PlayerGui.PAGE = 0;
			NetworkRegistry.I.sendToServer(new ServerMessage(new byte[]{SOpenSmallGui.ID}));
		}
	}
}
