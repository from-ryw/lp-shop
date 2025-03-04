package fromryw.lpshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LpShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(LpShopApplication.class, args);
    }

}
