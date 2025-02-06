package com.nali.list.network.method.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.system.Reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static com.nali.Nali.error;
import static com.nali.Nali.warn;

public class CPageDa
{
	public static byte ID;

	public static Method[] METHOD_ARRAY;
	static
	{
		List<Class> client_class_list = Reflect.getClasses("com.nali.list.gui.da.client");
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

	public static void run(ClientMessage clientmessage)
	{
		try
		{
			METHOD_ARRAY[clientmessage.data[1]].invoke(null, clientmessage);
		}
		catch (IllegalAccessException | InvocationTargetException e)
		{
			warn(e);
		}
	}
}
