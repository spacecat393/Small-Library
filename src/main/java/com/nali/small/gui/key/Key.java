package com.nali.small.gui.key;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class Key
{
    public static Key KEY;
    public abstract void run();
    public abstract void detect(char typedChar, int keyCode);
}
