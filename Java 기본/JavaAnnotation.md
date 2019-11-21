Java Annotation 정리
=========

> 날짜 : 19.09.09

그동안 대충 얼버무리면서 넘어갔던 Annotation의 개념에 대해 정확하게 짚고 넘어가려고 한다.

## Annotation이란?
- 클래스, 인터페이스, 메소드 또는 필드와 함께 **메타데이터**를 나타내는 태그로, Java 컴파일러 및 JVM에서 사용할 수 있는 추가적인 정보를 나타냄
- XML 및 Java 마커 인터페이스를 위한 대체 옵션
- 어노테이션이 붙은 코드는 어노테이션의 구현된 정보에 따라 연결되는 방향이 결정된다. 따라서 비즈니스 로직에는 영향을 주지는 않지만 **해당 타겟의 연결 방법이나 소스코드의 구조를 변경할 수 있다**.

## Java 기본 제공 Annotation

기본
1. @Override
   - 선언한 메서드가 오버라이드 되었다는 것을 나타냄
   - 만약 상위(부모) 클래스(또는 인터페이스)에서 해당 메서드를 찾을 수 없다면 컴파일 에러를 발생시킴
2. @Deprecated
   - 해당 메서드가 더 이상 사용되지 않음을 표시
   - 만약 사용되면, 컴파일 에러를 발생시킴
3. @SuppressWarnings
   - 선언한 곳의 컴파일 경고를 무시
4. @SafeVarargs
   - Java7 부터 지원하며, 제너릭 같은 가변인자의 매개변수를 사용할 때의 경고를 무시
5. @FunctionalInterface
   - Java8 부터 지원하며, 함수형 인터페이스를 지정하는 어노테이션
   - 만약 메서드가 존재하지 않거나, 1개 이상의 메서드(default 메서드 제외)가 존재할 경우 컴파일 에러를 발생시킴

메타
1. @Retention
   - 자바 컴파일러가 어노테이션을 다루는 방법을 기술
   - 특정 시점까지 영향을 미치는지를 결정함
   - 종류
     - RetentionPolicy.SOURCE : 컴파일 전까지만 유효. (컴파일 이후에는 사라짐)
     - RetentionPolicy.CLASS : 컴파일러가 클래스를 참조할 때까지 유효.
     - RetentionPolicy.RUNTIME : 컴파일 이후에도 JVM에 의해 계속 참조가 가능. (리플렉션 사용)

2. @Target
   - 어노테이션이 적용할 위치를 선택
   - 종류
     - ElementType.PACKAGE : 패키지 선언
     - ElementType.TYPE : 타입 선언
     - ElementType.ANNOTATION_TYPE : 어노테이션 타입 선언
     - ElementType.CONSTRUCTOR : 생성자 선언
     - ElementType.FIELD : 멤버 변수 선언
     - ElementType.LOCAL_VARIABLE : 지역 변수 선언
     - ElementType.METHOD : 메서드 선언
     - ElementType.PARAMETER : 전달인자 선언
     - ElementType.TYPE_PARAMETER : 전달인자 타입 선언
     - ElementType.TYPE_USE : 타입 선언
3. @Documented
   - 해당 어노테이션을 Javadoc에 포함
4. @Inherited
   - 어노테이션의 상속을 가능하게 함
5. @Repeatable
   - Java8 부터 지원하며, 연속적으로 어노테이션을 선언할 수 있게 함

## Custom Annotation

DBnFile 실습을 하면서 실제로 사용한 코드를 예시로 작성함.

### Annotation 선언
```java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
class @interface Order{
    int value() default 0;
}
```
- interface를 선언하는데 앞에 @(at)을 붙여 선언함. (interface 그런데 이제 @를 곁들인)
- 멤버 변수는 위와 같이 `타입 변수명() default x;` 의 형태로 작성.
- Target 으로 해당 annotation이 어떤 타입에 적용이 될 것인지 선택
- Retention 으로 컴파일 타임에도 참조하는지(RetentionPolicy.RUNTIME) 를 지정

### Annotation 사용

getter들을 `getDeclaredMethods()` 를 이용하여 가져올 때 순서를 보장하지 않아 insert문에 매핑을 하기 어려웠음. 

이를 해결하기 위하여, method에 `@Order` annotation을 붙여 가져온 메소드의 리스트를 정렬하여 Query에 맞게 순서를 정해준다.

#### Annotation을 부착한 클래스
```java
public class Document {
	// members
	private int DOC_SEQ;
	private String TITLE;
	private String REG_DT;

	// constructors
	public Document() {

	}

	// getter & setter
	@Order(value = 1)
	public int getDOC_SEQ() {
		return DOC_SEQ;
	}

	@Order(value = 2)
	public String getTITLE() {
		return TITLE;
	}

	@Order(value = 3)
	public String getREG_DT() {
		return REG_DT;
	}
}
```

#### Annotation을 이용하여 정렬
```java
// Document 클래스의 메소드를 가져온 후 정렬
Method[] methods = this.getClass().getDeclaredMethods();
Arrays.sort(methods, new Comparator<Method>() {
    @Override
    public int compare(Method m1, Method m2) {
        Order or1 = m1.getAnnotation(Order.class);
        Order or2 = m2.getAnnotation(Order.class);

        if (or1 != null && or2 != null) {
            return or1.value() - or2.value();
        } else if (or1 != null && or2 == null) {
            return -1;
        } else if (or1 == null && or2 != null) {
            return 1;
        }
        return o1.getName().compareTo(o2.getName());
    }
});
```

## Annotation과 Reflection

기본 제공 어노테이션인 `@Retention` 을 이용하면 런타임에도 컴파일러가 어노테이션을 계속 참조할 수 있게 된다.(`RetentionPolicy.RUNTIME` 옵션)

이를 Reflection 패턴과 함께 이용하여 보다 객체지향적이고 유연한 코드를 작성할 수 있다.

#### Reflection using Annotation 예시
```java
// method를 순회하면서
Method[] methods = doc.getClass().getDeclaredMethods();
for(Method method : methods) {
    // Order를 어노테이션으로 갖는 메소드를 실행시킨다.
    if(method.getAnnotation(Order.class) != null)
        method.invoke(doc, "msg");
}
```


참고
- https://elfinlas.github.io/2017/12/14/java-annotation/
- http://www.nextree.co.kr/p5864/