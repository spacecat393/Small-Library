package com.nali.small.gui.mouse;

import com.nali.small.gui.key.KeyMenuArmy;
import com.nali.small.gui.page.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.container.gui.SmallGui.FLAG;
import static com.nali.list.container.gui.SmallGui.PAGE_ARRAY;
import static com.nali.small.gui.key.Key.KEY;
import static com.nali.small.gui.page.Page.STRING_ARRAY;

@SideOnly(Side.CLIENT)
public class MouseSmall extends Mouse
{
    @Override
    public void run()
    {
        if (PageSmall.PAGE == PAGE)
        {
            if (HIT == 1)
            {
                PAGE_ARRAY = new Page[]
                {
                    new PageBack(),
                    new PageMenuArmy(STRING_ARRAY[14] + "|" + STRING_ARRAY[0]),
                    new PageArmy()
                };
                KEY = new KeyMenuArmy();
                MOUSE = new MouseArmy();
                FLAG |= 1;
            }
        }

        super.run();
    }

//    @Override
//    public void detect()
//    {
//        super.detect();
//        if ((STATE & 1) == 1)
//        {
//            PAGE = (byte)(OPENGL_FIXED_PIPE_FLOATBUFFER.get(1) * 255.0F);
//////            this.page = (byte)(OPENGL_INTBUFFER.get(1) & 0xFF);
////            SMALLGUI.state &= 255-1;
//        }
//    }
}
