package com.nali.small.entity.memo.client.render.mix;

import com.nali.data.IBothDaSe;
import com.nali.data.client.IClientDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.client.ClientSle;
import com.nali.small.entity.memo.client.box.mix.MixBoxSle;
import com.nali.small.entity.memo.client.render.RenderE;
import com.nali.small.entity.memo.client.render.layer.ArrowLayer;
import com.nali.small.entity.memo.client.render.layer.ItemLayer;
import com.nali.sound.ISoundLe;
import com.nali.system.opengl.memo.client.MemoAnimation;
import com.nali.system.opengl.memo.client.MemoGs;
import com.nali.system.opengl.memo.client.MemoSs;
import com.nali.system.opengl.store.StoreS;
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
import static com.nali.small.entity.memo.client.render.RenderSeMath.interpolateRotation;
import static com.nali.system.opengl.memo.client.MemoCurrent.*;

@SideOnly(Side.CLIENT)
public abstract class MixRenderSleInv<RG extends MemoGs, RS extends MemoSs, RC extends IClientDaS, RST extends StoreS<RG, RS>, R extends RenderS<SD, BD, RG, RS, RST, RC>, SD extends ISoundLe, BD extends IBothDaSe<SD>, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, MB extends MixBoxSle<RG, RS, RC, RST, R, SD, BD, E, I, ?, C>, C extends ClientSle<RG, RS, RC, RST, R, SD, BD, E, I, MB, ?>> extends MixRenderE<RG, RS, RC, RST, R, SD, BD, E, I, MB, C>
{
    public ArrowLayer<RG, RS, RC, RST, R, SD, BD, E, I, ?, MB, C> arrowlayerrender;
    public ItemLayer<RG, RS, RC, RST, R, SD, BD, E, I, ?, MB, C> itemlayerrender;

    public float
    body_rot,
    head_rot,
    net_head_yaw,
    head_pitch;

    public MixRenderSleInv(C c)
    {
        super(c);
        this.arrowlayerrender = new ArrowLayer(c);
        this.itemlayerrender = new ItemLayer(c);
    }

    public void doRender(RenderE<E> rendere, double ox, double oy, double oz, float partialTicks)
    {
        R r = this.c.r;
        E e = this.c.i.getE();
        this.renderLayer(rendere, ox, oy, oz, partialTicks);

        GL11.glPushMatrix();

        this.updateData(partialTicks);

        GL11.glTranslated(ox, oy, oz);

        this.renderHitBox(rendere);

        GL11.glScalef(r.scale, r.scale, r.scale);
        rendere.mShadowOpaque(r.scale);
        rendere.mShadowSize(r.scale);

        boolean invisible = e.isInvisible() || e.isInvisibleToPlayer(Minecraft.getMinecraft().player);
        if (invisible)
        {
            OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
            GL11.glGetFloat(GL11.GL_CURRENT_COLOR, OPENGL_FIXED_PIPE_FLOATBUFFER);
            GL_CURRENT_COLOR[0] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(0);
            GL_CURRENT_COLOR[1] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(1);
            GL_CURRENT_COLOR[2] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(2);
            GL_CURRENT_COLOR[3] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(3);
            GL11.glColor4f(GL_CURRENT_COLOR[0], GL_CURRENT_COLOR[1], GL_CURRENT_COLOR[2], 0.25F);
        }
        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        r.updateLightCoord(e.world, e.getPosition());
//        r.draw(/*ox, oy, oz*/);
        r.drawLater(/*ox, oy, oz*/);
        if (invisible)
        {
            GL11.glColor4f(GL_CURRENT_COLOR[0], GL_CURRENT_COLOR[1], GL_CURRENT_COLOR[2], GL_CURRENT_COLOR[3]);
        }

        GL11.glPopMatrix();

//        this.renderLayer(t, ox, oy, oz, partialTicks);

    }

    public void updateData(float partialTicks)
    {
        R r = this.c.r;
        E e = this.c.i.getE();
        this.body_rot = (float)Math.toRadians(interpolateRotation(e.prevRenderYawOffset, e.renderYawOffset, partialTicks));
        this.head_rot = (float)Math.toRadians(interpolateRotation(e.prevRotationYaw, e.rotationYaw, partialTicks));
        this.net_head_yaw = this.head_rot - this.body_rot;
        this.head_pitch = (float)Math.toRadians(e.prevRotationPitch + (e.rotationPitch - e.prevRotationPitch) * partialTicks);
//        r.timeline = partialTicks;

//        OpenGLAnimationMemory openglanimationmemory = r.dataloader.openglanimationmemory_list.get(((SkinningClientData)r.clientdata).AnimationID());
//        OpenGLAnimationMemory openglanimationmemory = (OpenGLAnimationMemory)r.dataloader.object_array[((SkinningClientData)r.clientdata).AnimationID()];
        MemoAnimation memoanimation = r.rst.memoanimation_list.get(r.rc.AnimationID());
        r.initSkinning(memoanimation);

        if (!this.c.fake)
        {
            this.multiplyAnimation();
        }

        r.setSkinning(memoanimation);
    }

    public void renderLayer(RenderE<E> rendere, double ox, double oy, double oz, float partialTicks)
    {
        R r = this.c.r;
        GL11.glPushMatrix();

        GL11.glTranslated(ox, oy, oz);
        GL11.glScalef(r.scale, r.scale, r.scale);
        GL11.glTranslated(-ox, -oy, -oz);
        this.itemlayerrender.x = (float)ox;
        this.itemlayerrender.y = (float)oy;
        this.itemlayerrender.z = (float)oz;
        this.itemlayerrender.layer(partialTicks);
        this.arrowlayerrender.layer(rendere, (float)ox, (float)oy, (float)oz, partialTicks);

        GL11.glPopMatrix();
    }

    public void renderHitBox(RenderE<E> rendere)
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

    public abstract void multiplyAnimation();
}
