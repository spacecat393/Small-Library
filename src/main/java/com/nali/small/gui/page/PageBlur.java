package com.nali.small.gui.page;

import com.nali.list.data.SmallData;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL20;

import static com.nali.list.container.gui.SmallGui.SMALLGUI;
import static com.nali.system.ClientLoader.S_LIST;
import static com.nali.system.opengl.memo.client.MemoC.FULL_ARRAY_BUFFER;

@SideOnly(Side.CLIENT)
public class PageBlur extends Page
{
	@Override
	public void init()
	{

	}

	@Override
	public void detect()
	{

	}

	@Override
	public void preDraw()
	{

	}

	@Override
	public void draw()
	{
		MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 5);
		OpenGlHelper.glUseProgram(rs.program);
		int v = rs.attriblocation_int_array[0];
		GL20.glEnableVertexAttribArray(v);

		this.drawQuadStatic
		(
			rs,
			FULL_ARRAY_BUFFER,
			SMALLGUI.mc.getFramebuffer().framebufferTexture
		);

		GL20.glDisableVertexAttribArray(v);
	}
}
