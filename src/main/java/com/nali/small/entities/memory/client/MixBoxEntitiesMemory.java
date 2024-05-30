package com.nali.small.entities.memory.client;

import com.nali.list.messages.ServerMessage;
import com.nali.list.netmethods.servermessage.SetWorkByte;
import com.nali.networks.NetworksRegistry;
import com.nali.render.SkinningRender;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.hits.SkinningEntitiesEat;
import com.nali.small.entities.skinning.hits.SkinningEntitiesHits;
import com.nali.small.entities.skinning.hits.SkinningEntitiesPat;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.small.entities.EntitiesMath.getDistanceAABBToAABB;

@SideOnly(Side.CLIENT)
public class MixBoxEntitiesMemory
{
//    public AxisAlignedBB[] axisalignedbb_array;
    public SkinningEntitiesHits[] skinningentitieshits_array;

    public MixBoxEntitiesMemory(SkinningEntities skinningentities)
    {
        this.init(skinningentities);
    }

    public AxisAlignedBB[] get(SkinningEntities skinningentities)
    {
        AxisAlignedBB[] axisalignedbb = new AxisAlignedBB[2];
        axisalignedbb[0] = this.getHeadAxisAlignedBB(skinningentities);
        axisalignedbb[1] = this.getMouthAxisAlignedBB(skinningentities);
        return axisalignedbb;
    }

    public void init(SkinningEntities skinningentities)
    {
        this.skinningentitieshits_array = new SkinningEntitiesHits[2];
        this.skinningentitieshits_array[0] = new SkinningEntitiesPat(skinningentities);
        this.skinningentitieshits_array[1] = new SkinningEntitiesEat(skinningentities);
    }

//    public void loop(SkinningEntities skinningentities)
//    {
//        this.axisalignedbb_array = this.get(skinningentities);
//    }

    public void end(SkinningEntities skinningentities)
    {
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;
        byte i = cliententitiesmemory.workbytes.SIT();
        byte[] byte_array = new byte[21];
        byte_array[0] = SetWorkByte.ID;
        BytesWriter.set(byte_array, cliententitiesmemory.uuid, 1);
        BytesWriter.set(byte_array, i, 17);
        NetworksRegistry.I.sendToServer(new ServerMessage(byte_array));
    }

    public void checkAxisAlignedBB(Entity player_entity, SkinningEntities skinningentities)
    {
        Vec3d player_vec3d = player_entity.getPositionEyes(1.0f);
        Vec3d look_vec3d = player_entity.getLookVec();

        Vec3d end_vec3d = player_vec3d.add(look_vec3d.scale(32.0D));

        double min = Double.MAX_VALUE;
        int index = -1;
        AxisAlignedBB[] axisalignedbb_array = this.get(skinningentities);
        for (int i = 0; i < axisalignedbb_array.length; ++i)
        {
            AxisAlignedBB axisalignedbb = axisalignedbb_array[i];
            if (this.skinningentitieshits_array[i].should(player_entity, axisalignedbb_array[i]) && axisalignedbb.calculateIntercept(player_vec3d, end_vec3d) != null)
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
            this.skinningentitieshits_array[index].run(player_entity, axisalignedbb_array[index]);
            return;
        }

        this.end(skinningentities);
    }

    public AxisAlignedBB getHeadAxisAlignedBB(SkinningEntities skinningentities)
    {
        double hw = skinningentities.width / 2.0F + 0.001F;
        double y = skinningentities.posY + skinningentities.height / 1.125F;

        return new AxisAlignedBB
        (
            skinningentities.posX - hw, y, skinningentities.posZ - hw,
            skinningentities.posX + hw, skinningentities.posY + skinningentities.height + 0.001F, skinningentities.posZ + hw
        );
    }

    public AxisAlignedBB getMouthAxisAlignedBB(SkinningEntities skinningentities)
    {
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;
        SkinningRender skinningrender = (SkinningRender)cliententitiesmemory.objectrender;
        int[] iv_int_array = cliententitiesmemory.getIVIntArray();

//        float[] pos_vec4 = skinningrender.getScale3DSkinning((OpenGLSkinningMemory)skinningrender.dataloader.openglobjectmemory_array[iv_int_array[10]], (float)this.posX, (float)this.posY, (float)this.posZ, 0, 0, 0, iv_int_array[10], iv_int_array[11]);
//        float[] pos_vec4 = skinningrender.getScale3DSkinning((OpenGLSkinningMemory)skinningrender.dataloader.object_array[iv_int_array[10]], (float)this.posX, (float)this.posY, (float)this.posZ, 0, 0, 0, iv_int_array[10], iv_int_array[11]);
        float[] pos_vec4 = skinningrender.getScale3DSkinning((float)skinningentities.posX, (float)skinningentities.posY, (float)skinningentities.posZ, 0, 0, 0, iv_int_array[10], iv_int_array[11]);

        double x = pos_vec4[0] / pos_vec4[3];
        double y = pos_vec4[1] / pos_vec4[3];
        double z = pos_vec4[2] / pos_vec4[3];

        double hw = skinningentities.width / 1.5F;
        double hh = skinningentities.height / 4.0F;

        return new AxisAlignedBB
        (
            x - hw, y - hh, z - hw,
            x + hw, y + hh, z + hw
        );
    }
}
