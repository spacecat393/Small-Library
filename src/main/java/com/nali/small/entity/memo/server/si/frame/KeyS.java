package com.nali.small.entity.memo.server.si.frame;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.list.entity.si.SIEKey;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;

//renew -> both
//send id/frame(byte) time(byte*2) from server
//create new animation from time
//fix time
public abstract class KeyS
<
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
>
{
	public S s;

	//key time future_key future_time
	//need edit
	//step += MAX_SHORT / max_key
//	public short step;//0 - MAX_SHORT
	//use as index
//	public byte key;//0 - 255 -> SIEFrame
	public byte key_data_index;
	public SIEKey<BD, E, I, S, MS> siekey;

	public KeyS(S s, byte key_data_index)
	{
		this.s = s;
		this.key_data_index = key_data_index;
		this.siekey = (SIEKey<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEKey.ID);
	}

	public void sync()
	{
		I i = this.s.i;
		byte[] key_data_byte_array = this.s.getKeyDataByteArray();
		byte[] key_index_byte_array = this.siekey.key_index_byte_array;
		short[] time_short_array = this.siekey.time_short_array;
		BD bd = i.getBD();
		byte sync_index = bd.Se_SyncIndex();
		EntityDataManager entitydatamanager = i.getE().getDataManager();
		DataParameter<Byte>[] byte_dataparameter_array = i.getByteDataParameterArray();

		int index = sync_index + key_data_byte_array[this.key_data_index] * 6;
//		time_short_array[0] += this.step;
		entitydatamanager.set(byte_dataparameter_array[index], key_index_byte_array[0]);
		entitydatamanager.set(byte_dataparameter_array[index + 1], (byte)time_short_array[0]);
		entitydatamanager.set(byte_dataparameter_array[index + 2], (byte)(time_short_array[0] >> 8));

//		index =
//		time_short_array[1] += this.step[1];
		entitydatamanager.set(byte_dataparameter_array[index + 3], key_index_byte_array[1]);
		entitydatamanager.set(byte_dataparameter_array[index + 4], (byte)time_short_array[1]);
		entitydatamanager.set(byte_dataparameter_array[index + 5], (byte)(time_short_array[1] >> 8));
	}

	public abstract boolean onUpdate();

	public float getKey(short[] key_short_array, short key_index, short time_index)
	{
		return key_short_array[key_index] + (this.siekey.time_short_array[time_index] & 0xFFFF) / this.getStep(key_short_array, key_index);
	}

	public float getStep(short[] key_short_array, short key_index)
	{
		return (float)Short.MAX_VALUE / key_short_array[key_index + 2];
	}
}
