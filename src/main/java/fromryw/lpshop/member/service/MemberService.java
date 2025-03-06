package fromryw.lpshop.member.service;

import fromryw.lpshop.member.entity.Member;

import java.util.List;

public interface MemberService {

    // 회원 데이터 저장
    void save(String name, String loginId, String loginPw);

    // 회원 데이터 조회
    Member find(String loginId, String loginPw);

    // 회원 데이터 조회
    Member find(String loginId);

    // 회원 데이터 조회
    List<Member> findAllByIds(List<Integer> ids);
}
