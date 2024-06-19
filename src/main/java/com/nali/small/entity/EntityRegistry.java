package com.nali.small.entity;

import com.nali.small.Small;
import com.nali.system.Reflect;
import com.nali.system.StringReader;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;

import static com.nali.Nali.I;

@Mod.EventBusSubscriber(modid = Small.ID)
public class EntityRegistry
{
//    public static Map<Class<? extends Entity>, EntityEntry> ENTITY_CLASS_ENTRIES = GameData.getEntityClassMap();
    public static List<Class> ENTITIES_CLASS_LIST = Reflect.getClasses("com.nali.list.entity");
    public static Object[] ENTITY_KEY_ARRAY;
    static
    {
        ENTITIES_CLASS_LIST.sort(Comparator.comparing(Class::getName));
    }

    public static void set()
    {
        int index = 0;
        for (Class clasz : ENTITIES_CLASS_LIST)
        {
            try
            {
//            String name = clasz.getSimpleName().toLowerCase();
                String[] string_array = StringReader.get(clasz);
                String name = string_array[0];
                net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(new ResourceLocation(string_array[1], name), clasz, name, index++, Small.I, 64, 1, true, (int)clasz.getField("eggPrimary").get(null), (int)clasz.getField("eggSecondary").get(null));
//                clasz.getField("ID").set(null, index++);
            }
            catch (NoSuchFieldException | IllegalAccessException e)
            {
                I.error(e);
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelRegistryEvent(ModelRegistryEvent modelregistryevent)
    {
        List<Class> render_class_list = Reflect.getClasses("com.nali.list.entity.render");
        render_class_list.sort(Comparator.comparing(Class::getName));

        for (int index = 0; index < ENTITIES_CLASS_LIST.size(); ++index)
        {
            try
            {
                Class render_class = render_class_list.get(index);
                Constructor constructor = render_class.getConstructor(RenderManager.class);
                RenderingRegistry.registerEntityRenderingHandler(ENTITIES_CLASS_LIST.get(index), new IRenderFactory()
                {
                    @Override
                    public Render createRenderFor(RenderManager rendermanager)
                    {
                        try
                        {
                            return (Render)constructor.newInstance(rendermanager);
                        }
                        catch (InstantiationException | InvocationTargetException | IllegalAccessException e)
                        {
                            I.error(e);
                        }

                        return null;
                    }
                });
            }
            catch (NoSuchMethodException e)
            {
                I.error(e);
            }
        }
    }

    // public static void setSpawn()
    // {
    //     EntityRegistry.addSpawn(.class, 16, 1, 4, EnumCreatureType.CREATURE, Biomes.PLAINS);
    // }
}
