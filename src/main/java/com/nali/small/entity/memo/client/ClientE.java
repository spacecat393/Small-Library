package com.nali.small.entity.memo.client;

import com.nali.da.IBothDaNe;
import com.nali.da.client.IClientDaO;
import com.nali.list.entity.ai.AIEInvOpenInv;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SAIE;
import com.nali.list.network.method.server.SSUUID;
import com.nali.list.network.method.server.SSound;
import com.nali.network.NetworkRegistry;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.IBothE;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.render.FRenderE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import com.nali.sound.Sound;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SideOnly(Side.CLIENT)
public abstract class ClientE<RC extends IClientDaO, R extends RenderO<RC>, SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, MB extends MixBoxE<RC, R, SD, BD, E, I, MR, ?>, MR extends MixRenderE<RC, R, SD, BD, E, I, MB, ?>> implements IBothE<SD, BD, E, I>
{
    public static Map<UUID, ClientE> C_MAP = new HashMap();
    public static Map<Integer, UUID> UUID_MAP = new HashMap();

    public I i;
    public R r;
    public MB mb;
    public MR mr;

//    public IEMixBox iemixbox;
//    public float body_rot,
//        head_rot,
//        net_head_yaw,
//        head_pitch;
    public boolean should_render;

    public Sound sound;

    public UUID uuid;

    public boolean fake;
    public byte state;//regen
    public int dimension = -1;
    public float x, y, z;

    public byte[] sync_byte_array;//remove later?

    public ClientE(I i, R r)
    {
        this.i = i;
        this.r = r;
//        this.mb = mb;
//        this.mr = mr;
//        this.r = this.createR();
//        this.mb = this.createMB();
//        this.mr = this.createMR();
        this.sound = this.createSound();
    }

    @Override
    public boolean processInitialInteract(EntityPlayer entityplayer, EnumHand enumhand)
    {
        if (entityplayer.isSneaking())
        {
            this.sendSAIE(new byte[1 + 16 + 1], AIEInvOpenInv.ID);
//            if (this.uuid != null)
//            {
//                byte[] byte_array = new byte[17];
//                byte_array[0] = SOpenInvGUI.ID;
//                ByteWriter.set(byte_array, this.uuid, 1);
//                NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
//            }
        }
        else
        {
            this.mb.checkAxisAlignedBB(entityplayer);
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
            byte_array[0] = SSUUID.ID;
            ByteWriter.set(byte_array, this.i.getE().getEntityId(), 1);
            NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
        }
    }

    @Override
    public void doRender(FRenderE<E> rendere, double ox, double oy, double oz, float partialTicks)
    {
        this.mr.doRender(rendere, ox, oy, oz, partialTicks);
    }

    @Override
    public void setUUID(UUID uuid)
    {
        this.uuid = uuid;
//        C_MAP.put(this.uuid, this);
//        UUID_MAP.put(this.i.getE().getEntityId(), this.uuid);
    }

    @Override
    public void playSound(int i)
    {
        byte[] byte_array = new byte[1 + 4 + 4];
        byte_array[0] = SSound.ID;
        ByteWriter.set(byte_array, this.i.getE().getEntityId(), 1);
        ByteWriter.set(byte_array, i, 5);
        NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
//        this.sound.play(i);
    }

    public void sendSAIE(byte[] byte_array, byte i)
    {
        byte_array[0] = SAIE.ID;
        ByteWriter.set(byte_array, this.uuid, 1);
        byte_array[17] = i;
        NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
    }

//    @Override
//    public void remove()
//    {
//    }

    @Override
    public I getI()
    {
        return this.i;
    }

    public abstract void initFakeFrame();
//    public abstract R createR();
//    public abstract MB createMB();
//    public abstract MR createMR();
    public abstract Sound createSound();
//    public abstract IEMixBox createIEMixBox();
    public abstract int[] getIVIntArray();
    public abstract float[] getRotationFloatArray();
    public abstract float[] getTransformFloatArray();
}
