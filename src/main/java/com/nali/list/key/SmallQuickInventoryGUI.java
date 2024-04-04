package com.nali.list.key;

import com.nali.key.MixKeyBinding;
import org.lwjgl.input.Keyboard;

public class SmallQuickInventoryGUI extends MixKeyBinding
{
    public static SmallQuickInventoryGUI I;
    public SmallQuickInventoryGUI(String[] string_array)
    {
        super(string_array, Keyboard.KEY_B);
    }
}
