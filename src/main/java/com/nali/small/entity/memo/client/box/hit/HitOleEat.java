package com.nali.small.entity.memo.client.box.hit;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaNE;
import com.nali.da.IBothDaO;
import com.nali.list.entity.si.SIESound;
import com.nali.list.entity.si.SILeEat;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientLe;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HitOleEat
<
	BD extends IBothDaE & IBothDaO & IBothDaNE,
	R extends RenderO<BD>,
	E extends EntityLivingBase,
	I extends IMixE<BD, E>,
	MC extends MixCIE<BD, R, E, I, MB, MR, C>,
	MR extends MixRenderE<BD, R, E, I, MC, MB, C>,
	MB extends MixBoxE<BD, R, E, I, MC, MR, C>,
	C extends ClientLe<BD, R, E, I, MC, MB, MR>
> extends HitE<BD, R, E, I, MC, MR, MB, C>
{
	public HitOleEat(C c)
	{
		super(c);
	}

	@Override
	public void run(Entity player_entity)
	{
		Item item = ((EntityLivingBase)player_entity).getHeldItemMainhand().getItem();
		boolean milk_bucket = item == Items.MILK_BUCKET;

		if (item instanceof ItemFood || milk_bucket)
		{
			this.c.sendSSI(new byte[1 + 8 + 1], SILeEat.ID);

			byte[] s_byte_array = new byte[1 + 8 + 1 + 4];
			ByteWriter.set(s_byte_array, this.c.i.getBD().NE_EAT(), 1 + 8 + 1);
			this.c.sendSSI(s_byte_array, SIESound.ID);
		}
	}

	@Override
	public boolean should(Entity player_entity)
	{
		Item item = ((EntityLivingBase)player_entity).getHeldItemMainhand().getItem();
		return item instanceof ItemFood || item == Items.MILK_BUCKET;
	}
}
