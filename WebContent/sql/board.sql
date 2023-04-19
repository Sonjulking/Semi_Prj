create table board(
board_index number primary key,
board_title varchar2(100) not null,
board_cont varchar2(10000),
board_writer varchar2(30) not null,
board_type varchar2(30) not null,
board_heading varchar2(20) not null,
board_hit number(5000) default 0,
board_thumbs number(3) default 0,
board_date date not null enable
);