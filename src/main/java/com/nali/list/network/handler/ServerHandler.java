package com.nali.list.network.handler;

import com.nali.list.network.message.ServerMessage;
import com.nali.small.Small;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.EntityLeInv;
import com.nali.system.Reflect;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ServerHandler implements IMessageHandler<ServerMessage, IMessage>
{
    public static Method[] METHOD_ARRAY;
    static
    {
        List<Class> servermessage_class_list = Reflect.getClasses("com.nali.list.network.method.server");
        METHOD_ARRAY = new Method[servermessage_class_list.size()];

        for (byte i = 0; i < servermessage_class_list.size(); ++i)
        {
            try
            {
                Class servermessage_class = servermessage_class_list.get(i);
                METHOD_ARRAY[i] = servermessage_class.getDeclaredMethod("run", EntityPlayerMP.class, ServerMessage.class);
                servermessage_class.getDeclaredField("ID").set(null, i);
            }
            catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException e)
            {
                Small.error(e);
            }
        }
    }

    @Override
    public IMessage onMessage(ServerMessage servermessage, MessageContext messagecontext)
    {
        EntityPlayerMP entityplayermp = messagecontext.getServerHandler().player;
        ((WorldServer)entityplayermp.world).addScheduledTask(() ->
        {
            try
            {
                METHOD_ARRAY[servermessage.data[0]].invoke(null, entityplayermp, servermessage);
            }
            catch (IllegalAccessException | InvocationTargetException e)
            {
                Small.warn(e);
//                Small.error(e);
            }
        });

        return null;
    }

    public static boolean canPass(EntityLeInv skinningentities, EntityPlayerMP entityplayermp)
    {
        ServerE serverentitiesmemory = (ServerE)skinningentities.bothentitiesmemory;
        if ((serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.LOCK_INVENTORY() / 8] >> serverentitiesmemory.workbytes.LOCK_INVENTORY() % 8 & 1) == 1)
        {
            return serverentitiesmemory.owner_uuid == null || entityplayermp.getUniqueID().equals(serverentitiesmemory.owner_uuid);
        }

        return true;
    }
}
