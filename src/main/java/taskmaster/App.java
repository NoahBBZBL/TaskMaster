package taskmaster;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class App {
    public static void main(String[] args) {
        System.out.println("Test");
    }
}
