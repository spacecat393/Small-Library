package com.nali.list.netmethods.clientmessage;

import com.nali.list.messages.ClientMessage;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.messages.EffectsGUIFeatures;
import com.nali.system.bytes.BytesReader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

import static com.nali.small.gui.features.messages.EffectsGUIFeatures.EFFECTS_INT_ARRAY;

public class SetEffects
{
    public static byte ID;

    public static void run(ClientMessage clientmessage)
    {
        Entity entity = Minecraft.getMinecraft().world.getEntityByID(BytesReader.getInt(clientmessage.data, 1));

        if (entity instanceof SkinningEntities)
        {
            EFFECTS_INT_ARRAY = new int[(clientmessage.data.length - 1 - 4) / 4];
            int index = 0;
            for (int i = 1 + 4; i < clientmessage.data.length; i += 4)
            {
                EFFECTS_INT_ARRAY[index++] = BytesReader.getInt(clientmessage.data, i);
                i += 4;
                EFFECTS_INT_ARRAY[index++] = BytesReader.getInt(clientmessage.data, i);
                i += 4;
                EFFECTS_INT_ARRAY[index++] = BytesReader.getInt(clientmessage.data, i);
            }

            MixGui.GUIFEATURESLOADER = new EffectsGUIFeatures(MixGui.I);
        }
//                //sync looking
//                Entity entity = Minecraft.getMinecraft().world.getEntityByID(BytesReader.getInt(clientmessage.data, 1));
//
//                if (entity instanceof SkinningEntities)
//                {
//                    SkinningEntities skinningentities = (SkinningEntities)entity;
//                    skinningentities.rotationYaw = BytesReader.getFloat(clientmessage.data, 1 + 4);
//                    skinningentities.rotationPitch = BytesReader.getFloat(clientmessage.data, 1 + 4 + 4);
//                    skinningentities.rotationYawHead = BytesReader.getFloat(clientmessage.data, 1 + 4 + 4 + 4);
//                    skinningentities.renderYawOffset = BytesReader.getFloat(clientmessage.data, 1 + 4 + 4 + 4 + 4);
//                }
    }
}
