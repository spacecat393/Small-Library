package com.nali.small.entity.memo.client;

import com.nali.render.SkinningRender;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.client.mixbox.MixBoxSe;
import com.nali.small.entity.memo.client.render.layer.ArrowLayer;
import com.nali.small.entity.memo.client.render.layer.ItemLayer;
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
public abstract class ClientSle<R extends SkinningRender, E extends EntityLivingBase, I extends IMixLe<E>, M extends MixBoxSe<R, E, I, ?>> extends ClientLe<R, E, I, M>
{
    public static Map<UUID, ClientSle> ENTITIES_MAP = new HashMap();
    public static Map<Integer, UUID> FAKE_ENTITIES_MAP = new HashMap();

    public ArrowLayer arrowlayerrender;
    public ItemLayer itemlayerrender;

    public ItemStack mouth_itemstack = ItemStack.EMPTY;

    public ClientSle(I i, M m/*, BothData bothdata, ByteActionLe workbytes*/)
    {
        super(i, m/*, bothdata, workbytes*/);
        this.arrowlayerrender = new ArrowLayer(this);
        this.itemlayerrender = new ItemLayer(this);
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
