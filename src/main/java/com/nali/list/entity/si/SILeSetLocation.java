package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.method.client.CSetLocation;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.util.math.BlockPos;

import static com.nali.small.entity.EntityMath.isInArea;

public class SILeSetLocation
<
	BD extends IBothDaE,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

	public SILeFindMove<BD, E, I, S, MS> silefindmove;

	public long blockpos_long = -1;
	public float far;
	public BlockPos blockpos;
//	public BlockPos temp_blockpos;
	public byte state;//on_move

	public SILeSetLocation(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.silefindmove = (SILeFindMove<BD, E, I, S, MS>)this.s.ms.si_map.get(SILeFindMove.ID);
	}

	@Override
	public void call()
	{

	}

	public void set()
	{
		byte[] byte_array = this.s.ms.byte_array;
		int id = (int) ByteReader.getFloat(byte_array, 1 + 8 + 1 + 1);
		if (id == 1)
		{
			this.far = ByteReader.getFloat(byte_array, 1 + 8 + 1 + 1 + 4);
		}
		else
		{
			this.blockpos_long = new BlockPos(ByteReader.getFloat(byte_array, 1 + 8 + 1 + 1 + 4), ByteReader.getFloat(byte_array, 1 + 8 + 1 + 1 + 4 + 4), ByteReader.getFloat(byte_array, 1 + 8 + 1 + 1 + 4 + 4 + 4)).toLong();
		}
	}

	public void fetch()
	{
		byte[] byte_array = new byte[1 + 8 + 4];
		byte_array[0] = CSetLocation.ID;
		ByteWriter.set(byte_array, this.blockpos_long, 1);
		ByteWriter.set(byte_array, this.far, 1 + 8);
		NetworkRegistry.I.sendTo(new ClientMessage(byte_array), this.s.ms.entityplayermp);
	}

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
					if (!isInArea(e, this.blockpos, this.far))
					{
//						this.silefindmove.setBreakGoal(this.blockpos.getX() + 0.5D, this.blockpos.getY() + 0.5D, this.blockpos.getZ() + 0.5D);
						this.silefindmove.setGoal(this.blockpos.getX() + 0.5D, this.blockpos.getY() + 0.5D, this.blockpos.getZ() + 0.5D);
//						this.set_location = true;
					}
//					else if (this.set_location)
					else if ((this.state & 1) == 1)
					{
						this.silefindmove.endGoal();
//						this.set_location = false;
						this.state &= 255-1;
					}
				}
//				this.temp_blockpos = this.blockpos;
			}
			else
			{
//				if (this.set_location)
				if ((this.state & 1) == 1)
				{
					this.silefindmove.endGoal();
//					this.set_location = false;
					this.blockpos = null;
				}

				this.state &= 255-1;
			}
		}
		else
		{
			this.state &= 255-1;
		}
	}

	@Override
	public void writeFile(SIData sidata)
	{
		sidata.byte_array[sidata.index++] = this.state;

		ByteWriter.set(sidata.byte_array, this.blockpos_long, sidata.index);
		sidata.index += 8;

		ByteWriter.set(sidata.byte_array, this.far, sidata.index);
		sidata.index += 4;
	}

	@Override
	public void readFile(SIData sidata)
	{
		this.state = sidata.byte_array[sidata.index++];

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
