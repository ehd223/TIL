구조 - Adapter
=========

> 날짜 : 2020.05.12

## Adapter 패턴
호환성이 없는 인터페이스 때문에 함께 동작할 수 없는 클래스들이 함께 작동하도록 해주는 패턴. 어댑터 역할을 하는 클래스를 새로 만들어서 구현하며, 기존 시스템의 변경점이 생겨도 클라이언트단의 코드 변경을 최소화할 수 있는 방법이다.

[](../assets/Adapter_Design_Pattern_UML.jpg)

Client에서 Targert 클래스의 메소드를 호출하면, 이를 해당 로직을 담당하는 클래스(Adaptee)로 적절하게 연결하는 역할을 한다.

## 분류
Adapter 패턴은 구현 방법에 따라 두 가지로 분류되는 경향이 있다.
### Class Adapter
클래스 어댑터는 Target 클래스와 Adaptee 클래스를 상속받아 만들어진다. Target과 Adaptee가 사용되는 곳 모두에서 사용될 수 있지만, Java처럼 다중상속을 지원하지 않는 언어에서는 구현하기 어렵다는 문제를 가지고 있다.
### Object Adapter
객체 어댑터는 




## 참고
- https://en.wikipedia.org/wiki/Adapter_pattern
- 