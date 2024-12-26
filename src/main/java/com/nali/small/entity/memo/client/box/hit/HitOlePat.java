package com.nali.small.entity.memo.client.box.hit;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaO;
import com.nali.list.entity.si.SIEPat;
import com.nali.list.entity.si.SIESound;
import com.nali.list.network.message.ServerMessage;
import com.nali.network.NetworkRegistry;
import com.nali.render.RenderO;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientLe;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HitOlePat
<
	BD extends IBothDaE & IBothDaO & IBothDaNe,
	R extends RenderO<BD>,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	MC extends MixCIE<BD, R, E, I, MB, MR, C>,
	MR extends MixRenderE<BD, R, E, I, MC, MB, C>,
	MB extends MixBoxE<BD, R, E, I, MC, MR, C>,
	C extends ClientLe<BD, R, E, I, MC, MB, MR>
> extends HitE<BD, R, E, I, MC, MR, MB, C>
{
	public byte pat_time;

	public HitOlePat(C c)
	{
		super(c);
		this.pat_time = (byte)this.c.i.getE().world.rand.nextInt(16);
	}

	@Override
	public void run(Entity player_entity)
	{
		E e = this.c.i.getE();
//					ItemStack itemstack = entityplayer.getHeldItem(enumhand);
////					this.getEntityData().set(this.getByteEntityDataAccessorArray()[2], (byte)5);
////
////					if (itemstack.getItem() == ItemsRegistry.HAIRBRUSH_ITEM_REGISTRYOBJECT.get())
////					{
		if (--this.pat_time <= 0)
		{
			this.pat_time = (byte)e.world.rand.nextInt(16);

//			this.c.sendSAIE(new byte[1 + 16 + 1], AIEPat.ID);
			byte[] byte_array = new byte[1 + 8 + 1 + 4];
			ByteWriter.set(byte_array, (float)e.getEntityBoundingBox().maxY, 1 + 8 + 1);
			this.c.sendSSI(byte_array, SIEPat.ID);
//			byte_array[0] = SAIE.ID;
//			ByteWriter.set(byte_array, this.c.uuid, 1);
//			byte_array[1 + 16] = AIEPat.ID;
			NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));

//			this.c.sound.play(this.c.i.getSD().PAT());
			byte[] s_byte_array = new byte[1 + 8 + 1 + 4];
			ByteWriter.set(s_byte_array, this.c.i.getBD().Ne_PAT(), 1 + 8 + 1);
			this.c.sendSSI(s_byte_array, SIESound.ID);

//			serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.ON_PAT()] = 1;
//			this.skinningentities.world.spawnEntity(new EntityXPOrb(this.skinningentities.world, this.skinningentities.posX, this.skinningentities.posY, this.skinningentities.posZ, 10));
//
//							if (!entityplayer.isCreative())
//							{
//								itemstack.damageItem(1, entityplayer);
//							}
		}
////					}
	}

	@Override
	public boolean should(Entity player_entity)
	{
		return ((EntityLivingBase)player_entity).getHeldItemMainhand().isEmpty();
	}
}
