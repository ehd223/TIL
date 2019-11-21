객체지향(OOP)
=========

> 과정 8일차 (19.05.22)

### 1. 객체지향이란
- 객체지향의 기본 개념 : "실제 세계는 사물로 이루어져 있으며, 발생하는 모든 사건들은 사물간의 상호작용이다"
- 객체지향의 등장배경 : 프로그램의 규모가 커지고 사용자들의 요구가 빠르게 변화하는 상황
- 필요한 세 가지 관점
  1. 재사용성
  2. 유지보수
  3. 중복된 코드의 제거

### 2. 클래스
- 클래스
  - 객체를 정의해 놓은 것.
  - 클래스는 객체를 생성하는데 사용된다.

- 객체
  - 실제로 존재하는 것.
  - 용도 : 객체가 가지고있는 기능과 속성에 따라 다름.

- 현실에서 객체(프로그래밍)으로 옮기는 순서
  1. Requirement Analysis(Request For Proposal)
  2. OOAD(Object Oriented Analysis & Design)
  3. OOP(Object Oriented Programming)

> eclipse의 `source > generate..` 기능을 사용하면 일반적인 getter setter, tostring 등을 쉽게 만들 수 있다.

> 메모리 구조 상 클래스의 인스턴스와 속성(멤버변수)는 Heap에 생성됨.(인스턴스 별로 독립적으로 생성) // 클래스의 메소드는 메소드 영역에 생성되고, 같은 코드를 공유하기 때문에 하나만 생성됨.


### 3. OOP의 키워드
- __Encapsulation__
  - 객체의 속성은 생성된 시점부터 외부 접근을 막아야함.
  - 접근 제한자
    - public
    - private
    - protected
- Inheritance
- Polymorphism
- Abstraction
- Override
- Overload