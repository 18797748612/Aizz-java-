import com.aizhong.componentDemo.ComponentTest;
import com.aizhong.componentDemo.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

public class AppTest {
    /**
    *  用于测试：配置文件 + 注解创建对象
    */
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        ComponentTest componentTest = (ComponentTest) context.getBean("componentTest");
        System.out.println(componentTest);
    }

    /**
    *  用于测试：@Autowired
    */
    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        UserService userServiceImpl = (UserService) context.getBean("userServiceImpl");
        userServiceImpl.save();
        Field[] declaredFields = userServiceImpl.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("declaredField = " + declaredField);
        }
    }
}
