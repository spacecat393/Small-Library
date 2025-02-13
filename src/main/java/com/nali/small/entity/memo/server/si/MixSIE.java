package com.nali.small.entity.memo.server.si;

import com.nali.da.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.system.Reflect;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nali.Nali.error;

public class MixSIE
<
	BD extends IBothDaE,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, ?>
>
{
	public static List<Class> SI_CLASS_LIST;

	public S s;
	public Map<Byte, SI/*<SD, BD, E, I, S, ?>*/> si_map = new HashMap();

	public final static byte B_MAIN_WORK = 1;
	public final static byte B_SUB_WORK = 2;
	public final static byte B_INIT = 4;
	public final static byte B_LOAD_CHUNK = 8;
	public final static byte B_LOCK_NET = 16;
	public byte flag = (byte)255 - (B_INIT + B_LOAD_CHUNK + B_LOCK_NET);

//	public int entityplayermp_id;
	public EntityPlayerMP entityplayermp;
	public byte[] byte_array;

	public MixSIE(S s, SI[] si_array)
	{
		this.s = s;
		byte[] ai_byte_array = this.s.i.getSI();
//		for (byte b : ai_byte_array)
		for (byte b = 0; b < ai_byte_array.length; ++b)
		{
//			try
//			{
//				this.si_map.put(b, (SI) SI_CLASS_LIST.get(b).getConstructors()[0].newInstance(this.s));
//			}
//			catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
//			{
//				error(e);
//			}
			this.si_map.put(ai_byte_array[b], si_array[b]);
		}
	}

	public static void initID()
	{
		SI_CLASS_LIST = Reflect.getClasses("com.nali.list.entity.si");
		SI_CLASS_LIST.sort(Comparator.comparing(Class::getName));
		for (byte i = 0; i < SI_CLASS_LIST.size(); ++i)
		{
			try
			{
				SI_CLASS_LIST.get(i).getField("ID").set(null, i);
			}
			catch (IllegalAccessException | NoSuchFieldException e)
			{
				error(e);
			}
		}
	}

	public int size()
	{
		int size = 1;
		for (SI si : this.si_map.values())
		{
			size += si.size();
		}

		return size;
	}

	public void init()
	{
		for (SI si : this.si_map.values())
		{
			si.init();
		}
	}

//	public static void init()
//	{
//		SI_CLASS_LIST = Reflect.getClasses("com.nali.list.entity.ai");
//		for (int i = 0; i < SI_CLASS_LIST.size(); ++i)
//		{
//			try
//			{
//				SI_CLASS_LIST.get(i).getField("ID").set(null, i);
//			}
//			catch (IllegalAccessException | NoSuchFieldException e)
//			{
//				Nali.I.error(e);
//			}
//		}
//	}
	public void initFile()
	{
		I i = this.s.i;
		byte[] byte_array = new byte[4];
		ByteWriter.set(byte_array, i.getBD().E_Scale(), 0);
		for (byte b = 0; b < 4; ++b)
		{
			i.getE().getDataManager().set(i.getByteDataParameterArray()[b], byte_array[b]);
		}
		this.init();
	}

	public void writeFile(SIData sidata)
	{
		sidata.byte_array[sidata.index++] = this.flag;

		for (byte b : this.s.i.getSI())
		{
			this.si_map.get(b).writeFile(sidata);
		}
	}

	public void readFile(SIData sidata)
	{
		this.flag = (byte)((sidata.byte_array[sidata.index++] & 255/* - MixSIE.B_LOAD_CHUNK*/) - B_LOCK_NET);

		for (byte b : this.s.i.getSI())
		{
			this.si_map.get(b).readFile(sidata);
		}
	}

	public void update()
	{
//		//load more chunk with move
////		E e = this.s.i.getE();
//		if ((this.state & 8) == 8/* && !ChunkCallBack.CHUNK_MAP.containsKey((long)e.world.provider.getDimension() << 32 | e.getEntityId())*/)
//		{
//			ChunkLoader.updateChunk(this.s);
//		}

		for (byte b : this.s.i.getSI())
		{
//			Nali.warn("si " + this.si_map.get(b));
//			Nali.warn("state " + this.state);
			this.si_map.get(b).onUpdate();
		}

		this.flag |= B_MAIN_WORK + B_SUB_WORK;
	}

//	public void set(EntityPlayerMP entityplayermp, byte[] byte_array)
//	{
//		if ((this.flag & B_LOCK_NET) == 0)
//		{
//			this.flag |= B_LOCK_NET;
//			this.entityplayermp = entityplayermp;
//			this.byte_array = byte_array;
//		}
//	}

	public void call(EntityPlayerMP entityplayermp, byte[] byte_array)
	{
		this.si_map.get(byte_array[1 + 8]).call(entityplayermp, byte_array);
	}

//	public void clear()
//	{
//		this.entityplayermp = null;
//		this.byte_array = null;
//	}
//	{
//		this.ms.aie_list.get().num;
//		this.main_work_byte_array[this.workbytes.FOLLOW() / 8] ^= (byte)Math.pow(2, this.workbytes.FOLLOW() % 8);
//		this.main_work_byte_array[this.workbytes.RANDOM_WALK() / 8] ^= (byte)Math.pow(2, this.workbytes.RANDOM_WALK() % 8);
//		this.main_work_byte_array[this.workbytes.RANDOM_LOOK() / 8] ^= (byte)Math.pow(2, this.workbytes.RANDOM_LOOK() % 8);
//		this.main_work_byte_array[this.workbytes.WALK_TO() / 8] ^= (byte)Math.pow(2, this.workbytes.WALK_TO() % 8);
//		this.main_work_byte_array[this.workbytes.LOOK_TO() / 8] ^= (byte)Math.pow(2, this.workbytes.LOOK_TO() % 8);
//		this.main_work_byte_array[this.workbytes.REVIVE() / 8] ^= (byte)Math.pow(2, this.workbytes.REVIVE() % 8);
//	}
//	public abstract int[][] getFrame2DIntArray();
}
