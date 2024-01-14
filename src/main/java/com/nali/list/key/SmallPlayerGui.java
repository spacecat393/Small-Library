package com.nali.list.key;

import com.nali.small.system.Reference;
import com.nali.key.MixKeyBinding;
import org.lwjgl.input.Keyboard;

public class SmallPlayerGui extends MixKeyBinding
{
    public static SmallPlayerGui I;
    public SmallPlayerGui(String name)
    {
        super(name, Keyboard.KEY_P, Reference.MOD_ID);
        I = this;
    }
}
