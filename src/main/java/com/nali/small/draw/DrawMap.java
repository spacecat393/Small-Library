package com.nali.small.draw;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class DrawMap
{
	public static List<DrawMap> DRAWMAP_LIST = new ArrayList();

	public int
		rg,
		texture,
		rs;
//		hash_code;
	public byte extra_bit;//transparent

	public DrawMap(int rg, int texture, int rs, byte extra_bit)
	{
		this.rg = rg;
		this.texture = texture;
		this.rs = rs;
		this.extra_bit = extra_bit;
//		this.hash_code = Objects.hash(this.rg, this.texture, this.rs, this.extra_bit);
	}

	@Override
	public boolean equals(Object obj)
	{
		DrawMap drawmap = (DrawMap)obj;
		return
			drawmap.rg == this.rg &&
			drawmap.texture == this.texture &&
			drawmap.rs == this.rs &&
			drawmap.extra_bit == this.extra_bit;
//		return drawmap.hash_code == this.hash_code;
	}

	public static DrawMap get(DrawMap drawmap)
	{
		for (DrawMap old_drawmap : DRAWMAP_LIST)
		{
			if (old_drawmap.equals(drawmap))
			{
				return old_drawmap;
			}
		}
		DRAWMAP_LIST.add(drawmap);
		return drawmap;
	}

//	@Override
//	public int hashCode()
//	{
//		return this.hash_code;
//	}
}
