package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.small.entity.memo.server.si.frame.KeyS;
import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;

import java.util.ArrayList;
import java.util.List;

public class SIEKey
<
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

	public List<Byte> sync_byte_arraylist = new ArrayList();
	//max_key * 2
//	public byte[] key_index_byte_array;
//	public short[] time_short_array;
	public short[] key_short_array;

	public SIEKey(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
//		short mix_max_frame = (short)(this.s.i.getBD().S_MaxFrame() * 2);
//		this.key_index_byte_array = new byte[mix_max_frame];
//		this.time_short_array = new short[mix_max_frame];
		this.key_short_array = new short[this.s.i.getBD().S_MaxFrame()];
	}

	@Override
	public void call()
	{

	}

	@Override
	public void onUpdate()
	{
//		this.updateFrame();
		for (KeyS[] keys_array : this.s.getKeyS2DArray())
		{
			for (KeyS keys : keys_array)
			{
				if (keys.onUpdate())
				{
//					Nali.warn(keys.toString());
//					keys.sync();
					break;
				}
			}
		}

		I i = this.s.i;
//		byte[] key_data_byte_array = this.s.getKeyDataByteArray();
		BD bd = i.getBD();
		byte sync_index = bd.Se_SyncIndex();
		EntityDataManager entitydatamanager = i.getE().getDataManager();
		DataParameter<Byte>[] byte_dataparameter_array = i.getByteDataParameterArray();

		for (Byte sync_byte : this.sync_byte_arraylist)
		{
//			Nali.warn("sync_byte " + sync_byte);
//			int index = sync_index + sync_byte * 6;
//			entitydatamanager.set(byte_dataparameter_array[index], this.key_index_byte_array[sync_byte]);
//			entitydatamanager.set(byte_dataparameter_array[index + 1], (byte)this.time_short_array[sync_byte]);
//			entitydatamanager.set(byte_dataparameter_array[index + 2], (byte)(this.time_short_array[sync_byte] >> 8));
//
//			++sync_byte;
//			entitydatamanager.set(byte_dataparameter_array[index + 3], this.key_index_byte_array[sync_byte]);
//			entitydatamanager.set(byte_dataparameter_array[index + 4], (byte)this.time_short_array[sync_byte]);
//			entitydatamanager.set(byte_dataparameter_array[index + 5], (byte)(this.time_short_array[sync_byte] >> 8));
			int index = sync_index + sync_byte;
			entitydatamanager.set(byte_dataparameter_array[index], (byte)(this.key_short_array[sync_byte] & 0xFF));
			entitydatamanager.set(byte_dataparameter_array[index + 1], (byte)((this.key_short_array[sync_byte] >> 8) & 0xFF));
		}

		this.sync_byte_arraylist.clear();
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
