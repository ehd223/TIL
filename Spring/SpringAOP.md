Spring AOP
=========

> 날짜 : 19.07.03

-----

## Spring AOP

**AOP(Aspect Oriented Programming)** : 관점 지향 프로그래밍
-> 관심사를 분리하여 모듈의 응집도를 높이는 것이 목표

나오게 된 이유 : 서비스의 비즈니스 로직 (핵심 관심 Core Concerns)을 부가적인 기능 (횡단관심 - Crosscutting Concerns)과 분리시켜 응집도를 높이고 유지보수를 편하게 하려하기 위함.

### AOP 용어 정리
- JoinPoint : 클라이언트가 호출하는 모든 비즈니스 메소드
- PointCut : 필터링된 JoinPoint

### Advice 동작 시점
- Before
    - `<aop:before>`
    - before 설명
- After
    - `<aop:after>` : 
    - `<aop:after-returning>` : 
    - `<aop:after-throwing>` : 
- Around
    - `<aop:around>` : 