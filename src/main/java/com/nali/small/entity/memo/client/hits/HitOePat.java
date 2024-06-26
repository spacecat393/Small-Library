package com.nali.small.entity.memo.client.hits;

import com.nali.data.IBothDaSe;
import com.nali.data.client.ClientDaSn;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.Pat;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HitOePat<RG extends MemoGo, RS extends MemoSo, RC extends ClientDaSn, RST extends StoreO<RG, RS>, R extends RenderO<RG, RS, RST, RC>, BD extends IBothDaSe, E extends Entity, I extends IMixE<BD, E>, M extends MixBoxE<RG, RS, RC, RST, R, BD, E, I, C>, C extends ClientE<RG, RS, RC, RST, R, BD, E, I, M>> extends HitE<RG, RS, RC, RST, R, BD, E, I, M, C>
{
    public byte pat_time;

    public HitOePat(C c)
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

            byte[] byte_array = new byte[1 + 16 + 4];
            byte_array[0] = Pat.ID;
            BytesWriter.set(byte_array, this.c.uuid, 1);
            BytesWriter.set(byte_array, (float)e.getEntityBoundingBox().maxY, 1 + 16);
            NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));

            this.c.soundrender.play(this.c.sounds.PAT());

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
