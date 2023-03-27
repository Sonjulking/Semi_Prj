create table comment(
comment_index number primary_key,
comment_cont varchar2(10000),
comment_writer varchar2(30) not null,
commemt_regdate date not null enable,
comment_hit number(5) default 0
);

