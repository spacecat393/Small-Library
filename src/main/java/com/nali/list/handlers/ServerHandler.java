package com.nali.list.handlers;

import com.nali.list.messages.ServerMessage;
import com.nali.small.Small;
import com.nali.small.entities.skinning.SkinningEntities;
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
        List<Class> servermessage_class_list = Reflect.getClasses("com.nali.list.netmethods.servermessage");
        METHOD_ARRAY = new Method[servermessage_class_list.size()];

        for (Class servermessage_class : servermessage_class_list)
        {
            try
            {
                METHOD_ARRAY[(int)servermessage_class.getDeclaredField("ID").get(null)] = servermessage_class.getDeclaredMethod("run", EntityPlayerMP.class, ServerMessage.class);
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
                Small.error(e);
            }
        });

        return null;
    }

    public static boolean canPass(SkinningEntities skinningentities, EntityPlayerMP entityplayermp)
    {
        if (skinningentities.main_server_work_byte_array[skinningentities.skinningentitiesbytes.LOCK_INVENTORY()] == 1)
        {
            return skinningentities.owner_uuid == null || entityplayermp.getUniqueID().equals(skinningentities.owner_uuid);
        }

        return true;
    }
}
