package com.nali.ilol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class NBTHelper
{
    public static byte[] serializeNBT(NBTTagCompound compound)
    {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        ByteBufUtils.writeTag(byteBuf, compound);
        byte[] byteArray = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(byteArray);
        byteBuf.release(); // Release the ByteBuf to avoid memory leaks
        return byteArray;
    }

    public static NBTTagCompound deserializeNBT(byte[] data)
    {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        byteBuf.writeBytes(data);
        NBTTagCompound nbtTagCompound = ByteBufUtils.readTag(byteBuf);
        byteBuf.release(); // Release the ByteBuf to avoid memory leaks
        return nbtTagCompound;
    }
}
