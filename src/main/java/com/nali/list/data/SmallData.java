package com.nali.list.data;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SmallData
{
	public static int
		TEXTURE_STEP,
		SHADER_STEP,
		MODEL_STEP,
//		ANIMATION_STEP
		FRAME_STEP,
//		SOUND_STEP
		MAX_BONE = 16 * 2;
	public static byte ORDINAL = 1;
}
