package com.nali.list.entity.ai;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

import java.util.UUID;

public class AIEOwner<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

    public UUID uuid;

    public AIEOwner(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {

    }

    @Override
    public void call()
    {
        this.uuid = this.s.a.entityplayermp.getUniqueID();
    }

    @Override
    public void onUpdate()
    {

    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        if (this.uuid != null)
        {
            byte[] byte_array = new byte[16];
            ByteWriter.set(byte_array, this.uuid, 0);
            nbttagcompound.setByteArray("AIEOwner_uuid", byte_array);
        }
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        if (nbttagcompound.hasKey("AIEOwner_uuid", 7))
        {
            this.uuid = ByteReader.getUUID(nbttagcompound.getByteArray("AIEOwner_uuid"), 0);
        }
    }

    public Entity getOwner()
    {
        if (this.uuid == null)
        {
            return null;
        }

        return this.s.worldserver.getEntityFromUuid(this.uuid);
    }
}