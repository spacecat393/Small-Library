package com.nali.small.entity.memo.client;

import com.nali.da.IBothDaNe;
import com.nali.da.client.IClientDaO;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixESoundDa;
import com.nali.small.entity.inv.InvLe;
import com.nali.small.entity.memo.IBothLeInv;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import com.nali.sound.ISoundDaLe;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ClientLeInv
<
	IE extends InvLe,
	RC extends IClientDaO,
	R extends RenderO<RC>,
	SD extends ISoundDaLe,
	BD extends IBothDaNe,
	E extends EntityLivingBase,
	I extends IMixE<BD, E> & IMixESoundDa<SD>,
	MC extends MixCIE<RC, R, BD, E, I, MB, MR, ?>,
	MB extends MixBoxE<RC, R, BD, E, I, MC, MR, ?>,
	MR extends MixRenderE<RC, R, BD, E, I, MC, MB, ?>
> extends ClientLe<RC, R, SD, BD, E, I, MC, MB, MR> implements IBothLeInv<IE, E>
{
	public IE ie;

	public ClientLeInv(I i, R r)
	{
		super(i, r);
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
