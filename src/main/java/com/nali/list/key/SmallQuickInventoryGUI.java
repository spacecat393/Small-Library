package com.nali.list.key;

import com.nali.key.MixKeyBinding;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.OpenInvGUI;
import com.nali.networks.NetworksRegistry;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.UUID;

import static com.nali.key.KeyHelper.getTextFromClipboard;
import static com.nali.key.KeyHelper.isValidUUIDString;

@SideOnly(Side.CLIENT)
public class SmallQuickInventoryGUI extends MixKeyBinding
{
    public static int ID;
    public SmallQuickInventoryGUI(String[] string_array, Integer key)
    {
        super(string_array, key == null ? Keyboard.KEY_B : key);
//        this.setKeyConflictContext(KeyConflictContext.IN_GAME);
    }

    public static void detect()
    {
        if (Minecraft.getMinecraft().currentScreen == null)
        {
            String uuid_string = getTextFromClipboard();
            if (isValidUUIDString(uuid_string))
            {
                byte[] byte_array = new byte[21];
                byte_array[0] = OpenInvGUI.ID;
                BytesWriter.set(byte_array, UUID.fromString(uuid_string), 1);
                BytesWriter.set(byte_array, 1, 17);
                NetworksRegistry.I.sendToServer(new ServerMessage(byte_array));
            }
        }
    }
}
