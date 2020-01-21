R Data Type2
=========

> 날짜 : 20.01.20

R Data Type1에 이어 나머지 데이터 타입을 알아본다.

#### Factor
범주형 데이터의 구조를 Factor라고 한다.
factor가 가질 수 있는 값들을 level이라고 하며 순서를 부여할 수도 있다.

```r
f1 = c("Middle", "High", "Low")
f2 = factor(f1)
f2
# [1] Middle High   Low   
# Levels: High Low Middle

f3 = factor(f2, order = TRUE, level = c("Low", "Middle", "High"))
f3
# [1] Middle High   Low   
# Levels: Low < Middle < High
```

#### Matrix
Matrix는 동일한 기본 데이터 타입을 가진 2차원 데이터 구조이다. 
n * m 형태로 되어있으며 반드시 같은 basic type을 원소로 가져야 한다.

```r
A = matrix( 
   c(2, 4, 3, 1, 5, 7), # 행렬에 들어갈 원소들
   nrow=2,              # row의 수
   ncol=3,              # col의 수
   byrow = TRUE)        # row 부터 원소를 채운다.
 
A
#      [,1] [,2] [,3] 
# [1,]    2    4    3 
# [2,]    1    5    7
```

Matrix는 다음과 같이 slice 할 수 있다.
```r
### Matrix slicing
A[2, 3]                  # 7
A[2, ]                   # 1 5 7
A[ ,3]                   # 3 7
A[ ,c(1,3)]              # 1, 3 열을 선택.
#      [,1] [,2] 
# [1,]    2    3 
# [2,]    1    7

### named row, col 
dimnames(A) = list( 
+   c("row1", "row2"),         # row names 
+   c("col1", "col2", "col3")) # column names 
 
> A                 # print A 
     col1 col2 col3 
row1    2    4    3 
row2    1    5    7 
 
> A["row2", "col3"] # element at 2nd row, 3rd column 
[1] 7

```

##### Matrix Construction
행렬을 만드는 방법은 여러가지가 존재한다.
데이터 원소들을 가지고 직접 만들때에는 column들을 기준으로 행렬이 채워진다.

```r
B = matrix( 
  c(2, 4, 3, 1, 5, 7), 
  nrow=3, 
  ncol=2) 
 
B                # 3행 2열짜리 행렬이 열을 우선으로 채워진 모습
#      [,1] [,2] 
# [1,]    2    1 
# [2,]    4    5 
# [3,]    3    7
```

역행렬은 `t()`함수를 이용하여 만들 수 있다.
```r
t(B)
#      [,1] [,2] [,3] 
# [1,]    2    4    3 
# [2,]    1    5    7
```

##### Matrix 결합
row의 수가 같거나 col의 수가 같은 두 행렬은 결합이 가능하다.

```r
C = matrix(
   c(7, 4, 2),
   nrow=3,
   ncol1
)

C
#      [,1] 
# [1,]    7 
# [2,]    4 
# [3,]    2

B                
#      [,1] [,2] 
# [1,]    2    1 
# [2,]    4    5 
# [3,]    3    7

### cbind

cbind(B, C)
#      [,1] [,2] [,3] 
# [1,]    2    1    7 
# [2,]    4    5    4 
# [3,]    3    7    2

### rbind
D = matrix(
   c(6, 2),
   nrow=1,
   ncol2
)

D
#      [,1] [,2] 
# [1,]    6    2 

rbind(B, D)
#      [,1] [,2] 
# [1,]    2    1 
# [2,]    4    5 
# [3,]    3    7 
# [4,]    6    2
```

#### List
리스트는 기본 타입이 다른 원소를 포함이 가능한 vector이다.

```r
n = c(2, 3, 5)
s = c("aa", "bb", "cc", "dd", "ee")
b = c(TRUE, FALSE, TRUE, FALSE, FALSE)
x = list(n, s, b, 3) 
```

##### List Slicing
리스트도 마찬가지로 `[]`을 이용하여 slicing이 가능하다.
```r
x[2]
[[1]]
[1] "aa" "bb" "cc" "dd" "ee"

### 인덱스 벡터를 이용한 slicing
x[c(2, 4)]
[[1]] 
[1] "aa" "bb" "cc" "dd" "ee" 
 
[[2]] 
[1] 3
```

##### Member 참조
리스트의 멤버를 직접 참조를 하기 위해서는 `[[]]`를 사용해야 한다.

```r
x[[2]]
# [1] "aa" "bb" "cc" "dd" "ee"

x[[2]][1] = "ta"
x[[2]]
# [1] "ta" "bb" "cc" "dd" "ee" 
s 
# [1] "aa" "bb" "cc" "dd" "ee"   # 원래의 vector s는 x의 원소를 변경했지만 영향을 받지 않았다. (Deep copy)
```

##### Named List Members
vector와 마찬가지로 list도 인덱스에 naming을 할 수 있다.
```r
v = list(babo=c(2, 3, 5), foo=c("aa", "bb"))
v
# $bob 
# [1] 2 3 5 
 
# $john 
# [1] "aa" "bb"

### Named index slicing
v["bob"]
# $bob 
# [1] 2 3 5 

### 
```

이름이 있는 멤버들은 R의 직접 변수로 등록이 가능하다.
```r
attach(v)
bob
# [1] 2 3 5
detach(v)
```

#### Data Frame
데이터 프레임은 데이터 테이블을 저장하는데에 사용되며, 동일한 길이의 벡터로 이루어져있다.

```r
n = c(2, 3, 5) 
s = c("aa", "bb", "cc") 
b = c(TRUE, FALSE, TRUE) 
df = data.frame(n, s, b)
df
#   n  s     b
# 1 2 aa  TRUE
# 2 3 bb FALSE
# 3 5 cc  TRUE
```