create table matching(
game_name varchar2(30) check (game_name == 1),
tier varchar2(20) not null,
user_id varchar2(10) primary key,
user_nickname varchar2(20) unique not null,
discord_nickname varchar2(20) unique,
kakao_id varchar2(10) unique,
matching_regdate date not null enable
);

