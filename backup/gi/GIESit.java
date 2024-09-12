package com.nali.list.entity.gi;

import com.nali.data.IBothDaE;
import com.nali.data.client.IClientDaO;
import com.nali.list.entity.si.AIESit;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.gi.GI;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.messages.works.SitGUIFeatures;
import com.nali.sound.ISoundN;
import com.nali.system.opengl.memo.client.MemoGo;
import com.nali.system.opengl.memo.client.MemoSo;
import com.nali.system.opengl.store.StoreO;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.small.gui.MixGui.GUIFEATURESLOADER;

@SideOnly(Side.CLIENT)
public class GIESit<G extends MixGui, RG extends MemoGo, RS extends MemoSo, RC extends IClientDaO, RST extends StoreO<RG, RS>, R extends RenderO<RG, RS, RST, RC>, SD extends ISoundN, BD extends IBothDaE, E extends Entity, I extends IMixE<SD, BD, E>, MR extends MixRenderE<RG, RS, RC, RST, R, SD, BD, E, I, MB, C>, MB extends MixBoxE<RG, RS, RC, RST, R, SD, BD, E, I, MR, C>, C extends ClientE<RG, RS, RC, RST, R, SD, BD, E, I, MB, MR>> extends GI<G, RG, RS, RC, RST, R, SD, BD, E, I, MR, MB, C>
{
    public GIESit()
    {
        this.x = 86;
        this.y = 0;
        this.width = 14;
        this.height = 14;
    }

    @Override
    public void text(G g)
    {
//        int x = left + 48, y = top + 89, width = 16, height = 16;
//        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
//        {
        if (g.mouse_released == 0)
        {
//            g.sendPacketUUIDInt(cliententitiesmemory.workbytes.SIT());
            g.sendPacketUUIDInt(AIESit.ID);
        }

        if (!(GUIFEATURESLOADER instanceof SitGUIFeatures))
        {
            GUIFEATURESLOADER = new SitGUIFeatures(g);
        }
        g.state |= 2;
//        }
    }
}
