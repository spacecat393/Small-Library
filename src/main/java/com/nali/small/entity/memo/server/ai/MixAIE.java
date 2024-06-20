package com.nali.small.entity.memo.server.ai;

import com.nali.Nali;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.frame.FrameE;
import com.nali.system.Reflect;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MixAIE<E extends Entity, I extends IMixE<E>, S extends ServerE<E, I, ?>>
{
    public static List<Class> AI_CLASS_LIST;

    public S s;
    public Map<Byte, AI<E, I, S, ?>> aie_map = new HashMap();
    public byte state = (byte)255;//main_work sub_work init ?map chunk

    public FrameE<E, I, S, ?>[] framee_array;

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
                Nali.I.error(e);
            }
        }

        for (AI<E, I, S, ?> aie : this.aie_map.values())
        {
            aie.init();
        }
    }

    public static void init()
    {
        AI_CLASS_LIST = Reflect.getClasses("com.nali.list.entity.ai");
        for (int i = 0; i < AI_CLASS_LIST.size(); ++i)
        {
            try
            {
                AI_CLASS_LIST.get(i).getField("ID").set(null, i);
            }
            catch (IllegalAccessException | NoSuchFieldException e)
            {
                Nali.I.error(e);
            }
        }
    }

    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        for (byte b : this.s.getI().getAI())
        {
            this.aie_map.get(b).writeNBT(nbttagcompound);
        }
    }

    public void readNBT(NBTTagCompound nbttagcompound)
    {
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

        for (FrameE<E, I, S, ?> framee : this.framee_array)
        {
            framee.onUpdate();
        }

//        this.state = (byte)((this.state & 1) * 4 | 255-4);
        this.state = (byte)255;
    }

    public abstract byte[][] getFrame2D();
}
