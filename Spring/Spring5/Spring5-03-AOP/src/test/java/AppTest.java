import com.aizhong.entity.User;
import com.aizhong.service.UserService;
import com.aizhong.service.proxy.UserServiceProxy;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.stream.events.Characters;
import java.util.Scanner;

public class AppTest {
    /**
     * 用于测试：静态代理
     */
    @Test
    public void test() {
        UserService userService = new UserServiceProxy();
        userService.register(new User());
        userService.login("Aizz", "123456");
    }

    /**
     * 用于测试：Spring动态代理
     */
    @Test
    public void test1() {
        ApplicationContext cpx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = cpx.getBean("userService", UserService.class);
        System.out.println("userService.getClass() = " + userService.getClass());// class com.sun.proxy.$Proxy6
        userService.register(new User());
        userService.login("Aizz", "123");
    }

    /**
     * 用于测试：BeanPostProcessor加工原始对象，返回代理对象
     */
    @Test
    public void test2() {
        ApplicationContext cpx = new ClassPathXmlApplicationContext("applicationContext1.xml");
        UserService userService = cpx.getBean("userService", UserService.class);
        System.out.println("userService = " + userService.getClass());
        userService.register(new User());

    }

    /**
     * 用于测试：注解AOP
     */
    @Test
    public void test3() {
        ApplicationContext cpx = new ClassPathXmlApplicationContext("applicationContext2.xml");
        UserService userService = cpx.getBean("userService", UserService.class);
        System.out.println("userService = " + userService.getClass());
        userService.register(new User());
        userService.login("Aizz", "123");
    }

    /**
     * 用于测试：
     */
    @Test
    public void test4() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int letter = 0;
        int space = 0;
        int digital = 0;
        int other = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                letter++;
            } else if (c == ' ') {
                space++;
            } else if (c >= '0' && c <= '9') {
                digital++;
            } else {
                other++;
            }
        }
    }
    /**
    *  用于测试：
    */
    @Test
    public void test5(){


    }
}
