package com.nali.small.entity.memo.client;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.da.client.IClientDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.render.mix.MixRenderSe;
import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IClientS<RC extends IClientDaS, R extends RenderS<BD, RC>, SD, BD extends IBothDaNe & IBothDaSn, E extends Entity, I extends IMixE<SD, BD, E>, MR extends MixRenderSe<RC, R, SD, BD, E, I, ?, ?>>
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

    default void setFakeS(MR mr)
    {
        mr.updateSkinning();
    }
}
