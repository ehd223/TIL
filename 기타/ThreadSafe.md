Thread Safe
=========

> 날짜 : 20.02.19

Thread Safe(스레드 안정성)이 병렬 프로그램에서 중요한 이슈인걸 알고있지만, 정확한 개념과 어떻게 Thread safe한 프로그램을 작성할 수 있을지에 대해 실무에 적용하는 알기 위해 하는 정리.

## Thread Safe
### 사전적 정의
thread-safe에 대해 wikipedia에서는 다음과 같이 정의하고 있다.

> **스레드 안전**(thread 安全, 영어: thread safety)은 멀티 스레드 프로그래밍에서 일반적으로 어떤 함수나 변수, 혹은 객체가 여러 스레드로부터 동시에 접근이 이루어져도 프로그램의 실행에 문제가 없음을 뜻한다. 보다 엄밀하게는 하나의 함수가 한 스레드로부터 호출되어 실행 중일 때, 다른 스레드가 그 함수를 호출하여 동시에 함께 실행되더라도 각 스레드에서의 함수의 수행 결과가 올바로 나오는 것으로 정의한다.
> 
> **Thread safety** is a computer programming concept applicable to multi-threaded code. Thread-safe code only manipulates shared data structures in a manner that ensures that all threads behave properly and fulfill their design specifications without unintended interaction. There are various strategies for making thread-safe data structures.

그래서 만약, "그래서 이 코드는 thread safe한거야?" 라고 질문하는 것은 "그래서 이 코드는 특정 상황에서 문제없이 동작하는거야?" 라는 질문이 되는 것이다. (특정 상황이란 동일 자원에 여러 개의 스레드가 접근하는 상황)
그래서, 이 코드가 문제없다는 것을 어떻게 결정할까? 이것에 대한 직접적인 해답은 지금까지 본 정의에서는 나와있지 않다.

### How to achieve thread-safety
내 주력 언어가 Java이니 Java에서 일반적으로 thread-safe한 코드를 작성할 지 간단하게 정리해보았다. 

#### 1. Stateless Implementation

multithreaded application에서 일어나는 에러들 중 대부분은 thread 간에 자원 공유 상태를 올바르게 다루지 않아 발생하게 된다.
그러므로, stateless한 구현을 우선적으로 고려할 필요가 있다.

예를 들어, 주어진 숫자의 차수를 갖는 factorial을 계산하는 정적메소드가 있다고 하자.
```java
public class MathUtils {
    
public static BigInteger factorial(int number) {
        BigInteger f = new BigInteger("1");
        for (int i = 2; i <= number; i++) {
            f = f.multiply(BigInteger.valueOf(i));
        }
        return f;
    }
}
```

factorial 메소드는 상태가 없고 결정론적인 함수이다.(참고 : 결정론적 알고리즘 - 예측한 그대로 동작하는 알고리즘) 특정 input에 대해 항상 동일한 결과를 출력한다.

이 메소드는 외부상태에 의존하지도 않고, 상태를 유지하지도 않는다. 따라서 이 메소드는 thread-safe이고, 여러 개의 thread에서 동시에 사용이 가능하다.    

#### 2. Immutable Implementations
만약 스레드 간 상태를 공유해야 한다고 하면, immtable한 클래스를 만들어 thread-safe를 만족시킬 수 있다.

Immutability(불변성)은 강력하고, 언어에 구애받지 않는 개념이며 Java에서 꽤 쉽게 구현이 가능하다.

간단하게 생각하자면, 인스턴스는 생성 후에 그것의 내부 멤버가 변경이 불가능하다면 immutable하다고 할 수 있다.

```java
public class MessageService{
    private final String message;

    public MessageService(String message){
        this.message = message;
    }

    // getter, 메소드 등.
}
```

위와 같이 setter를 제공하지 않는 클래스를 만들면 `MessageService`객체는 불변성을 갖게되고 thread-safe하게 사용이 가능하다.

#### 3. Thread-Local Fields
OOP에서 객체들은 사실 멤버변수를 통해 상태를 유지할 필요가 있으며, 하나 이상의 메소드를 통해 동작한다.

만약 상태를 진짜로 유지할 필요가 있다면, **thread-local** 필드를 만들어 스레드 간 상태를 공유하지 않는 thread-safe한 클래스를 만들 수 있다.

Thread 클래스를 상속받아 private 필드를 선언하는 것 만으로도 thread-local 필드를 만들 수 있다.

```java
public class ThreadA extends Thread {
    // thread-local field
    private final List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
    
    @Override
    public void run() {
        numbers.forEach(System.out::println);
    }
}

public class ThreadB extends Thread {
    // thread-local field
    private final List<Integer> letters = Arrays.asList("a", "b", "c", "d", "e", "f");
    
    @Override
    public void run() {
        letters.forEach(System.out::println);
    }
}
```

위 두 개의 클래스는 각자 '상태'를 가지고 있지만, 다른 스레드와 상태를 공유하지 않는다. 그러므로, thread-safe하다고 할 수 있다.

비슷하게 ThreadLocal 객체를 필드에 넣어 thread-local 필드를 만들 수 있다.

```java
public class StateHolder {
    private final String state;

    public StateHolder(String state){
        this.state = state;
    }

    // getter, 메소드들
}

public class ThreadState {
    public static final ThreadLocal<StateHolder> statePerThread = new ThreadLocal<StateHolder>() {
         
        @Override
        protected StateHolder initialValue() {
            return new StateHolder("active");  
        }
    };

    // ThreadLocal 클래스의 사용법은 찾아볼 것.
    public static StateHolder getState(){
        return statePerThread.get();
    }
}
```

Thread-local 필드는 getter와 setter를 통해 각 스레드가 독립적으로 초기화된 필드의 복사본을 얻어 자체적으로 상태를 갖는다는 점을 제외하면 일반 클래스의 필드와 비슷하다.

#### 4. Synchronized Collections
동기화 wrapper들을 이용하여 thread-safe한 콜렉션을 만들 수 있다.

```java
Collection<Integer> syncCollection = Collections.synchronizedCollection(new ArrayList<>());
Thread thread1 = new Thread(() -> syncCollection.addAll(Arrays.asList(1,2,3,4,5,6)));
Thread thread2 = new Thread(() -> syncCollection.addAll(Arrays.asList("a","b","c","d","e","f")));

thread1.start();
thread2.start();
```
synchronized 컬렉션은 각자 메소드에서 고유 로킹(intrinsic locking)을 한다는 것을 명심해야 한다.

이것은 메소드가 한 번에 하나의 스레드에서만 접근 가능하며, 다른 스레드는 unlock될 때까지 접근이 막힌다는 것을 의미한다.
따라서, 동기화는 synchhronized access의 기본적인 로직에 의해 성능상 불이익이 생긴다.

#### 5. Concurrent Collections
synchronized collection을 대체하기 위해 concurrent collection을 사용할 수 있다.
Java에서는 `ConcurrentHashMap`과 같은 동시성을 지닌 collection들을 포함하는 `java.util.concurrent` 패키지를 제공한다.

```java
Map<String,String> concurrentMap = new ConcurrentHashMap<>();
concurrentMap.put("1", "one");
concurrentMap.put("2", "two");
concurrentMap.put("3", "three");
```

synchronized와는 다르게 concurrent collection은 데이터를 분할하는 방식(segment(부분) 잠금 방식)을 이용하여 thread-safety를 구현한다. 예를 들어, `ConcurrentHashMap`에서는 여러 스레드가 서로 다른 Map segment에서 락을 걸 
수 있으므로, 여러 스레드가 하나의 Map에 동시에 접근이 가능하다.

> synchronized collection과 concurrent collection은 collection 자체만을 thread-safe하게 만들 뿐이지 그 내용(요소)까지 thread-safe하게 만들지는 못한다.

#### 6. Atomic Objects

Java에서 제공하는 **Atomic Class**를 이용하여 thread-safe한 코드를 구현할 수도 있다.
Atomic class들은 동기화 없이 thread-safe한 Atomic operation(*원자성* 연산)을 제공한다. Atomic operation은 단 하나의 machine level operation이다.

> atomic : DB에서 트랜잭션의 특성 중 하나인 atomicity(원자성)과 동일하다고 생각하면 된다.
> 하나의 원자 트랜잭션은 모두 성공하거나 모두 실패한다.
> => 하나의 연산은 모두 성공하거나 모두 실패한다.

atomic에 대해 다음의 예제를 보자.

```java
public class Counter {
    private int counter = 0;

    public void incrementCounter() {
        counter += 1;
    }

    public int getCounter() {
        return counter;
    }
}
```

두 개의 스레드가 동시에 `incrementCounter()` 메소드에 접근하여 race condition이 생겼다고 가정해보자.

이론적으로는 최종적으로 counter 필드의 값은 2가 되어야 한다. 그러나, 두 개의 스레드가 동시에 동일한 코드를 실행하고 증가연산(incrementation) 자체는 atomic하지 않기 때문이다.

```java
public class AtomicCounter {
    private final AtomicInteger counter = new AtomicInteger();

    public void incrementCounter() {
        counter.incrementAndGet();
    }

    public int getCounter() {
        return conter.get();
    }
}
```

위처럼 **AtomicInteger**클래스를 이용하여 증가연산(++) 자체는 하나 이상의 연산을 거치지만, `incrementAndGet`은 atomic하게되어 thread-safe하게 구현할 수 있다.

#### 7. Synchronized Methods
지금까지 봤던 접근방법들이 collection과 primitive에 좋은 방법이지만, 동시에 연산자체보다 많은 컨트롤을 요구한다.

thread-safety를 얻기 위한 또 다른 공통적인 접근법은 **synchronized method**를 구현하는 것이다.

간단하게 키워드를 쓰기만 하면, 단 하나의 thread만 synchronized method에 접근이 가능하고 동시에 다른 스레드의 접근은 막힌다. 먼저 들어간 thread가 작업을 끝내거나 예외가 발생하기 전까지 block은 유지된다.

```java
public synchronized void incrementCounter(){
    counter += 1;
}
```

Synchronized method는 "intrinsic locks" 또는 "monitor locks"이라고 불리우는 방법을 사용한다.(각각 고유 락, 모니터 락)
고유 락(Intrinsic lock)은 특정 클래스의 인스턴스와 관련된 암시적 내부 속성이다.(== 모든 객체가 고유 락을 갖고있다.)

스레드가 Synchronized 메소드를 호출하면 그 **스레드는** 고유 락을 얻게 된다. 스레드가 메소드 실행을 종료한 뒤에 락이 해제되므로 다른 스레드가 락을 얻어 메소드에 접근할 수 있다.


#### 8. Synchronized Statement
간단한 몇 줄의 코드를 Thread-safe하게 만들기 위해 하나의 메소드 전체에 동기화를 거는 것은 과도할 때가 있다.

이를 해결하기 위해 statement 단위로 동기화를 거는 것이 가능하다.

```java
public void incrementCounter(){
    // 동기화가 필요없는 statements
    // ..........
    synchronized(this){
        counter += 1
    }
}
```

`incrementCounter()`메소드에 부가적인 기능들이 추가되었다고 가정했을 때, 메소드 전체에 동기화를 거는 것 보다는 객체 내의 공유자원인 counter에 접근하는 부분에 대해서 만 동기화를 걸어 thread-safe하게 만드는 것이 효율적일 수 있다.

동기화의 비용은 만만치않기 때문에, synchronized statement를 적절히 활용하는 것이 중요하다.

#### 9. Volatile Fields
Syncrhnized 메소드와 블록은 스레드 간 변수의 가시성 문제를 해결하는데 편리하다. 그렇다해도, 정규 클래스의 멤버변수는 CPU에 캐시되어 있을 가능성이 높다. 따라서 특정 필드이 대한 후속 업데이트는 동기화된 경우에도 다른 스레드에서 보이지 않을 수 있다.

이러한 상황을 막기 위해 `volatile` 클래스의 필드를 사용할 수 있다.

```java
public class Counter {
    private volatile int counter;

    // 생성자, getter 등
}
```

`volatile` 키워드를 이용하여 원하는 변수를 메인 메모리에 저장하도록 JVM과 compiler에 명령을 할 수 있다. 이렇게하면 JVM이 예시의 counter 변수의 값을 읽을 때마다 CPU 캐시가 아닌 메인 메모리에서 읽어온다. 마찬가지로, JVM이 counter 변수의 값을 쓸 때마다 메인 메모리에 그 값이 기록된다.

게다가 volatile 변수의 사용은 지정된 스레드에 표시되는 모든 변수를 메인 메모리에서도 읽을 수 있다.

```java
public class User {
    private String name;
    private volatile int age;

    // 생성자, getter 등
}
```

이러한 경우에는, JVM은 age 변수를 메인 메모리에 쓸 때마다, volatile이 아닌이 메인 메모리에 name 변수도 메인 메모리에 쓰게된다. 이것은 두 변수의 가장 마지막 값이 메인 메모리에 저장되는 것을 보장하여 이후에 일어나는 값의 업데이트도 자동으로 다른 스레드에서 볼 수 있게된다.

volatile 변수가 제공하는 확장된 보증은 **full volatile visibility guarantee** 라고도 한다.

#### 10. Extrinsic Locking
intrinsic lock 대신 extrinsic monitor lock을 사용하여 Counter 클래스의 thread-safe 한 구현을 개선할 수 있다.

*extrinsic lock(명시적 혹은 외부 락 정도로 해석하면 될 듯)은 또 멀티스레드 환경에서 공유하는 자원에 대해 조정된 액세스를 제공하지만, 외부 엔티티를 사용하여 리소스에 대한 독점 액세스를 강제한다.* 

```java
public class ExtrinsicLockCounter {
    private int counter = 0;
    private final Object lock = new Object(0);

    public void incrementCounter() {
        synchronized(lock) {
            counter += 1;
        }
    }

    // getter 등
}
```

순수한 Object 클래스의 인스턴스를 써서 extrinsic lock을 만들었다. 이 방법이 락의 보안성을 강화시켜 조금 더 나은 방법이다. 

synchronize 키워드를 이용하는 intrinsic locking은 공격자가 intrinsic lock을 얻어서 서비스 거부(Denail of service - DoS) 상태를 유발하여 deadlock을 걸 수 있다.

intrisic lock과는 반대로 extrinsic lock은 밖에서 접근이 불가능한 private 엔티티를 사용한다. 이로 인해 공격자가 락을 얻고 deadlock 상태로 만드는 것이 어려워진다.

#### 11. Reentrant Locks
Java에서는 향상된 Lock의 구현 세트를 제공하며, 그것들의 동작은 위어서 설명한 intrinsick lock보다 더 정교하다(sophisticated).

intrinsic lock의 경우 락을 획득하기 위한 모델은 생각보다 견고하다. 한 스레드가 락을 얻은 후 메소드 또는 코드 블록을 실행한 다음, 마지막으로 락을 해제하여 다른 스레드가 락을 얻고 메소드에 액세스하는 구조이다.

intrinsic lock에는 대기중인 스레드를 확인하고 가장 긴 대기 스레드에 우선순위를 부여하는 기본 메커니즘이 없다. (불공정방법)

> intrinsic lock(synchronized)는 먼저 대기중이던 스레드의 우선순위를 보장하지 않음.

**ReentrantLock**는 이러한 불공정방법을 우선순위를 부여하여 순서를 보장하는 방법으로 해결한다. 또한, 스레드에 *리소스 부족*이 발생하지 않는다.

> Reentrant, Reentrancy
> 재진입이 가능하다는 뜻으로, 락의 획득이 호출 단위가 아닌 스레드 단위로 일어난다는 것을 의미함. 이미 락을 얻은 스레드는 같은 락을 얻기 위해 대기할 필요가 없다.

```java
public class ReentrantLockCounter {
    private int counter;
    private final ReentrantLock reLock = new ReentrantLock(true);

    public void incrementCounter() {
        reLock.lock();
        try {
            counter += 1;
        } finally {
            reLock.unlock();
        }
    }
}
```

`ReentrantLock`의 생성자는 `fairness` 파라미터를 선택적으로 추가할 수 있다. 여러 개의 스레드들이 락을 얻으려는 상태에서 fairness가 true로 되어있다면 JVM은 가장 오래 기다린 스레드에게 우선순위를 주어 락의 접근권한을 부여한다.

> **ReentrantLock** vs **Synchronized**
> 위 두 가지 방식의 가장 큰 차이점은 **lock을 인터럽트가 가능하도록 걸거나 timeout시킬 수 있다는 점**이다. 
> Synchronized로 구현을 하면 락을 획득하는 순서를 보장하지 않는 **불공정성** 만을 가지고있기 때문에 무한정 기다리게 되는 Thread가 생길 수 있다. 
> 물론 lock 획득에 있어서 불공정성이 갖는 장점도 있다. 이는 뒤에서 서술하기로 하고..
> 우선 두 방식의 차이를 좀 더 자세하게 알아보자면..
> 1. ReentrantLock은 명시적인 lock으로 락을 걸 시점과 종료할 시점을 명시해야 한다.
> 2. ReentrantLock은 fairness를 설정할 수 있다.
> 3. 앞서 말했듯 ReentrantLock은 락을 얻기 위해 기다리는 스레드에 interrupt를 걸 수 있다.
> <br>
> 위 차이점만 보면 ReentrantLock이 확실히 다루기 편해보인다.
> 하지만, Oracle의 선임 연구원인 Dave Dice의 글에 의하면 J2SE6 부터는 거의 차이가 없으며, 대부분의 상황에서 이용하기에 두 가지 방법 모두 충분하다고 언급했다.
> 또 상황에 따라 ReentrantLock이 좋을 수도 있고, Synchronized가 좋을 수도 있다고 한다. 지금은 모르겠지만, 상황에 따라 적절한 도구를 선택하면 된다는 뜻이라고 생각한다.



#### 13. Read/Write Locks
Thread-safety를 보장하기 위한 다른 강력한 메커니즘은 `ReadWriteLock` 인터페이스를 사용하는 것이다.

`ReadWriteLock`의 인스턴스는 실제로 읽기 전용 작업과 쓰기 작업을 위한 한 쌍의 락을 사용한다. 그 결과로, 리소스를 쓰는 중인 스레드가 없다면 많은 스레드가 동시에 읽을 수 있다. 또한 리소스를 쓰는 스레드는 다른 스레드가 읽지 못하게 할 수 있다.

```java
public class ReentrantReadWriteLockCounter {
    private int counter;
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    public void incrementCounter() {
        writeLock.lock();
        try {
            counter += 1;
        } finally {
            writeLock.unlock();
        }
    }
     
    public int getCounter() {
        readLock.lock();
        try {
            return counter;
        } finally {
            readLock.unlock();
        }
    }
}
```


### 이거 Thread safe야? (Communication)
Thread-safe에 대한 정의나 이론보다 우리가 궁금한 것은, '어떻게 Thread-safe한 코드를 작성하느냐' 이다. 공통적으로 사용하는 자원에 동시에 접근가능한 스레드가 단 하나만을 허용하는 Locking은 Thread-safe를 만드는 방법 중 하나이며, 스레드 안전성에 대한 정의 그 자체는 아니다.

이러한 식의 정의가 틀리거나 나쁘다는 것이 아니지만, 개념 자체가 모호하며 "어떤 상황에서는 올바르게 작동한다"는 것에 지나지 않는다는 것을 나타낼 뿐이다.
그래므로, "그래서 이 코드는 thread safe한 코드야?" 라는 질문은 "스레드를 사용한다면 *정확히* 고려해야하는게 뭐야?", "스레드를 사용하는 상황에서 객체가 *정확히* 어떻게 행동을 해야 하는거야?" 로 바꾸어 물어보는 것이 좋다.

실제 Thread Safety에 대해 대화를 할 때 사람들이 서로 다른 대답을 생각하는데에서 문제가 발생한다. 
예를 들면, A가 "threadsafe mutable queue"(스레드 안전성 있는 큐 정도로 이해하면 될 듯.) 를 가지고 있고, 다른 곳에서도 사용할 수 있다고 말했다 가정해보자. 
그리자, B는 신나서 그 큐를 다른 스레드가 사용중인 큐에 다음과 같은 코드를 실행시켰다고 해보자.

```c#
if(!queue.IsEmpty) Console.WriteLine(queue.Peek());
```

그리고, 위의 코드가 `QueueEmptyException`를 리턴하며 에러가 났다면?
"threadsafe mutable queue"라면서, multi-thread를 사용하는데 에러가 나버리다니..

A가 말한 의도는 다른 스레드에서 개별적인 동작의 순서가 어떻게 되든 **큐는 내부 상태가 일관되게 유지한다는 것을 의미**하는 것이지, **logical consistency**를 필요로 하는 아무 상황에서 연속적으로 사용해도 문제가 없다는 것을 의미하는 것은 아니었다고 한다. 요컨대, "올바른 동작"에 대한 생각이 서로 다른 상황에서 해석하여 달라졌기 때문이다. A는 충돌이 나지 않는 상황(queue의 내부 순서가 얽히지 않는다는 뜻 인듯)만 고려했지만, B는 각 메소드에서 리턴된 결과들이 논리적으로 추론 가능한 것(큐에서 나오는 결과들이 논리적 일관성이 유지되는지)만 고려한 것이다.

> logical consistency : 논리적 일관성
> 쉽게 말하면, 일련의 말 또는 행위가 동시에 일어나도 모순을 일으키지 않는 것.

Mutable한 자료구조의 스레드 안전성은 일반적으로 공유 데이터에 대한 작업이 변경 될 때 항상 공유 데이터의 최신 상태에서 작동하도록 보장하는 것이지, 위의 예와 같이 논리적으로 일치하지는 않는다. Immutable한 자료구조의 스레드 안전성은 오래된 immutable한 스냅샷을 보게되더라도 모든 작업에서 데이터 사용이 논리적으로 일관성을 유지하는 것이다.

여기서 문제는, (큐에서) 첫 번째 element에 접근할 지에 대한 선택은 "기존(stale - 오래된, 묵은 이라고 해석하면 될 듯)" 데이터에 의존한다는 것 이다. *신선하지 않은 상태를 허용하지 않는* 세계에서 진짜 thread-safe한 mutable 자료구조를 설계하는 것은 매우 어려운 일이다. 위의 예로 든 코드를 실제로 thread-safe하게 만들기 위해서는 어떻게 해야할까? 아마 새로운 Peek 메소드를 짜야 할 것이다.

```c#
if(!queue.Peek(out first)) Console.WriteLine(first);
```

위의 코드는 "thread-safe"할까? 아까보다는 낫다. 그러나 `Peek` 이후에 다른 스레드에서 `dequeue`를 했다면? 프로그램이 깨지진 않지만, 이전 코드의 동작을 상당히 많이 바꾸어놓았다. 이전의 코드는 if문 조건 실행 후(after test) 다른 스레드가 dequeue를 하여 첫 번째 요소를 변경하였다면, 프로그램이 깨지거나(`QueueEmptyException`) 최신의(up-to-date) 데이터를 출력하였다. 그러나, 지금의 코드는 애초에 이미 지난 요소를 출력한다.(Peek 이후 출력 전에 다른 스레드가 접근하여 변경했다고 가정하는 듯) 이것이 올바른 동작일까? 매 번 최신 데이터를 받아오는 것이 목적이라면 이것은 정답이 아니다.

그러나, 사실 이전의 코드 역시 `Peek()` 호출 이후 `Console.WriteLine()` 이전에 다른 스레드가 변경했다면? 역시나 최신이 아닌 데이터를 가져오게 된다.

그러면 어떻게 코드를 바꾸어야 할까?

```c#
queue.DoSomethingToHead(first => {Console.WriteLine(first);};)
```
> 위 코드에서 `DoSomethingToHead() 메소드는 delegate일 뿐이며, 콜백 형태로 thread-safe를 보장할 수 있게된다.`
이제 queue 를 만든 사람(A)과 사용하는 사람(B) 모두 동의할만한 thread-safe가 되었다. delegate 안에 엄청 복잡한 것이 있지 않는다면.....

만약 Delegate에 다른 스레드에서 실행되는 코드를 호출하는 이벤트를 발생시키고, 그 동작이 queue를 건드려 **교착상태**가 발생하게 된다면 그 데드락은 "올바른 동작"일까? 만약 아니라면, 이 코드는 진짜로 "safe"할까?

그러므로, 현재 상황에 대한 아무런 언지 없이 "그래서 이 코드 thread-safe 한 거야?" 라고 묻거나 "이거 thread-safe한 코드야" 라고 말하는 것은 도움이 되지 않는다.

"thread safety"는 코드 작성에 관해 협의보는 것 이상도, 이하도 아니다. 객체를 특정한 방식으로 다루는데에 동의하는 것은 그 방법을 통해 올바른 결과를 얻는데 동의한다는 것이다. *정확히 그 방법이 무엇인지, 그리고 올바른 응답은 어떤것인지 알아내는 것은 잠재적으로 어려운 문제이다.*



### 참고
- <https://docs.microsoft.com/en-nz/archive/blogs/ericlippert/what-is-this-thing-you-call-thread-safe>
- <https://www.baeldung.com/java-thread-safety>
- <https://blogs.oracle.com/dave/javautilconcurrent-reentrantlock-vs-synchronized-which-should-you-use>