package com.nali.small.gui.net;

import com.nali.list.container.InventoryContainer;
import com.nali.list.messages.ServerMessage;
import com.nali.small.Small;
import com.nali.small.entities.memory.client.ClientEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.gui.MixGui;
import com.nali.small.networks.NetworksRegistry;
import com.nali.system.bytes.BytesWriter;

import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

public abstract class GUINetLoader
{
    public MixGui mixgui;

    public GUINetLoader(MixGui mixgui)
    {
        this.mixgui = mixgui;
    }

    public abstract void run();

    public void sendUUIDIntBytes(byte id)
    {
        try
        {
            String[] string_array = MESSAGE_STRINGBUILDER.deleteCharAt(MESSAGE_STRINGBUILDER.length() - 1).toString().split(" ");
            byte[] byte_array = new byte[1 + 16 + string_array.length * 4];
            byte_array[0] = id;

            int new_index = 1 + 16;
            for (String new_string : string_array)
            {
                BytesWriter.set(byte_array, Integer.parseInt(new_string), new_index);
                new_index += 4;
            }

            SkinningEntities skinningentities = ((InventoryContainer)this.mixgui.inventorySlots).skinningentities;
            ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;
            BytesWriter.set(byte_array, cliententitiesmemory.uuid, 1);
            NetworksRegistry.I.sendToServer(new ServerMessage(byte_array));
        }
        catch (Exception e)
        {
            Small.warn(e);
        }
    }

    public void sendUUIDFloatBytes(byte id, byte size)
    {
        String[] string_array = MESSAGE_STRINGBUILDER.deleteCharAt(MESSAGE_STRINGBUILDER.length() - 1).toString().split(" ");
        if (string_array.length == size)
        {
            this.sendUUIDFloatBytes(id, size, string_array);
        }
    }

    public void sendUUIDFloatBytes(byte id, byte size, String[] string_array)
    {
        try
        {
            byte[] byte_array = new byte[1 + 16 + size * 4];
            byte_array[0] = id;

            int new_index = 1 + 16;
            for (int i = 0; i < size; ++i)
            {
                BytesWriter.set(byte_array, Float.parseFloat(string_array[i]), new_index);
                new_index += 4;
            }

            SkinningEntities skinningentities = ((InventoryContainer)this.mixgui.inventorySlots).skinningentities;
            ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;
            BytesWriter.set(byte_array, cliententitiesmemory.uuid, 1);
            NetworksRegistry.I.sendToServer(new ServerMessage(byte_array));
        }
        catch (Exception e)
        {
            Small.warn(e);
        }
    }

    public void sendIntBytes(byte id)
    {
        try
        {
            String[] string_array = MESSAGE_STRINGBUILDER.deleteCharAt(MESSAGE_STRINGBUILDER.length() - 1).toString().split(" ");
            byte[] byte_array = new byte[1 + string_array.length * 4];
            byte_array[0] = id;

            int new_index = 1 + 16;
            for (String new_string : string_array)
            {
                BytesWriter.set(byte_array, Integer.parseInt(new_string), new_index);
                new_index += 4;
            }

            NetworksRegistry.I.sendToServer(new ServerMessage(byte_array));
        }
        catch (Exception e)
        {
            Small.warn(e);
        }
    }
}
