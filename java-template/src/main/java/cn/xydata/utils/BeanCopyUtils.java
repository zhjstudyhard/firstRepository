package cn.xydata.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-02-11-38
 */
public class BeanCopyUtils {

    /**
     * 集合遍历转换vo
     * @param source
     * @param clazz
     * @param <T>
     * @param <U>
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static <T, U> List<U> getCopyList(List<T> source, Class<U> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<U> constructor = clazz.getConstructor();
        List<U> targets = new ArrayList<>();
        if(source!=null && source.size()>0){
            for (T t : source) {
                U target = constructor.newInstance();
                BeanUtils.copyProperties(t,target);
                targets.add(target);
            }
            return targets;
        }
        return null;
    }


}
