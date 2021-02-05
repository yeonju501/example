drop table QNA; drop table ;
DROP SEQUENCE QNA_SEQ; DROP SEQUENCE QNA_SEQ;
CREATE TABLE QNA
(
    QB_SEQ        NUMBER, 
    M_EMAIL       VARCHAR2(50 char)    NOT NULL, 
    QB_SUBJECT    VARCHAR2(50 char)   NOT NULL, 
    QB_CONTENT    VARCHAR2(500 char)    NOT NULL, 
    QB_RDATE      DATE, 
    QB_COUNT      NUMBER, 
    QB_REF        NUMBER, 
    QB_LEV        NUMBER, 
    QB_SUN        NUMBER, 
    CONSTRAINT QNA_PK PRIMARY KEY (QB_SEQ)
)
/

CREATE SEQUENCE QNA_SEQ
START WITH 1
INCREMENT BY 1;

CREATE TABLE QFile
(
    F_SEQ       NUMBER, 
    QB_SEQ      NUMBER, 
    F_FNAME     VARCHAR2(100 char)    NOT NULL, 
    F_OFNAME    VARCHAR2(100 char)    NOT NULL, 
    F_FSIZE     NUMBER,
    CONSTRAINT QF_PK PRIMARY KEY (QF_SEQ)
)

CREATE SEQUENCE QF_SEQ
START WITH 1
INCREMENT BY 1;