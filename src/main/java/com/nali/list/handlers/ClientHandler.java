package com.nali.list.handlers;

import com.nali.list.messages.ClientMessage;
import com.nali.small.Small;
import com.nali.system.Reflect;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ClientHandler implements IMessageHandler<ClientMessage, IMessage>
{
    public static Method[] METHOD_ARRAY;
    static
    {
        List<Class> servermessage_class_list = Reflect.getClasses("com.nali.list.netmethods.clientmessage");
        METHOD_ARRAY = new Method[servermessage_class_list.size()];

        for (Class servermessage_class : servermessage_class_list)
        {
            try
            {
                METHOD_ARRAY[(byte)servermessage_class.getDeclaredField("ID").get(null)] = servermessage_class.getDeclaredMethod("run", ClientMessage.class);
            }
            catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException e)
            {
                Small.error(e);
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
            Small.error(e);
        }

        return null;
    }
}