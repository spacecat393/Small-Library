//package com.nali.small.tiles;
//
//import com.nali.small.block.memo.client.IMixRender;
//import net.minecraft.block.Block;
//import net.minecraft.creativetab.CreativeTabs;
//
//public interface MixTiles extends IMixRender
//{
//	default void init(Block block, String name, String mod_id, CreativeTabs creativetabs)
//	{
//		block.setRegistryName(mod_id, name);
//		block.setCreativeTab(creativetabs);
//		block.setTranslationKey(mod_id + '.' + name);
//	}
//
////	default Item getNewItem(Block block)
////	{
////		return new ItemBlock(block);
////	}
//}
