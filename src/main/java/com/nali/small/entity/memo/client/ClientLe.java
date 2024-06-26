package com.nali.small.entity.memo.client;

import com.nali.data.IBothDaSe;
import com.nali.data.client.ClientDaSn;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SyncUUIDToClient;
import com.nali.network.NetworkRegistry;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.IBothLe;
import com.nali.small.entity.memo.client.mixbox.MixBoxE;
import com.nali.small.entity.memo.work.WorkEBodyYaw;
import com.nali.system.bytes.BytesWriter;
import com.nali.system.opengl.memo.MemoGo;
import com.nali.system.opengl.memo.MemoSo;
import com.nali.system.opengl.store.StoreO;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ClientLe<RG extends MemoGo, RS extends MemoSo, RC extends ClientDaSn, RST extends StoreO<RG, RS>, R extends RenderO<RG, RS, RST, RC>, BD extends IBothDaSe, E extends EntityLivingBase, I extends IMixLe<BD, E>, M extends MixBoxE<RG, RS, RC, RST, R, BD, E, I, ?>> extends ClientE<RG, RS, RC, RST, R, BD, E, I, M> implements IBothLe<BD, E, I>
{
    public WorkEBodyYaw workebodyyaw;

//    public byte[] work_byte_array;

    public byte[] sync_byte_array;

    public ClientLe(I i, M m)
    {
        super(i, m);
//        this.work_byte_array = new byte[workbytes.MAX_WORKS()];
        this.sync_byte_array = new byte[i.getBD().MaxSync()];
    }

    @Override
    public void onUpdate()
    {
        E e = this.i.getE();
        EntityDataManager entitydatamanager = e.getDataManager();
        DataParameter<Byte>[] byte_dataparameter_array = this.i.getByteDataParameterArray();

        if (!this.fake)
        {
            for (int i = 0; i < byte_dataparameter_array.length; ++i)
            {
                this.sync_byte_array[i] = entitydatamanager.get(byte_dataparameter_array[i]);
            }

            this.updateClientObject();
        }

        this.updateClient();

        this.soundrender.set((float)e.posX, (float)e.posY, (float)e.posZ);
    }

    @Override
    public void getHurtSound(DamageSource damagesource)
    {
        this.soundrender.play(this.i.getSoundLe().HURT());
    }

    @Override
    public void getDeathSound()
    {
        this.soundrender.play(this.i.getSoundLe().DEATH());
    }

    public void updateClient()
    {
        BD bd = this.i.getBD();
        E e = this.i.getE();
        float scale = e.getDataManager().get(this.i.getFloatDataParameterArray()[0]);
        e.width = bd.Width() * scale;
        e.height = bd.Height() * scale;
    }

    public void updateClientObject()
    {
        E e = this.i.getE();

        if (this.uuid == null)
        {
            byte[] byte_array = new byte[5];
            byte_array[0] = SyncUUIDToClient.ID;
            BytesWriter.set(byte_array, e.getEntityId(), 1);
            NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
        }

        this.r.c.scale = e.getDataManager().get(this.i.getFloatDataParameterArray()[0]);
    }

    @Override
    public WorkEBodyYaw getWorkEBodyYaw()
    {
        return this.workebodyyaw;
    }
}
