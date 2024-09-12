package com.nali.list.entity.si;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.frame.FrameS;
import net.minecraft.entity.Entity;

public class SIEFrame<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, MS> & IServerS, MS extends MixSIE<SD, BD, E, I, S>> extends SI<SD, BD, E, I, S, MS>
{
	public static byte ID;

	public int[] frame_int_array;

	public SIEFrame(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.frame_int_array = new int[this.s.i.getIntegerDataParameterArray().length];
	}

	@Override
	public void call()
	{

	}

	@Override
	public void onUpdate()
	{
//		this.updateFrame();
		for (FrameS[] frames_array : this.s.getFrameS2DArray())
		{
			for (FrameS frames : frames_array)
			{
				if (frames.onUpdate())
				{
					frames.stepFrame();
					break;
				}
			}
		}
	}

	@Override
	public void writeFile(SIData sidata)
	{

	}

	@Override
	public void readFile(SIData sidata)
	{

	}

	@Override
	public int size()
	{
		return 0;
	}
}
