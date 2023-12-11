package com.nali.list.handlers;

import com.nali.ilol.gui.OpenGUIHelper;
import com.nali.list.messages.OpenGUIMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class OpenGUIHandler implements IMessageHandler<OpenGUIMessage, IMessage>
{
    @Override
    public IMessage onMessage(OpenGUIMessage openguimessage, MessageContext messagecontext)
    {
        OpenGUIHelper.call(openguimessage.data);
//        FMLCommonHandler.instance().getWorldThread(messagecontext.netHandler).addScheduledTask(() ->
//        {

//        });


//        EntityPlayerMP serverPlayer = messagecontext.getServerHandler().player;
//        int amount = openguipacket.toSend;
//        serverPlayer.getServerWorld().addScheduledTask(() ->
//        {
//            serverPlayer.inventory.addItemStackToInventory(new ItemStack(Items.DIAMOND, amount));
//        });

        return null;
    }
}
