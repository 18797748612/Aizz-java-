import com.aizhong.entity.Person;
import com.aizhong.entity.Student;
import com.aizhong.entity.Teacher;
import com.aizhong.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;

public class AppTest {
    /**
    *  用于测试：JDK内置类型的set注入
    */
    @Test
    public void test1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = ctx.getBean("person", Person.class);
        System.out.println("person = " + person.getBirthday());
    }
    /**
    *  用于测试：用户自定义类型set注入
    */
    @Test
    public void test2(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = ctx.getBean("student", Student.class);
        Student student2 = ctx.getBean("student", Student.class);
        Student student3 = ctx.getBean("student", Student.class);
        System.out.println("student = " + student);
        System.out.println("student2 = " + student2);
        System.out.println("student3 = " + student3);
    }

    /**
    *  用于测试：构造注入
    */
    @Test
    public void test3(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = ctx.getBean("user", User.class);
        System.out.println("user = " + user);
    }

    /**
    *  用于测试：复杂对象的创建
    */
    @Test
    public void test4(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object conn = ctx.getBean("conn");
        System.out.println("conn = " + conn);
    }
    /**
    *  用于测试：
    */
    @Test
    public void test5(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }

    }

    /**
    *  用于测试：初始化、销毁、后置处理bean
    */
    @Test
    public void test6(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext1.xml");
        Teacher teacher = ctx.getBean("teacher", Teacher.class);
        Teacher teacher2 = ctx.getBean("teacher", Teacher.class);

        ctx.close();
    }
}
