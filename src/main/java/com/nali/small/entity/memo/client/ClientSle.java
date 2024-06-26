package com.nali.small.entity.memo.client;

import com.nali.data.IBothDaSe;
import com.nali.data.client.ClientDaSn;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.client.mixbox.MixBoxSle;
import com.nali.small.entity.memo.client.render.layer.ArrowLayer;
import com.nali.small.entity.memo.client.render.layer.ItemLayer;
import com.nali.system.opengl.memo.MemoGs;
import com.nali.system.opengl.memo.MemoSs;
import com.nali.system.opengl.store.StoreS;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.nali.small.entity.EntityLeInv.MOUTH_ITEMSTACK_DATAPARAMETER;

@SideOnly(Side.CLIENT)
public abstract class ClientSle<RG extends MemoGs, RS extends MemoSs, RC extends ClientDaSn, RST extends StoreS<RG, RS>, R extends RenderS<BD, RG, RS, RST, RC>, BD extends IBothDaSe, E extends EntityLivingBase, I extends IMixLe<BD, E>, M extends MixBoxSle<RG, RS, RC, RST, R, BD, E, I, ?>> extends ClientLe<RG, RS, RC, RST, R, BD, E, I, M>
{
    public static Map<UUID, ClientSle> ENTITIES_MAP = new HashMap();
    public static Map<Integer, UUID> FAKE_ENTITIES_MAP = new HashMap();

    public ArrowLayer<RG, RS, RC, RST, R, BD, E, I, M, ?> arrowlayerrender;
    public ItemLayer<RG, RS, RC, RST, R, BD, E, I, M, ?> itemlayerrender;

    public ItemStack mouth_itemstack = ItemStack.EMPTY;

    public ClientSle(I i, M m/*, BothData bothdata, ByteActionLe workbytes*/)
    {
        super(i, m/*, bothdata, workbytes*/);
        this.arrowlayerrender = new ArrowLayer(this);
        this.itemlayerrender = new ItemLayer<>(this);
    }

    @Override
    public void updateClientObject()
    {
        super.updateClientObject();
        EntityDataManager entitydatamanager = this.i.getE().getDataManager();
        this.mouth_itemstack = entitydatamanager.get(MOUTH_ITEMSTACK_DATAPARAMETER);

        DataParameter<Integer>[] integer_dataparameter = this.i.getIntegerDataParameterArray();

        for (int x = 0; x < this.r.frame_int_array.length; ++x)
        {
            this.r.frame_int_array[x] = entitydatamanager.get(integer_dataparameter[x++]);
        }
    }

    public abstract int[] getIVIntArray();
}
