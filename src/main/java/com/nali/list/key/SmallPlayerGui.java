package com.nali.list.key;

import com.nali.key.MixKeyBinding;
import org.lwjgl.input.Keyboard;

public class SmallPlayerGui extends MixKeyBinding
{
    public static SmallPlayerGui I;
    public SmallPlayerGui(String[] string_array)
    {
        super(string_array, Keyboard.KEY_P);
    }
}
