package com.nali.small.mix.memo.client;

import com.nali.da.client.IClientDaO;
import com.nali.draw.DrawScreen;
import com.nali.render.RenderO;
import com.nali.small.mix.IMixN;
import com.nali.small.mix.memo.IBothN;
import com.nali.system.opengl.memo.client.MemoGo;
import com.nali.system.opengl.memo.client.MemoSo;
import com.nali.system.opengl.memo.client.store.StoreO;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientN<RG extends MemoGo, RS extends MemoSo, RC extends IClientDaO, RST extends StoreO<RG, RS>, R extends RenderO<RG, RS, RST, RC>, D extends DrawScreen<RG, RS, RST, RC, R>, I extends IMixN<?, E>, E> implements IBothN
{
    public R r;
    public D d;
    public I i;

    public ClientN(R r, D d, I i)
    {
        this.r = r;
        this.d = d;
        this.i = i;
    }

    @Override
    public void render()
    {
        this.d.render();
    }

    @Override
    public void updateLight(World world, BlockPos blockpos)
    {
        this.r.lig_b = world.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, blockpos) / 16.0F;
        this.r.lig_s = world.getLightFromNeighborsFor(EnumSkyBlock.SKY, blockpos) / 16.0F;

        if (this.r.lig_b < 0.1875F)
        {
            this.r.lig_b = 0.1875F;
        }
    }

    @Override
    public void light()
    {
        this.r.lig_b = -1.0F;
    }
}
