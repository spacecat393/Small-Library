package com.nali.small.entity.memo.client.box.mix;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.da.client.IClientDaS;
import com.nali.list.entity.si.SIEInvOpenInv;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientLe;
import com.nali.small.entity.memo.client.IClientRsInv;
import com.nali.small.entity.memo.client.box.hit.HitOleEat;
import com.nali.small.entity.memo.client.box.hit.HitOlePat;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.mix.MixRenderSe;
import com.nali.sound.ISoundDaLe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class MixBoxSleInv<RC extends IClientDaS, R extends RenderS<BD, RC>, SD extends ISoundDaLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLivingBase, I extends IMixE<SD, BD, E>, MC extends MixCIE<RC, R, SD, BD, E, I, ?, MR, C>, MR extends MixRenderSe<RC, R, SD, BD, E, I, MC, ?, C>, C extends ClientLe<RC, R, SD, BD, E, I, MC, ?, MR> & IClientRsInv> extends MixBoxE<RC, R, SD, BD, E, I, MC, MR, C>
{
	public MixBoxSleInv(C c)
	{
		super(c);
	}

	@Override
	public List<AxisAlignedBB> get()
	{
//		List<AxisAlignedBB> axisalignedbb_list = super.get();
		List<AxisAlignedBB> axisalignedbb_list = new ArrayList();
		axisalignedbb_list.add(this.getHeadAxisAlignedBB());
		axisalignedbb_list.add(this.getMouthAxisAlignedBB());
		return axisalignedbb_list;
	}

	@Override
	public void init(C c)
	{
//		super.init(c);
		this.sehit_list.add(new HitOlePat(this.c));
		this.sehit_list.add(new HitOleEat(this.c));
	}

	@Override
	public void checkAxisAlignedBB(Entity player_entity)
	{
		if (player_entity.isSneaking())
		{
			this.c.sendSSI(new byte[1 + 8 + 1], SIEInvOpenInv.ID);
		}
		else
		{
			super.checkAxisAlignedBB(player_entity);
		}
	}

	public AxisAlignedBB getMouthAxisAlignedBB()
	{
		E e = this.c.i.getE();
//		R r = this.c.r;
		int[] iv_int_array = this.c.getIVIntArray();

//		float[] pos_vec4 = skinningrender.getScale3DSkinning((OpenGLSkinningMemory)skinningrender.dataloader.openglobjectmemory_array[iv_int_array[10]], (float)this.posX, (float)this.posY, (float)this.posZ, 0, 0, 0, iv_int_array[10], iv_int_array[11]);
//		float[] pos_vec4 = skinningrender.getScale3DSkinning((OpenGLSkinningMemory)skinningrender.dataloader.object_array[iv_int_array[10]], (float)this.posX, (float)this.posY, (float)this.posZ, 0, 0, 0, iv_int_array[10], iv_int_array[11]);
		float[] pos_vec4 = this.c.r.getScale3DSkinning((float)e.posX, (float)e.posY, (float)e.posZ, 0, 0, 0, iv_int_array[10], iv_int_array[11]);

		double x = pos_vec4[0] / pos_vec4[3];
		double y = pos_vec4[1] / pos_vec4[3];
		double z = pos_vec4[2] / pos_vec4[3];

		double hw = e.width / 1.5F;
		double hh = e.height / 4.0F;

		return new AxisAlignedBB
		(
			x - hw, y - hh, z - hw,
			x + hw, y + hh, z + hw
		);
	}

	public AxisAlignedBB getHeadAxisAlignedBB()
	{
		E e = this.c.i.getE();
		double hw = e.width / 2.0F + 0.001F;
		double y = e.posY + e.height / 1.125F;

		return new AxisAlignedBB
		(
			e.posX - hw, y, e.posZ - hw,
			e.posX + hw, e.posY + e.height + 0.001F, e.posZ + hw
		);
	}
}
