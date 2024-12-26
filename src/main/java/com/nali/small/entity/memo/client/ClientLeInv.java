package com.nali.small.entity.memo.client;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaO;
import com.nali.render.RenderO;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.inv.InvLe;
import com.nali.small.entity.memo.IBothLeInv;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ClientLeInv
<
	IE extends InvLe,
	BD extends IBothDaE & IBothDaO,
	R extends RenderO<BD>,
//	SD extends ISoundDaLe,
	E extends EntityLe,
	I extends IMixE<BD, E>/* & IMixESoundDa<SD>*/,
	MC extends MixCIE<BD, R, E, I, MB, MR, ?>,
	MB extends MixBoxE<BD, R, E, I, MC, MR, ?>,
	MR extends MixRenderE<BD, R, E, I, MC, MB, ?>
> extends ClientLe<BD, R/*, SD*/, E, I, MC, MB, MR> implements IBothLeInv<IE, E>
{
	public IE ie;

	public ClientLeInv(I i, R r)
	{
		super(i, r);
	}

	public ClientLeInv(R r)
	{
		super(r);
	}

	@Override
	public void damageArmor(float damage)
	{
	}

	@Override
	public void setItemStackToSlot(EntityEquipmentSlot entityequipmentslot, ItemStack itemstack)
	{
	}

	@Override
	public IE getIE()
	{
		return this.ie;
	}
}
