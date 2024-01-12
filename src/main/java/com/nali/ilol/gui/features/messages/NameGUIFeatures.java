package com.nali.ilol.gui.features.messages;

import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.ilol.gui.MixGui;
import com.nali.ilol.gui.features.GUIFeaturesLoader;
import com.nali.list.container.InventoryContainer;
import net.minecraft.entity.EntityList;
import net.minecraft.util.text.translation.I18n;

public class NameGUIFeatures extends GUIFeaturesLoader
{
    public NameGUIFeatures(MixGui mixgui, SkinningEntities skinningentities)
    {
        super(mixgui);

        String uuid_string = skinningentities.client_uuid.toString();

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
            I18n.translateToLocal("gui.info.n"),
            I18n.translateToLocal("entity." + entity_string + ".name"),
            I18n.translateToLocal("gui.info.cn"),
            custom_name_string,
            I18n.translateToLocal("gui.info.un"),
            uuid_string,
            I18n.translateToLocal("gui.info.unh")
        };

        this.createColor();
    }
}
