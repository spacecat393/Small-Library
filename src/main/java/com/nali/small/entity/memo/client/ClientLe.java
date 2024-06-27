package com.nali.small.entity.memo.client;

import com.nali.data.IBothDaE;
import com.nali.data.client.IClientDaO;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.IBothLe;
import com.nali.small.entity.memo.client.mixbox.MixBoxE;
import com.nali.small.entity.memo.client.render.IRender;
import com.nali.small.entity.memo.work.WorkEBodyYaw;
import com.nali.sound.ISoundLe;
import com.nali.system.opengl.memo.MemoGo;
import com.nali.system.opengl.memo.MemoSo;
import com.nali.system.opengl.store.StoreO;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ClientLe<RG extends MemoGo, RS extends MemoSo, RC extends IClientDaO, RST extends StoreO<RG, RS>, R extends RenderO<RG, RS, RST, RC>, SD extends ISoundLe, BD extends IBothDaE, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, M extends MixBoxE<RG, RS, RC, RST, R, SD, BD, E, I, IR, ?>, IR extends IRender> extends ClientE<RG, RS, RC, RST, R, SD, BD, E, I, M, IR> implements IBothLe<SD, BD, E, I>
{
    public WorkEBodyYaw workebodyyaw;

//    public byte[] work_byte_array;

    public ClientLe(I i, M m)
    {
        super(i, m);
//        this.work_byte_array = new byte[workbytes.MAX_WORKS()];
        this.sync_byte_array = new byte[i.getBD().MaxSync()];
    }

    @Override
    public void getHurtSound(DamageSource damagesource)
    {
        this.sound.play(this.i.getSD().HURT());
    }

    @Override
    public void getDeathSound()
    {
        this.sound.play(this.i.getSD().DEATH());
    }

    @Override
    public WorkEBodyYaw getWorkEBodyYaw()
    {
        return this.workebodyyaw;
    }
}
