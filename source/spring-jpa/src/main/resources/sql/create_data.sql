CREATE TABLE S_EMP(
  ID   NUMBER(7)     CONSTRAINT S_EMP_ID_NN NOT NULL
, NAME  VARCHAR2(25)  CONSTRAINT S_EMP_NAME_NN NOT NULL
, START_DATE  DATE
, TITLE	VARCHAR2(25)
, DEPT_NAME	VARCHAR2(25)
, SALARY NUMBER(11, 2)
, CONSTRAINT S_EMP_ID_PK PRIMARY KEY (ID)
);

INSERT INTO S_EMP
    (
          ID
        , NAME
        , START_DATE
        , TITLE
        , DEPT_NAME
        , SALARY
    )
VALUES
    (
          1
        , '안은경'
        , '2002-12-03'
        , '영업대표이사'
        , '영업부'
        , 2500
    );

CREATE TABLE S_DEPT(
  DEPT_ID   NUMBER(7)     CONSTRAINT s_dept_id_nn NOT NULL
, DEPT_NAME  VARCHAR2(25)  CONSTRAINT s_dept_name_nn NOT NULL
, CONSTRAINT s_dept_id_pk PRIMARY KEY (DEPT_ID)
);

