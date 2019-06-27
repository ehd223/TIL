HTML5에서 제공되는 유용한 객체
=========

> 과정 29일차 (19.06.24)

### 웹 스토리지

근본적으로 웹 서버는 클라이언트의 상태를 가지지 않는다.(stateless한 http 요청의 특성)

이전에는 방문횟수 등 저장할 필요가 있는 데이터를 Cookie로 해결하였지만, 용량 제한과 보안 등의 문제로 잘 사용하지 않게 됨.

HTML5에서는 LocalStorage와 SessionStorage를 이용하여 브라우저 내(클라이언트 단의 `window` 객체)에서 key-value 형태로 데이터를 저장할 수 있다.

> **LocalStorage vs SessionStorage**
두 Storage의 가장 큰 차이점은 세션 종료 후에도 유지되냐(local) 그렇지 않냐(session)의 차이. 
SessionStorage는 세션 종료 후에 전부 사라지지만
LocalStorage는 그렇지 않다.

### 웹 소켓

웹 어플리케이션을 위한 양방향 통신 기술.

기존 Http의 request, response 방식(polling)에서 벗어나 websocket 프로토콜을 이용하여 자유로운 송수신이 가능.

- 지속적으로 연결된 TCP라인을 통해 실시간으로 데이터를 주고 받을 수 있음.
- 하나의 소켓 위에서 동작
- HTML5에서 Javascript를 이용하여 사용 가능.
- TCP/IP가 제공하는 모든 기능을 사용할 수 있음.
- 채팅과 같은 실시간 응용 프로그램을 웹 상에서 구현 가능.

> 클라이언트 측 코드는 기본적인 서버 연결, 데이터 송신, 데이터 수신만 제외하면 나머지는 로직부분이다.

**웹 소켓 서버**

웹 소켓은 일반적인 TCP 소켓과는 다른 프로토콜로 설계되어있음.
따라서 기존 TCP 서버를 그대로 이용할 수 없으며, 새로 구현해야함.

언어별로 지원되는 오픈소스 모듈을 이용하면 웹 소켓 서버를 쉽게 구축 가능.
- pywebsocket, phpwebsocket, jWebSocket, web-socket-ruby, Socket.IO-node 등

### 서버 전송 이벤트(Server-Sent Event : SSE)
