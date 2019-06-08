package MyDubbo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ServiceAnnoUtil {
    public static void getObjectInfo(Class<?> clazz)
    {
        Field[] fields = clazz.getDeclaredFields();
        for(Field field:fields)
        {
            if(field.isAnnotationPresent(DubboService.class))
            {
                DubboService dubboService = field.getAnnotation(DubboService.class);
                try {
                    MyDubboServer.serviceMap.setObjectMap(dubboService.interfaceName(), clazz.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
