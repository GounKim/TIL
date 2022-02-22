# MyBatis 초기 설정

### jdbc의 문제
- SQL코드가 java안에 존재
	- 변경시 재컴파일 진행 필요
	- 재사용 불가능
- 예외처리 필수로 코드가 불필요하게 복잡해짐(예외처리가 많음)
	- SQLException발생 => complie check exception임
- connection 맺기까지 코드가 복잡

<br>

## MyBatis
### MyBatis의 장점
- mybatis가 내부적으로 jdbc를 사용함
- SQL코드가 외부파일에 저장됨(xml파일 -> mapper파일)
- DB설정정보 간소화(xml파일)
- 예외처리가 옵션임<br>
	=> jdbc를 사용하기 때문에 jdbc에서 예외처리가 발생하지만, mybatis가 이를 runtime exception으로 변경해줌<br>
	(complie checked를 complie unchecked로 변경)
- connection, preparedStatement, ResultSet, class.forName()등 전혀사용X<br>
	=> sqlSessionFactory 와 sqlSession만 알면됨

### 필요한 것
- 드라이버(jdbc사용) + mybatis라이브러리(mybatis.jar)

### 설치과정
1. https://blog.mybatis.org/ ->products -> MyBatis3의 links의 download ->mybatis-3.5.9.zip다운로드
2. 프로젝트에 build path 진행 (mybatis.jar & jdbc.jar)
	- C:\playdata\00.sw\00.lib\mybatis-3.5.9\mybatis-3.5.9.jar
	- C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib\ojdbc6_g.jar

---
프로젝트 만들기

1. 2개의 xml필요
   - DB설정 정보 
     - CONFIGURATION.XML / SQL_CONFIG.XML
     - 참고: https://mybatis.org/mybatis-3/configuration.html#
        ```xml
        <?xml version="1.0" encoding="UTF-8"?>
        <!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
        <configuration>

            <!-- jdbc.properties 등록 -->
            <properties resource="com/config/jdbc.properties"> </properties>

            <!-- DTO에 별칭 -->
            <typeAliases>
                <typeAlias type="com.dto.goods.GoodsDTO" alias="GoodsDTO"/>
            </typeAliases>

            <!-- DB연동 -->
            <environments default="development">
                <environment id="development">
                <transactionManager type="JDBC"/>
                <dataSource type="POOLED">
                    <property name="driver" value="${jdbc.oracle}"/>
                    <property name="url" value="${jdbc.url}"/>
                    <property name="username" value="${jdbc.userid}"/>
                    <property name="password" value="${jdbc.passwd}"/>
                </dataSource>
                </environment>
            </environments>
            <mappers>
                <mapper resource="com/config/GoodsMapper.xml"/>
                <mapper resource="com/config/MemberMapper.xml"/>
            </mappers>
        </configuration>
        ```

   - SQL 저장용 -> MAPPER파일 (테이블당 한개씩)
     - ex) DEPT => DEPTMAPPER.XML
        ```xml
        <?xml version="1.0" encoding="UTF-8"?>
        <!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
        <mapper namespace="com.config.GoodsMapper">
					
            <!-- 이곳에 SQL 작성 -->
            <select id="goodsList" parameterType="string" resultType="GoodsDTO">	
                <![CDATA[
                    SELECT	gCode, 
                            gCategory, 
                            gName, 
                            gContent, 
                            gPrice, 
                            gImage
                    FROM	goods
                    WHERE	gCategory = #{gCategory}
                ]]>
            </select>
        
        </mapper>
        ```
<br>

2. properties파일 만들기(DB 정보 저장)
	- 이후 configuration.xml과 연동시킴
    ```properties
    #문법은 key=value
    jdbc.oracle=oracle.jdbc.driver.OracleDriver
    jdbc.url=jdbc:oracle:thin:@localhost:1521:xe
    jdbc.userid=SCOTT
    jdbc.passwd=TIGER
    ```

> ${ }: jdbc.properties의 key, value를 불러올 때 사용<br>
> #{ }: 바인딩변수, jdbc에서의 ? 기능
