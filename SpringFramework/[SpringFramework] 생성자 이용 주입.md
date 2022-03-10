# DI (Dependency Injection)

## 생성자 이용 주입 (Constructor-based Injection)
 
- ### 빈(Bean) 생성
  패키지 위치:<br>&nbsp;&nbsp;
    src/main/java<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        └ com/dto/Person.java
    ```java
        public class Person {
            private String name;    // null => 외부에서 문자열을 주입
            private int age; 		// 0	=> 외부에서 값을 주입
            private Cat cat;

            // 기본 생성자
            public Person() { }

            // 생성자 주입
            public Person(String n) {
                name = n;
            }

            public Person(String n, int a) {
                this.name = n;
                this.age = a;
	        }

            public Person(String name, int age, Cat cat) {
                this.name = name;
                this.age = age;
                this.cat = cat;
            }

            public String getName() {
                return name;
            }

            public int getAge() {
                return age;
            }

            public Cat getCat() {
                return cat;
            }   

            @Override
            public String toString() {
                return "Person [name=" + name + ", age=" + age + ", cat=" + cat + "]";
            }
        }
    ```

- ### 빈(Bean) 등록 : Spring Bean Configuration File 이용
    패키지 위치:<br>&nbsp;&nbsp;
    src/main/resources<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        └ com/config/Person.xml
    ```xml
    <!-- 
        기본 생성자 호출 
        Person person = new Person(); 와 동일
    -->
    <bean id="person" class="com.dto.Person" />

    <!-- 
        파라미터有 생성자 호출 
        Person person = new Person("helloWorld"); 와 동일
    -->
    <bean id = "person2" class="com.dto.Person">
        <!-- 
            name: 파라미터 변수명
            value: 문자열|기본형
            ref: 참조형
        -->
        <constructor-arg name="m" value="이름" />
	</bean>

    <bean id = "person3" class="com.dto.Person">
        <constructor-arg name="m" value="이순신" />
        <constructor-arg name="n" value="44" />
	</bean>

    <!-- 다른 Bean 포함하는 생성자 주입-->
    <bean id="cat" class="com.dto.Cat">
        <constructor-arg name="name" value="나비" />
    </bean>
    <bean id = "person4" class="com.dto.Person">
        <constructor-arg name="name" value="홍길동" />
        <constructor-arg name="age" value="24" />
        <constructor-arg name="cat" ref="cat" />
	</bean>
    ```

- ### IOC Container에게 xml 지정
    ```java
        // IOC 컨테이너 생성 => xxxApplicationContext 이용
        ApplicationContext ctx = new GenericXmlApplicationContext("classpath:com/config/person.xml");
        
        // 생성된 빈 접근
        Person person = ctx.getBean("person", Person.class);
		System.out.println(person.getName());     // 출력: null

		Person person2 = ctx.getBean("person2", Person.class);
		System.out.println(person2.getName());    // 출력: 이름

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
