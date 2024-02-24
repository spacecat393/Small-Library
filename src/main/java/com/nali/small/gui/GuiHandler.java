package com.nali.small.gui;

import com.nali.list.container.InventoryContainer;
import com.nali.list.gui.InventoryGui;
import com.nali.small.entities.memory.ClientEntitiesMemory;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;
import java.util.UUID;

import static com.nali.small.entities.memory.ClientEntitiesMemory.FAKE_ENTITIES_MAP;

public class GuiHandler implements IGuiHandler
{
    @Nullable
    @Override
    public Object getServerGuiElement(int id, EntityPlayer entityplayer, World world, int x, int y, int z)
    {
        if (id == 0)
        {
            return new InventoryContainer(entityplayer.inventory, ServerEntitiesMemory.ENTITIES_MAP.get(entityplayer.getEntityData().getUniqueId("loli_nali")), entityplayer);
        }

        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int id, EntityPlayer entityplayer, World world, int x, int y, int z)
    {
        if (id == 0)
        {
            Entity entity = world.getEntityByID(x);
            if (!(entity instanceof SkinningEntities))
            {
                UUID uuid = FAKE_ENTITIES_MAP.get(x);
                entity = ClientEntitiesMemory.ENTITIES_MAP.get(uuid);
            }
            return new InventoryGui(entityplayer.inventory, (SkinningEntities)entity);
        }

        return null;
    }
}
