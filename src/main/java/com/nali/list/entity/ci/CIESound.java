package com.nali.list.entity.ci;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaO;
import com.nali.da.IBothDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixES;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.IClientESound;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.ci.CI;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.mix.MixRenderSe;
import com.nali.system.bytes.ByteReader;
import net.minecraft.entity.Entity;

//@SideOnly(Side.CLIENT)
public class CIESound
<
	BD extends IBothDaE & IBothDaO & IBothDaS,
	R extends RenderS<BD>,
	E extends Entity,
	I extends IMixE<BD, E> & IMixES,
	MC extends MixCIE<BD, R, E, I, MB, MR, C>,
	MB extends MixBoxE<BD, R, E, I, MC, MR, C>,
	MR extends MixRenderSe<BD, R, E, I, MC, MB, C>,
	C extends ClientE<BD, R, E, I, MC, MB, MR> & IClientESound
> extends CI<BD, R, E, I, MC, MB, MR, C>
{
	public static byte ID;

	public CIESound(C c)
	{
		super(c);
	}

	@Override
	public void init()
	{

	}

	@Override
	public void call()
	{
		this.c.getSound().play(ByteReader.getInt(this.c.mc.byte_array, 1 + 8 + 1));
		this.onUpdate();
	}

	@Override
	public void onUpdate()
	{
		E e = this.c.i.getE();
		this.c.getSound().set((float)e.posX, (float)e.posY, (float)e.posZ/*, this.c.mr.head_rot, this.c.mr.head_pitch*/);
	}

	@Override
	public void onReadNBT()
	{

	}
}
