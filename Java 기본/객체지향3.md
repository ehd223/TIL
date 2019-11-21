객체지향(OOP) III
=========

> 과정 11일차 (19.05.27)


### 1. 상속
- Method Overriding이 필요한 경우와 필요하지 않은 경우를 잘 구분해야 한다.
- 추상화를 잘 하는 사람이 설계를 잘 하는 사람.
- 자바는 단일 상속만 지원한다.
- Static으로 선언된 멤버(메소드 혹은 속성)는 override할 수 없다.
```java
// 실체가 없고 구체화를 해야하므로 추상클래스
// 반드시 override를 해야 하는 메소드가 있다면 추상 메소드
class Transportation{
    ...
}

class Bus extends Transportaion{
    ...
}

class MBus extends Bus{
    ...
}
```

- 추상 클래스는 추상 메소드도 가질 수 있지만, 그렇지 않은 메소드도 가질 수 있다.
```java
public abstract class Shape{
    protected Point point;
    ...
    public abstract double getArea();
    public String toString(){
        return "Shape [point=]" + point + "]";
    }
}

/////

// cannot instantiate the type Shape 에러
// 추상 클래스는 그 자체로 객체 구현은 불가능하고,
// 구체화 된 클래스로만 구현 가능.
Shape s = new Shape(new Point(10, 10));
```

> 자바의 모든 상위클래스는 java.lang.Object 이다.

- 알아야 할 키워드
    - Heterogeneous Collection
        - 다양한 타입의 객체를 한 곳에 모으는 것.
    - Polymorphism(다형성)
        - 하나의 클래스를 여러 다른 형태로 구현 가능.
        - 다양한 자료형에 속하는 것이 허가되는 성질(from wiki)
        - 상속을 통해 기능을 확장하거나, 변경하는 것을 가능하게 해주고
        같은 클래스 내의 코드의 길이도 줄일 수 있음.
        ```java
        // Heterogeneous collection
        Shape shape[] = new Shape[3];
        
        shape[0] = new Circle();
        shape[1] = new Rectangle();
        shape[2] = new Triangle();

        // Polymorphism(다형성)
        for(Shape s : shape){
            sh.toString();
            sh.getArea();
            sh.getCircum();
        }
        ```
    
    - Specialization
        - 상속받은 클래스만 특별한 멤버 혹은 메소드를 가지고있는 경우.
    <br>
    > `inctance of` 연산자는 java에만 있는 독특한 기능으로 다형성으로 생성된 객체의 인스턴스가 실제 어떤 클래스의 객체인지 확인 가능.

