package com.nali.list.key;

import com.nali.key.Key;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SOpenSmallGui;
import com.nali.network.NetworkRegistry;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class SmallGui extends Key
{
//	public static byte ID;
	@Override
	public void run()
	{
		if (Minecraft.getMinecraft().currentScreen == null && Keyboard.isKeyDown(Keyboard.KEY_P))
		{
//			PlayerGui.PAGE = 0;
			NetworkRegistry.I.sendToServer(new ServerMessage(new byte[]{SOpenSmallGui.ID}));
		}
	}
}
