package com.nali.list.entity.ai;

import com.nali.data.IBothDaNe;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.method.client.CSetLocation;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundLe;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

import static com.nali.small.entity.EntityMath.isInArea;

public class AILeSetLocation<SD extends ISoundLe, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

    public AILeFindMove<SD, BD, E, I, S, A> ailefindmove;

    public long blockpos_long = -1;
    public float far;
    public BlockPos blockpos;
//    public BlockPos temp_blockpos;
    public byte state;//on_move

    public AILeSetLocation(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {
        this.ailefindmove = (AILeFindMove<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeFindMove.ID);
    }

    @Override
    public void call()
    {

    }

    public void set()
    {
        byte[] byte_array = this.s.a.byte_array;
        int id = (int) ByteReader.getFloat(byte_array, 1 + 16 + 1 + 1);
        if (id == 1)
        {
            this.far = ByteReader.getFloat(byte_array, 1 + 16 + 1 + 1 + 4);
        }
        else
        {
            this.blockpos_long = new BlockPos(ByteReader.getFloat(byte_array, 1 + 16 + 1 + 1 + 4), ByteReader.getFloat(byte_array, 1 + 16 + 1 + 1 + 4 + 4), ByteReader.getFloat(byte_array, 1 + 16 + 1 + 1 + 4 + 4 + 4)).toLong();
        }
    }

    public void fetch()
    {
        byte[] byte_array = new byte[1 + 8 + 4];
        byte_array[0] = CSetLocation.ID;
        ByteWriter.set(byte_array, this.blockpos_long, 1);
        ByteWriter.set(byte_array, this.far, 1 + 8);
        NetworkRegistry.I.sendTo(new ClientMessage(byte_array), this.s.a.entityplayermp);
    }

    @Override
    public void onUpdate()
    {
        if (this.s.isMove())
        {
            if (this.blockpos_long != -1 && this.far != 0)
            {
//                if (this.temp_blockpos == null || this.blockpos != this.temp_blockpos)
                if (this.blockpos == null || this.blockpos.toLong() != this.blockpos_long)
                {
                    this.blockpos = BlockPos.fromLong(this.blockpos_long);
                }
                else
                {
                    if (!isInArea(this.s.getI().getE(), this.blockpos, this.far))
                    {
//                        this.ailefindmove.setBreakGoal(this.blockpos.getX() + 0.5D, this.blockpos.getY() + 0.5D, this.blockpos.getZ() + 0.5D);
                        this.ailefindmove.setGoal(this.blockpos.getX() + 0.5D, this.blockpos.getY() + 0.5D, this.blockpos.getZ() + 0.5D);
//                        this.set_location = true;
                    }
//                    else if (this.set_location)
                    else if ((this.state & 1) == 1)
                    {
                        this.ailefindmove.endGoal();
//                        this.set_location = false;
                        this.state &= 255-1;
                    }
                }
//                this.temp_blockpos = this.blockpos;
            }
            else
            {
//                if (this.set_location)
                if ((this.state & 1) == 1)
                {
                    this.ailefindmove.endGoal();
//                    this.set_location = false;
                    this.blockpos = null;
                }

                this.state &= 255-1;
            }
        }
        else
        {
            this.state &= 255-1;
        }
    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setByte("AILeSetLocation_state", this.state);
        nbttagcompound.setLong("AILeSetLocation_blockpos", this.blockpos_long);
        nbttagcompound.setFloat("AILeSetLocation_far", this.far);
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.state = nbttagcompound.getByte("AILeSetLocation_state");
        this.blockpos_long = nbttagcompound.getLong("AILeSetLocation_blockpos");
        this.far = nbttagcompound.getFloat("AILeSetLocation_far");
    }
}
