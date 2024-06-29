package com.nali.small.entity.memo.client;

import com.nali.data.IBothDaSe;
import com.nali.data.client.IClientDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.client.box.mix.MixBoxSle;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
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

import static com.nali.small.entity.EntityLeInv.MOUTH_ITEMSTACK_DATAPARAMETER;

@SideOnly(Side.CLIENT)
public abstract class ClientSle<RG extends MemoGs, RS extends MemoSs, RC extends IClientDaS, RST extends StoreS<RG, RS>, R extends RenderS<SD, BD, RG, RS, RST, RC>, SD extends ISoundLe, BD extends IBothDaSe<SD>, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, MB extends MixBoxSle<RG, RS, RC, RST, R, SD, BD, E, I, MR, ?>, MR extends MixRenderE<RG, RS, RC, RST, R, SD, BD, E, I, MB, ?>> extends ClientLe<RG, RS, RC, RST, R, SD, BD, E, I, MB, MR> implements IClientS<RG, RS, RC, RST, R, SD, BD, E, I>
{
    public ItemStack mouth_itemstack = ItemStack.EMPTY;

    public ClientSle(I i)
    {
        super(i);
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

    public abstract int[] getIVIntArray();
}
