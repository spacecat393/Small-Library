package com.nali.small.entity.memo.client;

import com.nali.data.IBothDaE;
import com.nali.data.client.IClientDaO;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.OpenInvGUI;
import com.nali.list.network.method.server.SyncUUIDToClient;
import com.nali.network.NetworkRegistry;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.IBothE;
import com.nali.small.entity.memo.client.mixbox.MixBoxE;
import com.nali.small.entity.memo.client.render.IRender;
import com.nali.sound.ISoundN;
import com.nali.sound.Sound;
import com.nali.system.bytes.ByteWriter;
import com.nali.system.opengl.memo.MemoGo;
import com.nali.system.opengl.memo.MemoSo;
import com.nali.system.opengl.store.StoreO;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

@SideOnly(Side.CLIENT)
public abstract class ClientE<RG extends MemoGo, RS extends MemoSo, RC extends IClientDaO, RST extends StoreO<RG, RS>, R extends RenderO<RG, RS, RST, RC>, SD extends ISoundN, BD extends IBothDaE, E extends Entity, I extends IMixE<SD, BD, E>, M extends MixBoxE<RG, RS, RC, RST, R, SD, BD, E, I, IR, ?>, IR extends IRender> implements IBothE<SD, BD, E, I>
{
    public I i;
    public R r;
    public M m;
    public IR ir;

//    public IEMixBox iemixbox;
    public float body_rot,
        head_rot,
        net_head_yaw,
        head_pitch;
    public boolean should_render;

    public Sound sound;

    public UUID uuid;
    public boolean fake;
    public byte[] sync_byte_array;

    public ClientE(I i, M m)
    {
        this.i = i;
        this.m = m;
        this.r = this.createObjectRender();
        this.sound = this.createSoundRender();
    }

    @Override
    public boolean processInitialInteract(EntityPlayer entityplayer, EnumHand enumhand)
    {
        if (entityplayer.isSneaking())
        {
            if (this.uuid != null)
            {
                byte[] byte_array = new byte[17];
                byte_array[0] = OpenInvGUI.ID;
                ByteWriter.set(byte_array, this.uuid, 1);
                NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
            }
        }
        else
        {
            this.m.checkAxisAlignedBB(entityplayer);
        }

        entityplayer.swingArm(enumhand);
        return true;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        this.initFakeFrame();
    }

    @Override
    public void setShouldRender(boolean result)
    {
        this.should_render = result;
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

        this.sound.set((float)e.posX, (float)e.posY, (float)e.posZ);
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
        if (this.uuid == null)
        {
            byte[] byte_array = new byte[5];
            byte_array[0] = SyncUUIDToClient.ID;
            ByteWriter.set(byte_array, this.i.getE().getEntityId(), 1);
            NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
        }
    }

    @Override
    public I getI()
    {
        return this.i;
    }

    public abstract void initFakeFrame();
    public abstract R createObjectRender();
    public abstract Sound createSoundRender();
//    public abstract IEMixBox createIEMixBox();
}
