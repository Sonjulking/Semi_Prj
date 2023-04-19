create table member(
member_index number not null,
member_id varchar2(20) primary key,
member_pwd varchar2(12) not null,
member_nickname varchar2(20) unique not null,
member_email varchar2(20) not null,
member_point number default 0,
member_regdate date not null enable,
member_phone varchar2(20) not null,
prefer_game1 varchar2(20) default null,
prefer_game2 varchar2(20) default null,
prefer_game3 varchar2(20) default null 
);

commit;

-- 댓글 기능 group, step, indent 컬럼 추가