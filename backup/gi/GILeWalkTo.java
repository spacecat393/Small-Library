package com.nali.list.entity.gi;

import com.mojang.authlib.GameProfile;
import com.nali.data.IBothDaE;
import com.nali.data.client.IClientDaO;
import com.nali.list.entity.ai.AIESit;
import com.nali.list.entity.ai.AILeWalkTo;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.gi.GI;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.messages.works.WalkToGUIFeatures;
import com.nali.sound.ISoundLe;
import com.nali.sound.ISoundN;
import com.nali.system.opengl.memo.MemoGo;
import com.nali.system.opengl.memo.MemoSo;
import com.nali.system.opengl.store.StoreO;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

import static com.nali.small.gui.MixGui.GUIFEATURESLOADER;

@SideOnly(Side.CLIENT)
public class GILeWalkTo<G extends MixGui, RG extends MemoGo, RS extends MemoSo, RC extends IClientDaO, RST extends StoreO<RG, RS>, R extends RenderO<RG, RS, RST, RC>, SD extends ISoundN, BD extends IBothDaE, E extends Entity, I extends IMixE<SD, BD, E>, MR extends MixRenderE<RG, RS, RC, RST, R, SD, BD, E, I, MB, C>, MB extends MixBoxE<RG, RS, RC, RST, R, SD, BD, E, I, MR, C>, C extends ClientE<RG, RS, RC, RST, R, SD, BD, E, I, MB, MR>> extends GI<G, RG, RS, RC, RST, R, SD, BD, E, I, MR, MB, C>
{
    public GILeWalkTo()
    {
        this.x = ;
        this.y = ;
        this.width = ;
        this.height = ;
    }

    @Override
    public void text(G g)
    {
//        x = left + 66 + 18;// y = top + 107;// width = 16; height = 16;
//        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
//        {
        if (g.mouse_released == 0)
        {
//            g.sendPacketUUIDInt(cliententitiesmemory.workbytes.WALK_TO());
            g.sendPacketUUIDInt(AILeWalkTo.ID);
        }

        if (!(GUIFEATURESLOADER instanceof WalkToGUIFeatures))
        {
            GUIFEATURESLOADER = new WalkToGUIFeatures(g);
        }
        g.state |= 2;
//        }
    }
}