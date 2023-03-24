
create table member(
member_index number not null,
member_id varchar2(10) primary key,
member_pwd varchar2(12) not null,
member_nickname varchar2(10) unique not null,
member_pro_game varchar2(10),
member_email varchar2(20),
member_point number not null,
member_regdate date not null enable,
member_phone varchar2(20) not null
);


-- 선호 게임 컬럼 추가....
alter table member rename column pro_game to prefer_lol;
alter table member add prefer_battle_ground varchar2(5);
alter table member add prefer_overwatch varchar2(5);

-- 댓글 기능 group, step, indent 컬럼 추가