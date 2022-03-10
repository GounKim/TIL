# JSP 고급

## 내용
### Ⅰ. [EL (Expression Language)](#el)
### Ⅱ. [JSTL (Jsp Standard Tag Libaray)](#jstl)
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- JSTL 사용
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- JSTL 라이브러리
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[1. Core 라이브러리](#core)
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[2.Formatting 라이브러리](#formatting)
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[3.Functions 라이브러리](#functions)
<br><br>

<h2 id="el"> Ⅰ. EL (Expression Language)</h2>

- 특징
    - 웹 브라우저에 데이터를 출력하기 위한 언어
    - 문법이 직관적임
    - 다른 출력 방법들과 비교하여 훨씬 사용하기 쉬움
    - 변수 선언과 초기화 없이 변수 출력 가능
    - null값 처리가 쉽다.
<br><br>
- 기본 문법
  - ${ 값/표현식 }
  - ${ 객체.속성 }<br>
    &nbsp;예시) People객체 
    - ${ person.name }
    - ${ person.age }
  - ${ 배열표기법[ 키/인덱스 ] }<br>
    &nbsp;예시) People객체 
    - ${ person["name"] }
    - ${ person["age"] }
<br><br>
- 내장 객체
  - pageScope => ${ pageScope.name }
  - requestScope => ${ requestScope.name }
  - sessionScope => ${ sessionScope.name }
  - applicationScope => ${ applicationScope.name }
  - cookie => ${ cookie.name }
<br><br>
- 산술 & 비교연산 가능
  - $ { key + 1 } : key + 1
  - $ { key > 10 } : true/false
   
> <br>
> **EL은 다양한 형태의 데이터를 효율적으로 처리할 수 있다.**<br>
> BUT 반복문에 적용이 불가능하다. ->JSPL 사용 필요
> <br><br>

<br><br>

<h2 id="jstl"> Ⅱ. JSTL (Jsp Standard Tag Libaray)</h2>

아파치에서 제공하는 **사용자 필요에 의해서 자체적으로 만든 커스텀 태그(Custom tag)중 자주 사용되는 태그들**을 말한다.<br>
&nbsp;&nbsp;&nbsp;&nbsp;=> jsp에서 프로그램적(자바)으로 사용하는 대신 태그를 사용하도록 만든 것이다.<br>

<br>

### JSP사용
1. Apache에서 jar파일 다운
   - https://tomcat.apache.org/taglibs/ 접속 -> Apache Standard Taglib클릭(제일 첫 링크)
2. WEB-INF폴더 > lib폴더 위치에 복사
3. jsp에서 jstl을 사용하도록 설정
   - tablig 태그 사용
      ```jsp
        <%@ taglib uri=" " prefix=" " %>
      ``` 
4. EL(${ }) 과 같이 사용한다.

<br><br>

### JSTL 라이브러리
---
※ 라이브러리 종류
라이브러리 | URI | Prefix | 사용 예시
----------|-----|--------|-----------
**Core** | http://java.sun.com/jsp/jstl/core | c | <c:tag - >
**Functions** | http://java.sun.com/jsp/jstl/functions | fn | <fn:function(-) >
**formatting** | http://java.sun.com/jsp/jstl/fmt | fmt | <fmt:tag - >
SQL | http://java.sun.com/jsp/jstl/sql | sql | <sql:tag ->
XML | http://java.sun.com/jsp/jstl/xml | fn | <x:tag ->
<br><br>

<h3 id="core">1. Core 라이브러리</h3>

    ```jsp
      // 사용 설정
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    ``` 
   -  태그 종류
      1. <c:set>태그: JSP의 setAttribute(name, value)메소드와 동일한 기능 제공
          ```jsp
            <c:set var="변수명" value="변수값" target="개체" property="객체의 프라퍼티" 
                   scope="page(기본)|request|session|application" />
          ``` 
      <br>

      2. <c:out>태그: 지정된 값을 웹 브라우저에 출력함
          ```jsp
            <c:set value="출력값" default="기본값" escapeXml="true|fales" />
          ```
            - default속성: value속성이 null인 경우 출력값
            - escapeXml속성: 특수기호들을 escape문자(\&nbsp;) 형태로 출력 여부
        <br><br>

      3. <c:remove>태그: 지정된 scope에 설정한 변수값 제거
          ```jsp
            <c:remove var="변수명" scope="page(기본)|request|session|application" />
          ``` 
          <br>

          ```jsp
           /* 태그 예시 */
            <c:set var = "request" vlalue="request scope에 저장" scope="request" />
            <c:out value="${request}" />  // 브라우저 출력값: request scope에 저장

            // login: dto일때
            <c:set var="my" value="${login}" />
            ${my.userid}                  // 출력방법1
            <c:out value="%{my.userid}">  // 출력방법2 (방법1과 방법2 출력 동일)

            <c:remove var="request">
            <c:out value="${request}" /> // 값이 출력되지 않는다.
          ```
        <br>

      4. <c:if> 태그: if문과 동일, _BUT if-else문 제공X_
         ```jsp
            <c:if test="조건식" var="변수명" scope="page(기본)|request|session|application"> 
              문장
            </c:if>

            // 예시
            <c:set var="myColor" value="red" />
            <c:if test="${myColor == 'red'}">
              색상은 빨강색이다.
            </c:if>
         ``` 
         <br>

      5. <c:choose>, <c:when>, <c:otherwise> 태그: switch문과 동일<br>
        => 하나 이상의 <c:when>태그와 하나 이상의 <c:otherwise>태그를 포함한다.  
          ```jsp
            <c:choose>
              <c:when test="조건">문장<c:when>
              <c:otherwise>문장<c:otherwise>
            </c:choose>

            // 예시
            <c:set var="grade" value="85" />
            <c:choose>
              <c:when test="${grade >= 90}">
                학점은 A이다.
              </c:when>
              <c:when test="${grade >= 80}">
                학점은 B이다.
              </c:when>
              <c:when test="${grade >= 70}">
                학점은 C이다.
              </c:when>
              <c:otherwise>
                학점은 F이다.
              </c:otherwise>
            </c:choose>
          ```
          <br>

      6. <c:forEach> 태그: for문과 유사
          ```jsp
          <c:forEach items="객체명" begin="시작 인덱스" end="끝 인덱스" step="증가값" 
                      var="변수명" varStatus="other변수">
            문장
          </c:forEach>

          // 예시
          <%
          int[] num = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
          request.setAttribute("myArray", num);
          %>
          <c:forEach var="n" items="${myArray}">
            <c:out value="${n}" />
          </c:forEach>  // 출력: 10 20 30 40 50 60 70 80 90 100

          <c:forEach var="n" items="${myArray}" begin="0" end="6">
    	      ${n}        // 출력: 10 20 30 40 50 60 70
          </c:forEach>
          ```
        <br>

        
      7. <c:forTokens> 태그: StringTokenzier 기능
          ```jsp
          <c:forTokens items="객체명" delims="구분자" begin="시작 인덱스" end="끝 인덱스" step="증가값" 
                        var="변수명" varStatus="other변수">
            문장
          </c:forTokens>

          // 예시
          <c:set var="data" value="A, B, C, D" />
          <c:forTokens var="n" items="${data}" delims=",">
            <c:out value="${n}"></c:out>
          </c:forTokens>

          ```
          <br>
        
      8. <c:url> 태그: 자동으로 전체 URL 값 지정 
          ```jsp 
          <a hred="<c:url value='/target.jsp'></c:url>">/target2</a>
          ```
<br><br>

<h3 id="formatting">2. Formatting 라이브러리</h3>

  ```jsp
    // 사용 설정
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  ``` 
   -  태그 종류
      1. \<fmt:requestEncoding>태그: JSP의 setCharacterEncoding("uft-8")메소드와 동일한 기능 제공
          ```jsp
          <fmt:requestEncoding value="utf-8" />
          ```
      <br>

      2. \<fmt:formatNumber> 태그: 수치 데이터를 특정 포맷으로 설정
          ```jsp
          <fmt:formatNumber var="변수명" 
                            value="실제 수치값" 
                            type="number|currency|percent" 
                            pattern="사용자가 지정한 형식패턴" 
                            scope="page(기본)|request|session|application" 
                            currencySymbol="통화기호" 
                            maxIntegerDigits="정수의 최대 자리수" 
                            minIntegerDigits="정수의 최소 자릿수"
                            maxFractionDigits="소수점 이하 최대 자리수" 
                            minFractionDigits="소수점 이하 최소 자릿수" />

          /* 예제 */
          <fmt:formatNumber value="100000" type="currency" /> // ￦100,000
          <fmt:formatNumber value="0.123" type="percent" /> // 12%
          <fmt:formatNumber value="987654321.1234" pattern="###,###,###.00" /> // 987,654,321.12
          ```
      <br>

      3. \<fmt:formatDate> 태그: 날짜 데이터를 특정 포맷으로 설정
          ```jsp
          <fmt:formatDate var="변수명" 
                          value="실제 날짜와 시간" 
                          type="time|date|both" 
                          dateStyle="날짜 스타일 형식 지정" 
                          timeStyle="시간 스타일 형식 지정" 
                          pattern="사용자가 지정한 형식 스타일" 
                          scope="page(기본)|request|session|application" />

          /* 예제 */
          <c:set var="myDate" value="<%= new Date() %>"></c:set>

          // type
          <c:out value="${myDate}" /> // Thu Feb 17 16:59:18 KST 2022
          <fmt:formatDate value="${myDate}" type="date"/> // 2022. 2. 17
          <fmt:formatDate value="${myDate}" type="time"/><br> // 오후 4:59:18
          <fmt:formatDate value="${myDate}" type="both"/><br> // 2022. 2. 17 오후 4:59:18

          // dayStyle, timeStyle
          <fmt:formatDate value="${myDate}" type="both" dateStyle="short" timeStyle="long"/><br> // 22. 2. 17 오후 5시 03분 51초
          <fmt:formatDate value="${myDate}" type="both" dateStyle="long" timeStyle="short"/><br> // 2022년 2월 17일 (목) 오후 5:03
          ```

<br><br>

<h3 id="functions">3. Functions 라이브러리</h3>

  ```jsp
  // 사용 설정
  <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  ``` 
   -  태그 종류
      1. ${fn:length("값")}: 문자열 길이 반환
          ```jsp
          ${fn:length("hello")} // 5
          ```
      <br>

      2. ${fn:toLowerCase("값")}: 문자열을 소문자로
          ```jsp
          ${fn:toLowerCase("HELLO")}  // hello
          ```
      <br>

       3. ${fn:toUpperCase("값")}: 문자열을 대문자로
          ```jsp
          ${fn:toUpperCase("hello")}  // HELLO
          ```
      <br>

      4. ${fn:substring("값", start, end)}: 문자열의 부분열 반환
          ```jsp
          ${fn:substring("hello", 0, 3)}  // hel
          ```
      <br>

      5. ${fn:trim("값")}: 문자열 공백 제거
          ```jsp
          ${fn:trim("    hello    ")}   // hello
          ```
      <br>

       6. ${fn:replace("값", "값1", "값2")}: 문자열 변경
          ```jsp
          ${fn:replace("hello", "h", "X")}  // Xello
          ```
      <br>

      7. ${fn:contains("값", "값2")}: 문자열 포함 여부 확인
          ```jsp
          ${fn:contains("hello", "hel")}  // true
          ```
      <br>

      8. ${fn:split("값", "구분자")}: 문자열을 특정 구분자로 분리 후 반환
          ```jsp
          ${fn:split("aaa/bbb/ccc", "/")}   // aaa bbb ccc
          ```
      <br>