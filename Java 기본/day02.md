JAVA 기초
=========

> 과정 2일차 (19.05.14)

### 1. Formatted printing
`System.out.printf();` 이런 식으로 출력
<pre><code>
int a = 10;
short b = 20;
double d = 123.456789;

// 10진수(decimal)
System.out.printf("\t result = %d, %d\n", a, b);
// 8진수(octal)
System.out.printf("\t result = %o\n", a);
// 16진수(hexa-decimal)
System.out.printf("\t result = %x\n", a);
// 부동 소수점(floating-point)
System.out.printf("\t result = %1.3f \n", d);
</code></pre>

### 2. Scanner
- Scanner는 Java에서 제공하는 표준 클래스 라이브러리
- java.util 패키지에 있음.
- `.nextLine()` 메소드로 다음 개행 전까지 입력받음.
- 그 외 메소드 다수. (`next`, `nextInt`, `next(pattern)` 등)
-

### 3. JVM 메모리구조

JVM 메모리 구조 및 동작 원리에 따로 정리함.

#### - __Stack__
  - 각 스레드마다 하나씩 존재하며, 스레드가 시작될 때 할당됨.
  - 모든 primitive type 변수는 스택에 직접 값을 가진다. 
  - 메소드를 호출할 때마다 프레임을 추가하고 메소드가 종료되면 해당 프레임을 제거 (push, pop)
  - 메소드 호출 시 생성되는 스레드 수행정보를 기록하는 Frame을 저장
  - 메소드 정보, 지역변수, 매개변수, 연산 중 발생하는 임시 데이터 저장

#### - __Heap__
  - JVM이 관리하는 프로그램 상에서 데이터를 저장하기 위해 런타임 시 동적으로 할당하여 사용하는 영역.
  - `new` 연산자로 생성된 객체 또는 인스턴스와 배열을 저장한다.
  - 힙 영역에 생성된 객체와 배열은 스택 영역의 변수나 다른 객체의 필드에서 참조한다.
  - 참조하는 변수나 필드가 없다면 의미없는 객체가 되어 Garbage Collecting의 대상이 된다.
  - 힙 영역의 사용기간 및 스레드 공유 범위
    - 객체가 더 이상 사용되지 않거나 명시적으로 null 선언 시
    - GC(Garbage Coolecting) 대상
    - 구성 방식이나 GC 방법은 JVM 벤더마다 다를 수 있음
    - 모든 스레드에서 공유

#### - __Method(Static) Area__
  - JVM이 읽어들인 클래스, 인터페이스에 대한 런타임 상수 풀, 멤버 변수(필드), 클래스 변수(Static 변수), 생성자와 메소드를 저장

  


### 4. 인코딩, 디코딩
- 아스키
- 확장 아스키
- 코드 페이지
- 유니코드
  - 유니코드 인코딩 방식

### 5. Reference Type
- Java에서 제공하는 라이브러리(Class)

- 모든 Reference Type의 실제 값은 Heap 영역에 각자의 Pool 영역에 생성이되고,
 변수가 갖는 값은 Pool 영역에 생성된 값을 가리키는 주소를 가짐.

- 프로그램 종료 후 Reference Type 변수가 Stack 영역에서 지워짐.
-> Heap 영역에 생성된 실제 Value는 남아있음. -> Garbage Collector가 Heap에 남아있는 값들을 정리


### 6. 연산자
- 비트 연산자(<<, >>)
  - 비트를 좌 우로 옮기는 연산자
  - 잘 안 씀

- 증감 연산자(++, --)
  - 실행 순서에 유의하여 앞, 뒤에 붙일 것

- 비교 연산자

- 특이한 것
  - 정수형을 0으로 나누면 / by zero Exception 발생
  - 실수형을 0으로 나누면 결과는 Infinity(특수숫자)
  - 0.0을 0.0으로 나누면 결과는 NaN(특수숫자)

- Math 관련
  - int 형 변환을 이용하여 버림, 반올림 등 가능
  <pre><code>
  double d = 3.141592;

  // 소수점 3자리 이하 버림
  double d1 = Math.floor(d*1000)/1000.0;
  double d2 = int(d*1000)/1000.0;

  // 소수점 3자리 이하 반올림
  double d3 = Math.round(d*1000)/1000.0;
  double d4 = (int)(d*1000 + 0.5)/1000.0;
  </code></pre>
