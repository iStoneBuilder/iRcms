package com.stone.it.micro.rcms.framecore.handle;

import java.lang.reflect.Method;
import java.util.Set;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

/**
 *
 * @author cj.stone
 * @Date 2023/5/3
 * @Desc
 */
@Component
public class AnnotateHandle {

  /**
   *  UUID,METHOD,URI,DESC
   */

  public void handle(){
    // 反射获取所有相关包
    Reflections reflections = new Reflections("com.stone.it.**.service");
    // 获取CXF path 相关的包
    Set<Class<?>> cxfPathClasses = reflections.getTypesAnnotatedWith(Path.class);
    // 循环类获取方法
    cxfPathClasses.forEach(pathClass  -> {
      // 获取类方法的接口路径
      String classPath = "";
      if(pathClass.isAnnotationPresent(Path.class)){
        classPath = pathClass.getAnnotation(Path.class).value();
      }
      // 获取方法
      Method[] methods = pathClass.getDeclaredMethods();
      for (Method iMethod : methods ) {
        String methodType = getCxfMethodType(iMethod);
          if(iMethod.isAnnotationPresent(Path.class ) && methodType != null){
            String methodPath = iMethod.getAnnotation(Path.class).value();
            methodPath = classPath + (methodPath.startsWith("/") ? methodPath : "/"+ methodPath);
            // 写入数据库
          }
      }
    });
}

  private String getCxfMethodType(Method iMethod) {
    if(iMethod.isAnnotationPresent(GET.class)){
      return  "GET";
    }else if(iMethod.isAnnotationPresent(PUT.class)){
      return "PUT";
    }else if(iMethod.isAnnotationPresent(POST.class)){
      return "POST";
    }else if(iMethod.isAnnotationPresent(PATCH.class)){
      return "PATCH";
    }else if(iMethod.isAnnotationPresent(DELETE.class)){
      return "DELETE";
    }
    return null;
  }

}
