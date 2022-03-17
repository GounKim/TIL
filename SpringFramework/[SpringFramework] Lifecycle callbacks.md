# Lifecycle callbacks
###### https://docs.spring.io/spring-framework/docs/4.3.30.RELEASE/spring-framework-reference/html/beans.html#beans-factory-lifecycle

### Callback
빈 생성 / 빈 소멸 관련 callback 처리<br>
  - IOC Container: 빈 관리(빈 생성 ~ 빈 소멸)
    - 빈 생성 시 임의의 메소드 call => 추가작업 _초기화_ 진행
    - 빈 소멸 시 임의의 메소드 call => 추가 작업 _자원 반납_ 진행

<br>
  
3가지 방법
  - 태그 속성(xml)
    - init-method
    - destroy-method
  - 인터페이스
    - InitializingBean
    - DisposableBean
  - 어노테이션
    - @PostConstruct
    - @PreDestroy
    - 어노테이션 활성화 코드 지정 필수

<br>

## 1. Use Method
빈 등록 시 init-method / destroy-method 사용
 
### 빈(Bean) 생성
```java
    public class ExampleBean {
	private static final Logger logger = LoggerFactory.getLogger(ExampleBean.class); 
	
	// init-method에서 호출
	public void init() {
		logger.info("init 메소드 호출");
	}
	
	// destroy-method에서 호출
	public void cleanup() {
		logger.info("cleanup 메소드 호출");
	}
}
```

### 빈(Bean) 등록
```xml
    <bean id="exampleInitBean" class="examples.ExampleBean" 
			init-method="init" destroy-method="cleanup" />
```

### IOC Container에게 xml 지정
```java
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:com/config/conf.xml");
		ctx.close();
```

<br>

## 2. Implementing interface
InitializingBean, DisposableBean 인터페이스 이용

### 빈(Bean) 생성
```java
    public class ExampleBean implements InitializingBean, DisposableBean {
	private static final Logger logger = LoggerFactory.getLogger(ExampleBean.class); 

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("afterPropertiesSet 메소드 호출");
	}

	@Override
	public void destroy() throws Exception {
		logger.info("destroy 메소드 호출");
	}

}
```

### 빈(Bean) 등록
```xml
    <bean id="exampleInitBean" class="examples.ExampleBean" />
```

### IOC Container에게 xml 지정
```java
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:com/config/conf.xml");
		ctx.close();
```

<br>

## 3. 어노테이션(@) 이용
###### https://docs.spring.io/spring-framework/docs/4.3.30.RELEASE/spring-framework-reference/html/beans.html#beans-postconstruct-and-predestroy-annotations
@PostConstruct / @PreDestroy 어노테이션 이용

### 빈(Bean) 생성
```java
    public class ExampleBean {
	private static final Logger logger = LoggerFactory.getLogger(ExampleBean.class); 

	@PostConstruct
	public void populateMovieCache() {
		logger.info("populateMovieCache 메소드 호출");
	}
	
	@PreDestroy
	public void clearMovieCache() {
		logger.info("clearMovieCache 메소드 호출");
	}
}
```

### 빈(Bean) 등록
```xml
    <!-- 어노테이션 활성화 코드 (namespace -> context 체크) -->
	<context:annotation-config />
    <!-- 빈 등록 -->
	<bean id="exampleInitBean" class="examples.ExampleBean" />
```

### IOC Container에게 xml 지정
```java
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:com/config/conf.xml");
		ctx.close();
```