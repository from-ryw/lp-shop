create table items (
   id              int auto_increment comment '아이디' primary key,
   name            varchar(50)                             not null comment '상품 이름',
   artist          varchar(50)                             not null comment '상품 아티스트',
   img_path        varchar(100)                            not null comment '상품 사진 경로',
   price           int                                     not null comment '상품 가격',
   discount_per    int                                     not null comment '상품 할인율',
   created_at      datetime default current_timestamp()    not null comment '생성 일시',
   created_by      int                                         null comment '생성자',
   updated_at      datetime default current_timestamp()    not null comment '수정 일시',
   updated_by      int                                         null comment '수정자'
) comment '상품';

INSERT INTO items (name, artist, img_path, price, discount_per, created_by, updated_by)
VALUES ('CLOVER',               '나상현씨밴드',   '/img/BandNah___CLOVER.jpg',                  100000, 5,  1, 1)
     , ('Summer Tales',         'Lacuna',       '/img/Lacuna_Summer_Tales.jpg',             200000, 10, 1, 1)
     , ('CHAPTER OF YOUTH',     'LUCY',         '/img/LUCY___CHAPTER_OF_YOUTH.jpg',         300000, 15, 1, 1)
     , ('Colorful Express',     '페퍼톤스',      '/img/PEPPERTONES___Colorful_Express.jpg',  400000, 20, 1, 1)
     , ('Share',                '소란',         '/img/SORAN___Share.jpg',                   500000, 25, 1, 1)
     , ('GSI_Edition',          '터치드',        '/img/TOUCHED___GSI_Edition.jpg',           600000, 30, 1, 1)
     , ('선물',                   '백예린',      '/img/YerinBaek___Present.jpg',             700000, 35, 1, 1);