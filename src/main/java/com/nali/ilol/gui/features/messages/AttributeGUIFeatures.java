package com.nali.ilol.gui.features.messages;

import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.ilol.gui.MixGui;
import com.nali.ilol.gui.features.GUIFeaturesLoader;
import com.nali.list.container.InventoryContainer;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.util.text.translation.I18n;

public class AttributeGUIFeatures extends GUIFeaturesLoader
{
    public AttributeGUIFeatures(MixGui mixgui)
    {
        super(mixgui);
        SkinningEntities skinningentities = ((InventoryContainer)this.mixgui.inventorySlots).skinningentities;
        int size = skinningentities.getAttributeMap().getAllAttributes().size();
        this.string_array = new String[size];
        int index = 0;
        for (IAttributeInstance iattributeinstance : skinningentities.getAttributeMap().getAllAttributes())
        {
            double value = iattributeinstance.getAttributeValue();
            for (AttributeModifier attributemodifier : iattributeinstance.getModifiers())
            {
                value += attributemodifier.getAmount();
            }

            this.string_array[index++] = I18n.translateToLocal("attribute.name." + iattributeinstance.getAttribute().getName()) + " : " + value;
        }
        this.createColor();
    }
}
