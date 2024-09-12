package com.nali.list.network.message;

import com.nali.network.NetworkMessage;
import net.minecraftforge.fml.relauncher.Side;

public class ClientMessage extends NetworkMessage
{
	public static Side SIDE = Side.CLIENT;
	public ClientMessage()
	{

	}

	public ClientMessage(byte[] data)
	{
		this.data = data;
	}
}
