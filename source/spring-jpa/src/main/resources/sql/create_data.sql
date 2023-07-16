CREATE TABLE S_EMP(
  IDV   NUMBER(7)     CONSTRAINT S_EMP_ID_NN NOT NULL
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

SELECT * FROM S_EMP;
