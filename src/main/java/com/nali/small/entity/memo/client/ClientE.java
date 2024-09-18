package com.nali.small.entity.memo.client;

import com.nali.da.IBothDaNe;
import com.nali.da.client.IClientDaO;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
public abstract class ClientE
<
	RC extends IClientDaO,
	R extends RenderO<RC>,
	BD extends IBothDaNe,
	E extends Entity,
	I extends IMixE<BD, E>,
	MC extends MixCIE<RC, R, BD, E, I, MB, MR, ?>,
	MB extends MixBoxE<RC, R, BD, E, I, MC, MR, ?>,
	MR extends MixRenderE<RC, R, BD, E, I, MC, MB, ?>
> implements IBothE<E>
{
//	public static Map<UUID, ClientE> C_MAP = new HashMap();
	public static Map<Long, ClientE> C_MAP = new HashMap();
//	public static Map<Integer, UUID> UUID_MAP = new HashMap();

	public I i;
	public R r;
	public MC mc;
	public MB mb;
	public MR mr;

	public boolean should_render;
//	public UUID uuid;
	public long key;
	public byte[] sync_byte_array;//remove later?

	public ClientE(I i, R r)
	{
		this.i = i;
		this.r = r;
		this.sync_byte_array = new byte[this.i.getBD().MaxSync()];
	}

	@Override
	public boolean processInitialInteract(EntityPlayer entityplayer, EnumHand enumhand)
	{
		this.mb.checkAxisAlignedBB(entityplayer);
		entityplayer.swingArm(enumhand);
		return true;
	}

	@Override
	public void writeFile()
	{
	}

	@Override
	public void readFile()
	{
	}

//	@Override
//	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
//	{
//		this.initFakeFrame();
//	}

	@Override
	public void onReadNBT()
	{
		this.mc.onReadNBT();
	}

	@Override
	public void setShouldRender(boolean result)
	{
		this.should_render = result;
	}

	@Override
	public void onUpdate()
	{
		this.mc.onUpdate();
	}

	@Override
	public void doRender(FRenderE<E> rendere, double ox, double oy, double oz, float partialTicks)
	{
		this.mr.doRender(rendere, ox, oy, oz, partialTicks);
	}

//	@Override
//	public void setUUID(UUID uuid)
//	{
//		this.uuid = uuid;
////		C_MAP.put(this.uuid, this);
////		UUID_MAP.put(this.i.getE().getEntityId(), this.uuid);
//	}

	public void sendSSI(byte[] byte_array, byte i)
	{
		E e = this.i.getE();
		byte_array[0] = SSI.ID;
		ByteWriter.set(byte_array, (long)e.world.provider.getDimension() << 32 | e.getEntityId(), 1);
		byte_array[1 + 8] = i;
		NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
	}

//	@Override
//	public void setGlowing(boolean b)
//	{
////		this.glowing = b;
//		if (b)
//		{
//			this.state |= 2;
//		}
//		else
//		{
//			this.state &= 255-2;
//		}
//	}

//	@Override
//	public I getI()
//	{
//		return this.i;
//	}

//	public abstract byte[] getCI();
}
