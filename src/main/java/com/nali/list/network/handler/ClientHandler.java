package com.nali.list.network.handler;

import com.nali.list.network.message.ClientMessage;
import com.nali.system.Reflect;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static com.nali.Nali.*;

public class ClientHandler implements IMessageHandler<ClientMessage, IMessage>
{
	public static Method[] METHOD_ARRAY;
	static
	{
		List<Class> client_class_list = Reflect.getClasses("com.nali.list.network.method.client");
		METHOD_ARRAY = new Method[client_class_list.size()];

		for (byte i = 0; i < client_class_list.size(); ++i)
		{
			try
			{
				Class client_class = client_class_list.get(i);
				METHOD_ARRAY[i] = client_class.getDeclaredMethod("run", ClientMessage.class);
				client_class.getDeclaredField("ID").set(null, i);
			}
			catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException e)
			{
				error(e);
			}
		}
	}

	@Override
	public IMessage onMessage(ClientMessage clientmessage, MessageContext messagecontext)
	{
		try
		{
			METHOD_ARRAY[clientmessage.data[0]].invoke(null, clientmessage);
		}
		catch (IllegalAccessException | InvocationTargetException e)
		{
			warn(e);
//			Small.error(e);
		}

		return null;
	}
}