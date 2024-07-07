package com.nali.small.mix.memo.client;

import com.nali.da.client.IClientDaO;
import com.nali.draw.DrawScreen;
import com.nali.render.RenderO;
import com.nali.small.mix.IMixN;
import com.nali.small.mix.memo.IBothB;
import com.nali.system.opengl.memo.client.MemoGo;
import com.nali.system.opengl.memo.client.MemoSo;
import com.nali.system.opengl.memo.client.store.StoreO;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ClientB<RG extends MemoGo, RS extends MemoSo, RC extends IClientDaO, RST extends StoreO<RG, RS>, R extends RenderO<RG, RS, RST, RC>, D extends DrawScreen<RG, RS, RST, RC, R>, I extends IMixN<?, E>, E extends Block, T extends TileEntity> extends ClientN<RG, RS, RC, RST, R, D, I, E> implements IBothB<T, E>
{
    public ClientB(R r, D d, I i)
    {
        super(r, d, i);
    }

    @Override
    public void render(T t, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        GL11.glPushMatrix();
        this.translate(x, y, z);
        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        this.scale();
        this.r.drawLater();
        GL11.glPopMatrix();
    }

    public void translate(double x, double y, double z)
    {
        GL11.glTranslated(x + 0.5D, y + 0.001D, z + 0.5D);
    }

    public void scale()
    {
        GL11.glScalef(0.499F, 0.499F, 0.499F);
    }
}
