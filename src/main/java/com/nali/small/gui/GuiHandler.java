package com.nali.small.gui;

import com.nali.list.container.InventoryContainer;
import com.nali.list.container.PlayerContainer;
import com.nali.list.gui.InventoryGui;
import com.nali.list.gui.PlayerGui;
import com.nali.small.entity.memo.client.ClientLe;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.EntityLeInv;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;
import java.util.UUID;

import static com.nali.small.entity.memo.client.ClientLe.FAKE_ENTITIES_MAP;

public class GuiHandler implements IGuiHandler
{
    @Nullable
    @Override
    public Object getServerGuiElement(int id, EntityPlayer entityplayer, World world, int x, int y, int z)
    {
        if (id == 0)
        {
            return new InventoryContainer(entityplayer.inventory, ServerE.ENTITIES_MAP.get(entityplayer.getEntityData().getUniqueId("loli_nali")), entityplayer);
        }
        else
        {
            return new PlayerContainer();
        }
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int id, EntityPlayer entityplayer, World world, int x, int y, int z)
    {
        if (id == 0)
        {
            Entity entity = world.getEntityByID(x);
            if (!(entity instanceof EntityLeInv))
            {
                UUID uuid = FAKE_ENTITIES_MAP.get(x);
                entity = ClientLe.ENTITIES_MAP.get(uuid);
            }
            return new InventoryGui(entityplayer.inventory, (EntityLeInv)entity);
        }
        else
        {
            return new PlayerGui(new PlayerContainer());
        }
    }
}
