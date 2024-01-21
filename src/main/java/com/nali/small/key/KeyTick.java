package com.nali.small.key;

import com.nali.small.system.Reference;
import com.nali.list.container.PlayerContainer;
import com.nali.list.gui.PlayerGui;
import com.nali.list.key.SmallPlayerGui;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.function.Function;

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
        if (Keyboard.isKeyDown(SmallPlayerGui.I.getKeyCode()))
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
