###### https://docs.spring.io/spring-framework/docs/4.3.30.RELEASE/spring-framework-reference/html/beans.html#context-functionality-messagesource

# I18N (Internationalization, 국제화)
I18N/i18n은 국제화(internationalization)의 영어 철자에서 첫 글자와 마지막 글자만 뺀 나머지 글자들을 그 숫자로 표시한 것이다. 즉, 'I' + 18글자 + 'N'을 뜻한다.

<br>

### SW 국제화(i18n)란?
SW가 특정 지역이나 언어제 종속되지 않고 다양한 지역, 언어에서 정상 동작하도록 **국제적으로 통용되는 SW를 설계하고 개발하는 과정**이다.

<br>

- Messagesource 빈 이용
- 초창기에는 각 버전의 코드 작성 => 현재는 각 언어 문자를 저장하는 파일(*.properties) 작성
    - 파일명: 파일명_국가_언어.properties
        - ex) hello_ko.properties
            ```properties
            hello=안녕하세요
            ```  
        - ex) hello_en.properties 
            ```properties
            hello=Good Morning
            ```  
- getMessage() 메소드
  - String getMessage(String code, Object[] args, String default, Locale loc)
  - String getMessage(String code, Object[] args, Locale loc)
  - String getMessage(MessageSourceResolvable resolvable, Locale locale)
  
  
<br><br>

## Spring의 국제화
### .properties 파일
```properties
    # hello_ko.properties
    greet=안녕하세요.
    greet2=이름:{0}
    greet3=이름:{0} 나이:{1}
```
```properties
    # hello_en.properties
    greet=Good Morning
    greet2=name:{0}
    greet3=name:{0} age:{1}
```

### 방법1: main에서 주입
1. 빈(bean) 등록
   - **_반드시_ id를 messageSource로 설정해야지만 적용된다.**
    ```xml
	<bean id="messageSource" class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
		<property name="basenames">
			<list>
				<value>classpath:com/config/hello</value>
			</list>
		</property>
		<property name="defaultEncoding" value="utf-8" />
	</bean>
    ```

2. IOC Container에게 xml 지정
    ```java
        // ApplicationContext는 MessageSource와 상속관계
        ApplicationContext ctx = new GenericXmlApplicationContext("classpath:com/config/user.xml");
            
        String mesg = ctx.getMessage("greet", null, "Hello!", Locale.KOREA);
        System.out.println(mesg);
            
        String mesg2 = ctx.getMessage("greet2", new Object[] {"hong"}, "Hello!", Locale.KOREA);
        System.out.println(mesg2);
            
        String mesg3 = ctx.getMessage("greet3", new Object[] {"hong", 20}, "Hello!", Locale.KOREA);
        System.out.println(mesg3);
    ```

### 방법2: main 아닌 곳에서 주입
1. 빈 생성
    ```java
    public class Example {
	
        MessageSource messageSource;

        // setter 메소드 주입
        public void setMessageSource(MessageSource messageSource) {
            this.messageSource = messageSource;
        }
        
        public void printMessage() {
            String mesg = messageSource.getMessage("greet", null, "Hello!", Locale.KOREA);
            System.out.println(mesg);
        }
    }
    ```
2. 빈(bean) 등록
   ```xml
    <bean id="exam" class="com.foo.Example">
		<property name="messageSource" ref="messageSource" />
	</bean>
   ```

3. IOC Container에게 xml 지정
   ```java
    // ApplicationContext는 MessageSource와 상속관계
	ApplicationContext ctx = new GenericXmlApplicationContext("classpath:com/config/user.xml");
		
	Example exam = ctx.getBean("exam", Example.class);
	exam.printMessage();
   ```