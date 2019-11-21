Java Reflection 정리
=========

> 날짜 : 19.09.09

## Reflection 정의

**Reflection이란?**
- 프로세스가 스스로 자신의 구조나 동작을 시험, 조사, 수정 할 수 있는 능력이다(위키백과)
- Reflection 아키텍처 패턴은 소프트웨어 시스템의 구조와 동작을 동적으로 변경하기위한 메커니즘을 제공한다. 형식 구조 및 함수 호출 메커니즘과 같은 기본적인 측면의 수정을 지원한다.
- 메타 레벨에서 선택한 시스템 속성에 대한 정보를 제공하고 소프트웨어를 자동 인식한다. 기본 레벨에는 응용 프로그램 논리가 포함된다. 구현은 메타 수준을 기반으로 한다. 메타 수준에 보관 된 정보를 변경하면 후속 기본 수준 동작에 영향을 준다.

=> 프로세스 실행 중에 동적으로 자기 자신의 구조나 행위에 대한 조작이 가능한 것을 의미.

## Java Reflection

- 자바에서 리플렉션은 클래스의 구체적인 타입을 알지 못하더라도 그 클래스를 런타임에 알아내어 사용할 수 있는 기법을 의미
- 유연성을 제공하는 강력한 도구
- Reflection을 통해 런타임에 얻을 수 있는 정보
  - ClassName
  - Class Modifiers (public, private, synchronized 등)
  - Package Info
  - Superclass
  - Implemented Interfaces
  - Constructors
  - Methods
  - Fields
  - Annotations

Reflection을 이용한 간단한 예시
```java
import java.io.*;
import java.lang.reflect.*;
 
public class ReflectionExample
{
  public static void main(String args[])
  {
    try{
        // Class 클래스를 이용하여 이름으로 원하는 클래스를 찾음.
        Class carClass = Class.forName("Car");
        // carClass의 생성자 중 String 하나를 인자로 받는 생성자 획득
        Constructor constructor = carClass.getConstructor(
            new Class[] { String.class }
        );
        // carObj을 Object 클래스로 생성
        Object carObj = constructor.newInstance("venue");
        // method 찾기
        Method method = carClass.getMethod("getCarName");
        // 가져온 method를 실행 할 객체(carObj)를 인자로 넘겨 해당 리턴값을 String으로 캐스팅하여 차 이름 가져오기
        String carName = (String)method.invoke(carObj);
    
        System.out.println("My new car is "+ carName);
    }
    catch (Exception ex)
    {
        ex.printStackTrace();
    }
  }
}
```
### Reflection을 이용한 필드 값 획득(Get)과 변경(Set)

1. `Method` 클래스를 순회하며 찾는 방법
2. `Field` 클래스의 `.get()`, `.set()` 메소드를 이용하는 방법
3. `PropertyDescriptor`를 이용하는 방법

1번 방법 예시
```java
// readFromFile 이라는 메소드를 이용하여 파일을 파싱하여 doc 객체를 반환한다고 가정
Document doc = readFromFile();
Field field = doc.getClass().getDeclaredField("title");
// private 또는 protected 필드에 대한 접근을 허용
field.setAccessible(true);
Object attr = field.get(doc);
System.out.println(attr.toString());
```

2번 방법 예시
```java
Document document = readFromFile();
// Document에 개발자가 선언된 메소드를 순회하면서
for(Method method : doc.getClass().getDeclaredMethods()) {
    // 'get'으로 시작하고 'Title'로 끝나는 (ex. getTitle)메소드를 찾아서 실행.
    if(method.getName().endsWith("Title") && method.getName().startsWith("get")) {
        Object attr = method.invoke(doc);
        System.out.println(attr.toString());
    }
}
```

3번 방법 예시
```java
Document doc = new Document(1234,"The Greatest Show Man", "1234123123");

PropertyDescriptor pd = null;
try {
    // doc 객체의 title 이라는 속성을 기술하는  PropertyDescriptor 객체를 생성
    pd = new PropertyDescriptor("TITLE", doc.getClass());
    
    Method getter = pd.getReadMethod();
    String getResult = (String) getter.invoke(doc);
    
    System.out.println("Get Result : " + getResult + "\n");
    
    Method setter = pd.getWriteMethod();
    setter.invoke(doc, "This is me");
    
    System.out.println(doc);
    
} catch (IllegalAccessException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
} catch (IllegalArgumentException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
} catch (InvocationTargetException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
} catch (IntrospectionException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}
```

1. 1번 방법은 getter나 setter 메소드를 이용하는 것이 아니라, 멤버의 접근을 허용하는 과정(`setAccessible(true)`)이 필요할 수 있다. 
2. 2번 방법은 객체에 선언된 getter, setter 메소드를 이용하여 해당 메소드를 실행하는 것이다. 순회 + 메소드 이름 비교를 하기 때문에 필드의 이름이 길거나, 선언된 메소드가 많을 경우 상대적으로 느려진다
3. 3번 방법은 위의 두 가지 방법과는 조금 다른 접근방법임.<br>java.beans 패키지에서 제공하는 `PropertyDescriptor`를 이용하여 객체의 특정 속성을 기술하는 객체를 생성하여, 접근자 메소드(getter, setter)를 `Method` 타입의 객체로 리턴하여 사용할 수 있게 한다.

> DBtoFile의 TaggedFormat output을 하는 과정에서 테스트해본 결과, 실행 속도는 2 > 1 > 3의 순서로 Field를 이용하는 방법이 가장 빨랐음.
> <br><br>그러나 어디까지나 속도적인 측면에서의 얘기이고, 이 외에도 다양한 방법이 있는 만큼 구현하고자 하는 시스템에 알맞는 방법을 사용하는 것이 중요하다고 생각함.

