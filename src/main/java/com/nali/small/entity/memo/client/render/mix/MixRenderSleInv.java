package com.nali.small.entity.memo.client.render.mix;

import com.nali.data.IBothDaNe;
import com.nali.data.IBothDaSn;
import com.nali.data.client.IClientDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.client.ClientSle;
import com.nali.small.entity.memo.client.box.mix.MixBoxSle;
import com.nali.small.entity.memo.client.render.FRenderE;
import com.nali.small.entity.memo.client.render.layer.LayerSleArrow;
import com.nali.small.entity.memo.client.render.layer.LayerSleItem;
import com.nali.sound.ISoundLe;
import com.nali.system.opengl.memo.client.MemoGs;
import com.nali.system.opengl.memo.client.MemoSs;
import com.nali.system.opengl.memo.client.store.StoreS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL20;

import java.awt.*;

import static com.nali.key.KeyHelper.generateRainbowColor;
import static com.nali.small.entity.memo.client.render.FRenderSeMath.interpolateRotation;
import static com.nali.system.opengl.memo.client.MemoCurrent.*;

@SideOnly(Side.CLIENT)
public abstract class MixRenderSleInv<RG extends MemoGs, RS extends MemoSs, RC extends IClientDaS, RST extends StoreS<RG, RS>, R extends RenderS<BD, RG, RS, RST, RC>, SD extends ISoundLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, MB extends MixBoxSle<RG, RS, RC, RST, R, SD, BD, E, I, ?, C>, C extends ClientSle<RG, RS, RC, RST, R, SD, BD, E, I, MB, ?>> extends MixRenderSe<RG, RS, RC, RST, R, SD, BD, E, I, MB, C>
{
    public LayerSleArrow<RG, RS, RC, RST, R, SD, BD, E, I, ?, MB, C> layerslearrow;
    public LayerSleItem<RG, RS, RC, RST, R, SD, BD, E, I, ?, MB, C> layersleitem;

    public float
    body_rot,
    net_head_yaw;

    public MixRenderSleInv(C c)
    {
        super(c);
        this.layerslearrow = new LayerSleArrow(c);
        this.layersleitem = new LayerSleItem(c);
    }

    @Override
    public void updateData(float partialTicks)
    {
        E e = this.c.i.getE();
        this.body_rot = (float)Math.toRadians(interpolateRotation(e.prevRenderYawOffset, e.renderYawOffset, partialTicks));
        super.updateData(partialTicks);
        this.net_head_yaw = this.head_rot - this.body_rot;
    }

    @Override
    public void doRender(FRenderE<E> rendere, double ox, double oy, double oz, float partialTicks)
    {
        this.renderLayer(rendere, ox, oy, oz, partialTicks);
        super.doRender(rendere, ox, oy, oz, partialTicks);
    }

    public void renderLayer(FRenderE<E> rendere, double ox, double oy, double oz, float partialTicks)
    {
        R r = this.c.r;
        GL11.glPushMatrix();

        GL11.glTranslated(ox, oy, oz);
        GL11.glScalef(r.scale, r.scale, r.scale);
        GL11.glTranslated(-ox, -oy, -oz);
        this.layersleitem.x = (float)ox;
        this.layersleitem.y = (float)oy;
        this.layersleitem.z = (float)oz;
        this.layersleitem.layer(partialTicks);
        this.layerslearrow.layer(rendere, (float)ox, (float)oy, (float)oz, partialTicks);

        GL11.glPopMatrix();
    }

    @Override
    public void renderHitBox(FRenderE<E> rendere)
    {
        E e = this.c.i.getE();
        if (rendere.getRenderManager().isDebugBoundingBox() && !e.isInvisible() && !Minecraft.getMinecraft().isReducedDebug())
        {
            GL11.glPushMatrix();

            GL_TEXTURE_2D = GL11.glIsEnabled(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_TEXTURE_2D);

            GL_BLEND = GL11.glIsEnabled(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_BLEND);

            GL11.glGetInteger(GL20.GL_BLEND_EQUATION_RGB, OPENGL_INTBUFFER);
            GL_BLEND_EQUATION_RGB = OPENGL_INTBUFFER.get(0);
            GL11.glGetInteger(GL20.GL_BLEND_EQUATION_ALPHA, OPENGL_INTBUFFER);
            GL_BLEND_EQUATION_ALPHA = OPENGL_INTBUFFER.get(0);
            GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);

            GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB, OPENGL_INTBUFFER);
            GL_BLEND_SRC_RGB = OPENGL_INTBUFFER.get(0);
            GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA, OPENGL_INTBUFFER);
            GL_BLEND_SRC_ALPHA = OPENGL_INTBUFFER.get(0);
            GL11.glGetInteger(GL14.GL_BLEND_DST_RGB, OPENGL_INTBUFFER);
            GL_BLEND_DST_RGB = OPENGL_INTBUFFER.get(0);
            GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA, OPENGL_INTBUFFER);
            GL_BLEND_DST_ALPHA = OPENGL_INTBUFFER.get(0);
            GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

            GL11.glTranslated(-e.posX, -e.posY, -e.posZ);
            Color color = generateRainbowColor();
            float r = color.getRed() / 255.0F, g = color.getGreen() / 255.0F, b = color.getBlue() / 255.0F;

            OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
            GL11.glGetFloat(GL11.GL_LINE_WIDTH, OPENGL_FIXED_PIPE_FLOATBUFFER);
            GL_LINE_WIDTH = OPENGL_FIXED_PIPE_FLOATBUFFER.get(0);
            GL11.glLineWidth(5.0F);

//            AxisAlignedBB[] axisalignedbb_array = new AxisAlignedBB[]
//            {
//                t.getHeadAxisAlignedBB(),
//                t.getMouthAxisAlignedBB()
//            };
//            for (AxisAlignedBB axisalignedbb : axisalignedbb_array)
//            if (cliententitiesmemory.mixboxentitiesmemory.axisalignedbb_array != null)
//            {
            for (AxisAlignedBB axisalignedbb : this.c.mb.get()/*cliententitiesmemory.mixboxentitiesmemory.axisalignedbb_array*/)
            {
                RenderGlobal.drawSelectionBoundingBox(axisalignedbb, r, g, b, 1.0F);
            }
//            }

            if (GL_BLEND)
            {
                GL11.glEnable(GL11.GL_BLEND);
            }
            else
            {
                GL11.glDisable(GL11.GL_BLEND);
            }

            GL20.glBlendEquationSeparate(GL_BLEND_EQUATION_RGB, GL_BLEND_EQUATION_ALPHA);
            GL14.glBlendFuncSeparate(GL_BLEND_SRC_RGB, GL_BLEND_DST_RGB, GL_BLEND_SRC_ALPHA, GL_BLEND_DST_ALPHA);

            GL11.glLineWidth(GL_LINE_WIDTH);

            if (GL_TEXTURE_2D)
            {
                GL11.glEnable(GL11.GL_TEXTURE_2D);
            }
            else
            {
                GL11.glDisable(GL11.GL_TEXTURE_2D);
            }

            GL11.glPopMatrix();
        }
    }
}
