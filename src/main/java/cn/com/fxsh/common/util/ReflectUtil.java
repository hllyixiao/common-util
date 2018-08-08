package cn.com.fxsh.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 反射工具类
 *
 * @author hell
 * @date 2018/5/24
 */
public class ReflectUtil {

    /**
     * 利用反射将对象转换为map
     * 数据为null的字段不会添加到map中
     * @param obj
     * @return
     */
    public static Map<String, Object> obj2MapNotNull(Object obj) {
        return obj2Map(obj, false);
    }

    /**
     * 利用反射将对象转换为map
     * 数据为null的字段也会添加到map中
     * @param obj
     * @return
     */
    public static Map<String, Object> obj2MapAll(Object obj) {
        return obj2Map(obj, true);
    }

    /**
     * map转对象
     *
     * @param map
     * @param clz
     * @return
     */
    public static Object map2Obj(Map<String, Object> map, Class<?> clz) {
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            obj = clz.newInstance();
            BeanInfo beanInfo;
            beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                Method setter = property.getWriteMethod();
                if (setter != null) {
                    setter.invoke(obj, map.get(property.getName()));
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 对象转map
     * @param obj
     * @param needNull 是否需要值为null的字段
     * @return
     */
    private static Map<String, Object> obj2Map(Object obj, boolean needNull) {
        Map<String, Object> map = new HashMap<>();
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (key.compareToIgnoreCase("class") == 0) {
                    continue;
                }
                Method getter = property.getReadMethod();
                Object value = getter.invoke(obj);
                if (needNull || Objects.nonNull(value)) {
                    map.put(key, value);
                }
            }
        } catch (IntrospectionException
                | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) {

    }
}
