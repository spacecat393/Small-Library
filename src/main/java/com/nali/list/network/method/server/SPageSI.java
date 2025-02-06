package com.nali.list.network.method.server;

import com.nali.list.network.message.ServerMessage;
import com.nali.system.Reflect;
import net.minecraft.entity.player.EntityPlayerMP;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static com.nali.Nali.error;
import static com.nali.Nali.warn;

public class SPageSI
{
	public static byte ID;

	public static Method[] METHOD_ARRAY;
	static
	{
		List<Class> server_class_list = Reflect.getClasses("com.nali.list.gui.si.server");
		METHOD_ARRAY = new Method[server_class_list.size()];

		for (byte i = 0; i < server_class_list.size(); ++i)
		{
			try
			{
				Class server_class = server_class_list.get(i);
				METHOD_ARRAY[i] = server_class.getDeclaredMethod("run", EntityPlayerMP.class, ServerMessage.class);
				server_class.getDeclaredField("ID").set(null, i);
			}
			catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException e)
			{
				error(e);
			}
		}
	}

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		try
		{
			METHOD_ARRAY[servermessage.data[1]].invoke(null, entityplayermp, servermessage);
		}
		catch (IllegalAccessException | InvocationTargetException e)
		{
			warn(e);
		}
	}
}
