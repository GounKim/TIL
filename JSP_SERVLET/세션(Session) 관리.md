# 세션 관리(Session Tracking)
### [1. 세션 처리 방법](#1-세션-처리-방법)
### [2. 쿠키 처리 방법](#2-쿠키-처리-방법)
### [3.기타 유용한 정보](#3기타-유용한-정보)
<br>

### WHY?

Http 프로토콜의 특징 때문에 한 컴포넌트에서 진행한 작업들을 다른 컴포넌드에서는 알 수 없다. **즉, 컴포넌트 간 데이터 공유가 불가능하다.**

    http프로토콜의 특징
    - 무상태(stateless): 상태 관리를 하지 않음
    - connectionless: 브라우저가 서블릿에 요청하고 응답이 왔을 때 화면에 결과를 보여준 뒤 바로 연결이 끊어짐
    
    즉, 페이지간의 연결고리가 없다.

따라서 컴포넌트 사이에 데이터를 공유할 수 있도록 세션 관리를 한다.

<br>

## 세션 관리 방법
---
## 1. 세션 처리 방법 (HttpSession)

- HttpSession 클래스를 이용
- 데이터를 서버에 저장<br>
    - 저장소(HttpSession)를 이용해 다른 컴포넌트에서 접근 가능
- 웹 브라우저와 LifeCycle 동일
    - 웹 브라우저를 열면 저장소 생성됨
    - 웹 브라우저를 닫으면 세션이 종료됨
- 크기 제한 없음
- 저장되는 데이터 제한 없음
- 보안 높음
- 세션 실행(사용) 순서
    
        서블릿A에서 요청 -> 서블릿A에서 실행 -> 결과값(Data)을 얻음 -> 세션 얻기 -> 세션에 결과값 저장 -> 응답 처리 -> 응답출력 -> 서블릿B 열기 -> 서블릿B에서 요청 -> 실행 -> 세션 얻기 -> 세션 값 가져오기

### **세션 사용 방법**
1. 세션 얻기
    - session이 있으면 반환, 없으면 새로 생성하여 반환
        ```java
        HttpSession session = request.getSession();
        HttpSession session = request.getSession(true);
        ```
        - session이 있으면 반환, 없으면 null반환
        ```java
        HttpSession session = request.getSession(false);
        ```
        => 데이터를 저장하는 서블릿에서는 getSession() 또는 getSession(true) 사용
        => 데이터를 사용하는 서블릿에서는 getSession(false) 사용
        => BUT 일반적으로는 getSession()으로 모든 작업 진행

2. 세션에 값 저장
    ```java
    session.setAttribute("ket값", "value");
    ``` 

3. 세션의 값 가져오기
    - null 체크 진행<br>
      why?<br> 
      정상적인 절차를 거치지 않은 경우
      & 일정 시간이 지나 time-out된 경우<br>
      session에 데이터가 존재하지 않기 때문
            
         ```java
            String value = (String) session.getAttribute("key값");

            if (value != null) {
                // value값(데이터) 처리
            } else {
                /*
                    서블릿A를 거치지 않고 서블릿B를 바로 왔을 경우
                    A로 이동하도록 처리해줌
                    ex) 로그인하지 않고 회원정보를 볼 경우 로그인하도록 유도
                */
            }
        ``` 

4. 추가적인 메소드
    - 삭제 메소드
        ```java
            session.removeAttribute("key값");
        ``` 
    - 세션 영역 전체 삭제 메소드 -> ex. 로그아웃ㅉ
        ```java
            session.invalidate();
        ``` 

<br><br>

## 2. 쿠키 처리 방법 (Cookie)
- Cookie 클래스를 이용
- 클라이언트에 저장 
  - 웹브라우저 메모리(default)
  - 파일 (time-out 지정 가능)
- **문자열만 저장 가능**
- 도메인 당 300개만 저장 가능
- 보안이 취약함(클라이언트에 저장됨으로)
- **클라이언트가 쿠키 사용 여부 결정 가능**<br>
    -> 개발자가 쿠키를 사용해 개발했을 경우 쿠키 사용이 불가능해진다.<br>
    -> **_가장 큰 단점_**

<br>

### **쿠키 사용 방법**
1. 쿠키 생성
    ```java
        Cookie c = new Cookie(key, value);
        /* 
            time-out 지정 가능
            -> 지정 시 파일에 저장(기본=브라우저 저장)
            c.setMaxAge(시간);
        */
    ```
2. 쿠키에 데이터 저장
    ```java
        response.addCookie(c);
    ```
3. 쿠키 얻기
    ```java
        Cookie[] x = request.getCookies();  // cookie가 아닌 Cookies 복수임!
        for (Cookie c : x) {
            if (c.getName().equals(key)) {
                String 변수 = c.getValue();
            }
        }
    ```


## 기타 유용한 정보
- 컬렉션에 저장된 데이터를 반복적으로 추출 방법<br>
   (컬렉션: List계열, Set계열, Map계열)
	1. Iterator(인터페이스)
		- 메소드
          - hasNext(): boolean
          - next(): Object
          - remove()
        - 사용방법
            ```java 
 		        Iterator ite = list.iterator();
			    // Iterator ite = set.iterator();
		
		        while(ite.hasNext()) {
			        변수 = ite.next();
		        }
            ```

    2. Enumeration(인터페이스)
	    - hasMoreElements(): boolean
		- nextElement(): Object
		- Iterator과 사용방법 동일
    
    <br>

	    ※ Iterator과 Enumeration 모두 인터페이스이기 때문에 선언 시 new 사용 불가능!!!<br>
		-> Literator ite = (  ).메소드();<br>	
		-> Enumeration enu = ( ).메소드();

- 유일한 값 얻는 방법
    - UUID.randomUUID().toString();
    - System.currentTimeMillis();