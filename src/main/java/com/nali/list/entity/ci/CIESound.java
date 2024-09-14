package com.nali.list.entity.ci;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.da.client.IClientDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.IClientESound;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.ci.CI;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.mix.MixRenderSe;
import com.nali.system.bytes.ByteReader;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CIESound
<
	RC extends IClientDaS,
	R extends RenderS<BD, RC>,
	BD extends IBothDaNe & IBothDaSn,
	E extends Entity,
	I extends IMixE<BD, E>,
	MC extends MixCIE<RC, R, BD, E, I, MB, MR, C>,
	MB extends MixBoxE<RC, R, BD, E, I, MC, MR, C>,
	MR extends MixRenderSe<RC, R, BD, E, I, MC, MB, C>,
	C extends ClientE<RC, R, BD, E, I, MC, MB, MR> & IClientESound
> extends CI<RC, R, BD, E, I, MC, MB, MR, C>
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
	}

	@Override
	public void onUpdate()
	{
		E e = this.c.i.getE();
		this.c.getSound().set((float)e.posX, (float)e.posY, (float)e.posZ);
	}

	@Override
	public void onReadNBT()
	{

	}
}
