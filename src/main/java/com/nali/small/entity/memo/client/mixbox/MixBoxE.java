package com.nali.small.entity.memo.client.mixbox;

import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SetWorkByte;
import com.nali.network.NetworkRegistry;
import com.nali.render.ObjectRender;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.hits.HitE;
import com.nali.small.entity.memo.client.hits.HitSePat;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

import static com.nali.small.entity.EntityMath.getDistanceAABBToAABB;

@SideOnly(Side.CLIENT)
public class MixBoxE<R extends ObjectRender, E extends Entity, I extends IMixE<E>, C extends ClientE<R, E, I, ?>>
{
    public C c;
    public List<HitE> sehit_list = new ArrayList();

    public MixBoxE(C c)
    {
        this.c = c;
    }

    public List<AxisAlignedBB> get()
    {
        List<AxisAlignedBB> axisalignedbb_list = new ArrayList();
        axisalignedbb_list.add(this.getHeadAxisAlignedBB());
        return axisalignedbb_list;
    }

    public void init(C c)
    {
        this.c = c;
        this.sehit_list.add(new HitSePat(this.c));
    }

//    public void loop(SkinningEntities skinningentities)
//    {
//        this.axisalignedbb_array = this.get(skinningentities);
//    }

    public void end()
    {
        byte i = this.c.bytele.SIT();
        byte[] byte_array = new byte[21];
        byte_array[0] = SetWorkByte.ID;
        BytesWriter.set(byte_array, this.c.uuid, 1);
        BytesWriter.set(byte_array, i, 17);
        NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
    }

    public void checkAxisAlignedBB(Entity player_entity)
    {
        Vec3d player_vec3d = player_entity.getPositionEyes(1.0f);
        Vec3d look_vec3d = player_entity.getLookVec();

        Vec3d end_vec3d = player_vec3d.add(look_vec3d.scale(32.0D));

        double min = Double.MAX_VALUE;
        int index = -1;
        List<AxisAlignedBB> axisalignedbb_list = this.get();
        for (int i = 0; i < axisalignedbb_list.size(); ++i)
        {
            AxisAlignedBB axisalignedbb = axisalignedbb_list.get(i);
            if (this.sehit_list.get(i).should(player_entity, axisalignedbb_list.get(i)) && axisalignedbb.calculateIntercept(player_vec3d, end_vec3d) != null)
            {
                double new_min = getDistanceAABBToAABB(player_entity.getEntityBoundingBox(), axisalignedbb);
                if (new_min < min)
                {
                    index = i;
                    min = new_min;
                }
            }
        }

        if (index != -1)
        {
            this.sehit_list.get(index).run(player_entity, axisalignedbb_list.get(index));
            return;
        }

        this.end();
    }

    public AxisAlignedBB getHeadAxisAlignedBB()
    {
        E e = this.c.i.getE();
        double hw = e.width / 2.0F + 0.001F;
        double y = e.posY + e.height / 1.125F;

        return new AxisAlignedBB
        (
            e.posX - hw, y, e.posZ - hw,
            e.posX + hw, e.posY + e.height + 0.001F, e.posZ + hw
        );
    }
}
