package com.nali.list.key;

import com.nali.ilol.system.Reference;
import com.nali.key.MixKeyBinding;
import org.lwjgl.input.Keyboard;

public class IlolPlayerGui extends MixKeyBinding
{
    public static IlolPlayerGui I;
    public IlolPlayerGui(String name)
    {
        super(name, Keyboard.KEY_P, Reference.MOD_ID);
        I = this;
    }
}
