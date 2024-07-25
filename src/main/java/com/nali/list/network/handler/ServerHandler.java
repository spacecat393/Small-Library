package com.nali.list.network.handler;

import com.nali.list.network.message.ServerMessage;
import com.nali.system.Reflect;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static com.nali.Nali.*;

public class ServerHandler implements IMessageHandler<ServerMessage, IMessage>
{
    public static Method[] METHOD_ARRAY;

//    public static EntityPlayerMP ENTITYPLAYERMP;
//    public static byte[] BYTE_ARRAY;

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
                error(e);
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
                warn(e);
//                Small.error(e);
            }
        });

        return null;
    }

//    public static boolean canPass(ServerE servere, EntityPlayerMP entityplayermp)
//    {
////        if ((serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.LOCK_INVENTORY() / 8] >> serverentitiesmemory.workbytes.LOCK_INVENTORY() % 8 & 1) == 1)
//        if ((((AILeInvLockInv)servere.a.aie_map.get(AILeInvLockInv.ID)).state & 1) == 1)
//        {
//            return servere.owner_uuid == null || entityplayermp.getUniqueID().equals(servere.owner_uuid);
//        }
//
//        return true;
//    }
}
