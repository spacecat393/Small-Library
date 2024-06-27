package com.nali.small.entity.memo.client;

import com.nali.data.IBothDaSe;
import com.nali.data.client.IClientDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.client.mixbox.MixBoxSle;
import com.nali.small.entity.memo.client.render.IRender;
import com.nali.small.entity.memo.client.render.RenderE;
import com.nali.small.entity.memo.client.render.layer.ArrowLayer;
import com.nali.small.entity.memo.client.render.layer.ItemLayer;
import com.nali.sound.ISoundLe;
import com.nali.system.opengl.memo.MemoGs;
import com.nali.system.opengl.memo.MemoSs;
import com.nali.system.opengl.store.StoreS;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.nali.small.entity.EntityLeInv.MOUTH_ITEMSTACK_DATAPARAMETER;

@SideOnly(Side.CLIENT)
public abstract class ClientSle<RG extends MemoGs, RS extends MemoSs, RC extends IClientDaS, RST extends StoreS<RG, RS>, R extends RenderS<BD, RG, RS, RST, RC>, SD extends ISoundLe, BD extends IBothDaSe<SD>, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, M extends MixBoxSle<RG, RS, RC, RST, R, SD, BD, E, I, IR, ?>, IR extends IRender> extends ClientLe<RG, RS, RC, RST, R, SD, BD, E, I, M, IR> implements IClientS<RG, RS, RC, RST, R, SD, BD, E, I>
{
    public static Map<UUID, ClientSle> ENTITIES_MAP = new HashMap();
    public static Map<Integer, UUID> FAKE_ENTITIES_MAP = new HashMap();

    public ArrowLayer<RG, RS, RC, RST, R, SD, BD, E, I, IR, M, ?> arrowlayerrender;
    public ItemLayer<RG, RS, RC, RST, R, SD, BD, E, I, IR, M, ?> itemlayerrender;

    public ItemStack mouth_itemstack = ItemStack.EMPTY;

    public ClientSle(I i, M m/*, BothData bothdata, ByteActionLe workbytes*/)
    {
        super(i, m/*, bothdata, workbytes*/);
        this.arrowlayerrender = new ArrowLayer(this);
        this.itemlayerrender = new ItemLayer(this);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        this.readEntityFromNBT(this.r, this.i);
    }

    @Override
    public void updateClientObject()
    {
        super.updateClientObject();
        this.updateClientObject(this.r, this.i);

        EntityDataManager entitydatamanager = this.i.getE().getDataManager();
        this.mouth_itemstack = entitydatamanager.get(MOUTH_ITEMSTACK_DATAPARAMETER);
    }

    @Override
    public void doRender(RenderE<E> render, double ox, double oy, double oz, float partialTicks)
    {
        this.doRender(this, this.r, this.i.getE(), render, ox, oy, oz, partialTicks);
    }

    public abstract int[] getIVIntArray();
}
