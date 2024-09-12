package com.nali.small.render;

import com.nali.da.IBothDaSn;
import com.nali.da.client.IClientDaS;
import com.nali.render.RenderS;
import com.nali.system.opengl.memo.client.MemoG;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.data.SmallData.SHADER_STEP;
import static com.nali.list.data.SmallData.TEXTURE_STEP;

@SideOnly(Side.CLIENT)
public class SmallRenderS<BD extends IBothDaSn, RC extends IClientDaS> extends RenderS<BD, RC>
{
	public SmallRenderS(RC rc, BD bd)
	{
		super(rc, bd);
	}

	@Override
	public int getTextureID(MemoG rg)
	{
		return TEXTURE_STEP + super.getTextureID(rg);
	}

	@Override
	public int getShaderID(MemoG rg)
	{
		return SHADER_STEP + super.getShaderID(rg);
	}
}
