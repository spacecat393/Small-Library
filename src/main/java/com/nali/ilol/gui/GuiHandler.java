package com.nali.ilol.gui;

import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.list.container.InventoryContainer;
import com.nali.list.gui.InventoryGui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;
import java.util.UUID;

public class GuiHandler implements IGuiHandler
{
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == 0)
        {
            Entity entity = world.getEntityByID(x);
            return new InventoryContainer(player.inventory, (SkinningEntities)entity, player);
        }

        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == 0)
        {
            Entity entity = world.getEntityByID(x);
            if (!(entity instanceof SkinningEntities))
            {
                UUID uuid = SkinningEntities.FAKE_CLIENT_ENTITIES_MAP.get(x);
                entity = SkinningEntities.CLIENT_ENTITIES_MAP.get(uuid);
            }
            return new InventoryGui(player.inventory, (SkinningEntities)entity);
        }

        return null;
    }
}
