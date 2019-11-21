Ajax
=========

> 과정 27일차 (19.06.24)

### Ajax란?
Asynchronous JavaScript and XML의 약자 (비동기 자바스크립트 XML 통신)

- XMLHttpRequest 개체를 이용하여 비동기적으로 서버와 데이터를 교환.
- XML 파일 조각 단위로 서버로부터 데이터를 받아와 페이지를 Reload 없이 업데이트 가능.

> javax.servlet 패키지를 사용하기 위해서는 java servlet-api를 BuildPath에 추가해야함

```javascript
// Plain javascript
function getData(){
    var req;
    if(window.XMLHttpRequest){
        req = new XMLHttpReauest();
    }
    else{ // IE6, IE5
        req = new ActiveXObject("Microsoft.XMLHTTP");
    }
    req.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            callbackFunction();
       }
    };
    req.open('GET', url, true); // Get이라면 parameter와 함께,
                         // POST면 send() 메소드에 parameter로 넣을 것
                        // 세 번째 인자는 async로 기본값은 True
                        // false로 할 경우 동기적으로 실행됨.
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhttp.send(); // xhttp.send("fname=Henry&lname=Ford");


}

```

```java
// using jQuery
function idDuplicationCheck (id) {
    var url = "url name";
    var par1 = $("input[name='param1']");
    var par2 = $("input[name='param2']");
    $.ajax({
        type : 'GET',
        url : url,
        data : {
            "par1" : par1,
            "par2" : par2,
        },
        success : function (data) {
            if (data) {
                alert("Success return"); 
                successCallback();
            } else {
                // 서버단에서 에러 
                alert("Fail");
                failCallback();
            }             
        }
        error : function(error) {
            // 전송 실패시
        },
        always : function(response) {
            // 성공하든 실패하든 항상 할 동작
        }

        ...  

    });
}
```

#### Ajax 사용 시 주의점
Ajax는 기본적으로 `async`로 동작하기 때문에 실행 후 response로 넘어오는 데이터를 처리할 경우 callback이나 `Promise` 나 `Async`를 이용하여 처리할 것.

> Ajax call을 구현하는 것은 어렵지 않지만, 비동기 처리 등에 꼭 신경쓰면서 사용하자

#### CORS(Cross-Origin Resource Sharing)

**처음 전송되는 리소스의 도메인과 다른 도메인으로부터 리소스를 요청할 때** 기본적으로 **cross-origin HTTP** 요청에 의해 요청됨.
ex) img태그 src속성에 외부 리소스의 도메인을 넣어 요청하는 경우.

그러나, 보안 상의 이유로 브라우저들은 스크립트 내에서 초기화되는 cross-origin HTTP 요청을 제한한다. 
XMLHttpRequest(ajax)는 same-origin 정책을 따르기 때문에 원래는 자신과 동일한 도메인으로 HTTP 요청을 보내는 것만 가능.

Cross-Origin Resource Sharing(CORS)는 웹 서버에게 보안 cross-domain 데이터 전송을 활성화하는 cross-domain 접근 제어권을 부여함.
모던 브라우저들은 cross-origin HTTP 요청의 위험성을 완화시키기 위해 API컨테이너 내에서 CORS를 사용함.

CORS의 종류
- Simple Request
    기본적인 단순 요청
    1. GET, HEAD, POST 중 한 가지 방식을 사용해야 함.
    2. POST일 경우 Content-type이 아래 셋 중 하나를 만족.
        - application/x-www-form-urlencode
        - multipart/form-data
        - text/plain
    3. 커스텀 헤더를 전송하지 않아야 함.
- Preflight Request
    사전 요청을 먼저 보내고 서버가 응답이 가능한지 확인하는 방법.
    OPTION 메소드를 이용해 사전 요청을 전송.
    서버에서 사전 요청을 받고 
    - Access-Control-Allow-Origin
    - Access-Control-Allow-Method
    - Access-Control-Allow-Headers
    를 응답한다. 

    Access-Control-Allow-Origin에 포함된 정보를 이용하여 본 요청을 보내어 응답을 받을 수 있음.

    >**주의점**
    Preflight Request는 Simple Request에 해당하는 조건 이외의 상황에서 사용하는 방법임.
    
- Credential Request
    HTTP Cookie와 HTTP Authentication 정보를 인식할 수 있게 하는 요청.
    xhr 객체의 withCredentials를 true로 지정함.
    `xhr.withCredentials = true`
    요청 헤더에는 Credential이 있는 Cookie가 포함되어 있고,
    응답 헤더에는 Access-Control-Allow-Credentials가 포함되어 있음.
    
    > 응답 헤더는 요청자의 Credential이 허가된 경우에만 Response를 하므로 응답이 돌아온다면 Access-Control-Allow-Credentials의 값은 항상 true이고, 허가되지 않은 Credential을 이용하여 요청이 들어왔다면 응답 자체가 없음.
- Non-Credential Request
    Credential과 반대로 withCredential의 값이 false인 경우의 모든 요청 (default가 false)


**결론**
1. 내가 만든 다른 API 서버를 호출하고 싶다면 위 네 가지 방법 중 하나를 이용하여 Response와 Request의 헤더를 조작하자.(특히, Response의 Access-Control-Allow-*)
2. 다른 사람이 만든 서버(예를 들면 공공데이터 포털 혹은 API서버)를 파싱할때 **굳이** XMLHttpRequest같은 Same-Origin 정책을 채택하는 Request를 써야한다면
    - 해당 서버가 CORS를 지원하는지 알아보고 지원한다면 지원하는 정책에 맞게  요청
    - 지원하지 않는다면 다른 방법을 찾아볼 것(cors.io, 브라우저 플러그인, jsonp 등등 )

**파싱은 가능하면 내 서버에서 처리할 것**