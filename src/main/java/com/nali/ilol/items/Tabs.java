package com.nali.ilol.items;

import com.nali.ilol.system.Reference;
import com.nali.list.items.IlolBox;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Tabs extends CreativeTabs
{
    public static Tabs TABS = new Tabs();
    public Tabs()
    {
        super(Reference.MOD_ID);
        this.setBackgroundImageName("item_search.png");
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(IlolBox.I);
    }

    @Override
    public boolean hasSearchBar()
    {
        return true;
    }
}
