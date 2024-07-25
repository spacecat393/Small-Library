package com.nali.small.entity.memo.client.box.mix;

import com.nali.da.IBothDaNe;
import com.nali.da.client.IClientDaO;
import com.nali.list.entity.ai.AIESit;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.box.hit.HitE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

import static com.nali.small.entity.EntityMath.getDistanceAABBToAABB;

@SideOnly(Side.CLIENT)
public abstract class MixBoxE<RC extends IClientDaO, R extends RenderO<RC>, SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, MR extends MixRenderE<RC, R, SD, BD, E, I, ?, C>, C extends ClientE<RC, R, SD, BD, E, I, ?, MR>>
{
    public C c;
    public List<HitE> sehit_list = new ArrayList();

    public MixBoxE(C c)
    {
        this.c = c;
        this.init(c);
    }

//    public List<AxisAlignedBB> get()
//    {
//        List<AxisAlignedBB> axisalignedbb_list = new ArrayList();
////        axisalignedbb_list.add(this.getHeadAxisAlignedBB());
//        return axisalignedbb_list;
//    }

//    public void init(C c)
//    {
////        this.c = c;
////        this.sehit_list.add(new HitOlePat(this.c));
//    }

//    public void loop(SkinningEntities skinningentities)
//    {
//        this.axisalignedbb_array = this.get(skinningentities);
//    }

//    public void end()
//    {
////        byte i = this.c.bytele.SIT();
//        byte b = AIESit.ID;
//        byte[] byte_array = new byte[18];
//        byte_array[0] = SetWorkByte.ID;
//        ByteWriter.set(byte_array, this.c.uuid, 1);
//        byte_array[17] = b;
////        ByteWriter.set(byte_array, i, 17);
//        NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
//    }

    public void checkAxisAlignedBB(Entity player_entity)
    {
        Vec3d player_vec3d = player_entity.getPositionEyes(1.0f);
        Vec3d look_vec3d = player_entity.getLookVec();

        Vec3d end_vec3d = player_vec3d.add(look_vec3d.scale(32.0D));

        double max = Double.MAX_VALUE;
        int index = -1;
        List<AxisAlignedBB> axisalignedbb_list = this.get();
        for (int i = 0; i < axisalignedbb_list.size(); ++i)
        {
            AxisAlignedBB axisalignedbb = axisalignedbb_list.get(i);
            if (this.sehit_list.get(i).should(player_entity, axisalignedbb) && axisalignedbb.calculateIntercept(player_vec3d, end_vec3d) != null)
            {
                double new_max = getDistanceAABBToAABB(player_entity.getEntityBoundingBox(), axisalignedbb);
                if (new_max < max)
                {
                    index = i;
                    max = new_max;
                }
            }
        }

        if (index != -1)
        {
            this.sehit_list.get(index).run(player_entity, axisalignedbb_list.get(index));
            return;
        }

        this.c.sendSAIE(new byte[1 + 16 + 1], AIESit.ID);
//        this.c.sendPacketUUID(AIESit.ID);
    }

    public abstract List<AxisAlignedBB> get();
    public abstract void init(C c);

//    public AxisAlignedBB getHeadAxisAlignedBB()
//    {
//        E e = this.c.i.getE();
//        double hw = e.width / 2.0F + 0.001F;
//        double y = e.posY + e.height / 1.125F;
//
//        return new AxisAlignedBB
//        (
//            e.posX - hw, y, e.posZ - hw,
//            e.posX + hw, e.posY + e.height + 0.001F, e.posZ + hw
//        );
//    }
}
