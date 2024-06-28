package com.nali.small;

import com.nali.list.item.SmallBox;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import static com.nali.small.item.ItemRegistry.ITEM_ARRAY;

public class SmallTab extends CreativeTabs
{
    public static SmallTab TAB = new SmallTab();
    public SmallTab()
    {
        super(Small.ID);
        this.setBackgroundImageName("item_search.png");
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(ITEM_ARRAY[SmallBox.ID]);
    }

    @Override
    public boolean hasSearchBar()
    {
        return true;
    }
}
