package com.nali.small.entity.memo.client.box.hit;

import com.nali.da.IBothDaNe;
import com.nali.da.client.IClientDaO;
import com.nali.list.entity.ai.AILeEat;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SAIE;
import com.nali.network.NetworkRegistry;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import com.nali.system.bytes.ByteWriter;
import com.nali.system.opengl.memo.client.MemoGo;
import com.nali.system.opengl.memo.client.MemoSo;
import com.nali.system.opengl.memo.client.store.StoreO;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HitOeEat<RG extends MemoGo, RS extends MemoSo, RC extends IClientDaO, RST extends StoreO<RG, RS>, R extends RenderO<RG, RS, RST, RC>, SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, MR extends MixRenderE<RG, RS, RC, RST, R, SD, BD, E, I, MB, C>, MB extends MixBoxE<RG, RS, RC, RST, R, SD, BD, E, I, MR, C>, C extends ClientE<RG, RS, RC, RST, R, SD, BD, E, I, MB, MR>> extends HitE<RG, RS, RC, RST, R, SD, BD, E, I, MR, MB, C>
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
            byte[] byte_array = new byte[1 + 16 + 1];
//            this.c.sendSAIE(new byte[1 + 16 + 1], AILeEat.ID);
            byte_array[0] = SAIE.ID;
            if (milk_bucket)
            {
                byte_array = new byte[1 + 16 + 1/* + 1*/];
//                byte_array[0] = SDrinkMilk.ID;
//                byte_array[0] = SAIE.ID;
//                byte_array[18] = ;
            }
            else
            {
                byte_array = new byte[1 + 16 + 1/* + 1*/ + 4 + 4 + 4];
//                byte_array[0] = SEat.ID;
//                byte_array[18] = ;
                ByteWriter.set(byte_array, (float)(axisalignedbb.maxX + (axisalignedbb.minX - axisalignedbb.maxX) / 2.0D), 1 + 16 + 1);
                ByteWriter.set(byte_array, (float)(axisalignedbb.maxY + (axisalignedbb.minY - axisalignedbb.maxY) / 2.0D), 1 + 16 + 1 + 4);
                ByteWriter.set(byte_array, (float)(axisalignedbb.maxZ + (axisalignedbb.minZ - axisalignedbb.maxZ) / 2.0D), 1 + 16 + 1 + 4 + 4);
            }
            ByteWriter.set(byte_array, this.c.uuid, 1);
            byte_array[17] = AILeEat.ID;
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
