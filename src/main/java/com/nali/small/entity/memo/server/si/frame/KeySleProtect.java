package com.nali.small.entity.memo.server.si.frame;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.list.entity.si.SIEProtect;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;

public class KeySleProtect
<
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeyS<BD, E, I, S, MS>
{
	public SIEProtect<BD, E, I, S, MS> sileprotect;
	public KeySleProtect(S s, byte key_data_index)
	{
		super(s, key_data_index);

		this.sileprotect = (SIEProtect<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEProtect.ID);
	}
//
//	@Override
//	public void init()
//	{
//		super.init();
//		this.sileprotect = (SILeProtect<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeProtect.ID);
//	}

	@Override
	public boolean onUpdate()
	{
		if ((this.sileprotect.flag & SIEProtect.B_ON) == SIEProtect.B_ON)
		{
			short[] key_short_array = this.siekey.key_short_array;
			short[] fix_key_short_array = this.s.getFixKeyShortArray();
			byte[] key_data_byte_array = this.s.getKeyDataByteArray();

			byte key_short_index = key_data_byte_array[this.key_data_index];
			byte fix_key_index0;
			byte fix_key_index1;

			switch (this.sileprotect.flag & (SIEProtect.B_ANIMATE1 + SIEProtect.B_ANIMATE2))
			{
				case 0:
				{
					fix_key_index0 = key_data_byte_array[this.key_data_index + 1];
					fix_key_index1 = key_data_byte_array[this.key_data_index + 2];

					if (key_short_array[key_short_index] < fix_key_short_array[fix_key_index0] || key_short_array[key_short_index] > fix_key_short_array[fix_key_index0 + 1])
					{
						key_short_array[key_short_index] = fix_key_short_array[fix_key_index0];
					}
					else if (key_short_array[key_short_index] == fix_key_short_array[fix_key_index0 + 1])
					{
						key_short_array[key_short_index] = fix_key_short_array[fix_key_index1];
						this.sileprotect.flag |= SIEProtect.B_ANIMATE1;
					}
					else
					{
						++key_short_array[key_short_index];
					}

					break;
				}
				case SIEProtect.B_ANIMATE1:
				{
					fix_key_index1 = key_data_byte_array[this.key_data_index + 2];

					if (key_short_array[key_short_index] < fix_key_short_array[fix_key_index1] || key_short_array[key_short_index] > fix_key_short_array[fix_key_index1 + 1])
					{
						key_short_array[key_short_index] = fix_key_short_array[fix_key_index1];
					}
					else if (key_short_array[key_short_index] == fix_key_short_array[fix_key_index1 + 1])
					{
						key_short_array[key_short_index] = fix_key_short_array[fix_key_index1];
					}
					else
					{
						++key_short_array[key_short_index];
					}

					break;
				}
				case SIEProtect.B_ANIMATE2:
				{
					byte fix_key_index2 = key_data_byte_array[this.key_data_index + 3];

					if (key_short_array[key_short_index] < fix_key_short_array[fix_key_index2] || key_short_array[key_short_index] > fix_key_short_array[fix_key_index2 + 1])
					{
						key_short_array[key_short_index] = fix_key_short_array[fix_key_index2];
					}
					else if (key_short_array[key_short_index] == fix_key_short_array[fix_key_index2 + 1])
					{
						key_short_array[key_short_index] = fix_key_short_array[fix_key_index2];
						this.sileprotect.flag ^= SIEProtect.B_ANIMATE1 + SIEProtect.B_ANIMATE2;
					}
					else
					{
						++key_short_array[key_short_index];
					}

					break;
				}
				case SIEProtect.B_ANIMATE1 + SIEProtect.B_ANIMATE2:
				{
					fix_key_index0 = key_data_byte_array[this.key_data_index + 1];

					byte fix_key_index3 = key_data_byte_array[this.key_data_index + 4];

					if (key_short_array[key_short_index] < fix_key_short_array[fix_key_index3] || key_short_array[key_short_index] > fix_key_short_array[fix_key_index3 + 1])
					{
						key_short_array[key_short_index] = fix_key_short_array[fix_key_index3];
					}
					else if (key_short_array[key_short_index] == fix_key_short_array[fix_key_index3 + 1])
					{
						key_short_array[key_short_index] = fix_key_short_array[fix_key_index0];
						this.sileprotect.flag &= 255 - (SIEProtect.B_ANIMATE1 + SIEProtect.B_ANIMATE2);
					}
					else
					{
						++key_short_array[key_short_index];
					}

					break;
				}
				default:
				{
					break;
				}
			}

			this.siekey.sync_byte_arraylist.add(key_short_index);
			return true;
//			int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
//			byte[] frame_byte_array = this.s.getFrameByteArray();
//			this.step = 1;
//			byte frame = frame_byte_array[this.rg];
//			byte rg = frame_byte_array[this.rg + 1];
//			byte index1 = frame_byte_array[this.rg + 2];
//			switch (this.sileprotect.state & (4+8))
//			{
//				case 0:
//				{
//					if (this.siekey.frame_int_array[frame] < frame_2d_int_array[rg][0] || this.siekey.frame_int_array[frame] > frame_2d_int_array[rg][1])
//					{
//						this.siekey.frame_int_array[frame] = frame_2d_int_array[rg][0];
//						this.step = 0;
//					}
//					else if (this.siekey.frame_int_array[frame] == frame_2d_int_array[rg][1])
//					{
//						this.siekey.frame_int_array[frame] = frame_2d_int_array[index1][0];
//						this.step = 0;
//		//					this.sileprotect.main_state = 1;
//						this.sileprotect.state |= 4;
//					}
//
//					break;
//				}
//				case 4:
//				{
//					if (this.siekey.frame_int_array[frame] < frame_2d_int_array[index1][0] || this.siekey.frame_int_array[frame] > frame_2d_int_array[index1][1])
//					{
//						this.siekey.frame_int_array[frame] = frame_2d_int_array[index1][0];
//						this.step = 0;
//					}
//					else if (this.siekey.frame_int_array[frame] == frame_2d_int_array[index1][1])
//					{
//						this.siekey.frame_int_array[frame] = frame_2d_int_array[index1][0];
//						this.step = 0;
//					}
//
//					break;
//				}
//				case 8:
//				{
//					byte index2 = frame_byte_array[this.rg + 3];
//					if (this.siekey.frame_int_array[frame] < frame_2d_int_array[index2][0] || this.siekey.frame_int_array[frame] > frame_2d_int_array[index2][1])
//					{
//						this.siekey.frame_int_array[frame] = frame_2d_int_array[index2][0];
//						this.step = 0;
//					}
//					else if (this.siekey.frame_int_array[frame] == frame_2d_int_array[index2][1])
//					{
//						this.siekey.frame_int_array[frame] = frame_2d_int_array[index2][0];
//						this.step = 0;
//		//					this.sileprotect.main_state = 1;
//		//					this.sileprotect.state |= 4;
//		//					this.sileprotect.state &= 255-(8);
//						this.sileprotect.state ^= 4+8;
//					}
//
//					break;
//				}
//				case 4+8:
//				{
//					byte index3 = frame_byte_array[this.rg + 4];
//					if (this.siekey.frame_int_array[frame] < frame_2d_int_array[index3][0] || this.siekey.frame_int_array[frame] > frame_2d_int_array[index3][1])
//					{
//						this.siekey.frame_int_array[frame] = frame_2d_int_array[index3][0];
//						this.step = 0;
//					}
//					else if (this.siekey.frame_int_array[frame] == frame_2d_int_array[index3][1])
//					{
//		//					this.siekey.frame_int_array[frame] = frame_2d_int_array[index3][0];
//						this.step = 0;
//		//					this.sileprotect.main_state = -2;
//						this.sileprotect.state &= 255-(4+8);
//						return true;
//					}
//					break;
//				}
//				default:
//				{
//					break;
//				}
//			}
//
//			return true;
		}

		return false;
	}
}
