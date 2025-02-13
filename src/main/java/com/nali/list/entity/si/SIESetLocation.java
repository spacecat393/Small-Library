package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.small.entity.EntityMath;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

public class SIESetLocation
<
	BD extends IBothDaE,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

	public SIEFindMove<BD, E, I, S, MS> siefindmove;

	public long blockpos_long = -1;
	public float far;
	public BlockPos blockpos;
//	public BlockPos temp_blockpos;

	public final static byte B_ON = 1;
	public byte flag;//on_move

	public SIESetLocation(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.siefindmove = (SIEFindMove<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEFindMove.ID);
	}

	@Override
	public void call(EntityPlayerMP entityplayermp, byte[] byte_array)
	{
	}

//	public void set()
//	{
//		byte[] byte_array = this.s.ms.byte_array;
//		int id = (int) ByteReader.getFloat(byte_array, 1 + 8 + 1 + 1);
//		if (id == 1)
//		{
//			this.far = ByteReader.getFloat(byte_array, 1 + 8 + 1 + 1 + 4);
//		}
//		else
//		{
//			this.blockpos_long = new BlockPos(ByteReader.getFloat(byte_array, 1 + 8 + 1 + 1 + 4), ByteReader.getFloat(byte_array, 1 + 8 + 1 + 1 + 4 + 4), ByteReader.getFloat(byte_array, 1 + 8 + 1 + 1 + 4 + 4 + 4)).toLong();
//		}
//	}

//	public void fetch()
//	{
//		byte[] byte_array = new byte[1 + 8 + 4];
//		byte_array[0] = CSetLocation.ID;
//		ByteWriter.set(byte_array, this.blockpos_long, 1);
//		ByteWriter.set(byte_array, this.far, 1 + 8);
//		NetworkRegistry.I.sendTo(new ClientMessage(byte_array), this.s.ms.entityplayermp);
//	}

	@Override
	public void onUpdate()
	{
		E e = this.s.i.getE();
		if (this.s.isMove(e))
		{
			if (this.blockpos_long != -1 && this.far != 0)
			{
//				if (this.temp_blockpos == null || this.blockpos != this.temp_blockpos)
				if (this.blockpos == null || this.blockpos.toLong() != this.blockpos_long)
				{
					this.blockpos = BlockPos.fromLong(this.blockpos_long);
				}
				else
				{
					if (!EntityMath.isInArea(e, this.blockpos, this.far))
					{
//						this.siefindmove.setBreakGoal(this.blockpos.getX() + 0.5D, this.blockpos.getY() + 0.5D, this.blockpos.getZ() + 0.5D);
						this.siefindmove.setGoal(this.blockpos.getX() + 0.5D, this.blockpos.getY() + 0.5D, this.blockpos.getZ() + 0.5D);
//						this.set_location = true;
					}
//					else if (this.set_location)
					else if ((this.flag & B_ON) == B_ON)
					{
						this.siefindmove.endGoal();
//						this.set_location = false;
						this.flag &= 255 - B_ON;
					}
				}
//				this.temp_blockpos = this.blockpos;
			}
			else
			{
//				if (this.set_location)
				if ((this.flag & B_ON) == B_ON)
				{
					this.siefindmove.endGoal();
//					this.set_location = false;
					this.blockpos = null;
				}

				this.flag &= 255 - B_ON;
			}
		}
		else
		{
			this.flag &= 255 - B_ON;
		}
	}

	@Override
	public void writeFile(SIData sidata)
	{
		sidata.byte_array[sidata.index++] = this.flag;

		ByteWriter.set(sidata.byte_array, this.blockpos_long, sidata.index);
		sidata.index += 8;

		ByteWriter.set(sidata.byte_array, this.far, sidata.index);
		sidata.index += 4;
	}

	@Override
	public void readFile(SIData sidata)
	{
		this.flag = sidata.byte_array[sidata.index++];

		this.blockpos_long = ByteReader.getLong(sidata.byte_array, sidata.index);
		sidata.index += 8;

		this.far = ByteReader.getFloat(sidata.byte_array, sidata.index);
		sidata.index += 4;
	}

	@Override
	public int size()
	{
		return 1 + 8 + 4;
	}
}
