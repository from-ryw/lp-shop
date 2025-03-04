package fromryw.lpshop.config;

import fromryw.lpshop.account.helper.AccountHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {

    @Bean
    public AuditorAware<Integer> auditorAware(AccountHelper accountHelper, HttpServletRequest request) {
        return new AuditorAwareImpl(accountHelper, request);
    }
}

