package cn.com.fxsh.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author hell
 * @date 2018/5/24
 */
public class ReflectUtil {


    /**
     * 获取利用反射获取类里面的值和名称
     * @param obj
     * @return
     */
    public static Map<String, Object> obj2Map(Object obj) {
        return obj2Map(obj,true);
    }

    /**
     * 对象转map
     * @param obj
     * @param needNull
     * 是否需要值为null的字段
     * @return
     */
    public static Map<String,Object> obj2Map(Object obj,boolean needNull){
        Map<String,Object> map=new HashMap<>();
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
                if(needNull || Objects.nonNull(value)){
                    map.put(key,value);
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * map转对象
     * @param map
     * @param clz
     * @return
     */
    public static Object map2Obj(Map<String,Object> map,Class<?> clz){
        if (map == null){
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
}
