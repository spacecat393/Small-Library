package com.nali.small.render;

import com.nali.da.IBothDaO;
import com.nali.da.IBothDaS;
import com.nali.render.RenderS;
import com.nali.small.draw.Draw;
import com.nali.small.draw.DrawDa;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IRenderS<BD extends IBothDaO & IBothDaS, R extends RenderS<BD>> extends IRenderO<BD, R>
{
	@Override
	default void startDrawLater(BD bd, R r, DrawDa drawda)
	{
		IRenderO.super.startDrawLater(bd, r, drawda);
		Draw.KEY_FLOAT_ARRAY_LIST.put(Draw.DATA_SIZE, r.skinning_float_array);
	}
}
