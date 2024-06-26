package com.nali.small.entity.memo.client.mixbox;

import com.nali.data.IBothDaSe;
import com.nali.data.client.ClientDaSn;
import com.nali.render.RenderS;
import com.nali.render.SkinningRender;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.client.ClientSle;
import com.nali.small.entity.memo.client.hits.HitOeEat;
import com.nali.system.opengl.memo.MemoGs;
import com.nali.system.opengl.memo.MemoSs;
import com.nali.system.opengl.store.StoreS;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
public class MixBoxSle<RG extends MemoGs, RS extends MemoSs, RC extends ClientDaSn, RST extends StoreS<RG, RS>, R extends RenderS<BD, RG, RS, RST, RC>, BD extends IBothDaSe, E extends EntityLivingBase, I extends IMixLe<BD, E>, C extends ClientSle<RG, RS, RC, RST, R, BD, E, I, ?>> extends MixBoxE<RG, RS, RC, RST, R, BD, E, I, C>
{
    public MixBoxSle(C c)
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
        this.sehit_list.add(new HitOeEat(this.c));
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
