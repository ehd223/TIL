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