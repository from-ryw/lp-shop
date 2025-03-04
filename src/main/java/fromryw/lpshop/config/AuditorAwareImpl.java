package fromryw.lpshop.config;

import fromryw.lpshop.account.helper.AccountHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<Integer> {

    private final AccountHelper accountHelper;
    private final HttpServletRequest request;

    public AuditorAwareImpl(AccountHelper accountHelper, HttpServletRequest request) {
        this.accountHelper = accountHelper;
        this.request = request;
    }

    @Override
    public Optional<Integer> getCurrentAuditor() {
        Integer currentUserId = accountHelper.getMemberId(request);

        // 로그인한 사용자가 없을 경우 기본값 0 (시스템 계정)
        return Optional.ofNullable(currentUserId).or(() -> Optional.of(0));
    }
}