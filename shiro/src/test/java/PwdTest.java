import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

public class PwdTest {
    @Test
    public void pwdTest1(){
        String pwd ="66666666";
        System.out.println(DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(pwd.getBytes()).getBytes()));
    }
}

