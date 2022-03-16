# Use Collections
###### https://docs.spring.io/spring-framework/docs/4.3.30.RELEASE/spring-framework-reference/html/beans.html#beans-collection-elements

<br>

### 2가지 사용 방법 존재
1. 일반적인 방법
2. util Namespace 이용
   - 장점: 재사용 가능 

<br>

> ### 필요한 코드
> 1. [빈(bean) 생성](#필요한-빈bean)
> 2. Cat 빈 등록
>       ```xml
>       <bean id="cat1" class="com.dto.Cat">
>           <property name="name" value="나비" />
>       </bean>
>    
>       <bean id="cat2" class="com.dto.Cat">
>           <property name="name" value="나비2" />
>       </bean>
>       ```
><br>

<br><br>

## Collection - **list**
    순서 존재 => 삽입 순서대로 저장됨 & __중복 가능__

1. 빈(bean) 등록 
    - list 이용 방법1
        ```xml
        <bean id="p1" class="com.dto.Person">
            <property name="catList">
                <list>
                    <ref bean="cat1"/>
                    <ref bean="cat2"/>
                </list>
            </property>
            <property name="emailList">
                <list>
                    <value>aaa@daum.net</value>
                    <value>aaa@naver.com</value>
                </list>
            </property>
        </bean>
        ```

    - list 이용 방법2 : util Namespace 이용
        ```xml
        <util:list id="globalCatList">
            <ref bean="cat1"/>
            <ref bean="cat2"/>
            <ref bean="cat1"/>
            <ref bean="cat2"/>
        </util:list>
            
        <util:list id="globalEmailList">
            <value>aaa@daum.net</value>
            <value>aaa@naver.com</value>
        </util:list>

        <bean id="p2" class="com.dto.Person">
            <property name="catList" ref="globalCatList" />
            <property name="emailList" ref="globalEmailList" />
        </bean>
        ```

2. IOC Container에게 xml 지정
    ```java
    ApplicationContext ctx = new GenericXmlApplicationContext("classpath:com/config/test.xml"); //IoC Container
		
    Person p1 = ctx.getBean("p1", Person.class);    // 방법2 출력시 p2로 변경 (이후 코드 동일)
	List<String> emailList = p.getEmailList();
	for (String email : emailList) {
		System.out.println("이메일: " + email);
	}
	
	List<Cat> catList = p.getCatList();
	for(Cat c: catList) {
		System.out.println("고양이 이름: " + c.getName());
	}
    ```

<br>

## Collection - **set**
순서 존재X => __중복 *불*가능__
1. 빈(bean) 등록 
    - set 이용 방법1
        ```xml
        <bean id="p1" class="com.dto.Person">
            <property name="catSet">
                <set>
                    <ref bean="cat1"/>
                    <ref bean="cat2"/>
                </set>
            </property>
            <property name="emailSet">
                <set>
                    <value>aaa@daum.net</value>
                    <value>aaa@naver.com</value>
                </set>
            </property>
        </bean>
        ```

    - set 이용 방법2 :  util Namespace 이용
        ```xml
        <util:set id="globalCatSet">
            <ref bean="cat1"/>
            <ref bean="cat2"/>
            <ref bean="cat1"/>
            <ref bean="cat2"/>
        </util:set>
            
        <util:set id="globalEmailSet">
            <value>aaa@daum.net</value>
            <value>aaa@naver.com</value>
        </util:set>
            
        <bean id="p1" class="com.dto.Person">
            <property name="catSet" ref="globalCatSet" />
            <property name="emailSet" ref="globalEmailSet" />
        </bean>
        ```
   
2. IOC Container에게 xml 지정
    ```java
    ApplicationContext ctx = new GenericXmlApplicationContext("classpath:com/config/test.xml"); //IoC Container
		
    Person p1 = ctx.getBean("p1", Person.class);    // 방법2 출력시 p2로 변경 (이후 코드 동일)
	Set<String> emailSet = p.getEmailSet();
	for (String email : emailSet) {
		System.out.println("이메일: " + email);
	}
	
	List<Cat> catSet = p.getCatSet();
	for(Cat c: catSet) {
		System.out.println("고양이 이름: " + c.getName());  // set은 중복이 불가능 하므로 두 코드의 출력 결과는 동일
	}
    ```

## Collection - **map**
1. 빈(bean) 등록 
   - map 이용 방법1
        ```xml
        <bean id="p1" class="com.dto.Person">
            <property name="catMap">
                <map>
                    <entry key="c1" value-ref="cat1" />
                    <entry key="c2">
                        <ref bean="cat2"/>
                    </entry>
                </map>
            </property>
            <property name="emailMap">
                <map>
                    <entry key="e1" value="aaa@daum.net" />
                    <entry key="e2">
                        <value>aaa@naver.com</value>
                    </entry>
                </map>
            </property>
        </bean>
        ```

    - map 이용 방법2 :  util Namespace 이용
        ```xml
        <util:map id="globalCatMap">
            <entry key="c1" value-ref="cat1" />
            <entry key="c2">
                <ref bean="cat2"/>
            </entry>
        </util:map>
         
        <util:map id="globalEmailMap">
            <entry key="e1" value="aaa@daum.net" />
            <entry key="e2">
                <value>aaa@naver.com</value>
            </entry>
        </util:map>
          
        <bean id="p1" class="com.dto.Person">
            <property name="catMap" ref="globalCatMap" />
            <property name="emailMap" ref="globalEmailMap" />
        </bean>
        ```
   
2. IOC Container에게 xml 지정
    ```java
    ApplicationContext ctx = new GenericXmlApplicationContext("classpath:com/config/test.xml"); //IoC Container
		
    Person p1 = ctx.getBean("p1", Person.class);    // 방법2 출력시 p2로 변경 (이후 코드 동일)
	Map<String, String> emailMap = p1.getEmailMap();
	Set<String> keys = emailMap.keySet();
	for (String key : keys) {
		System.out.println("이메일: " + emailMap.get(key));
	}
		
	Map<String, Cat> catMap = p1.getCatMap();
	Set<String> keys2 = catMap.keySet();
	for(String key: keys2) {
		Cat c = catMap.get(key);
		System.out.println("고양이 이름: " + c.getName());
	}
    ```


## Collection - **props**
1. 빈(bean) 등록 
   - props 이용 방법1
        ```xml
        <bean id="p1" class="com.dto.Person">
            <property name="emailProp">
                <props>
                    <prop key="e1">aaa@daum.net</prop>
                    <prop key="e2">aaa@naver.com</prop>
                </props>
            </property>
        </bean>
        ```

    - props 이용 방법2 :  util Namespace 이용
        ```xml
        <util:properties id="globbalEmailProps">
            <prop key="e1">aaa@daum.net</prop>
            <prop key="e2">aaa@naver.com</prop>
        </util:properties>
          
        <property name="emailProp" ref="globbalEmailProps" />
        ```
   
2. IOC Container에게 xml 지정
    ```java
    ApplicationContext ctx = new GenericXmlApplicationContext("classpath:com/config/test.xml"); //IoC Container
		
	Person p1 = ctx.getBean("p1", Person.class);    // 방법2 출력시 p2로 변경 (이후 코드 동일)
	Properties emailProp = p1.getEmailProp();
	Set<String> keys = emailProp.stringPropertyNames();
	for (String key : keys) {
		System.out.println("이메일: " + emailProp.get(key));
	}
    ```


<br><br>

---
<br>

## 필요한 빈(bean)

``` java
    public class Person {

	// 여러 개의 Email 관리
	private List<String> emailList;
    private Set<String> emailSet;
    private Map<String, String> emailMap;
    Properties emailProp;
	
	// 여러개의 Cat 관리
	private List<Cat> catList;
    private Set<Cat> catSet;
	private Map<String, Cat> catMap;

	// 각각 getter/setter 메소드 (코드생략)
}
```

```java
    public class Cat {
        private String name;
        
        public Cat() {
            
        }
        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
```
