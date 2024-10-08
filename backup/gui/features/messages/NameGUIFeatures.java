package com.nali.small.gui.features.messages;

import com.nali.small.entity.memo.client.ClientLe;
import com.nali.small.entity.EntityLeInv;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.system.Reference;
import net.minecraft.entity.EntityList;
import net.minecraft.util.text.translation.I18n;

public class NameGUIFeatures extends GUIFeaturesLoader
{
    public NameGUIFeatures(MixGui mixgui, EntityLeInv skinningentities)
    {
        super(mixgui);

        ClientLe cliententitiesmemory = (ClientLe)skinningentities.bothentitiesmemory;
        String uuid_string = cliententitiesmemory.uuid.toString();

        String custom_name_string = "-";
        if (skinningentities.hasCustomName())
        {
            custom_name_string = skinningentities.getCustomNameTag();
        }

        String entity_string = EntityList.getEntityString(skinningentities);
        if (entity_string == null)
        {
            entity_string = "generic";
        }

        this.string_array = new String[]
        {
            I18n.translateToLocal("info." + Small.ID + ".b7"),
            I18n.translateToLocal("entity." + entity_string + ".name"),
            I18n.translateToLocal("info." + Small.ID + ".b8"),
            custom_name_string,
            I18n.translateToLocal("info." + Small.ID + ".b9"),
            uuid_string,
            I18n.translateToLocal("info." + Small.ID + ".ba")
        };

        this.createColor();
    }
}
