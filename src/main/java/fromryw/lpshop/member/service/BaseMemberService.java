package fromryw.lpshop.member.service;

import fromryw.lpshop.common.util.HashUtils;
import fromryw.lpshop.member.entity.Member;
import fromryw.lpshop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BaseMemberService implements MemberService {

    private final MemberRepository memberRepository;

    // 회원 데이터 저장
    @Transactional
    @Override
    public void save(String name, String loginId, String loginPw) {
        // 솔트 생성
        String loginPwSalt = HashUtils.generateSalt(16);

        // 입력 패스워드에 솔트를 적용
        String loginPwSalted = HashUtils.generateHash(loginPw, loginPwSalt);

        // 회원 데이터 INSERT 실행 (createdBy = 0)
        Member newMember = new Member(name, loginId, loginPwSalted, loginPwSalt);
        newMember = memberRepository.save(newMember); // INSERT 실행 (id 생성됨)

        newMember.setCreatedBy(newMember.getId());
        newMember.setUpdatedBy(newMember.getId());

        memberRepository.save(newMember);
    }

    // 회원 데이터 조회
    @Override
    public Member find(String loginId, String loginPw) {
        // 로그인 아이디로 회원 조회
        Optional<Member> memberOptional = memberRepository.findByLoginId(loginId);

        // 회원 데이터가 있으면
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();

            // 솔트 조회
            String loginPwSalt = memberOptional.get().getLoginPwSalt();

            // 입력 패스워드에 솔트를 적용
            String loginPwSalted = HashUtils.generateHash(loginPw, loginPwSalt);

            // 저장된 회원 로그인 패스워드와 일치한다면
            if (member.getLoginPw().equals(loginPwSalted)) {
                return member;
            }
        }

        return null;
    }

    // 회원 데이터 조회
    @Override
    public Member find(String loginId) {
        //회원 데이터가 있으면 해당 값 리턴
        return memberRepository.findByLoginId(loginId).orElse(null);
    }

    // 해당 id들의 회원 목록 조회
    @Override
    public List<Member> findAllByIds(List<Integer> ids) {
        return memberRepository.findAllById(ids);
    }

}
