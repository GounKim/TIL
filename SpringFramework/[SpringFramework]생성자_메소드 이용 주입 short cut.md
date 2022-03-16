# DI (Dependency Injection)
###### https://docs.spring.io/spring-framework/docs/4.3.30.RELEASE/spring-framework-reference/html/beans.html#beans-p-namespace
1. [생성자 이용 주입 SHORT CUT](#생성자-이용-주입-short-cut)
2. [메소드 이용 주입 SHORT CUT](#메소드-이용-주입-short-cut)

<br><br>

## 생성자 이용 주입 SHORT CUT
1. [Person/Cat 빈(bean) 생성]([SpringFramework]%20생성자%20이용%20주입.md)
2. ### p-namespace 삽입
    xml파일-> Namespace -> c 체크
    ```
    xmlns:p="http://www.springframework.org/schema/c"
    ```

3. ### 빈(Bean) 등록
    ```xml
    <!-- 
        Cat cat = new Cat("나비");
    -->
     <bean id="cat" class="com.dto.cat" p:name="나비" />

    <!-- 
        Person person = new Person("홍길동", 20, cat);
	 -->
   <bean id="person" class="com.dto.Person" c:name="홍길동" c:age="20" c:cat-ref="cat" />

    ```

4. ### IOC Container에게 xml 지정
    ```java
    ApplicationContext ctx = new GenericXmlApplicationContext("classpath:com/config/person.xml");
      Cat cat = ctx.getBean("cat", Person.class);
      System.out.println(cat.getName()); // 출력: 나비

      Person person = ctx.getBean("person", Person.class);
      System.out.println(person)); // 출력: Person [name=홍길동, age=20, cat=Cat [name=나비]]
    ```

<br><br><br>

## 메소드 이용 주입 SHORT CUT
1. ### [Person/Cat 빈(bean) 생성]([SpringFramework]%20메소드%20이용%20주입.md)
2. ### p-namespace 삽입
    xml파일-> Namespace -> p 체크
    ```
    xmlns:p="http://www.springframework.org/schema/p"
    ```

3. ### 빈(Bean) 등록
    ```xml
    <!-- 
        Cat cat = new Cat();
        cat.setName("나비");
    -->
    <bean id="cat" class="com.dto.cat" p:name="나비" />

    <!-- 
        Person person2 = new Person();
        person2.setName("홍길동2");
        person2.setAge(20);
        person2.setCat(cat);  // 다른 빈(bean) 삽입
	 -->
   <bean id="person" class="com.dto.Person" p:name="홍길동" p:age="20" p:cat-ref="cat" />

    ```

4. ### IOC Container에게 xml 지정
    ```java
      ApplicationContext ctx = new GenericXmlApplicationContext("classpath:com/config/person.xml");
      Cat cat = ctx.getBean("cat", Cat.class);
      System.out.println(cat.getName()); // 출력: 나비

      Person person = ctx.getBean("person", Person.class);
      System.out.println(person)); // 출력: Person [name=홍길동, age=20, cat=Cat [name=나비]]

    ```

> ※ 생성자 이용 주입과 메소드 이용 주입 혼합 사용 가능
