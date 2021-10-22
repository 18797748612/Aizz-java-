import com.aizhong.configuration.AppConfig2;
import com.aizhong.configuration.AppConfig3;
import com.aizhong.dao.UserDaoImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppTest {
    /**
    *  用于测试：@Bean创建对象
    */
    @Test
    public void test(){
        ApplicationContext context = new AnnotationConfigApplicationContext("com.aizhong.AOP");
    }
}
