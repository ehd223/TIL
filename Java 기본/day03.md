조건문, 반복문
=========

> 과정 3일차 (19.05.15)

### 1. 조건문(if, switch)

- 간단한 변수 assign 정도는 삼항연산자가 깔끔하다.
- else if를 쓰는 경우 마지막을 else로 마무리하는 것이 실수를 방지할 수 있다.

  > **Math 클래스를 비롯한 java.lang 패키지 내부에 있는 클래스 import 하지 않아도 기본적으로 로드되어있다.

- switch 문의 expression에는 결과가 int형인 표현식 또는 문자열만 들어갈 수 있다. (char 형도 가능은 하지만, case에는 int형으로만 써야함)
- case문의 값은 정수 상수만 가능하며, 중복되지 않아야 한다.(변수 불가, 실수 불가.)
<pre><code>
char a = 'c';
char c = 'g';
switch(a){
  // 변수이기 때문에 불가능
  case c:

  // 가능 문자 상수
  case 'c':

  // 가능 정수 (ascii 코드 표에서 "c" 는 decimal 값으로 99)
  case 99 :
  .
  .
}
</code></pre>

- switch 문은 주로 특정 결과가 정해진 정수형 결과값을 분기할 때 사용됨.(ex. 관리자 권한 컨트롤 등)
- 여러 case를 한 번에 묶어서 다루어야 할 경우 switch문이 아닌 if문을 쓸 것.
> 아래는 switch 문 활용의 좋지 않은 예시
<pre><code>int month = 2;
int lastDayOfMonth = 0;
// 해당 월의 마지막 일자를 출력하시오.

// 딱 봐도 if 쓰는게 훨씬 간결함.
switch(month) {
case 1:
case 3: 
case 5: 
case 7: 
case 8: 
case 10: 
case 12: lastDayOfMonth = 31;
break;

case 4: 
case 6: 
case 9: 
case 11: lastDayOfMonth = 30;
break;

case 2: lastDayOfMonth = 28;
break;

default: lastDayOfMonth = -1;
break;

}

System.out.printf("해당 월의 마지막 일자는 %d", lastDayOfMonth);

</code></pre>

> Random의 두 가지 방법<br>
> 1. Math.random() => double형으로 0.0 ~ 1.0 사이의 값을 리턴함.`(Math.random()*20)+1` 을 수행하면 최댓값 20, 최솟값 1
> 2. import java.util.Random => Random 객체를 이용하여 type에 맞게 최댓값 지정 후 random한 값 리턴
---
### 2. 반복문(forwhile)

- for 문이 반복되는 이유
  - 첫 번째 인자 : iterator 초기화 (executed one time before the execution of the code block)
  - 두 번째 인자 : 조건식(boolean) -> True일 때 실행 (defines the condition for executing the code block)
  - 세 번재 인자 : 증감식 -> loop 이후 iterator 처리 (is executed(every time) after the code block has been executed.)
<br>
- for 문에서 iterator가 하나라는 법은 없다.
<pre><code>// i와 j 두 개의 iterator가 돌게된다.
for(int i=0, j=15; (i<=10 || j>=6); i++, j--){

}
</code></pre>

- iterator의 선언은 for 문 밖에 하는 경우도 알아두어야 한다.
<pre><code>// for문 밖에서 선언 및 초기화를 해도 상관 X
int i = 0, j = 15;
for(; (i<=10 || j>=6); i++, j--){

}
</code></pre>

- while로 괜히 2중 루프 짜지 말자.(중첩된 반복문의 변수 혹은 iterator 관리가 어려울 수 있음.)
