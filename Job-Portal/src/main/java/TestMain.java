import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestMain {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String pass1 = passwordEncoder.encode("123");
        String pass2 = passwordEncoder.encode("123");
        System.out.println(passwordEncoder.matches("123", pass2));
    }
}
