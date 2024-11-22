package com.nali.small.render;

import com.nali.da.IBothDaO;
import com.nali.render.RenderO;
import com.nali.system.opengl.memo.client.MemoG;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.data.SmallData.SHADER_STEP;
import static com.nali.list.data.SmallData.TEXTURE_STEP;

@SideOnly(Side.CLIENT)
public class SmallRenderO
<
	BD extends IBothDaO
> extends RenderO<BD>
{
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
