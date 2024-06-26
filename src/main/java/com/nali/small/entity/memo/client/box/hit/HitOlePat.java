package com.nali.small.entity.memo.client.box.hit;

import com.nali.data.IBothDaNe;
import com.nali.data.client.IClientDaO;
import com.nali.list.entity.ai.AIEPat;
import com.nali.list.network.message.ServerMessage;
import com.nali.network.NetworkRegistry;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.client.ClientLe;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import com.nali.sound.ISoundLe;
import com.nali.system.bytes.ByteWriter;
import com.nali.system.opengl.memo.client.MemoGo;
import com.nali.system.opengl.memo.client.MemoSo;
import com.nali.system.opengl.memo.client.store.StoreO;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HitOlePat<RG extends MemoGo, RS extends MemoSo, RC extends IClientDaO, RST extends StoreO<RG, RS>, R extends RenderO<RG, RS, RST, RC>, SD extends ISoundLe, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, MR extends MixRenderE<RG, RS, RC, RST, R, SD, BD, E, I, MB, C>, MB extends MixBoxE<RG, RS, RC, RST, R, SD, BD, E, I, MR, C>, C extends ClientLe<RG, RS, RC, RST, R, SD, BD, E, I, MB, MR>> extends HitE<RG, RS, RC, RST, R, SD, BD, E, I, MR, MB, C>
{
    public byte pat_time;

    public HitOlePat(C c)
    {
        super(c);
        this.pat_time = (byte)this.c.i.getE().world.rand.nextInt(16);
    }

    @Override
    public void run(Entity player_entity, AxisAlignedBB axisalignedbb)
    {
        E e = this.c.i.getE();
//                    ItemStack itemstack = entityplayer.getHeldItem(enumhand);
////                    this.getEntityData().set(this.getByteEntityDataAccessorArray()[2], (byte)5);
////
////                    if (itemstack.getItem() == ItemsRegistry.HAIRBRUSH_ITEM_REGISTRYOBJECT.get())
////                    {
        if (--this.pat_time <= 0)
        {
            this.pat_time = (byte)e.world.rand.nextInt(16);

//            this.c.sendSAIE(new byte[1 + 16 + 1], AIEPat.ID);
            byte[] byte_array = new byte[1 + 16 + 1 + 4];
            byte_array[0] = AIEPat.ID;
            ByteWriter.set(byte_array, this.c.uuid, 1);
            ByteWriter.set(byte_array, (float)e.getEntityBoundingBox().maxY, 1 + 16);
            NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));

//            this.c.sound.play(this.c.i.getSD().PAT());

//            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.ON_PAT()] = 1;
//            this.skinningentities.world.spawnEntity(new EntityXPOrb(this.skinningentities.world, this.skinningentities.posX, this.skinningentities.posY, this.skinningentities.posZ, 10));
//
//                            if (!entityplayer.isCreative())
//                            {
//                                itemstack.damageItem(1, entityplayer);
//                            }
        }
////                    }
    }

    @Override
    public boolean should(Entity player_entity, AxisAlignedBB axisalignedbb)
    {
        return ((EntityLivingBase)player_entity).getHeldItemMainhand().isEmpty();
    }
}
