package com.nali.list.key;

import com.nali.key.MixKeyBinding;
import com.nali.small.system.Reference;
import org.lwjgl.input.Keyboard;

public class SmallQuickInventoryGUI extends MixKeyBinding
{
    public static SmallQuickInventoryGUI I;
    public SmallQuickInventoryGUI(String name)
    {
        super(name, Keyboard.KEY_B, Reference.MOD_ID);
        I = this;
    }
}
