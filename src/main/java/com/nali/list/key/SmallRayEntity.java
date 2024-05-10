package com.nali.list.key;

import com.nali.key.MixKeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class SmallRayEntity extends MixKeyBinding
{
    public static int ID;
    public SmallRayEntity(String[] string_array, Integer key)
    {
        super(string_array, key == null ? Keyboard.KEY_T : key);
    }

    public static void detect()
    {
        if (Minecraft.getMinecraft().world != null)
        {
            
        }
    }
}
