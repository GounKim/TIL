# JSP 기초
## 내용
### 1. [JSP 태그](#tag)
### 2. [JSP 내장객체 (내장변수)](#inner)
### 3. 서블릿 vs JSP
### &nbsp;&nbsp;&nbsp;&nbsp;[A. Life Cycle](#lifecycle)
### &nbsp;&nbsp;&nbsp;&nbsp;[B. 서블릿 코드 vs JSP 코드](#sCode_jCode)

<br><br>

<h1 id="tag"> 1. JSP 태그</h1>

## 1. ditective 태그
```jsp
<% page /*속성 = "값"*/ %>
```
속성
- contentType: 한글처리
- import 라이브러리 / 클래스 삽입
- info 등  
<br>

## 2. declaration 태그 (거의 사용X)
```jsp
<%!
    // 서블릿의 인스턴스 변수
    // 서블릿의 메소드
%>
```
<br>

## 3 **_scriptlet 태그_** (주로 사용)
```jsp
<%!
    // 서블릿의 인스턴스 변수
    // 서블릿의 메소드
%>
```
<br>

## 4. expression 태그
```jsp
<%= /*브라우저에 출력할 변수*/ %>
```

<br><br>

<h1 id="inner"> 2. 내장객체(내장변수)</h1>
jsp에서 선언없이 사용 가능한 변수 (내부적으로 자동으로 선언됨)

- request: HttpServletRequest
- response: HttpServletResponse
- session: HttpSession
- application: ServletContext
- config: ServletConfig
- out: JspWriter
  
※ 서블릿에서는 아래와 같이 doGet() 또는 doPost() 메소드의 HttpServletRequest request파라미터를 이용하거나 session등을 사용하기 위해서 API를 import해줌.
```java
    String userid = request.getParameter("userid");
```


<br><br>

<h1 id="servlet_jsp"> 3. 서블릿 vs JSP</h1>
서블릿은 자바코드 비율이 높아 비즈니스 로직 처리에 탁월하다.<br>
JSP는 html비율이 높아 화면 처리에 탁월하다.<br><br>
<h2 id="lifecycle"> A. Life Cycle</h2>
<table>
        <tr>
        <th>단계</th><th>서블릿</th><th>JSP</th>
        </tr>
        <tr>
            <td>[ 1단계 ]<br>서블릿/JSP 요청</td>
            <td>
            http://localhost:port/context/서블릿mapping<br>
            ※ 저장: src폴더에 패키지로 저장
            </td>
            <td>
            http://localhost:port/context/WebContent폴더부터의경로<br>
            ※ 저장: 저장: WebContent폴더<br>
            ※ JSP의 요청과 저장은 html과 동일함
            </td>
        </tr>
        <tr>
            <td>[ 2단계 ]<br>서블릿/JSP 생성</td>
            <td width="44%">
            init호출<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </td> 
            <td width="44%">
            가. **변환단계**<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            \*.jsp -> \*.java(서블릿과 유사)<br>
            나. 컴파일 단계{<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            \*.java -> \*.class(서블릿과 유사)<br>
            다. 생성<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            jspInit() 호출<br><br>
            </td>
        </tr>
        <tr>
            <td rowspan="2">서비스 실행</td>
            <td>
                - doGet() / doPost()<br>
                - 요청 처리, DB연동, 응답처리
            </td>
            <td>
                - jspService(request, response)<br>
                - 요청처리, DB연동, 응답처리
            </td>
        </tr>
        <tr>
            <td colspan="2">※ 서블릿의 응답처리와 JSP의 응답처리에서 가장 차이점이 두드러짐<br>&nbsp;&nbsp;&nbsp;&nbsp;
                - 서블릿의 응답처리: 명시적으로 html작성<br>&nbsp;&nbsp;&nbsp;&nbsp;
                - jsp의 응답처리: jsp자체가 html태그로 구성되어있기 때문에 쉽게 html이 작성된다.</td>
        </tr>
        <tr>
            <td>결과</td>
            <td colspan="2" align="center">
                html
            </td>
        </tr>
        <tr>
            <td>응답</td>
            <td colspan="2" align="center">
                html
            </td>
        </tr>
        <tr>
            <td>이후<br>다른 브라우저 실행시</td>
            <td colspan="2">
                바. 브라우저2에서 동일한 서블릿/JSP 요청<br>
                사. 서비스 실행<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                -> 브라우저1에서 서블릿/JSP 요청했을 때 이미 해당 서블릿/JSP가 생성되었으므로 바로 실행으로 넘어감<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <b>★생성 과정은 단 한번만 진행됨★</b><br>
                아. 결과(html)<br>
                자. 응답(html)
            </td>
        </tr>
    </table>

<br>

<h2 id="sCode_jCode"> B. 서블릿 code vs JSP code</h2>
<table>
        <tr>
        <th>&nbsp;</th><th width="42%">서블릿</th><th width="42%">JSP</th>
        </tr>
        <tr>
            <td>import</td>
            <td>import java.io.PrintWriter;</td>
            <td><% import="com.servlet" %></td>
        </tr>
        <td>인스턴스 변수</td>
            <td>
                int num = 10;
            </td>
            <td> 
                <%! int num = 10; %>
            </td>
        <tr>
            <td>요청처리<br>(응답처리 이전)</td>
            <td>
                String id = request.getParameter("userid");<br>
                LoginService servic = new LodinServiceImpl();
            </td>
            <td> 
                <% String id = request.getParameter("userid);<br>
                LoginService servic = new LodinServiceImpl();
            </td>
        </tr>
        <tr>
            <td>ContentType</td>
            <td>response.setContentType("text/html; charset='utf-8'");</td>
            <td><% page contentType="text/html; charset="UTF-8" %></td>
        </tr>
        <tr>
            <td>응답처리</td>
            <td>
                PrintWriter out = response.getWriter();<br>
                out.print(&lt;html>&lt;body>");<br>
                out.print(id);<br>
                out.print("&lt;/body>&lt;/html>");
            </td>
            <td> 
                <%= id %>
            </td>
        </tr>
    </table>

    ※ 추가
    webApplication 개발 패턴
    1. model 1 Architecture
        - JSP
        - 유지 보수 어려움
    2. model 2 Architecture(MVC 패턴)
        - 서블릿 + JSP
        - 과정
            1. 브라우저가 서블릿에 요청
            2. 서블릿에서 서비스 실행(데이터처리 작업)
            3. 결과를 데이터 서블릿에 전달
            4. 서블릿이 JSP에 결과 위임
            5. JSP에서 결과 생성(html)
            6. 브라우저에 응답