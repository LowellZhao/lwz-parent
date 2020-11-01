import com.lagou.edu.EmailApplication8082;
import com.lagou.edu.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmailApplication8082.class)
public class SomeTest {

    @Resource
    private EmailService emailService;

    @Test
    public void test01() {
        emailService.sendSimpleMailMessge("1043257024@qq.com", "1996");
    }

}
