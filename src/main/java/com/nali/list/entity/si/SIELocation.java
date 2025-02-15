package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class SIELocation
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

//	public long blockpos_long = -1;
	public float
		far,
		x, y, z;
//	public BlockPos blockpos;
//	public BlockPos temp_blockpos;

	public final static byte B_WORK = 1;

	public final static byte B_ON = 2;
	public byte flag;//on_move

	public SIELocation(S s)
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
			if ((this.flag & B_ON) == B_ON && this.far != 0)
			{
				if (!this.in(e, this.x, this.y, this.z, this.far))
				{
					this.siefindmove.setGoal(this.x, this.y, this.z);
				}
				else if ((this.flag & B_WORK) == B_WORK)
				{
					this.siefindmove.endGoal();
					this.flag &= 255 - B_WORK;
				}
			}
			else
			{
				if ((this.flag & B_WORK) == B_WORK)
				{
					this.siefindmove.endGoal();
				}

				this.flag &= 255 - B_WORK;
			}
//			if (this.blockpos_long != -1 && this.far != 0)
//			{
////				if (this.temp_blockpos == null || this.blockpos != this.temp_blockpos)
//				if (this.blockpos == null || this.blockpos.toLong() != this.blockpos_long)
//				{
//					this.blockpos = BlockPos.fromLong(this.blockpos_long);
//				}
//				else
//				{
//					if (!EntityMath.isInArea(e, this.blockpos, this.far))
//					{
////						this.siefindmove.setBreakGoal(this.blockpos.getX() + 0.5D, this.blockpos.getY() + 0.5D, this.blockpos.getZ() + 0.5D);
//						this.siefindmove.setGoal(this.blockpos.getX() + 0.5D, this.blockpos.getY() + 0.5D, this.blockpos.getZ() + 0.5D);
////						this.set_location = true;
//					}
////					else if (this.set_location)
//					else if ((this.flag & B_WORK) == B_WORK)
//					{
//						this.siefindmove.endGoal();
////						this.set_location = false;
//						this.flag &= 255 - B_WORK;
//					}
//				}
////				this.temp_blockpos = this.blockpos;
//			}
//			else
//			{
////				if (this.set_location)
//				if ((this.flag & B_WORK) == B_WORK)
//				{
//					this.siefindmove.endGoal();
////					this.set_location = false;
//					this.blockpos = null;
//				}
//
//				this.flag &= 255 - B_WORK;
//			}
		}
		else
		{
			this.flag &= 255 - B_WORK;
		}
	}

	@Override
	public void writeFile(SIData sidata)
	{
		sidata.byte_array[sidata.index++] = this.flag;

//		ByteWriter.set(sidata.byte_array, this.blockpos_long, sidata.index);
//		sidata.index += 8;

		ByteWriter.set(sidata.byte_array, this.x, sidata.index);
		sidata.index += 4;

		ByteWriter.set(sidata.byte_array, this.y, sidata.index);
		sidata.index += 4;

		ByteWriter.set(sidata.byte_array, this.z, sidata.index);
		sidata.index += 4;

		ByteWriter.set(sidata.byte_array, this.far, sidata.index);
		sidata.index += 4;
	}

	@Override
	public void readFile(SIData sidata)
	{
		this.flag = sidata.byte_array[sidata.index++];

//		this.blockpos_long = ByteReader.getLong(sidata.byte_array, sidata.index);
//		sidata.index += 8;

		this.x = ByteReader.getFloat(sidata.byte_array, sidata.index);
		sidata.index += 4;

		this.y = ByteReader.getFloat(sidata.byte_array, sidata.index);
		sidata.index += 4;

		this.z = ByteReader.getFloat(sidata.byte_array, sidata.index);
		sidata.index += 4;

		this.far = ByteReader.getFloat(sidata.byte_array, sidata.index);
		sidata.index += 4;
	}

	@Override
	public int size()
	{
		return 1 /*+ 8 */+ 4 + 4 + 4 + 4;
	}

	public boolean in(Entity entity)
	{
		return this.pass() || this.in(entity, this.x, this.y, this.z, this.far);
	}

	public boolean in(double x, double y, double z)
	{
		return this.pass() || this.in(x, y, z, this.x, this.y, this.z, this.far);
	}

	public boolean pass()
	{
		return this.far == 0 || (this.flag & B_ON) == 0;
	}

	public boolean in(Entity entity, double xb, double yb, double zb, double minimum_distance)
	{
		return this.in(entity.posX, entity.posY, entity.posZ, xb, yb, zb, minimum_distance);
	}

	public boolean in(double xa, double ya, double za, double xb, double yb, double zb, double minimum_distance)
	{
		double d0 = xa - xb;
		double d1 = ya - yb;
		double d2 = za - zb;
		return d0 * d0 + d1 * d1 + d2 * d2 < minimum_distance;
	}
}
