package com.nali.small.entity.memo.client.box.mix;

import com.nali.da.IBothDaNe;
import com.nali.da.client.IClientDaO;
import com.nali.list.entity.ai.AIEPlayWithRSe;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import com.nali.system.opengl.memo.client.MemoGo;
import com.nali.system.opengl.memo.client.MemoSo;
import com.nali.system.opengl.memo.client.store.StoreO;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
public class MixBoxSeRSe<RG extends MemoGo, RS extends MemoSo, RC extends IClientDaO, RST extends StoreO<RG, RS>, R extends RenderO<RG, RS, RST, RC>, SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, MR extends MixRenderE<RG, RS, RC, RST, R, SD, BD, E, I, ?, C>, C extends ClientE<RG, RS, RC, RST, R, SD, BD, E, I, ?, MR>> extends MixBoxE<RG, RS, RC, RST, R, SD, BD, E, I, MR, C>
{
    public MixBoxSeRSe(C c)
    {
        super(c);
    }

    @Override
    public void checkAxisAlignedBB(Entity player_entity)
    {
        this.c.sendSAIE(new byte[1 + 16 + 1], AIEPlayWithRSe.ID);
    }

    @Override
    public List<AxisAlignedBB> get()
    {
        return null;
    }

    @Override
    public void init(C c)
    {

    }
}
