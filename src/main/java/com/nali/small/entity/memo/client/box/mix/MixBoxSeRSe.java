package com.nali.small.entity.memo.client.box.mix;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaO;
import com.nali.list.entity.si.SIEPlayWithRSe;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.box.hit.HitE;
import com.nali.small.entity.memo.client.box.hit.HitEPlayWithRSe;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
public class MixBoxSeRSe
<
	BD extends IBothDaE & IBothDaO,
	R extends RenderO<BD>,
	E extends Entity,
	I extends IMixE<BD, E>,
	MC extends MixCIE<BD, R, E, I, ?, MR, C>,
	MR extends MixRenderE<BD, R, E, I, MC, ?, C>,
	C extends ClientE<BD, R, E, I, MC, ?, MR>
> extends MixBoxE<BD, R, E, I, MC, MR, C>
{
//	public final static byte B_OPEN = 1;
//	public static byte PAGE;

	public MixBoxSeRSe(C c)
	{
		super(c);
	}

	@Override
	public HitE isOn(Entity entity, Vec3d stand_vec3d, Vec3d look_vec3d, boolean work)
	{
//		if (entity.isSneaking())
//		{
//			if (work)
//			{
//				PAGE |= B_OPEN;
//			}p
//			return HitEPage.HITEPAGE;
////			SmallPage.setSmallPage();
////			this.c.sendSSI(new byte[1 + 8 + 1], SIEInvOpenInv.ID);
//		}
//		else
//		{
		if (work)
		{
			this.c.sendSSI(new byte[1 + 8 + 1], SIEPlayWithRSe.ID);
		}
		return HitEPlayWithRSe.HITEPLAYWITHRSE;
//		}
//		return null;
	}

	@Override
	public List<AxisAlignedBB> getAxisAlignedBBList()
	{
		return null;
	}

	@Override
	public void init(C c)
	{
	}
}
