package com.nali.small.entity.memo.client.box.mix;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaO;
import com.nali.da.IBothDaS;
import com.nali.gui.key.Key;
import com.nali.gui.key.KeyEdit;
import com.nali.gui.page.Page;
import com.nali.list.key.SmallPage;
import com.nali.render.RenderS;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixES;
import com.nali.small.entity.IMixESInv;
import com.nali.small.entity.memo.client.ClientLe;
import com.nali.small.entity.memo.client.box.hit.HitOleEat;
import com.nali.small.entity.memo.client.box.hit.HitOlePat;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.mix.MixRenderSe;
import com.nali.small.gui.page.PageEntityMe;
import com.nali.small.render.IRenderS;
import com.nali.system.BothLoader;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class MixBoxSleInv
<
	BD extends IBothDaE & IBothDaO & IBothDaS,
	R extends RenderS<BD> & IRenderS<BD, R>,
	E extends EntityLe,
	I extends IMixE<BD, E> & IMixES & IMixESInv,
	MC extends MixCIE<BD, R, E, I, ?, MR, C>,
	MR extends MixRenderSe<BD, R, E, I, MC, ?, C>,
	C extends ClientLe<BD, R, E, I, MC, ?, MR>
> extends MixBoxE<BD, R, E, I, MC, MR, C>
{
	public MixBoxSleInv(C c)
	{
		super(c);
	}

	@Override
	public List<AxisAlignedBB> getAxisAlignedBBList()
	{
		List<AxisAlignedBB> axisalignedbb_list = new ArrayList();
		axisalignedbb_list.add(this.getHeadAxisAlignedBB());
		axisalignedbb_list.add(this.getMouthAxisAlignedBB());
		return axisalignedbb_list;
	}

	@Override
	public void init(C c)
	{
		this.hite_list.add(new HitOlePat(this.c));
		this.hite_list.add(new HitOleEat(this.c));
	}

	@Override
	public boolean isOn(Entity entity, Vec3d stand_vec3d, Vec3d look_vec3d)
	{
		if (entity.isSneaking())
		{
			E e = this.c.i.getE();
			SmallPage.setSmallPage();

			if (Page.PAGE_LIST.size() > 1)
			{
				int index = Page.PAGE_LIST.size() - 1;
				Page.PAGE.set(Page.PAGE_LIST.get(index), Page.KEY_LIST.get(index));
				Page.PAGE_LIST.remove(index);
				Page.KEY_LIST.remove(index);
			}

//			Page.PAGE_LIST.add(Page.PAGE);
//			Page.KEY_LIST.add(Key.KEY);
//			Page.PAGE.set(new PageEntity(), new KeySelect());

			Page.PAGE_LIST.add(Page.PAGE);
			Page.KEY_LIST.add(Key.KEY);
			Page.PAGE.set(new PageEntityMe(e.getEntityId(), e.world.provider.getDimension(), e.getName()), new KeyEdit());

//			this.c.sendSSI(new byte[1 + 8 + 1], SIEInvOpenInv.ID);
			return true;
		}
		else
		{
			return super.isOn(entity, stand_vec3d, look_vec3d);
		}
	}

	public AxisAlignedBB getMouthAxisAlignedBB()
	{
		E e = this.c.i.getE();
//		R r = this.c.r;
		int[] iv_int_array = this.c.i.getIVIntArray();

//		float[] pos_vec4 = skinningrender.getScale3DSkinning((OpenGLSkinningMemory)skinningrender.dataloader.openglobjectmemory_array[iv_int_array[10]], (float)this.posX, (float)this.posY, (float)this.posZ, 0, 0, 0, iv_int_array[10], iv_int_array[11]);
//		float[] pos_vec4 = skinningrender.getScale3DSkinning((OpenGLSkinningMemory)skinningrender.dataloader.object_array[iv_int_array[10]], (float)this.posX, (float)this.posY, (float)this.posZ, 0, 0, 0, iv_int_array[10], iv_int_array[11]);
		float[] pos_vec4 = BothLoader.F2_LIST.get(this.c.i.getBD().S_FrameID()).getScale3DSkinning(this.c.r.scale, this.c.r.skinning_float_array, (float)e.posX, (float)e.posY, (float)e.posZ, 0, 0, 0, iv_int_array[10], iv_int_array[11]);

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
