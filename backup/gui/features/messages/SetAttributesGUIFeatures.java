package com.nali.small.gui.features.messages;

import com.nali.list.container.InventoryContainer;
import com.nali.small.entity.EntityLeInv;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.gui.net.messages.inventory.SetAttributeGUINet;
import com.nali.small.system.Reference;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.util.text.translation.I18n;

import static com.nali.small.gui.MixGui.GUINETLOADER;

public class SetAttributesGUIFeatures extends GUIFeaturesLoader
{
    public SetAttributesGUIFeatures(MixGui mixgui)
    {
        super(mixgui);
        EntityLeInv skinningentities = ((InventoryContainer)this.mixgui.inventorySlots).skinningentities;
        int size = skinningentities.getAttributeMap().getAllAttributes().size();
        this.string_array = new String[size + 1];
        this.string_array[0] = I18n.translateToLocal("info." + Small.ID + ".bn");
        int index = 1;
        for (IAttributeInstance iattributeinstance : skinningentities.getAttributeMap().getAllAttributes())
        {
            double value = iattributeinstance.getAttributeValue();
            for (AttributeModifier attributemodifier : iattributeinstance.getModifiers())
            {
                value += attributemodifier.getAmount();
            }

            this.string_array[index] = (index++ - 1) + " " + iattributeinstance.getAttribute().getName() + " : " + value;
        }
        this.createColor();
        GUINETLOADER = new SetAttributeGUINet(mixgui);
    }
}
