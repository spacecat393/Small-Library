package com.nali.list.handlers;

import com.nali.ilol.capabilities.CapabilitiesRegistryHelper;
import com.nali.list.messages.CapabilitiesClientMessage;
import com.nali.system.bytes.BytesReader;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CapabilitiesClientHandler implements IMessageHandler<CapabilitiesClientMessage, IMessage>
{
    @Override
    public IMessage onMessage(CapabilitiesClientMessage capabilityclientmessage, MessageContext messagecontext)
    {
        CapabilitiesRegistryHelper.CLIENT_CAPABILITY_OBJECT_ARRAYLIST.set(BytesReader.getInt(capabilityclientmessage.data, 0), BytesReader.getInt(capabilityclientmessage.data, 4));
        return null;
    }
}