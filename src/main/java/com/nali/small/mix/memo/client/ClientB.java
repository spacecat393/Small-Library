package com.nali.small.mix.memo.client;

import com.nali.da.client.IClientDaO;
import com.nali.render.RenderO;
import com.nali.small.mix.IMixN;
import com.nali.small.mix.memo.IBothB;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ClientB<RC extends IClientDaO, R extends RenderO<RC>, I extends IMixN<?, E>, E extends Block, T extends TileEntity> extends ClientN<RC, R, I, E> implements IBothB<T, E>
{
    public ClientB(R r, I i)
    {
        super(r, i);
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
