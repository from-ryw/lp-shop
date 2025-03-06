create table reviews (
   id              int auto_increment comment '아이디' primary key,
   member_id       int                                     not null comment '주문자 회원 아이디',
   order_id        int                                     not null comment '주문 아이디',
   item_id         int                                     not null comment '주문 상품 아이디',
   rating          int                                     not null comment '리뷰 별점',
   comment         text                                    not null comment '리뷰 내용',
   created_at      datetime default current_timestamp()    not null comment '생성 일시',
   created_by      int                                         null comment '생성자',
   updated_at      datetime default current_timestamp()    not null comment '수정 일시',
   updated_by      int                                         null comment '수정자'
) comment '리뷰';