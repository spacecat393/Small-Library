package com.nali.small.entity.memo.client;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.da.client.IClientDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.render.mix.MixRenderSe;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ClientSe<RC extends IClientDaS, R extends RenderS<BD, RC>, SD, BD extends IBothDaNe & IBothDaSn, E extends Entity, I extends IMixE<SD, BD, E>, MB extends MixBoxE<RC, R, SD, BD, E, I, MR, ?>, MR extends MixRenderSe<RC, R, SD, BD, E, I, MB, ?>> extends ClientE<RC, R, SD, BD, E, I, MB, MR> implements IClientS<RC, R, SD, BD, E, I, MR>
{
    public ClientSe(I i, R r)
    {
        super(i, r);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        this.readEntityFromNBT(this.r, this.i);
    }

    @Override
    public void updateClientObject()
    {
        super.updateClientObject();
        this.updateClientObject(this.r, this.i);
    }

    @Override
    public void initFakeFrame()
    {
        this.setFakeS(this.mr);
    }
}
