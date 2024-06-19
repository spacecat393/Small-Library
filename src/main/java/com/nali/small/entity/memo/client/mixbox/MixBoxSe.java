package com.nali.small.entity.memo.client.mixbox;

import com.nali.render.SkinningRender;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.client.ClientSle;
import com.nali.small.entity.memo.client.hits.HitSeEat;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
public class MixBoxSe<R extends SkinningRender, E extends EntityLivingBase, I extends IMixLe<E>, C extends ClientSle<R, E, I, ?>> extends MixBoxE<R, E, I, C>
{
    public MixBoxSe(C c)
    {
        super(c);
    }

    @Override
    public List<AxisAlignedBB> get()
    {
        List<AxisAlignedBB> axisalignedbb_list = super.get();
        axisalignedbb_list.add(this.getMouthAxisAlignedBB());
        return axisalignedbb_list;
    }

    @Override
    public void init(C c)
    {
        super.init(c);
        this.sehit_list.add(new HitSeEat(this.c));
    }

    public AxisAlignedBB getMouthAxisAlignedBB()
    {
        E e = this.c.i.getE();
        SkinningRender skinningrender = this.c.r;
        int[] iv_int_array = this.c.getIVIntArray();

//        float[] pos_vec4 = skinningrender.getScale3DSkinning((OpenGLSkinningMemory)skinningrender.dataloader.openglobjectmemory_array[iv_int_array[10]], (float)this.posX, (float)this.posY, (float)this.posZ, 0, 0, 0, iv_int_array[10], iv_int_array[11]);
//        float[] pos_vec4 = skinningrender.getScale3DSkinning((OpenGLSkinningMemory)skinningrender.dataloader.object_array[iv_int_array[10]], (float)this.posX, (float)this.posY, (float)this.posZ, 0, 0, 0, iv_int_array[10], iv_int_array[11]);
        float[] pos_vec4 = skinningrender.getScale3DSkinning((float)e.posX, (float)e.posY, (float)e.posZ, 0, 0, 0, iv_int_array[10], iv_int_array[11]);

        double x = pos_vec4[0] / pos_vec4[3];
        double y = pos_vec4[1] / pos_vec4[3];
        double z = pos_vec4[2] / pos_vec4[3];

        double hw = e.width / 1.5F;
        double hh = e.height / 4.0F;

        return new AxisAlignedBB
        (
            x - hw, y - hh, z - hw,
            x + hw, y + hh, z + hw
        );
    }
}
