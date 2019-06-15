HTML
=========

> 과정 19일차 (19.06.12)

Eclipse의 Dynamic web project 구조
- Dynamic(Java Resources) : src
- Static : WebContent
- 그 외 : JavaScript Resources

> HTML은 계속 했었으니 몰랐던 것만 짚고 넘어가자

**1. HTML 5에서 모든 요소는 Contents Category로 분류한다.**
여전히 Inline 속성 태그와 Block 속성 태그가 구분되어 있지만, HTML5에서는 따로 정의하고있지는 않고, Contents Category로 분류되어있다.
- Metadata
- Flow
    - Interactive
    - Phrasing
        - Embedded
    - Heading
    - Section

**2. Javascript의 위치는 어떠한 상황에서 실행되는 부분인지 나눠서 넣자**
- Place library script such as the jQuery library in the head section.
- Place normal script in the head unless it becomes a performance/page load issue.
- Place script associated with includes, within and at the end of that include. One example of this is .ascx user controls in asp.net pages - place the script at the end of that markup.
- Place script that impacts the render of the page at the end of the body (before the body closure).
- do NOT place script in the markup such as `<input onclick="myfunction()"/>` - better to put it in event handlers in your script body instead.
- If you cannot decide, put it in the head until you have a reason not to such as page blocking issues.

**3. head 태그 안에 `<base>` 태그를 이용하여 모든 href의 기본을 지정 가능하다.**
ex)
```html
    <head>
        <base href="http://www.mysite.com"/>
    </head>
    <a href="info.html">info</a> 
    <!-- http://www.mysite.com/info.html -->
```

**4. `<img>` 태그는 다른 태그와 다르게 서버에서 다운로드 받아 렌더링 한다.**
**5. `<table>` 태그는 HTML5에서 border 속성을 지원하지 않는다.**
CSS를 이용하여 조정하자
**6. ``onclick`` 사용을 하지 말자**
- **Javascript와 HTML의 분리**
- => 동작과 표현을 분리함으로써, 코드의 유지보수 관점에서 더 좋은 코드를 작성 가능.
