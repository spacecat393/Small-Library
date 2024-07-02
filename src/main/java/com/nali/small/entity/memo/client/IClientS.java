package com.nali.small.entity.memo.client;

import com.nali.data.IBothDaNe;
import com.nali.data.IBothDaSn;
import com.nali.data.client.IClientDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixE;
import com.nali.system.opengl.memo.client.MemoGs;
import com.nali.system.opengl.memo.client.MemoSs;
import com.nali.system.opengl.memo.client.store.StoreS;
import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IClientS<RG extends MemoGs, RS extends MemoSs, RC extends IClientDaS, RST extends StoreS<RG, RS>, R extends RenderS<BD, RG, RS, RST, RC>, SD, BD extends IBothDaNe & IBothDaSn, E extends Entity, I extends IMixE<SD, BD, E>>
{
    default void updateClientObject(R r, I i)
    {
        EntityDataManager entitydatamanager = i.getE().getDataManager();
        r.scale = entitydatamanager.get(i.getFloatDataParameterArray()[0]);

        DataParameter<Integer>[] integer_dataparameter = i.getIntegerDataParameterArray();

        for (int x = 0; x < r.frame_int_array.length; ++x)
        {
            r.frame_int_array[x] = entitydatamanager.get(integer_dataparameter[x++]);
        }
    }

    default void readEntityFromNBT(R r, I i/*, NBTTagCompound nbttagcompound*/)
    {
        r.scale = i.getBD().Scale();
    }
}
