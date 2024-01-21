package com.nali.list.function;

import com.nali.small.key.KeyTick;
import org.lwjgl.input.Keyboard;

import static com.nali.small.key.KeyTick.FUNCTION;

public class SmallFunction
{
    public SmallFunction()
    {
        FUNCTION = v -> KeyTick.run(Keyboard.getEventKey());
    }
}
