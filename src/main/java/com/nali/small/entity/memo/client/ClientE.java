package com.nali.small.entity.memo.client;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaO;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SSI;
import com.nali.network.NetworkRegistry;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.IBothE;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.FRenderE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
public abstract class ClientE
<
	BD extends IBothDaE & IBothDaO,
	R extends RenderO<BD>,
	E extends Entity,
	I extends IMixE<BD, E>,
	MC extends MixCIE<BD, R, E, I, MB, MR, ?>,
	MB extends MixBoxE<BD, R, E, I, MC, MR, ?>,
	MR extends MixRenderE<BD, R, E, I, MC, MB, ?>
> implements IBothE<E>
{
	public static String EMPTY_STRING = "---";

	public static Map<Long, ClientE> C_MAP = new HashMap();

	public I i;
	public R r;
	public MC mc;
	public MB mb;
	public MR mr;

	//not fake
	public byte[] sync_byte_array;
//	public boolean should_render;

	//fake
	public long key;
	public int e_id;
	public boolean fake;
	public String name_string = EMPTY_STRING;
	public byte state;//regen outline/glowing die
	public float x, y, z, hp;

	public ClientE(R r)
	{
		this.r = r;
	}

	public ClientE(I i, R r)
	{
		this(r);
		this.i = i;

		this.sync_byte_array = new byte[this.i.getBD().E_MaxSync()];
	}

	@Override
	public void writeFile()
	{
	}

	@Override
	public void readFile()
	{
	}

	@Override
	public void onReadNBT()
	{
		this.mc.onReadNBT();
	}

//	@Override
//	public void setShouldRender(boolean result)
//	{
//		this.should_render = result;
//	}

	@Override
	public void onUpdate()
	{
		this.mc.onUpdate();
	}

	@Override
	public void doRender(FRenderE<E> frendere, double ox, double oy, double oz, float partial_ticks)
	{
		this.mr.doRender(frendere, ox, oy, oz, partial_ticks);
	}

	public void sendSSI(byte[] byte_array, byte i)
	{
		E e = this.i.getE();
		byte_array[0] = SSI.ID;
		ByteWriter.set(byte_array, (long)e.world.provider.getDimension() << 32 | e.getEntityId(), 1);
		byte_array[1 + 8] = i;
		NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
	}
}
