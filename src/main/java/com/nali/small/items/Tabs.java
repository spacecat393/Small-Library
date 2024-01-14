package com.nali.small.items;

import com.nali.small.system.Reference;
import com.nali.list.items.SmallBox;
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
        return new ItemStack(SmallBox.I);
    }

    @Override
    public boolean hasSearchBar()
    {
        return true;
    }
}
