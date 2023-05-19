drop table if exists member;
drop table if exists product;
drop table if exists earned_product;
drop table if exists ticket;

create table member
(
    member_id bigint primary key not null auto_increment comment 'PK',
    email     varchar(255)       not null comment '이메일',
    nickname  varchar(255)       not null comment '닉네임',
    password  varchar(255)       not null comment '비밀번호'
);
create table product
(
    product_id        bigint primary key not null auto_increment comment 'PK',
    name              varchar(255)       not null comment '상품명',
    need_ticket_count integer            not null comment '필요 티켓 수량',
    remain_quantity   integer            not null comment '남은 수량',
    total_quantity    integer            not null comment '총 수량',
    valid_start_date  date               not null comment '유효 시작 일자',
    valid_end_date    date               not null comment '유효 종료 일자',
    probability       double precision   not null comment '확률'
);
create table earned_product
(
    earned_product_id bigint primary key not null auto_increment comment 'PK',
    created_at        datetime           not null comment '생성 일자',
    member_id         bigint             not null comment '회원 ID',
    product_id        bigint             not null comment '상품 ID',
    constraint fk_earned_product_member_id foreign key (member_id) references member (member_id) on delete restrict on update restrict,
    constraint fk_earned_product_product_id foreign key (product_id) references product (product_id) on delete restrict on update restrict
);
create table ticket
(
    ticket_id      bigint primary key not null auto_increment comment 'PK',
    acquire_date   date               not null comment '티켓 획득 일자',
    expire_date    date               not null comment '티켓 만료 일자',
    how_to_acquire varchar(255)       not null comment '티켓 획득 방법',
    how_to_use     varchar(255) comment '티켓 사용 방법',
    is_used        boolean            not null comment '티켓 사용 여부',
    used_date      date comment '티켓 사용 일자',
    member_id      bigint             not null comment '회원 ID',
    constraint fk_ticket_member_id foreign key (member_id) references member (member_id) on delete restrict on update restrict
);
alter table if exists member add constraint email_unique unique (email);
alter table if exists member add constraint nickname_unique unique (nickname);