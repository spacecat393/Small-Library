package com.nali.ilol.networks;

import com.nali.ilol.ILOL;
import com.nali.ilol.entities.skinning.SkinningEntitiesClientHandler;
import com.nali.ilol.entities.skinning.SkinningEntitiesClientMessage;
import com.nali.ilol.entities.skinning.SkinningEntitiesServerHandler;
import com.nali.ilol.entities.skinning.SkinningEntitiesServerMessage;
import com.nali.ilol.gui.OpenGUIHandler;
import com.nali.ilol.gui.OpenGUIMessage;
import net.minecraftforge.fml.relauncher.Side;

public class NetworksRegistry
{
    public static void register()
    {
        int id = 0;
        ILOL.SIMPLENETWORKWRAPPER.registerMessage(OpenGUIHandler.class, OpenGUIMessage.class, id++, Side.CLIENT);
        ILOL.SIMPLENETWORKWRAPPER.registerMessage(SkinningEntitiesClientHandler.class, SkinningEntitiesClientMessage.class, id++, Side.CLIENT);
        ILOL.SIMPLENETWORKWRAPPER.registerMessage(SkinningEntitiesServerHandler.class, SkinningEntitiesServerMessage.class, id++, Side.SERVER);
//        ILOL.SIMPLENETWORKWRAPPER.registerMessage(CapabilitiesClientHandler.class, CapabilitiesClientMessage.class, id++, Side.CLIENT);
//        ILOL.SIMPLENETWORKWRAPPER.registerMessage(CapabilitiesServerHandler.class, CapabilitiesServerMessage.class, id++, Side.SERVER);
    }
}
