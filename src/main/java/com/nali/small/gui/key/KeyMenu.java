package com.nali.small.gui.key;

import com.nali.small.gui.page.PageMenu;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeyMenu extends Key
{
    public boolean result;

    @Override
    public void run()
    {
        this.result = false;
    }

    @Override
    public void detect(char typedChar, int keyCode)
    {
        if (typedChar == 'q' || keyCode == Keyboard.KEY_LEFT)
        {
            PageMenu.BT = 5.0F;
            this.result = true;
        }
    }
}
