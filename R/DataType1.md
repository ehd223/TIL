R Data Type1
=========

> 날짜 : 20.01.20

R의 기본적인 데이터 타입을 알아본다.

#### Numeric
10진수로 표현되는 값을 R에서는 numeric이라고 한다. 다음과 같이 변수 `x`에 10진수 값을 할당한다면 x의 타입은 numeric이 된다.
(integer, double 포함)
```r
x = 10.5
x               # [1] 10.5
class(x)        # [1] "numeric"
is.integer(x)   # [1] FALSE
```

정수라고 생각하는 값을 할당해도 기본적으로 numeric이니 주의.
```r
x = 10
x               # [1] 10
class(x)        # [1] "numeric"
is.integer(x)   # [1] FALSE
```
#### Integer
numeric과 별개로 integer라는 타입이 존재한다.
`integer()` 함수를 쓰거나 `L`을 숫자뒤에 붙여 만든다.

```r
y = as.integer(3)
y                 # [1] 3
class(y)          # [1] "integer"

### L을 붙여 정수만들기
y = 3L
is.integer(y)     # [1] TRUE
```

정수가 아닌 값을 정수로 변환하는 것도 가능하다. (내림연산)
문자열을 파싱하는 것도 가능하며, 변환이 불가능하면 `NA`가 된다.

```r
as.integer(3.14)     # 3
as.integer("5.67")   # 5
as.integer("ABC")    # NA

### logical value의 변환
as.integer(TRUE)     # 1
as.integer(FALSE)    # 0
```

#### Complex
복소수를 표현할 수 있는 데이터 타입이다. `i`를 붙여 나타낼 수 있다.
```r
z = 1 + 2i            
z                    # 1 + 2i
class(z)             # "complex"
```

본래라면 불가능한 연산인 `sqrt(-1)`은 complex 타입을 이용하여 다음과 같이 표현 가능하다.
```r
sqrt(-1)                # NaN (불가능)

### 위의 방법 대신 다음과 같이 표현하면 연산이 가능하다.
sqrt(-1 + 0i)           # 0 + 1i
sqrt(as.complex(-1))    # 0 + 1i
```

#### Logical
logical은 두 값의 비교에 의해 생성되는 값이다.

```r
x = 1; y = 2
z = x > y
z                       # FALSE
class(z)                # "logical"
```

r에서의 표준 논리연산은 `&`(and), `|`(or), `!`(negation) 이다.
```r
u = TRUE; v = FALSE
u & v                   # FALSE
u | v                   # TRUE
!u                      # FALSE
```

#### Character
character는 r에서 문자열 값을 표현하기 위해 사용된다. `as.character()` 함수를 이용하여 문자열로 변환이 가능하다.

```r
x = as.character(3.14)
x                         # "3.14"
class(x)                  # "character"
```

두 문자열은 `paste()` 함수를 이용하여 접합이 가능하며, `sprintf()` 함수를 이용하여 formatting하여 출력이 가능하다.
```r
fname = "DongYoung"; lname = "Lee"
paste(fname, lname)        # "Joe Smith"

### using sprintf
sprintf("%s은 %d 원을 가지고 있다.", "동영", 100)
# "동영은 100 원을 가지고 있다."
```

부분 문자열을 추출하려면, `substr()`함수를 이용하면 가능하다. 
1부터 시작하는 start value에 주의.
```r
substr("Mary has a little lamb.", start=3, stop=12)
# "ry has a l"
```

특정 문자열을 다른 문자열로 바꾸려면 `sub()`함수를 이용하면 된다.
처음 만나는 문자열만 변경하니 주의.
```r
sub("little", "big", "Mary has a little lamb and a little goatling.")
# "Mary has a big lamb and a little goatling."
```

#### Vector
vector는 같은 기본타입을 지닌 원소의 연속이다.
vector의 원소들은 공식적으로 componenets라고 한다.

##### 기본적인 vector 생성
```r
c(2, 3, 5)                # 2 3 5

### 두 벡터의 결합
n = c(2, 3, 5)
s = c("aa", "bb", "cc", "dd")
c(n, s)
# "2"  "3"  "5"  "aa" "bb" "cc" "dd"
# 위의 예시에서 n의 components들은 numeric이었지만 강제로 string으로 변환되어 결합되었다.
```

##### 벡터의 산술연산
벡터의 산술연산은 component by component로 이루어진다.
예를 들면..
```r
a = c(1, 3, 5, 7) 
b = c(2, 4, 6, 8) 

5 * a                # 5 15 25 35
a + b                # 3 7 11 15
a - b                # -1 -1 -1 -1
a * b                # 2 12 30 56
a / b                # 0.500 ... 생략
```

만약 연산하려는 두 벡터의 길이가 다르다면 길이가 더 짧은 벡터가 긴 벡터와의 연산을 맞추기 위해 재활용된다. (배수 관계에 있어야 함.)
```r
u = c(10, 20, 30)
v = c(1, 2, 3, 4, 5, 6, 7, 8, 9)
u + v
# 11 22 33 14 25 36 17 28 39
```

##### 벡터의 인덱스
벡터의 인덱스는 1부터 시작한다. `[]`를 이용하여 원하는 요소를 slice할 수 있다.

```r
s = c("aa", "bb", "cc", "dd", "ee")
s[3]                              # "cc"
s[-3]                             # "aa" "bb" "dd" "ee" ("cc"를 제외한 나머지 원소들)
s[10]                             # NA
```

벡터는 벡터에 의해서도 slice될 수 있다.

```r
s = c("aa", "bb", "cc", "dd", "ee")
s[c(2, 3)]                        # "bb" "cc"
s[c(2, 3, 3)]                     # "bb" "cc" "cc"
s[c(2, 1, 3)]                     # "bb" "aa" "cc"
s[2:4]                            # "bb" "cc" "dd"

### logical vector 에 의한 인덱싱도 가능
L = c(FALSE, TRUE, FALSE, TRUE, FALSE)
s[L]                              # "bb" "dd"
```

##### Named vector components
벡터의 components에 이름을 부여할 수 있다.
```r
v = c("DongYoung", "Lee")
names(v) = c("First", "Last")
v
#      First    Last
# "DongYoung"  "Lee"
```

##### 모든 기본 데이터 타입은 벡터이다.
R에서 모든 기본 데이터 타입은 벡터이다.
정확하게 얘기하면 numeric, integer, character, logical, complex는 원소가 하나인 벡터이고, 원소가 하나인 벡터를 묶어서 scalar라고 한다.

**벡터는 R에서 가장 중요한 타입의 객체이다.**