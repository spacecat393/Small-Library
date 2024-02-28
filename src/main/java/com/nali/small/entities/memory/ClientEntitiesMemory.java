package com.nali.small.entities.memory;

import com.nali.data.BothData;
import com.nali.render.ObjectRender;
import com.nali.small.entities.bytes.WorkBytes;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.render.layer.ArrowLayerRender;
import com.nali.small.entities.skinning.render.layer.ItemLayerRender;
import com.nali.small.entities.skinning.works.SkinningEntitiesPat;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

public class ClientEntitiesMemory extends BothEntitiesMemory
{
    @SideOnly(Side.CLIENT)
    public static Map<UUID, SkinningEntities> ENTITIES_MAP = new WeakHashMap<>();
    @SideOnly(Side.CLIENT)
    public static Map<Integer, UUID> FAKE_ENTITIES_MAP = new WeakHashMap<>();
    @SideOnly(Side.CLIENT)
    public ObjectRender objectrender;
//    @SideOnly(Side.CLIENT)
//    public long client_last_time = Minecraft.getSystemTime();
//    @SideOnly(Side.CLIENT)
//    public long client_m_last_time = System.currentTimeMillis();

    @SideOnly(Side.CLIENT)
    public ArrowLayerRender arrowlayerrender;
    @SideOnly(Side.CLIENT)
    public ItemLayerRender itemlayerrender;

    @SideOnly(Side.CLIENT)
    public UUID uuid;
    @SideOnly(Side.CLIENT)
    public boolean fake;

    @SideOnly(Side.CLIENT)
    public byte[] work_byte_array;
//    public byte[] client_state_byte_array;
    //    public UUID current_client_uuid;
    @SideOnly(Side.CLIENT)
    public SkinningEntitiesPat skinningentitiespat;

    @SideOnly(Side.CLIENT)
    public ItemStack mouth_itemstack = ItemStack.EMPTY;

    @SideOnly(Side.CLIENT)
    public byte[] sync_byte_array;

    public ClientEntitiesMemory(SkinningEntities skinningentities, BothData bothdata, WorkBytes workbytes)
    {
        super(skinningentities, bothdata, workbytes);
        this.arrowlayerrender = new ArrowLayerRender(skinningentities);
        this.itemlayerrender = new ItemLayerRender(skinningentities);
        this.objectrender = (ObjectRender)skinningentities.createObjectRender();
        this.work_byte_array = new byte[workbytes.MAX_WORKS()];
        this.skinningentitiespat = new SkinningEntitiesPat(skinningentities);
        this.sync_byte_array = new byte[bothdata.MaxSync()];
    }
}
