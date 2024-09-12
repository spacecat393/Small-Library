package com.nali.list.network.message;

import com.nali.network.NetworkMessage;
import net.minecraftforge.fml.relauncher.Side;

public class ServerMessage extends NetworkMessage
{
	public static Side SIDE = Side.SERVER;
	public ServerMessage()
	{

	}

	public ServerMessage(byte[] data)
	{
		this.data = data;
	}
}
