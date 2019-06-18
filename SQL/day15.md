SQL
=========

> 과정 15일차 (19.05.31)

### Relational Database
- RDBMS
    - 여러 종류가 있음
    - Relation 간 관계를 가지고 있는 DB형태
    - DDL, DML, DCL
        - DDL(Data Definition Language) : 데이터 정의어
            - CREATE
            - ALTER
            - DROP
            - TRUNCATE
            - RENAME
        - DML(Data Manipulation Language) : 데이터 조작어
            - SELECT
            - INSERT
            - UPDATE
            - DELETE
        - DCL(Data Control Language) : 데이터 제어어(권한과 관련있음)
            - GRANT
            - REVOKE        


### 1.SELECT

- Aliasing
    - 출력 시 열의 이름에 별명을 붙여 출력할 수 있다.
    ```sql
    SELECT product_id AS id,
            product_name AS name,
            product_price AS price
    FROM Products;
    ```

-  `SELECT` 시 기본 사칙연산을 사용할 수 있다.
    ```sql
    SELECT name,
            empno AS epn,
            product_price AS price
    FROM Products;
    ```
- `||` 연산자를 통해 컬럼 병합을 할 수 있다.
    ```sql
    SELECT name || " " || empno AS epn,
            product_price AS price
    FROM Products;
    /*
    결과
    name epn | price
    -----------------
    Tshirt 1 | 2000

    name과 epn 칼럼이 한 칼럼으로 병합되어 출력
    */
    ```

- `DINSTINCT` 명령어를 통해 중복이 제거된 데이터들을 출력할 수 있다. (data의 종류를 보고싶을 때)
    ```sql
    SELECT DISCINT(JOB) FROM EMP;

    /*
    결과
    JOB
    ---
    CLERK
    MANAGER
    ...
    */
    ```

- 비교 연산자로 날짜도 비교 가능하다. (단 날짜는 따옴표를 앞뒤로 붙여야함.)
- 주석은 한 줄은 `--` 한 줄 이상은 `/* */`로 나타낸다.
- SQL에도 미리 정의되어있는 함수가 있다. (ex. `NVL(COLNAME, x)` COLNAME에는 NULL이 있는 칼럼명. 출력 시 NULL이 나오면 x로 치환하여 출력한다.)

- 비교 연산자
    - `=` `<>` `>=` `>` `<=` `<`
    > `!=`(not equal) 는 표준이 아니다. 대신 `<>`를 쓰도록 할 것.

    > 문자열의 비교도 가능하나 사전적 순서로 정렬한 데이터의 순서 비교로 결과가 리턴됨.

- `NULL` 은 비교가 가능한 값이 아님. (`IS`와 `NOT`을 사용하여 NULL인지 아닌지 판명할 것.)

> Tip.
> 기존 테이블에서 SELECT문을 이용하여 새로운 테이블을 만들 수 있다.
> ```sql
>CREATE TABLE <TABLE_NAME>
>AS SELECT * FROM <EXISTING_TABLE>
>WHERE ...
> ```


### 2. 정렬
- `ORDER BY` 로 결과 재정렬
- `ASC` -> 오름차순(default) / `DESC` -> 내림차순
- 정렬 기준 칼럼이름 대신 숫자가 들어갈 수도 있다.(선택한 칼럼 중 n 번째 칼럼을 기준으로 정렬) => 장래에 배제해야 할 기능 목록에 올라가있으므로, 사용을 지양한다.
- 정렬은 alias된 칼럼 이름도 사용 가능.

### 3. 함수
- 산술 함수
    - ABS(수치)) : 절댓값
    - MOD(피제수, 제수) : 나머지 (SQL server에서는 사용 불가)
    - ROUND(대상 수, 반올림 자릿수) : 반올림
- 문자열 함수
    - ||(연결) : 두 문자열을 연결(concat)
    - LENGTH(문자열) : 문자열의 길이 (SQL server에서는 사용 불가)
    - LOWER(문자열) : 소문자화
    - REPLACE(대상 문자열, 치환 전 문자열, 치환 후 문자열) : 문자열 치환.
    - SUBSTR(대상 문자열 FROM 잘라내기 시작 위치 FOR 잘라낼 문자 수) : 잘라내기
    - UPPER(문자열) : 대문자화

- 날짜 함수
    - CUREENT_DATE : 현재날짜(SQL server에서는 사용 불가)
    - CURRENT_TIME : 현재 시간(SQL server에서는 사용 불가)
    - EXTRACT(날짜 요소(YEAR, MONTH 등) FROM 날짜) : 날짜 요소 추출
    
- 변환함수
    - CAST(변환 전 값 AS 변환할 데이터형) : 타입 변환
    - COALESCE(데이터1, 데이터2, ....) : NULL을 값으로 변환.
    - TO_CHAR(데이터) : 데이터를 문자열로 바꿀 때 사용(특히, 날짜 데이터)
    - NVL(칼럼, 대체 값) : 해당 칼럼의 NULL인 값을 대체값으로 대체한다
    - NVL2(칼럼, 대체 값1, 대체 값2) : 해당 칼럼의 NULL인 값을 대체값1로, NULL이 아닌 값을 대체 값2로 대체한다.

- 술어
    - LIKE : 문자열의 부분일치를 검색할때 사용.
    - BETWEEM A AND B : A 와 B 사이의 값(양 끝단 포함.)
    - IS NULL, IS NOT NULL
    - IN 술어(OR의 간략버전) : 값이 범위 안에 있는지 판별.
    
- CASE 식
    ```sql
    SELECT ENAME,
    CASE WHEN JOB = 'PRESIDENT' -- 조건식
        THEN '왕'
        WHEN JOB = 'MANAGER'
        THEN '관리자'
        ELSE '직원' -- default
    END /* 필수 */ AS GRADE -- ALIAS
    FROM EMP;
    ```

