Spring II
=========

> 과정 34일차 (19.07.03)

## Annotation
Annotation이란?
- Java 코드에 추가하여 사용할 수 있는 메타데이터의 일종
- JDK 1.5 버전 이상에서 사용 가능
- Annotation은 클래스 파일에 삽입되어 컴파일러에 의해 생성된 후 자바 가상머신에 포함되어 작동한다.

Annotation의 기능
- 컴파일러에게 코드 문법 에러를 체크하도록 정보 제공
- 소프트웨어 개발 툴이 빌드나 배치 시 코드를 자동으로 생성할 수 있도록 정보 제공
- 실행 시(런타임 시) 특정 기능을 실행하도록 정보 제공
---
### Spring에서의 Annotation
XML에 Context 관련 네임스페이스를 추가하여 어노테이션을 이용한 bean등록 및 의존성 주입이 가능하다.

**component-scan 설정**
bean을 하나하나 등록하지 않고, 패키지를 스캔하여 자동으로 생성하기 위한 Element
```xml
<context:component-scan base-package="com.example"></context:component-scan>
<!-- com.example 패키지를 스캔하여 bean을 자동으로 등록 -->
```

### Bean 등록하기
- @Component
`<context:component-scan>`을 이용하여 스캔할 때 bean으로 등록할 대상 Class를 지정하는 annotation

> Component 외에도 많은 annotation이 bean 등록을 지원한다.
자세한건 해당 파트에 가서 정리하는걸로...


```java
@Component("ltv") // 괄호 안은 bean의 id 지정
public class LTV implements TV{
    public LTV(){
        ...
    }
}
```

> id나 name 속성 미지정 시 Lookup할 때 이름은 클래스 이름의 앞 글자를 소문자로 바꾸면 됨. (ex. `class LTV` -> `lTV`)

### 의존성 주입 설정
의존성 주입을 지원하는 Annotation들
- @Autowired
해당 변수 위에 설정하여 해당 타입의 객체를 찾아서 자동으로 할당(Spring 기본 제공)
- @Qualifier
특정 객체의 이름을 이용하여 의존성 주입 시 사용 (Spring 기본 제공)
- @Inject
@Autowired와 동일한 기능 (Spring 기본 X)
- @Resource
@Autowired와 @Qualifier의 기능을 결합 (Spring 기본 X)

의존성 주입에 있어서 XML 설정과 Annotation 설정은 장단점이 상충함.

- XML
    - Java 코드를 수정하지 않고, XML만 변경하면 의존관계를 변경 가능. -> 유지보수가 편함.
    - XML 설정 자체의 복잡함, 부담
    - Java 코드에 의존관계와 관련된 어떠한 메타데이터가 없으므로 XML 설정을 해석해야 의존성 확인 가능.
- Annotation
    - XML 부담 X
    - 의존관계에 대한 정보를 Java 코드에서 확인 가능. -> 사용하기 편함
    - 의존관계 변경 시 Java 코드를 변경해야함.

#### 결론
**둘 다 섞어서 쓰자.**

변경되지 않는 객체는 **Annotation** 설정 -> 쉽게 작성 가능
변경될 가능성이 있는 객체는 **XML** 설정 -> 유지보수 편함

> 라이브러리 형태로 제공되는 클래스는 반드시 XML 설정을 통해서만 사용 가능

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