시큐어 코딩 가이드 정리
=========

> 날짜 : 20.01.10

전자정부 SW 개발을 위한 Java 시큐어코딩 가이드를 읽고 정리.

---

# 개요

## 배경
소프트웨어 개발보안은 보안약점을 최소화하고, 안전한 SW를 개발하기 위한 일련의 보안활동.
소스코드 구현단계에서 보안약점을 배제하기 위한 '시큐어코딩'을 의미한다.

**Java 기반의 시큐어코딩 기법을 예제 위주로 제시함.**

# 시큐어 코딩 가이드

## 1. 입력 데이터 검증 및 표현
외부 입력 값의 검증누락 등으로 인해 발생되는 보안약점.

예상공격
  - SQL 삽입(SQL Injection)
  - 크로스사이트 스크립트(XSS)

### 1. SQL 삽입(SQL Injection)
#### 정의
입력 폼이나 url 입력란에 SQL을 삽입하여 DB로부터 정보를 열람 또는 조작할 수 있는 보안약점.

#### 안전한 코딩기법
- preparedStatement 클래스를 이용하여 외부 인자를(특히 String) 상수 스트링으로 생성하여, 외부 입력이 쿼리의 구조를 바꾸는 것을 방지해야함.
- makeSecureString 함수를 이용하여 parameter의 길이를 제한하거나, 정규식을 이용해 예약어의 삽입을 제한해야 함.

#### 예제
- preparedStatement 이용
```java
    // 안전하지 않은 코드의 예 (name 자리에 `OR + 참을 갖는 값`이 들어가면 조작된 쿼리를 생성하는 문자열 전달이 가능함.)
    try{
        String query = "SELECT * FROM " + tableName + " WHERE Name = " + name;
        stmt = con.preparedStatement(query);
        rs = stmt.executeQuery();
    } catch (SQLException sqle){

    }....

    // 안전한 코드의 예 (setXXX 메소드를 통해 외부 입력이 쿼리의 구조를 바꾸는 것을 제한.)
    try{
        String query = "SELECT * FROM ? WHERE Name = ? ";
        stmt = con.preparedStatement(query);
        stmt.setString(1, tableName);
        stmt.setString(2, name);
        rs = stmt.executeQuery();
    } catch (SQLException sqle){

    }
```
- 인자 길이제한 및 정규식 이용
```java
    // makeSecureString 함수의 예시
    private final static int MAX_USER_ID_LENGTH = 8;
    private final static int MAX_PASSWORD_LENGTH = 16

    private final static String UNSECURED_CHAR_REGULAR_EXPRESSION =
"[^\\p{Alnum}]|select|delete|update|insert|create|alter|drop";

    private String makeSecureString(final String str, int maxLength){
        String secureStr = str.substring(0, maxLength);
        Matcher matcher = unsecuredCharPattern.matcher(secureStr);
        return matcher.replaceAll("");
    }
```

### 2. 자원 삽입
#### 정의
외부 입력 값의 검증누락 등으로 인해 시스템 자원에 대해 발생되는 보안약점.

#### 안전한 코딩기법
- 외부의 입력을 자원(파일, 소켓의 포트 등) 식별자로 사용하는 경우, 적절한 검증을 거치도록 하거나 사전에 정의된 리스트에서 선택되도록 작성.
- 입력이 파일명인 경우, 경로순회를 수행할 수 있는 문자를 제거

#### 예제
- 포트번호 지정
```java
// 안전하지 않은 예 (포트번호를 직접 입력받는 경우 서버의 사용중 혹은 의도치 않은 포트를 사용할 수 있음)
String service = props.getProperty("Service No");
int port = Integer.parseInt(service);
....

// 안전한 예 (포트를 사용자로부터 꼭 입력받아야 하는 경우에만 허용된 리스트 내에서 할당하도록 작성)
String service = props.getProperty("Service No");
if(service.eqauls(""))
    service = "8080";

int port = Integer.parseInt(service);
switch(port){
    case 1:
        port = 3001; break;
    case 2:
        port = 3002; break;
    case 3:
        port = 3003; break;
    default:
        port = 3000;
}
....
```

- 서버 소켓접속
```java
// 안전하지 않은 예 (ip를 직접 입력받는 경우. 서버ip를 피싱사이트 등의 주소로 값을 지정하면 본래 의도와 다른 동작을 허용할 수 있음.)
SocketClient c;
String ip = args[0];
int port = 7777;

try{
    c = newSocket(ip, port);
} catch(IOException e){
    ...
}
....
// 안전한 예 (포트를 사용자로부터 꼭 입력받아야 하는 경우에만 허용된 리스트 내에서 할당하도록 작성)
SocketClient c;
String ip = args[0];
int port = 7777;

try{
    if(ip.equals("127.0.0.1")){
        c = newSocket(ip, port);
        c.startSocket();
    }
} catch(IOException e){
    ...
}
....
```

### 3. 크로스 사이트 스크립트
#### 정의
웹 페이지에 악의적인 스크립트를 포함시켜 사용자 측에서 실행되게 유도하는 공격방법

#### 안전한 코딩기법
- 일반적인 경우, 사용자가 입력한 문자열 중 <,>,&," 등을 replace등의 문자 변환 함수나 메소드를 사용하여 &lt, &gt, &amp, &quot로 치환.
- HTML 태그를 허용하는 게시판에서는 게싶나에서 지원하는 태그의 리스트를 선정한 후 해당 태그만 허용
- 보안성이 검증되어 있는 API를 사용하여 위험한 문자열을 제거.

### 4. 운영체제 명령어 삽입
#### 정의
입력값이 운영체제 명령어의 일부 또는 전부로 구성되어 실행되는 경우, 적절한 처리를 하지 않으면 공격자가 원하는 명령어 실행이 가능하다.

#### 안전한 코딩기법
- 시스템 명령어를 서버 내부로 전달시키지 않도록 구성, 외부에서 전달되는 값 그대로 시스템 내부 명령어로 사용하지 않는다.
- 외부 입력에 따라 명령어를 생성하거나 선택이 필요한 경우에는 명령어 생성에 필요한 값들을 미리 지정해놓고 외부 입력에 따라 선택하여 사용.

### 5. 위험한 형식 파일 업로드
#### 정의
서버에서 실행될 수 있는 스크립트 파일이 업로드가 가능하다면, 이 파일을 통해 시스템 내부명령어를 실행하거나 외부와 연결하여 시스템을 제어할 수 있다.

#### 안전한 코딩기법
- 업로드하는 파일 타입과 크기를 제한하고, 업로드 디렉토리를 웹서버의 Document 외부에 설정.
- 화이트리스트 방식으로 허용된 확장자만 업로드되도록 하고, 확장자도 대소문자 구분이 없게 처리하도록 함.
- 공격자의 웹을 통한 직접 접근을 차단. 파일 실행 여부를 설정할 수 있을경우, 실행 속성을 제거


#### 예제
- 파일크기 제한
```java
// 사이즈 제한을 두어 무분별한 파일 업로드를 제한.
int size = file.getSize();
if(size > MAX_FILE_SIZE) throw new ServletException("Non Uploadable file size");
```

- 유효성 검사
```java
// 실행 가능한 파일 타입인지 확인 후 에러 처리
if(extractFileType(file) == EXECUTABLE_FILE_TYPE){
    throw new ServletException("Non Uploadable file type");
}
```

### 6. 신뢰되지 않는 URL 주소로 자동 접속 연결
#### 정의
사용자로부터 입력되는 값을 외부사이트의 주소로 사용하여 연결하는 서버 프로그램은 피싱 공격에 노출되는 취약점을 가짐.
공격자는 요청을 변조하여 사용자가 위험한 URL로 접속할 수 있도록 공격할 수 있다.

#### 안전한 코딩기법
자동 연결 외부 사이트의 URL과 도메인은 화이트 리스트로 관리.
사용자 입력값이 화이트 리스트에 존재하는지 확인해야 함.

### 7. XQuery 삽입 (XQuery Injection)
#### 정의
XQuery를 사용하여 XML 데이터에 대한 동적 쿼리문을 생성할 때 검증절차가 없으면 쿼리의 구조를 변경할 수 있다.

#### 안전한 코딩기법
XQuery에 사용되는 외부 입력데이터에 대해 특수문자 및 쿼리 예약어를 필터링하고 XQuery를 사용한 쿼리는 스트링을 연결하는 형태로 구성하지 않고 파라미터로 받는 쿼리를 사용한다.

### 8. XPath 삽입 (XPath Injection)
#### 정의
XPath 쿼리 생성을 위한 문자열을 입력으로 그대로 받으면 임의의 쿼리를 실행하여 인가되지 않은 데이터를 열람할 수 있다.

#### 안전한 코딩기법
- XPath 쿼리에 사용되는 외부 입력데이터에 대해 특수문자 및 쿼리 예약어 필터링.
- 인자로 변경된 쿼리를 지원하는 XQuery 사용

### 9. LDAP 삽입(LDAP Injection)
> LDAP란..?
> X.500 이란 전자 디렉토리 서비스를 전달하는 컴퓨터 네트워크 표준이 있다. 
> 이를 정의하는 구성요소 중 몇 개만 살펴보면..
> - DAP(Directory Access Protocol) : DUA와 디렉토리 간 수행되는 프로토콜
> - DUA(Directory User Agent) : 사용자와 디렉토리 간의 인터페이스 역할 수행 프로세스
> - DSA(Directory System Agent) : 디렉토리 내 사용자의 요구 수행 프로세스
> ITU에서 개발한 X.500 디렉토리 서비스 표준은 다양한 쿼리를 이용하여 강력한 검색기능을 제공하며 서버와 데이터의 분산이 용이한 표준이다.
> 그러나, DAP가 방대하고 복잡하여 구현하기 어렵다는 점 때문에 LDAP라는 경량화된 DAP를 개발하였다.
> Directory Server의 프론트엔드로 사용되며, TCP/IP 상에서 운영되어 사용자 혹은 어플리케이션 단에서 요청을 보내면 LDAP서버에 요청을 전달하여 결과를 다시 사용자에게 전달한다.


#### 정의
외부 입력을 통해 의도치 않은 LDAP 명령어를 수행할 수 있음.
프로세스가 명령을실행한 컴포넌트와 동일한 권한을 가지고 동작하게 된다.

#### 안전한 코딩기법
- 위험 문자에 대한 검사 없이 외부 입력을 LDAP 질의 생성에 사용하면 안됨
- DN과 필터에 사용하는 사용자 입력값에는 특수문자가 포함되지 않도록 특수문자를 제거
- 특수문자를 사용해야하면 일반문자로 인식되도록 처리

### 10. 크로스사이트 요청 위조(Cross-Site Request Forgery)
#### 정의
특정 웹사이트에 대해 사용자의 의도와 무관하게 공격자가 의도한 행위를 요청하게 하는 공격.
사용자가 의도한대로 요청이 전송된 것인지 확인하지 않는 경우 발생함
> ex. 피싱 사이트에 허위광고 등을 클릭 시 세션에 유지되고 있는 사용자의 권한으로 (예를 들면, 페이스북) 광고글 작성 API 요청 등.

#### 안전한 코딩기법
- 입력화면 Form 작성 시 GET방식 보다는 POST방식 사용
- 입력화면 Form과 입력 처리 프로그램 사이에 token을 사용하여 공격자의 직접적인 URL 사용이 동작하지 않도록 처리
- 중요한 기능에 대해서는 사용자 세션검증과 더불어 재인증 유도


### 11. 디렉터리 경로 조작
#### 정의
외부의 입력을 통해 디렉터리 경로 문자열 생성이 필요한 경우, 접근 제한 영역에 대한 시스템 정보누출, 서비스 장애 등을 유발할 수 있음.

#### 안전한 코딩 기법
- 외부의 입력이 직접 파일이름을 생성할 수 없도록 한다.
- 디렉터리의 다른 파일에 접근할 수 없도록 위험한 문자열을 치환하도록 한다.
- 미리 정의된 케이스(디렉토리 및 파일 화이트 리스트)를 제외하고는 모두 무시하도록 한다.

### 12. HTTP 응답 분할 (Http Response Splitting)
#### 정의
HTTP 요청에 들어있는 파라미터가 HTTP 응답헤더에 포함되어 사용자에게 다시 전달될 때 Carrige Return 이나 Line Feed 같은 개행이 존재하면 응답이 2개 이상으로 분리될 수 있음.
공격자가 개행문자를 이용하여 두 번째 응답에 악의적인 코드를 주입하여 XSS 및 캐시 훼손 공격 등을 할 수 있음.

#### 안전한 코딩기법
- 외부에서 입력된 파라미터를 HTTP 응답헤더에 포함시킬 경우, Carrige Return 이나 Line Feed 같은 개행을 제거하고 적절한 인코딩 기법을 사용하여 변환한다.
- 적절한 필터링 코드를 통해 파라미터를 필터링한다.

### 13. 정수 오버플로우(Integer Overflow)
#### 정의
정수형 변수의 값이 언어 내에서 허용된 정수의 범위를 벗어나면서 의도하지 않은 작은 값이나 음수가 되는 것.
보안상 문제를 유발할 수 있음

#### 안전한 코딩기법
- 언어/플랫폼 별 정수타입의 범위를 확인하여 사용한다. 정수형 변수를 연산에 사용하는 경우 결과값의 범위를 체크하는 모듈을 사용
- 특히 외부 입력값을 동적으로 할당하여 사용하는 변수의 값 범위를 검사해 적정 범위 내의 값인지 확인

### 14. 보호 메커니즘을 우회할 수 있는 입력값 변조
#### 정의

.....???????