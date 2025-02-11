create table members (
    id          int             auto_increment                       comment '아이디' primary key,
    name        varchar(50)                                 not null comment '회원명',
    login_id    varchar(50)                                 not null comment '로그인 아이디',
    login_pw    varchar(100)                                not null comment '로그인 패스워드',
    created_at  datetime        default current_timestamp() not null comment '생성 일시',
    created_by  int                                             null comment '생성자',
    updated_at  datetime        default current_timestamp() not null comment '수정 일시',
    updated_by  int                                             null comment '수정자',
    constraint members_uk unique (login_id)
) comment '회원';

INSERT INTO members (name, login_id, login_pw, created_by, updated_by)
VALUES ('알파', 'alpha@example.com', 'alpha!', 1, 1);

delete from members where 1;

ALTER TABLE members ADD login_pw_salt char(16) NOT NULL comment '로그인 패스워드 솔트' after login_pw;