import com.aizhong.dao.UserDao;
import com.aizhong.entity.User;
import com.aizhong.service.UserService;
import com.aizhong.service.UserServiceImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;

public class AppTest {
    /**
    *  用于测试：mybatis
    */
    @Test
    public void test(){
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // mybatis 默认关闭自动提交事务
        SqlSession session = sqlSessionFactory.openSession(false);
        UserDao userDAO = session.getMapper(UserDao.class);
        User user = new User("az22","222222");
        userDAO.save(user);

        session.commit();
        session.close();
    }

    /**
    *  用于测试：Spring整合
    */
    @Test
    public void test1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        UserDao userDAO = (UserDao) ctx.getBean("userDao");

        User user = new User("Se","ssf");
        userDAO.save(user);
    }

    /**
     *  用于测试：事务
     */
    @Test
    public void test2(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        UserService userService = ctx.getBean("userService", UserService.class);

    }
}
