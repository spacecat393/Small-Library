package com.nali.small.entity.memo.server.ai;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.system.Reflect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nali.Nali.error;

public abstract class MixAIE<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, ?>>
{
    public static List<Class> AI_CLASS_LIST;

    static
    {
        AI_CLASS_LIST = Reflect.getClasses("com.nali.list.entity.ai");
        AI_CLASS_LIST.sort(Comparator.comparing(Class::getName));
        for (byte i = 0; i < AI_CLASS_LIST.size(); ++i)
        {
            try
            {
                AI_CLASS_LIST.get(i).getField("ID").set(null, i);
            }
            catch (IllegalAccessException | NoSuchFieldException e)
            {
                error(e);
            }
        }
    }

    public S s;
    public Map<Byte, AI/*<SD, BD, E, I, S, ?>*/> aie_map = new HashMap();
    public byte state = (byte)255;//main_work sub_work init ai-lock ?map chunk ?regen

    public EntityPlayerMP entityplayermp;
    public byte[] byte_array;

//    public FrameE<SD, BD, E, I, S, ?>[] framee_array;

    public MixAIE(S s)
    {
        this.s = s;

        byte[] ai_byte_array = this.s.getI().getAI();
//        for (byte i = 0; i < ai_byte_array.length; ++i)
        for (byte b : ai_byte_array)
        {
            try
            {
//                byte b = ai_byte_array[i];
//                aie.num = i;
                this.aie_map.put(b, (AI)AI_CLASS_LIST.get(b).getConstructors()[0].newInstance(this.s));
            }
            catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
            {
                error(e);
            }
        }
    }

    public/* abstract*/ void init()
    {
        for (AI ai : this.aie_map.values())
        {
            ai.init();
        }
    }

//    public static void init()
//    {
//        AI_CLASS_LIST = Reflect.getClasses("com.nali.list.entity.ai");
//        for (int i = 0; i < AI_CLASS_LIST.size(); ++i)
//        {
//            try
//            {
//                AI_CLASS_LIST.get(i).getField("ID").set(null, i);
//            }
//            catch (IllegalAccessException | NoSuchFieldException e)
//            {
//                Nali.I.error(e);
//            }
//        }
//    }

    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        if ((this.state & 4) == 0)
        {
//            this.main_work_byte_array[this.workbytes.LOCK_INVENTORY() / 8] ^= (byte)Math.pow(2, this.workbytes.LOCK_INVENTORY() % 8);
//            this.main_work_byte_array[this.workbytes.LOCK_DAMAGE() / 8] ^= (byte)Math.pow(2, this.workbytes.LOCK_DAMAGE() % 8);
//
//            if (this.workbytes.CARE_OWNER() != -1)
//            {
//                this.main_work_byte_array[this.workbytes.CARE_OWNER() / 8] ^= (byte)Math.pow(2, this.workbytes.CARE_OWNER() % 8);
//            }

            nbttagcompound.setFloat("float_0", this.s.i.getBD().Scale());
//            this.initWriteEntityToNBT(nbttagcompound);
            this.init();
//            nbttagcompound.setByteArray("work_bytes", this.main_work_byte_array);
            this.state |= 4;
        }

        nbttagcompound.setByte("MixAIE_state", this.state);

        for (byte b : this.s.getI().getAI())
        {
            this.aie_map.get(b).writeNBT(nbttagcompound);
        }
    }

    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.state = (byte)(nbttagcompound.getByte("MixAIE_state") & 255-8);

        if ((this.state & 4) == 0)
        {
//            this.main_work_byte_array[this.workbytes.LOCK_INVENTORY() / 8] ^= (byte)Math.pow(2, this.workbytes.LOCK_INVENTORY() % 8);
//            this.main_work_byte_array[this.workbytes.LOCK_DAMAGE() / 8] ^= (byte)Math.pow(2, this.workbytes.LOCK_DAMAGE() % 8);
//
//            if (this.workbytes.CARE_OWNER() != -1)
//            {
//                this.main_work_byte_array[this.workbytes.CARE_OWNER() / 8] ^= (byte)Math.pow(2, this.workbytes.CARE_OWNER() % 8);
//            }

            I i = this.s.i;
            i.getE().getDataManager().set(i.getFloatDataParameterArray()[0], i.getBD().Scale());
//            this.initReadEntityFromNBT(nbttagcompound);
            this.init();

//            this.sus_init = true;
            this.state |= 4;
        }

        for (byte b : this.s.getI().getAI())
        {
            this.aie_map.get(b).readNBT(nbttagcompound);
        }
    }

    public void update()
    {
        for (byte b : this.s.getI().getAI())
        {
            this.aie_map.get(b).onUpdate();
        }

//        for (FrameE<SD, BD, E, I, S, ?> framee : this.framee_array)
//        {
//            framee.onUpdate();
//        }

//        this.state = (byte)((this.state & 1) * 4 | 255-4);
        this.state = (byte)255;
    }

    public void set(EntityPlayerMP entityplayermp, byte[] byte_array)
    {
//        if ((this.state & 8) == 8)
//        {
        this.entityplayermp = entityplayermp;
        this.byte_array = byte_array;
//        }
    }

    public void call(byte id)
    {
        this.aie_map.get(id).call();
    }

    public void clear()
    {
        this.entityplayermp = null;
        this.byte_array = null;
    }
//    {
//        this.a.aie_list.get().num;
//        this.main_work_byte_array[this.workbytes.FOLLOW() / 8] ^= (byte)Math.pow(2, this.workbytes.FOLLOW() % 8);
//        this.main_work_byte_array[this.workbytes.RANDOM_WALK() / 8] ^= (byte)Math.pow(2, this.workbytes.RANDOM_WALK() % 8);
//        this.main_work_byte_array[this.workbytes.RANDOM_LOOK() / 8] ^= (byte)Math.pow(2, this.workbytes.RANDOM_LOOK() % 8);
//        this.main_work_byte_array[this.workbytes.WALK_TO() / 8] ^= (byte)Math.pow(2, this.workbytes.WALK_TO() % 8);
//        this.main_work_byte_array[this.workbytes.LOOK_TO() / 8] ^= (byte)Math.pow(2, this.workbytes.LOOK_TO() % 8);
//        this.main_work_byte_array[this.workbytes.REVIVE() / 8] ^= (byte)Math.pow(2, this.workbytes.REVIVE() % 8);
//    }
//    public abstract int[][] getFrame2DIntArray();
}
