Bigdata 특강
=========

> 과정 17일차 (19.06.04)

강사 : 나용찬 교수님(ycna@sogang.ac.kr)

### 1. Orientation
- 4차 산업혁명 : 2016년 다보스 포럼의 회장이 첫 언급
- 문제점 : 예측이 불가함 (키워드가 없음)
- **특이점(from Ray Kuzweil)** : 미래에 기술변화의 속도가 빨라지고 그 영향이 매우 깊어 인간의 생활이 되돌릴 수 없도록 변화하는 시기 => 2045년으로 예측되었으나, 2025년으로 특이점이 앞당겨짐.
<br>
- **4차 산업혁명의 핵심 구성요소** : Connectivity + Intelligence

- 잘 대응중인 나라
    - 독일
        - Keyword : Indeustry 4.0
        - ICT를 자동차 기계 산업에 접목
        - 목표 : 제조업의 서비스화

    - 중국
        - Keyword : 중국제조 2025
        - 3단계를 거쳐 제조업의 선진적인 경쟁력 확보가 목표

    - 일본
        - Keyword : 로봇 기술 등을 통해 '잃어버린 20년' 회복 -> 일본 재흥전략
        - 7개의 세부정책(빅데이터, 인재육성 등등)
- IoT 
    - 사람도 thing이다. (데이터가 나에게 알아서 들어오고 나감)
- 인공지능 & 데이터마이닝

> 공부는 책으로..! (not google)
> IEEE / ACM 논문으로 공부해보자

### 2. Big data
- 단순히 많은 양의 데이터는 DB를 만들 때부터 고려하던것/(VLDB)
- Big data의 의미
    - 인위적으로 만든 단어(Gartner)
    - 빅데이터의 정의(2001년 Gartner) 3V
        - **Volume(데이터의 양)**
        - **Velocity(데이터의 입출력 속도)**
        - **Variety(종류의 다양성)**
    - 2012년 기존 정의 개정 : 의사 결정 및 통찰 발견, 프로세스 최적화를 향상시키기 위해 새로운 형태의 처리방식
    - IBM 4V : 기존 3V에 **진실성(Veracity)** 추가
    - Brian Hopkins 4V : 기존 3V에 **가변성(Variability)** 추가
    - McKinsey : 데이터 핸들링 관점에 집중
    - IDC : 데이터를 이용한 업무수행에 초점
- 기업들이 Bigdata를 통해 알고싶어 하는 것 : 미래
- 의사결정을 위한 예측모델 : 주로 Classification(이진분류 선호)
<br>

#### 예측 모델
- 예측모델에서 반드시 필요한 것 : 알고싶은 미래에 대해 수집된 과거의 데이터(Target Value 혹은 Class) / 목표 데이터의 근거가 되는 각 데이터(attribute 혹은 feature vector) / 데이터 한 줄(Instance)
- 데이터의 객관성이 반드시 필요.
- **객관성** : 수학으로 보증
#### - Data-Driven Decision Making
- DDD1
- DDD2
- 비즈니스 문제를 각 하위작업으로 분할
    - 자동화 가능 부분(전처리)과 창의력(~= 경험) 필요 부분으로 구분
    - 전처리를 어떻게 하느냐의 따라 같은 알고리즘이더라도 결과(성능)이 바뀜
- Data Mining Algorithms
    - Data Mining : 알려진 데이터 속에서 체계적이고 자동적으로 규칙을 찾아내는 것
    - 분류와 계층확률 추정
        - 각 개인이 어느 계층에 속할 것인지 예측
    - 회귀분석
        - Target Value에 숫자가 나옴.
        - 숫자를 예측하는 분석방법.(얼마나 많이 일어나는지 예측)
    - 유사도 매칭
        - 주로 추천시스템에 사용됨
        - 알려진 데이터에 기반해 비슷한 개인을 찾음
        - 분류, 회귀 분석, 군집화 같은 데이터마이닝 작업 해결의 기반이 됨
    - 군집화
    - 동시발생 그룹화
        - 휴리스틱
    - 프로파일링
        - 행위기술(개인 혹은 그룹 전체의 전형적인 행위 특징을 찾음)
        - Intrugen Detection
        - 정상 패턴과는 다른 행위 탐지
    - 연결 예측
        - Link Prediction
    - 데이터 축소
        - Data Reduction
        - 데이터의 feature를 줄임
        - 정보는 손실되나 데이터의 통찰력은 높아짐
        - feature selection
    - 인과 모델링
        - 그래프이론 + 확률모델
        - 조건부 확률을 기반으로 시작
        - 결과가 그래프이기 때문에 시각화에 좋음

- Supervised Learning vs Unsupervised Learning
    -  Target Value가 있는지를 고려하여 어떠한 방법을 선택할지 결정


### 3. 예측모델 알고리즘 실습
#### Abstract
- 예측 모델링(Predictive Modeling)
    - 감독 세분화(Supervised Segmentation)
    - 속성 찾기
- 예측 모델링 기법
    - Entropy
    - Decision Tree

#### 용어 정리
- Model : 목적 달성을 위해 실 세계를 단순하게 표현한 것 / 모델은 단순해야함.(관련 없는 정보는 생략)
- 예측모델
    - 우리가 관심 있는 Target Value를 예측하는 공식
- 설명 모델링
- 모델 유도(Model Induction)
    - 데이터로부터 모델을 만드는 것
    - 귀납법 / 범용규칙 / 유도 알고리즘, 학습자


#### 감독 세분화 모델
- 엔트로피 : 어떤 집합에 대한 무질서 정도를 측정(혼잡도)
- 물리학에서의 엔트로피 vs 분석에서의 엔트로피
    - 똑같은 수식 사용
    - 엔트로피가 0에 가까울수록 분류가 잘 되어 있다.(어느 한 쪽에 데이터가 치우쳐져 있으므로)
- 정보 증가량(Information Gain)
    - 기존 엔트로피가 얻던 속성의 순서에 따라 분류할 때 감소하겠는가?
    - 클 수록 해당 속성이 Target Value에 미치는 영향력이 큰 것
- 엔트로피 계산 -> 확률을 알아야 함.
- 확률은? -> 셀 수 있는 데이터여야 함
> 알고리즘을 알아야 전처리가 가능하다.

- 수치형 데이터(countinuous data)의 counting 방법
=> **구간화**를 시켜준다.

- 구간화를 잘 하는 방법 -> 전문가의 도움을 받아 근거있는 구간을 찾아낸다.
- 가중치(weight) : 샘플의 갯수가 다른 표본들의 엔트로피를 보정하는 역할
    - weight에 대한 팩트
    => 가중치가 맞긴 하나, 대부분의 경우에는 기울기의 의미로 훨씬 더 많이 사용된다.


<br>

#### - 의사결정나무(Decision Tree)

구성요소
- Root
- Internal Node(Root와 Leaf를 제외한 나머지 중간 노드)
- Leaf(단말 노드 ,Terminal Node) : Target Value

- Decision Tree의 정지 조건 : 보고있는 데이터의 Target Value가 모두 같은 값이 될 때.
- Target Value를 가장 잘 분류하는 속성부터 기준으로 데이터를 조각내자
=> Information Gain이 가장 높은 속성부터 기준으로 쪼개자.

1. IG 가 가장 높은 속성을 Decision Tree의 Root로 지정
2. Root 속성을 기준으로 데이터 세트 재 정렬
3. 분류가 된 노드의 아래에는 단말노드 결정
4. 다음 Internal node(Subset)들을 대상으로 다시 IG를 구한다.
5. 데이터 세트 재 정렬.
6. 3 ~ 5 반복

- DecisionTree의 장점 : 최종 모델 생성 후 `if else` 룰로 *바로* 만들 수 있음

------

> Tool을 잘쓰는 사람 X / 알고리즘을 잘 아는 사람이 되자



** 김희원 개발자님 (futurian@naver.com / kimhe1@kaist.ac.kr)