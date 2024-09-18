package com.nali.small.entity.memo.client.ci;

import com.nali.da.IBothDaNe;
import com.nali.da.client.IClientDaO;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import com.nali.system.Reflect;
import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nali.Nali.error;

//@SideOnly(Side.CLIENT)
public class MixCIE
<
	RC extends IClientDaO,
	R extends RenderO<RC>,
	BD extends IBothDaNe,
	E extends Entity,
	I extends IMixE<BD, E>,
	MB extends MixBoxE<RC, R, BD, E, I, ?, MR, C>,
	MR extends MixRenderE<RC, R, BD, E, I, ?, MB, C>,
	C extends ClientE<RC, R, BD, E, I, ?, MB, MR>
>
{
	public static String EMPTY_STRING = "---";
	public static List<Class> CI_CLASS_LIST;

	public C c;
	public Map<Byte, CI> ci_map = new HashMap();

//	public int packet_int;//sound_id
	public byte[] byte_array;

	public boolean fake;
	public String name_string = EMPTY_STRING;
	public byte state;//regen outline/glowing die
//	public Integer dimension;
	public float x, y, z, hp;

	public MixCIE(C c)
	{
		this.c = c;
		byte[] ci_byte_array = this.c.i.getCI();
		for (byte b : ci_byte_array)
		{
			try
			{
				this.ci_map.put(b, (CI) CI_CLASS_LIST.get(b).getConstructors()[0].newInstance(this.c));
			}
			catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
			{
				error(e);
			}
		}
	}

	public static void initID()
	{
		CI_CLASS_LIST = Reflect.getClasses("com.nali.list.entity.ci");
		CI_CLASS_LIST.sort(Comparator.comparing(Class::getName));
		for (byte i = 0; i < CI_CLASS_LIST.size(); ++i)
		{
			try
			{
				CI_CLASS_LIST.get(i).getField("ID").set(null, i);
			}
			catch (IllegalAccessException | NoSuchFieldException e)
			{
				error(e);
			}
		}
	}

	public void init()
	{
		for (CI ci : this.ci_map.values())
		{
			ci.init();
		}
	}

	public void onReadNBT()
	{
		for (byte b : this.c.i.getCI())
		{
			this.ci_map.get(b).onReadNBT();
		}
	}

	public void call(byte id)
	{
		this.ci_map.get(id).call();
	}

	public void onUpdate()
	{
		if (!this.fake)
		{
			I i = this.c.i;
			E e = i.getE();

			EntityDataManager entitydatamanager = e.getDataManager();
			DataParameter<Byte>[] byte_dataparameter_array = i.getByteDataParameterArray();

			for (int x = 0; x < byte_dataparameter_array.length; ++x)
			{
				this.c.sync_byte_array[x] = entitydatamanager.get(byte_dataparameter_array[x]);
			}

//			this.updateNotFake();
			this.updateBox();
		}

		for (byte b : this.c.i.getCI())
		{
			this.ci_map.get(b).onUpdate();
		}
	}

//	public void updateNotFake()
//	{
////		if (this.c.uuid == null)
////		{
////			byte[] byte_array = new byte[5];
////			byte_array[0] = SSUUID.ID;
////			ByteWriter.set(byte_array, this.c.i.getE().getEntityId(), 1);
////			NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
////		}
//
//		this.updateBox();
//	}

	public void updateBox()
	{
		BD bd = this.c.i.getBD();
		E e = this.c.i.getE();
		float scale = e.getDataManager().get(this.c.i.getFloatDataParameterArray()[0]);
		e.width = bd.Width() * scale;
		e.height = bd.Height() * scale;
	}
}
