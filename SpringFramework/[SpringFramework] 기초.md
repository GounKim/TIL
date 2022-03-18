# Spring Framework

### 개요
이전에는 EJB를 주 프레임워크로 사용하다 
Road Johnson이 발간한 책인 ‘Export One-on-One J2EE Development without EJB’가 Spring 프레임 워크의 모태가 되어 Spring이 개발, 사용되기 시작하였다.

<br>

### 특징
1. POJO (Plain Old Java Object) 방식
2. AOP (Aspect Oriented Programming, 관점 지향 프로그래밍)
3. DI(Dependency Injection, 의존성 주입)
4. IoC(Inversion of Control, 제어 역전)
5. 생명주기 관리

<br>

### Spring Framework 개발 방법
1. xml 이용
2. 어노테이션(@) + 최소한의 xml 이용
3. 어노테이션(@) 이용  (Java Coding Configuration)<br>
	=> Spring Boot의 기반임

<br>

### Spring Framework 실행순서
1. 빈 생성(클래스 생성)
2. xml등록
	```xml
	<bean id=" " class="com.dto.A" />
	<bean id=" " class="com.dto.B" />
	```
3. IOC Container에게 xml 인식
	```java
	// xxxApplicationContext 사용
	ApplicationContext ctx = new GenericXmlApplicationContext("classpath:com/config/test.xml");
	```
5. 빈 얻기
   ```java
	ctx.getBean("id", A.class);
	```

---
<br>

### 사용 Spring Framework
1. 버전 : 4.3.30 release
2. JDK 버전 : 1.8
3. DB : oracle 11g Express Edition 버전, mybatis
4. 서블릿/JSP버전
5. 빌드 툴(buildTool): 소스 작성 후 이후 작업을 모두 해주는 툴 <br>
    => 대표적인 툴: marven(과거~현재) / Gradle(현재))
        
        이후 작업
        - 외부 라이브러리
        - 컴파일
		- 단위테스트
		- 패키징(압축) -비웹(jar)/웹(war
		- 배포
		
    를 모두 해주는 툴이 빌드툴
			대표적인 툴: marven(과거~현재) / Gradle(현재)

	※ 단위 테스트란?
		main, service, dao를 모두 작성하고 실행해본 후 올바르게 실행되는지 확인하는 것이 아니라<br>
		각각 실행하여 오류가 나지 않는 것을 확인할 수 있다.<br>
		=> 습관화 해야함
6. 로깅 : logback
7. 버전관리(형상관리) : Git / Github