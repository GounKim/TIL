# DI (Dependency Injection)
###### https://docs.spring.io/spring-framework/docs/4.3.30.RELEASE/spring-framework-reference/html/beans.html#beans-setter-injection
## 메소드 이용 주입 (Setter-based dependency injection)
 
- ### 빈(Bean) 생성
  패키지 위치:<br>&nbsp;&nbsp;
    src/main/java<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        └ com/dto/Person.java<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        └ com/dto/Cat.java<br>
  ***setter 메소드 주입 필요***
    ```java
        public class Person {
            private String name;    // null => 외부에서 문자열을 주입
            private int age; 		// 0	=> 외부에서 값을 주입
            private Cat cat;

            // setter 메소드 주입
            public void setName(String name) {
                this.name = name;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public void setMesgNum(String name, int age) {
                this.name = name;
                this.age = age;
            }

            public void setCat(Cat cat) {
                this.cat = cat;
            }

            public String getName() {
                return name;
            }

            public int getAge() {
                return age;
            }

            @Override
            public String toString() {
                return "Person [name=" + name + ", age=" + age + ", cat=" + cat + "]";
            }
        }
    ```
    ```java
    public class Cat {
        private String name;
	
        public Cat(String name) {
            this.name = name;
        }
        
        public void setName(String name) {
		    this.name = name;
	    }

        @Override
        public String toString() {
            return "Cat [name=" + name + "]";
        }
}
    ```

- ### 빈(Bean) 등록 : Spring Bean Configuration File 이용
    패키지 위치:<br>&nbsp;&nbsp;
    src/main/resources<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        └ com/config/Person.xml
    ```xml
    <!-- 
            name: 파라미터 변수명
            value: 문자열|기본형
            ref: 참조형
        -->

    <!-- 
        Person person = new Person();
        person.setName("홍길동"); 과 동일
    -->
    <bean id="person" class="com.dto.Person">
		<property name="name" value="홍길동" />
	</bean>

    <bean id="person2" class="com.dto.Person">
		<property name="age" value="20" />
	</bean>

    <bean id = "person3" class="com.dto.Person">
        <property name="name" value="이순신" />
        <property name="age" value="44" />
	</bean>

    <!-- 다른 Bean 포함하는 생성자 주입-->
    <!--
        Cat catDTO = new CatDTO();
        Person person = new Person();
        person.setCat(catDTO); 와 동일
     -->
    <bean id="catDTO" class="com.dto.Cat">
        <property name="name" value="나비" />
    </bean>
    <bean id = "person4" class="com.dto.Person">
        <property name="name" value="홍길동" />
        <property name="age" value="24" />
        <property name="cat" ref="catDTO" />
	</bean>
    ```

- ### IOC Container에게 xml 지정
    ```java
        // IOC 컨테이너 생성 => xxxApplicationContext 이용
        ApplicationContext ctx = new GenericXmlApplicationContext("classpath:com/config/person.xml");
        
        // 생성된 빈 접근
        Person person = ctx.getBean("person", Person.class);
		System.out.println(person.getName());     // 출력: 홍길동

		Person person2 = ctx.getBean("person2", Person.class);
		System.out.println(person2.getAge());    // 출력: 20

        Person person3 = ctx.getBean("person3", Person.class);
		System.out.println(person3.getName() + ", " + person3.getAge());  // 출력: 이순신, 44

        Person person4 = ctx.getBean("person4", Person.class);
		System.out.println(person4);    // 출력: Person [name=홍길동, age=24, cat=Cat [name=나비]]
    ```
    
    > IOC Container
    > - 파라미터 개수 제한 없음 
    > - 외부에 존재하는 파일의 경우 file:을 명시<br>
    >   "file:c:\\user\\Person.xml"
    > - 내부 파일이여도  classpath:를 명시해주면 좋음

<br><br>

> ※ 생성자 이용 주입과 메소드 이용 주입 혼합 사용 가능