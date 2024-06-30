package com.nali.list.key;

import com.nali.key.MixKeyBinding;
import com.nali.list.container.gui.PlayerGui;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SOpenPlayerGUI;
import com.nali.networks.NetworksRegistry;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class SmallPlayerGui extends MixKeyBinding
{
    public static int ID;
    public SmallPlayerGui(String[] string_array, Integer key)
    {
        super(string_array, key == null ? Keyboard.KEY_P : key);
//        this.setKeyConflictContext(KeyConflictContext.IN_GAME);
    }

    public static void detect()
    {
        if (Minecraft.getMinecraft().currentScreen == null)
        {
            PlayerGui.PAGE = 0;
            NetworksRegistry.I.sendToServer(new ServerMessage(new byte[]{SOpenPlayerGUI.ID}));
        }
    }
}
