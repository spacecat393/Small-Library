package com.nali.small.draw;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DrawDa
{
	public float[]
		projection_m4x4_float = new float[16],
		modelview_m4x4_float = new float[16],
		color_v4_float = new float[4],
		light0position_v4_float = new float[4];
	public float
		light_b,
		light_s;
}
