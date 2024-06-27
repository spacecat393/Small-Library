package com.nali.small.entity.memo.client;

import com.nali.data.IBothDaSe;
import com.nali.data.client.IClientDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.mixbox.MixBoxE;
import com.nali.small.entity.memo.client.render.IRender;
import com.nali.sound.ISoundN;
import com.nali.system.opengl.memo.MemoGs;
import com.nali.system.opengl.memo.MemoSs;
import com.nali.system.opengl.store.StoreS;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ClientSe<RG extends MemoGs, RS extends MemoSs, RC extends IClientDaS, RST extends StoreS<RG, RS>, R extends RenderS<BD, RG, RS, RST, RC>, SD extends ISoundN, BD extends IBothDaSe<SD>, E extends Entity, I extends IMixE<SD, BD, E>, M extends MixBoxE<RG, RS, RC, RST, R, SD, BD, E, I, IR, ?>, IR extends IRender> extends ClientE<RG, RS, RC, RST, R, SD, BD, E, I, M, IR> implements IClientS<RG, RS, RC, RST, R, SD, BD, E, I>
{
    public ClientSe(I i, M m)
    {
        super(i, m);
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
}
