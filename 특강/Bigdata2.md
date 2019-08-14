Bigdata 특강
=========

> 날짜 : 2019.08.14

강사 : 나용찬 교수님(ycna@sogang.ac.kr)

---

> **Roadmap**
Linear Classifier -> Perceptron -> Artificial Neural Network -> Deep Learning

> **미분?**
x에 대한 상대적인 y의 변화량

## Parameter Learning
특정 수식 안에서 최적의 Parameter(상수)를 찾아내는 과정
= 기울기와 절편을 찾아라!
### Abstract

__모델에 사용될 속성__
- Domain knowledge(기반 지식) 이용
- Data Mining 기법 이용
    - Parameter Learning or Parametric Modeling
    - Linear 모델 기반

__가정__
- 분류와 계층 확률 추정 시 2진 계층만 고려
- 모든 속성이 수치형이라 가정
- 수치형 데이터의 공통단위 표준화 필요 없음

__목표__
- 최적의 모델 파라미터를 찾아 모델을 데이터에 맞추기

---

## Linear Classifier

### Predictive Model
**Parameter Learning**
- 모델 설정 후 데이터 __fitting__

실제 현장에서 데이터는 노이즈를 가지고 있고, 노이즈가 반영된 모델이 만들어짐.

노이즈 혹은 이상치같은 데이터의 영향력을 제한하기 위하여 학습률(Learning Rate)을 사용

**선형 분류 모델의 약점**
XOR 문제를 못 풂

---

### 함수이용분류 (Classification via Mathematical Functions)

HyperPlane : n차원의 분류를 했을 때 n-1차원의 단면

> Occam's razor
-> 성능 차이가 많이나지 않는다면, 단순한 모델이 더 좋다

선형 판별 함수로 가장 좋은 경계선 만들기?
-> 현실적으로 어려움

경계선 근처에 있는 점(데이터) : 절편이 조금만 바뀌어도 레이블이 바뀔 수 있는 데이터
-> 특정 레이블에 속할 확률이 상대적으로 낮음

---

### 함수이용 회귀분석 (Regression via Math Functions)

얼마나 많이? -> 회귀 문제

주어진 데이터에서 종속변수(Y^)와 독립변수(X)를 빨리 결정하는 것이 핵심.

### 계층 확률 추정과 로지스틱 회귀분석

**승산(Odds)**
- 사건이 일어날 가능성을 표현하는 방법 중 하나
- (일어날 일) / (일어나지 않을 일)
- `odds = e^(w0 + w1x1 + w2x2 + ...)`

**로그 승산**
```math
log_e odds = log_e e^{(w0 + w1x1 + w2x2 + ...)} = w0 + w1x1 + w2x2 + ... = f(x)
```
- 객체가 어떤 계층에 속할 가능성 보다는 어떤 가능성인지 안다면 f(x)에 로그 승산을 적용한 모델 사용 가능

## Logistic Regression

### Abstract

**Supervised Learning**
- Classification 문제 해결
- 일반적으로 2 범주 문제를 다룸

### From Linear Regression To Logistic Regression

**목적**
- A와 B, 두 카테고리 중 하나로 분류
- 선형 회귀분석 식에서..
    - 우리가 예측하려는 y값을 A일 확률로 가정
    - y값이 0.5보다 크면 A로 분류하고 0.5보다 작으면 B라고 분류

#### 수식 전개
1. 선형 회귀식
```math
y = ax + b
```

2. y를 확률로 변환
```math
P = ax + b
```
이 때 양변이 갖는 값의 범위가 일치하지 않음

3. P 자리에 Odds 대입
4. Odds에 log를 씌운다

### P에 관한 수식 정리
1. Odds로 표현된 선형관계식
```math
log_e({P \over 1-P}) = ax + b
```

2. e를 양변에 씌움
3. 역수를 취함
4. 1을 더함
5. 역수를 취함

-> **Logistic Curve**

![](https://upload.wikimedia.org/wikipedia/commons/5/54/Generalized_logistic_function_A0_K1_B1.5_Q0.5_%CE%BD0.5_M0.5.png)


## Single Layer Perceptron

신경망에서 가장 최초로 나온 알고리즘

> **Perceptron의 한계**
2진분류가 한계이다.

### Terminology (용어)

**Threshold** : 어떤 값이 활성화되기 위한 최소값 (활성 함수의 경계값)
**Weight** : 선형분류모델의 기울기
**Bias** : 선형분류를 위한 직선의 절편
**Neuron** : 인공신경망의 가장 작은 요소

---
