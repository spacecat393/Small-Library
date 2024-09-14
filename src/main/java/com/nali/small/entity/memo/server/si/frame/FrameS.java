package com.nali.small.entity.memo.server.si.frame;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.si.SIEFrame;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import net.minecraft.entity.Entity;

public abstract class FrameS
<
	BD extends IBothDaNe,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
>
{
	public S s;

	public byte step = 1;
//	public int main_integer_index;
//	public byte[] byte_array;//frame index...
//	public byte frame/*, index*/;
//	public int[][] int_2d_array; // start end
	public int index;
//	public boolean lock;
//	public Supplier<Boolean>[] condition_boolean_supplier_array;
	public SIEFrame<BD, E, I, S, MS> sieframe;

	public FrameS(S s, int index/*, byte[] byte_array*//*, int main_integer_index*//*, byte frame*//*, byte index*//*, int[][] int_2d_array*/)
	{
		this.s = s;
		this.index = index;
//		this.byte_array = byte_array;
//		this.main_integer_index = main_integer_index;
//		this.frame = frame;
//		this.index = index;
//		this.int_2d_array = int_2d_array;
		this.init();
		this.sieframe = (SIEFrame<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEFrame.ID);
	}

//	@Override
//	public void onUpdate()
//	{
//		I i = this.s.getI();
////		Small.LOGGER.info("FRAME " + this.s.frame_int_array[this.integer_index]);
//
//		if (!this.lock)
//		{
//			for (Supplier<Boolean> boolean_supplier : this.condition_boolean_supplier_array)
//			{
//				if (boolean_supplier.get())
//				{
//					i.getE().getDataManager().set(i.getIntegerDataParameterArray()[/*this.main_integer_index*/this.integer_index], this.s.frame_int_array[this.integer_index] + this.step);
//					return;
//				}
//			}
//		}
//	}

	public void stepFrame()
	{
		I i = this.s.i;
		byte[] frame_byte_array = this.s.getFrameByteArray();
		int[] frame_int_array = this.sieframe.frame_int_array;
		byte frame = frame_byte_array[this.index];
		frame_int_array[frame] += this.step;
		i.getE().getDataManager().set(i.getIntegerDataParameterArray()[frame_byte_array[frame]], frame_int_array[frame]);
	}

	public void init()
	{
	}

	public abstract boolean onUpdate();
}
