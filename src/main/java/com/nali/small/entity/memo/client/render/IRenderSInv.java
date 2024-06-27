package com.nali.small.entity.memo.client.render;

import com.nali.data.IBothDaSe;
import com.nali.data.client.IClientDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.client.ClientSle;
import com.nali.small.entity.memo.client.mixbox.MixBoxSle;
import com.nali.sound.ISoundLe;
import com.nali.system.opengl.memo.MemoAnimation;
import com.nali.system.opengl.memo.MemoGs;
import com.nali.system.opengl.memo.MemoSs;
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
import static com.nali.system.opengl.memo.MemoCurrent.*;

@SideOnly(Side.CLIENT)
public interface IRenderSInv<RG extends MemoGs, RS extends MemoSs, RC extends IClientDaS, RST extends StoreS<RG, RS>, R extends RenderS<BD, RG, RS, RST, RC>, SD extends ISoundLe, BD extends IBothDaSe<SD>, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, M extends MixBoxSle<RG, RS, RC, RST, R, SD, BD, E, I, ?, C>, C extends ClientSle<RG, RS, RC, RST, R, SD, BD, E, I, M, ?>> extends IRender
{
    default void doRender(C c/*, R r, E e*/, RenderE<E> rendere, double ox, double oy, double oz, float partialTicks)
    {
        R r = c.r;
        E e = c.i.getE();
        this.renderLayer(c/*, r*/, rendere, ox, oy, oz, partialTicks);

        GL11.glPushMatrix();

        this.updateData(c/*, e*/, partialTicks);

        GL11.glTranslated(ox, oy, oz);

        this.renderHitBox(c, rendere);

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

    default void updateData(C c/*, E e*/, float partialTicks)
    {
        R r = c.r;
        E e = c.i.getE();
        c.body_rot = (float)Math.toRadians(interpolateRotation(e.prevRenderYawOffset, e.renderYawOffset, partialTicks));
        c.head_rot = (float)Math.toRadians(interpolateRotation(e.prevRotationYaw, e.rotationYaw, partialTicks));
        c.net_head_yaw = c.head_rot - c.body_rot;
        c.head_pitch = (float)Math.toRadians(e.prevRotationPitch + (e.rotationPitch - e.prevRotationPitch) * partialTicks);
//        r.timeline = partialTicks;

//        OpenGLAnimationMemory openglanimationmemory = r.dataloader.openglanimationmemory_list.get(((SkinningClientData)r.clientdata).AnimationID());
//        OpenGLAnimationMemory openglanimationmemory = (OpenGLAnimationMemory)r.dataloader.object_array[((SkinningClientData)r.clientdata).AnimationID()];
        MemoAnimation memoanimation = r.st.memoanimation_list.get(r.c.AnimationID());
        r.initSkinning(memoanimation);

        if (!c.fake)
        {
            this.multiplyAnimation(c);
        }

        r.setSkinning(memoanimation);
    }

    default void renderLayer(C c/*, R r*/, RenderE<E> rendere, double ox, double oy, double oz, float partialTicks)
    {
        R r = c.r;
        GL11.glPushMatrix();

        GL11.glTranslated(ox, oy, oz);
        GL11.glScalef(r.scale, r.scale, r.scale);
        GL11.glTranslated(-ox, -oy, -oz);
        c.itemlayerrender.x = (float)ox;
        c.itemlayerrender.y = (float)oy;
        c.itemlayerrender.z = (float)oz;
        c.itemlayerrender.layer(partialTicks);
        c.arrowlayerrender.layer(rendere, (float)ox, (float)oy, (float)oz, partialTicks);

        GL11.glPopMatrix();
    }

    default void renderHitBox(C c/*, E e*/, RenderE<E> rendere)
    {
        E e = c.i.getE();
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
            for (AxisAlignedBB axisalignedbb : c.m.get()/*cliententitiesmemory.mixboxentitiesmemory.axisalignedbb_array*/)
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

    void multiplyAnimation(C c);
}
