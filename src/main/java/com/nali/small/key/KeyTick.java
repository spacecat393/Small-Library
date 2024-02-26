package com.nali.small.key;

import com.nali.list.container.PlayerContainer;
import com.nali.list.gui.PlayerGui;
import com.nali.list.key.SmallQuickInventoryGUI;
import com.nali.list.key.SmallPlayerGui;
import com.nali.list.messages.ServerMessage;
import com.nali.small.networks.NetworksRegistry;
import com.nali.small.system.Reference;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.UUID;
import java.util.function.Function;

import static com.nali.small.gui.MixGui.getTextFromClipboard;
import static com.nali.small.gui.MixGui.isValidUUIDString;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Side.CLIENT)
@SideOnly(Side.CLIENT)
public class KeyTick
{
    public static Function FUNCTION = (v) -> v;
    public static Void run(int key)
    {
//        if
//        (
//            key == KeyRegistryHelper.PLAYER_GUI_KEYBINDING.getKeyCode()
//        )
//        {
//            com.nali.key.KeyTick.addKey(key);
//        }
        if (Keyboard.isKeyDown(SmallQuickInventoryGUI.I.getKeyCode()))
        {
            if (Minecraft.getMinecraft().currentScreen == null)
            {
                Minecraft minecraft = Minecraft.getMinecraft();
                minecraft.addScheduledTask(() ->
                {
                    String uuid_string = getTextFromClipboard();
                    if (isValidUUIDString(uuid_string))
                    {
                        byte[] byte_array = new byte[21];
                        byte_array[0] = 6;
                        BytesWriter.set(byte_array, UUID.fromString(uuid_string), 1);
                        BytesWriter.set(byte_array, 1, 17);
                        NetworksRegistry.I.sendToServer(new ServerMessage(byte_array));
                    }
                });
            }
        }
        else if (Keyboard.isKeyDown(SmallPlayerGui.I.getKeyCode()))
        {
            PlayerGui.PAGE = 0;
            if (Minecraft.getMinecraft().currentScreen == null)
            {
                Minecraft minecraft = Minecraft.getMinecraft();
                minecraft.addScheduledTask(() ->
                {
                    Minecraft.getMinecraft().displayGuiScreen(new PlayerGui(new PlayerContainer()));
                });
            }
//            Minecraft minecraft = Minecraft.getMinecraft();
//            minecraft.addScheduledTask(() -> minecraft.displayGuiScreen(new PlayerGui(new PlayerContainer())));
        }

        return null;
    }
}
