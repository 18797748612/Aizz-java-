import com.aizhong.entity.User;
import com.aizhong.factory.BeanFactory;
import com.aizhong.service.UserService;
import com.aizhong.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppTest {
    /**
    *  用于测试：简单工厂
    */
    @Test
    public void test1(){
//        UserServiceImpl userService = new UserServiceImpl();
//        userService.saveUser(new User());

//        UserService userService = BeanFactory.getUserService();
//        userService.saveUser(new User());

        UserService userServiceImpl = (UserService) BeanFactory.getBean("userService");
        userServiceImpl.saveUser(new User());
    }

    /**
    *  用于测试：获取对象
    */
    @Test
    public void test2(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        // 1、只通过bean的id获取，需要强转
        //User user = (User) ctx.getBean("user");
        // 2、只通过类型获取，要求工厂中同种class类型的bean只有一个
        //User user = ctx.getBean(User.class);
        // 3、通过id加class类型
        User user = ctx.getBean("user",User.class);
        System.out.println("user = " + user.toString());
    }

    /**
    *  用于测试：工厂API
    */
    @Test
    public void test(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        //获取Spring工厂配置文件中所有bean标签的id的值
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }
        //根据类型获得Spring配置文件中对应的id值
        String[] beanNamesForType = ctx.getBeanNamesForType(User.class);
        for (String s : beanNamesForType) {
            System.out.println("s = " + s);
        }
        //判断是否存在指定的id值的bean，不能判断 name 值
        System.out.println(ctx.containsBeanDefinition("username"));
        //判断是否存在指定的id值的bean，可以判断 name 值
        System.out.println(ctx.containsBean("username"));
    }
}
