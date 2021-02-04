--create user spring identified by java;
--grant resource, connect to spring;
--conn spring/java;

drop table BOARD;
drop sequence BOARD_SEQ;
purge recyclebin;

create table BOARD(
   SEQ number constraint BOARD_PK primary key, 
   WRITER varchar2(15), 
   EMAIL varchar2(20),
   SUBJECT varchar2(20), 
   CONTENT varchar2(20), 
   RDATE date default SYSDATE
); 
create sequence BOARD_SEQ increment by 1 start with 1 nocache;

insert into BOARD values(BOARD_SEQ.nextval, '������', 'kim@hanmail.net','����1', '����1', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '�躸��', 'lee@hanmail.net','����2', '����2', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '�輺��', 'han@hanmail.net','����3', '����3', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '�輼��', 'oh@hanmail.net','����4', '����4', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '��Ҵ�', 'chm@hanmail.net','����5', '����5', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '��öȣ', 'kim@hanmail.net','����6', '����6', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '�����', 'lee@hanmail.net','����7', '����7', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '�տ���', 'han@hanmail.net','����8', '����8', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '�۽���', 'oh@hanmail.net','����9', '����9', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '������', 'chm@hanmail.net','����10', '����10', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '����', 'kim@hanmail.net','����11', '����11', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '�Ӽ���', 'lee@hanmail.net','����12', '����12', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '������', 'han@hanmail.net','����13', '����13', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '����ȯ', 'oh@hanmail.net','����14', '����14', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '������', 'chm@hanmail.net','����15', '����15', SYSDATE);

commit;

select CONSTRAINT_NAME, CONSTRAINT_TYPE from user_constraints where TABLE_NAME='BOARD';
select * from BOARD order by SEQ desc;