package com.nali.small.entity.memo.client.hits;

import com.nali.data.IBothDaSe;
import com.nali.data.client.ClientDaSn;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.DrinkMilk;
import com.nali.list.network.method.server.Eat;
import com.nali.network.NetworkRegistry;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.mixbox.MixBoxE;
import com.nali.system.bytes.BytesWriter;
import com.nali.system.opengl.memo.MemoGo;
import com.nali.system.opengl.memo.MemoSo;
import com.nali.system.opengl.store.StoreO;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HitOeEat<RG extends MemoGo, RS extends MemoSo, RC extends ClientDaSn, RST extends StoreO<RG, RS>, R extends RenderO<RG, RS, RST, RC>, BD extends IBothDaSe, E extends Entity, I extends IMixE<BD, E>, M extends MixBoxE<RG, RS, RC, RST, R, BD, E, I, C>, C extends ClientE<RG, RS, RC, RST, R, BD, E, I, M>> extends HitE<RG, RS, RC, RST, R, BD, E, I, M, C>
{
    public HitOeEat(C c)
    {
        super(c);
    }

    @Override
    public void run(Entity player_entity, AxisAlignedBB axisalignedbb)
    {
        Item item = ((EntityLivingBase)player_entity).getHeldItemMainhand().getItem();
        boolean milk_bucket = item == Items.MILK_BUCKET;
//        boolean eat = item instanceof ItemFood;

        if (item instanceof ItemFood || milk_bucket)
        {
            byte[] byte_array;
            if (milk_bucket)
            {
                byte_array = new byte[1 + 16];
                byte_array[0] = DrinkMilk.ID;
            }
            else
            {
                byte_array = new byte[1 + 16 + 4 + 4 + 4];
                byte_array[0] = Eat.ID;
                BytesWriter.set(byte_array, (float)(axisalignedbb.maxX + (axisalignedbb.minX - axisalignedbb.maxX) / 2.0D), 1 + 16);
                BytesWriter.set(byte_array, (float)(axisalignedbb.maxY + (axisalignedbb.minY - axisalignedbb.maxY) / 2.0D), 1 + 16 + 4);
                BytesWriter.set(byte_array, (float)(axisalignedbb.maxZ + (axisalignedbb.minZ - axisalignedbb.maxZ) / 2.0D), 1 + 16 + 4 + 4);
            }

            BytesWriter.set(byte_array, this.c.uuid, 1);
            NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
        }
    }

    @Override
    public boolean should(Entity player_entity, AxisAlignedBB axisalignedbb)
    {
        Item item = ((EntityLivingBase)player_entity).getHeldItemMainhand().getItem();
        return item instanceof ItemFood || item == Items.MILK_BUCKET;
    }
}
