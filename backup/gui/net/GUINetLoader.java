package com.nali.small.gui.net;

import com.nali.list.container.InventoryContainer;
import com.nali.list.network.message.ServerMessage;
import com.nali.network.NetworkRegistry;
import com.nali.networks.NetworksRegistry;
import com.nali.small.Small;
import com.nali.small.entity.EntityLeInv;
import com.nali.small.entity.memo.client.ClientLe;
import com.nali.small.gui.MixGui;
import com.nali.system.bytes.ByteWriter;
import com.nali.system.bytes.BytesWriter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.Nali.I;
import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

@SideOnly(Side.CLIENT)
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
                ByteWriter.set(byte_array, Integer.parseInt(new_string), new_index);
                new_index += 4;
            }

            EntityLeInv skinningentities = ((InventoryContainer)this.mixgui.inventorySlots).skinningentities;
            ClientLe cliententitiesmemory = (ClientLe)skinningentities.bothentitiesmemory;
            ByteWriter.set(byte_array, cliententitiesmemory.uuid, 1);
            NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
        }
        catch (Exception e)
        {
            I.warn(e);
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
                ByteWriter.set(byte_array, Float.parseFloat(string_array[i]), new_index);
                new_index += 4;
            }

            EntityLeInv skinningentities = ((InventoryContainer)this.mixgui.inventorySlots).skinningentities;
            ClientLe cliententitiesmemory = (ClientLe)skinningentities.bothentitiesmemory;
            ByteWriter.set(byte_array, cliententitiesmemory.uuid, 1);
            NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
        }
        catch (Exception e)
        {
            I.warn(e);
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
                ByteWriter.set(byte_array, Integer.parseInt(new_string), new_index);
                new_index += 4;
            }

            NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
        }
        catch (Exception e)
        {
            I.warn(e);
        }
    }
}
