Javascript 1
=========

> 과정 23일차 (19.06.18)

자바스크립트의 표준 : ECMA(European Computer Manufacturer's Association)

### 변수

#### 자료형
primitive type
- 수치형(Number) : 정수, 실수
- 문자열(String) : 문자열
- 부울형(Boolean) : true or false
- undefined : 값이 정해지지 않은 상태
- Null
- Symbol

>`null` vs `undefined`
> null은 명시적으로 값이 비어있는 상태를 나타낼 때.
> undefined는 초기화가 되지 않은 상태(모든 변수의 초기값이 undefined라고 생각하면 편함.)
> `typeof(null)`은 object 이고, `typeof(undefined)`는 undefined 이다.
> ==를 사용하면 true가 나오기 때문에 엄격한 검사를 위해 ===를 사용해야한다.

그 외 -> 전부 Object형

**형 변환**
문자열 -> 숫자
- `parseInt`
- `parseFloat`

