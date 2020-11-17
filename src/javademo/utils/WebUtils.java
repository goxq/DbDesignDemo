package javademo.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


public class WebUtils {

    //request内容装载到实体类中
    public static <T> T request2Bean(HttpServletRequest request, Class<T> beanClass) {
        // 创建要封装数据的bean
        try {
            T bean = beanClass.newInstance();
            Enumeration<String> e = request.getParameterNames();
            while (e.hasMoreElements()) {
                String name = e.nextElement();
                String value = request.getParameter(name);
                BeanUtils.setProperty(bean, name, value);
            }
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
