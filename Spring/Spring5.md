# 引言

## 1、什么是Spring？

Spring一个轻量级的JavaEE解决方案，整合众多优秀的设计模式。

```markdown
1. 轻量级：对运行环境没有额外要求，代码移植性高。
2. 解决方案：整合了Struts2、Mybatis等。
3. 设计模式：工厂、代理、模板、策略。
```



## 2、工厂设计模式

### 2.1 什么是工厂设计模式

```markdown
1. 概念：通过工厂类，创建对象。
2. 好处：解耦合
```



### 2.2 简单工厂模式

```properties
#-----applicationContext.properties

userService = com.aizhong.service.UserServiceImpl
userDao = com.aizhong.dao.UserDaoImpl
```

```java
//-----BeanFactory.java
public class BeanFactory {
    private static Properties env = new Properties();

    static {
        try {
            //获得IO输入流
            InputStream inputStream = BeanFactory.class.getResourceAsStream("/applicationContext.properties");
            //文件内容分装
            env.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        对象的创建方式：
            1、直接通过调用构造方法 UserService userService = new UserService();
            2、通过反射的形式，创建对象，解耦合
     */
    public static UserService getUserService() {
        //return new UserServiceImpl();
        UserService userService = null;
        try {
            Class clazz = Class.forName(env.getProperty("userService"));
            userService = (UserService) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return userService;
    }

    public static UserDaoImpl getUserDao() {
        UserDaoImpl userDaoImpl = null;
        try {
            Class clazz = Class.forName(env.getProperty("userDao"));
            userDaoImpl = (UserDaoImpl) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userDaoImpl;
    }
}

//-----AppTest.java
/**
*  用于测试：简单工厂
*/
@Test
public void test1(){
    //        UserServiceImpl userService = new UserServiceImpl();
    //        userService.saveUser(new User());
    UserService userService = BeanFactory.getUserService();
    userService.saveUser(new User());
}
```



### 2.3 通用工厂模式

简单工厂存在大量代码冗余，通用工厂如下。

```java
//-----BeanFactory.java
public class BeanFactory {
    private static Properties env = new Properties();

    static {
        try {
            //获得IO输入流
            InputStream inputStream = BeanFactory.class.getResourceAsStream("/applicationContext.properties");
            //文件内容分装
            env.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
            对象的创建方式：
                1、直接通过调用构造方法 UserService userService = new UserService();
            key 小配置文件中的key
    */
    public static Object getBean(String key) {
        Object obj = null;
        Class clazz = null;
        try {
            clazz = Class.forName(env.getProperty(key));
            obj = clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return obj;
    }
}

//-----AppTest.java
/**
*  用于测试：通用工厂
*/
@Test
public void test1(){
    UserService userServiceImpl = (UserService) BeanFactory.getBean("userService");
    userServiceImpl.saveUser(new User());
}
```



## 3、总结

Spring的本质是一个工厂（applicationContext.xml）



# 第一个Spring

## 1、环境搭建

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.3.9</version>
</dependency>
```



## 2、Spring的配置文件

```markdown
1. 配置文件的放置位置：任意位置 没有硬性要求
2. 配置文件命名：没有硬性要求 建议：applicationContext.xml
```



## 3、Spring的核心API

-   ApplicationContext

```markdown
1. 作用：Spring提供的ApplicationContext工厂，用于对象的创建
2. 好处：解耦合
```

-   ApplicationContext为借口类型

```markdown
1. 接口：屏蔽实现的差异
2. 非web环境：ClassPathXmlApplicationContext
3. web环境：XmlWebApplicationContext
```

-   ApplicationContext为重量级资源

```markdown
1. ApplicationContext工厂的对象占用大量内存
2. 不会频繁的创建对象：
		一个应用只会创建一个工厂对象
3. ApplicationContext工厂：
		一定是线程安全的（多线程并发访问）
```



## 4、程序开发

```xml
<!--applicationContext.xml-->
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="user" class="com.aizhong.entity.User"/>
</beans>
```

```java
/**
*  用于测试：获取对象
*/
@Test
public void test2(){
    ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
    // 1、只通过bean的id获取，需要强转
    //User user = (User) ctx.getBean("user");
    // 2、只通过类型获取，要求工厂中同种class类型的bean只有一个
    //User user = ctx.getBean(User.class);
    // 3、通过id加class类型
    User user = ctx.getBean("user",User.class);
    System.out.println("user = " + user.toString());
}
```



## 5、细节分析

-   名词解释

```markdown
Spring工厂创建的对象，叫做bean或者组件
```

-   Spring工厂的的相关方法

```java
ApplicationContext tcx = new ClassPathXmlApplicationContext("/applicationContext.xml");
//获取Spring工厂配置文件中所有bean标签的id的值
String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
//根据类型获得Spring配置文件中对应的id值
String[] beanNamesForType = ctx.getBeanNamesFOrType(Person.class);
//判断是否存在指定的id值的bean，不能判断 name 值
boolean isExist = ctx.containBeanDefinition("a");
//判断是否存在指定的id值的bean，可以判断 name 值
boolean isExist = ctx.containBean("a");
```

-   配置文件中的细节

```markdown
1. 只配置class属性 <bean class=""/>
	a) Spring工厂会自动分配一个名字 （包名.类名#序号）
	b) 适合用于这个bean只用一次
2. name属性
	a) 作用：在Spring配置文件中，为bean对象定义别名。
	b) 相同点：
		getBean(id)或getBean(name)都可以创建或获取对象
	   	在配置文件中 id 与 name 属性等效
	c) 区别：
		别名可以定义多个,但是 id 属性只能有⼀个值。
		XML 的 id 属性的值，命名要求：必须以字母开头，可以包含 字母、数字、下划线、连字符；不能以特		  殊字符开头 “/person”。XML 的 name 属性的值，命名没有要求，“/person” 可以。
		**但其实 XML 发展到了今天：ID属性的限制已经不存在，“/person”也可以。
```



## 6、思考

问题：未来在开发过程中，是不是所有的对象，都会交给 Spring 工厂来创建呢？

回答：理论上是的，但是有特例 ：**实体对象(entity)** 是不会交给Spring创建，它由持久层框架进行创建。



# 整合日志

Spring5.X整合log4j。

引入依赖

```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-log4j12</artifactId>
    <version>1.7.26</version>
</dependency>
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```

在resource目录下创建**log4j**.properties文件

```properties
### 配置根
log4j.rootLogger = debug,console

### 日志输出到控制台显示
log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.Target=System.out
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n
```



# 注入（injection）

## 1、什么是注入

通过Spring工厂及配置文件，为所创建的对象的成员变量赋值。



## 2、为什么要注入

通过编码方式赋值存在耦合



## 3、如何注入

1.  为类的成员变量提供**set方法**或**构造方法**
2.  配置Spring配置文件

```xml
<bean id="user" class="com.aizhong.entity.User">
    <property name="name">
        <value>ai</value>
    </property>
    <property name="age">
        <value>20</value>
    </property>
</bean>
```

property标签的name属性对应需要赋值的成员变量的名字，value标签为值。这种注入方式为**set**注入。



## 4、set注入详解

### 4.1	set注入的变量类型

-   JDK内置类型：8种基本类型、String类型、List、Set、Map、Properties，复杂类型（Data。。）需要用户自定义类型转换器来解决。
-   用户自定义类型

针对不同类型变量的注入，需要在`<property>`标签中，需要嵌套其他标签。



### 4.2	JDK内置类型

```java
private String name;
private Integer age;
private String[] hobbys;
private List<String> emails;
private Set<String> qqs;
private Map<String, String> addrs;
private Properties p;
```

-   8种基本类型+String，可简化

```xml
<property name="name">
    <value>Aizz</value>
</property>
<property name="age" value="20"/>
```

-   数组+List

```xml
<property name="hobbys">
    <list>
        <value>game</value>
        <value>pingpong</value>
    </list>
</property>
<property name="emails">
    <list>
        <value>123@qq.com</value>
        <value>456@qq.com</value>
    </list>
</property>
```

-   Set

```xml
<property name="qqs">
    <set>
        <value>123</value>
        <value>456</value>
    </set>
</property>
```

-   Map，可简化

```xml
<property name="addrs">
    <map>
        <entry>
            <key><value>NO.1</value></key>
            <value>hunan</value>
        </entry>
        <entry key="NO.2" value="Hangzhou"/>
    </map>
</property>
```

-   Properties

```xml
<property name="p">
    <props>
        <prop key="key1">value1</prop>
        <prop key="key2">value2</prop>
    </props>
</property>
```



### 4.3	用户自定义类型

```java
private String name;
private Teacher teacher;
```

-   第一种方式

```xml
<bean id="student" class="com.aizhong.entity.Student">
    <property name="name" value="Aizz" />
    <property name="teacher">
        <bean class="com.aizhong.entity.Teacher"/>
    </property>
</bean>
```

问题：每创建一次Student对象，Teacher就会被创建一次，浪费资源，且代码存在冗余。

-   第二种方式

```xml
<bean id="student" class="com.aizhong.entity.Student">
    <property name="name" value="Aizz" />
    <property name="teacher" ref="teacher"/>
</bean>
<bean id="teacher" class="com.aizhong.entity.Teacher"/>
```

先创建一个用户自定义类型的bean，再将bean的id赋给<property>标签的ref属性。



## 5、构造注入

-   提供不同的构造方法

```java
public User(String name) {
    this.name = name;
}

public User(Integer age) {
    this.age = age;
}

public User(Integer age, String name) {
    this.age = age;
    this.name = name;
}
public User(String name, Integer age) {
        this.age = age;
        this.name = name;
    }

```

-   在配置文件中配置

```xml
<bean id="user1" class="com.aizhong.entity.User">
    <constructor-arg value="Aizz" type="java.lang.String"/>
        <constructor-arg value="20" type="java.lang.Integer"/>
</bean>
```



### 构造方法的重载（参数的个数、类型、顺序）

-   个数

由<constructor-arg>标签的数量来控制。

-   类型

由<constructor-arg>标签的 type 属性来控制。

-   顺序

由Spring选择一一对应的。



## 6、注入总结

![](img/注入总结.png)

```markdown
未来的实战开发中，应用set注入还是构造注入？
	set注入更多
		1、构造注入麻烦（重载）
		2、Spring框架底层大量应用了Set注入
```





# 控制反转 和 依赖注入

## 1、反转控制（IOC Inverse of Control）

-   控制：**对象的创建**和**成员变量的复制**的控制权
-   反转控制：把控制权，从代码反转（转移）到Spring工厂和配置文件中完成。
-   好处：解耦合
-   底层实现：工厂模式

![](img/控制反转.png)



## 2、依赖注入 (Dependency Injection - DI)

-   注入：通过 Spring 的工厂及配置文件，为对象（bean，组件）的成员变量赋值
-   依赖注入：当⼀个类需要另⼀个类时，就意味着依赖，⼀旦出现依赖，就可以把另⼀个类作为本类的成员变量，最终通过 Spring 配置文件进行注入（赋值）。
-   好处：解耦合

![](img/依赖.png)





# Spring工厂创建复杂对象

## 1、什么是复杂对象

-   简单对象：可以直接通过new构造方法创建的对象

```markdown
Integer
String
Map
Data
用户自定义
...
```

-   复杂对象：不可以直接通过new构造方法创建的对象

```markdown
Connection
SqlSessionFactory
......
```



## 2、方式一：FactoryBean接口

### 2.1	开发步骤

-   创建一个工厂类实现FactoryBean接口，实现接口中的方法。

```java
public class ConnectionFactoryBean implements FactoryBean<Connection> {
    // 用于创建复杂对象
    @Override
    public Connection getObject() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/web?useSSL=false", "root", "root");
    }

    // 返回对象的类型
    @Override
    public Class<?> getObjectType() {
        return Connection.class;
    }
    
    // 是否单例
    // 在接口中有默认实现，默认是单例
    @Override
    public boolean isSingleton() {
        return false;
    }
}
```

-   在配置文件中配置：如果 class 中指定的类型是 `FactoryBean` 接口的实现类，那么通过 id 值获得的是这个类所创建的复杂对象。

```xml
<bean id="conn" class="com.aizhong.factory.ConnectionFactoryBean"/>
```

```java
Object conn = ctx.getBean("conn");
//conn = com.mysql.jdbc.JDBC4Connection@65d6b83b
```

-   细节1：如果想要获取`FactoryBean` 的对象，需要在getBean(“**&id**”)，id前加个**“&”**

```java
Object conn = ctx.getBean("&conn");
// conn = com.aizhong.factory.ConnectionFactoryBean@7bb58ca3
```

-   细节2：不管是获取复杂对象还是工厂对象，Spring容器中都不会保留`FactoryBean` 工厂对象

![image-20211005172418274](img/image-20211005172418274.png)



### 2.2	原理

原理：**接口回调**

问题：

```markdown
1. 为什么 Spring 规定 FactoryBean 接口实现 getObject()？
2. 为什么 ctx.getBean("conn") 获得的是复杂对象 Connection ⽽非 ConnectionFactoryBean？
```

Spring内部运行流程：

```markdown
1. 配置文件中通过 id = conn 获得 ConnectionFactoryBean 类的对象 ，进而通过 instanceof 判断出是 FactoryBean 接口的实现类
2. Spring 按照规定 getObject() —> Connection
3. 返回 Connection
```

![](img/FactoryBean原理步骤.png)



### 2.3	总结

Spring 中用于创建复杂对象的⼀种方式，也是 Spring **原生提供的**，后续 Spring 整合其他框架时会大量应用 FactoryBean 方式。



## 3、实例工厂

-   创建ConnectionFactory 类

```java
public class ConnectionFactory {
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web?useSSL=false", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
```

-   配置文件

```xml
<!--实例工厂-->
<!--先创建出工厂的实例-->
<bean id="connFactory" class="com.aizhong.factory.ConnectionFactory"/>
<!-- 通过工厂实例里的方法创建复杂对象 -->
<bean id="conn1" factory-bean="connFactory" factory-method="getConnection"/>
```

工厂和复杂对象都有被创建，并保留在容器中

![image-20211005172550720](img/image-20211005172550720.png)



## 4、静态工厂

-   编写StaticFactoryBean类

```java
public class StaticFactoryBean {
    // 静态方法
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web?useSSL=false", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
```

-   配置文件

```xml
<!--静态工厂-->
<bean id="conn2" class="com.aizhong.factory.StaticFactoryBean" factory-method="getConnection"/>
```

只有Connection对象创建并保留

![image-20211005173012528](img/image-20211005173012528.png)



## 5、DI改进

在工厂类中存在大量String，而且可能频繁修改，可以通过配置文件注入的方式来赋值，提高代码的**可维护性**。以FactoryBean工厂为例。

```java
public class ConnectionFactoryBean implements FactoryBean<Connection> {
    private String driveName;
    private String URL;
    private String user;
    private String password;
    
    //set()、get()
    
    @Override
    public Connection getObject() throws Exception {
        Class.forName(driveName);
        return DriverManager.getConnection(URL, user, password);
    }
    // ...
}  
```

```xml
<!--实现FactoryBean接口-->
<bean id="conn" class="com.aizhong.factory.ConnectionFactoryBean">
    <property name="driveName" value="com.mysql.jdbc.Driver"/>
    <property name="URL" value="jdbc:mysql://localhost:3306/web?useSSL=false"/>
    <property name="user" value="root"/>
    <property name="password" value="root"/>
</bean>
```



## 6、对象的创建次数

### 6.1	简单对象

bean标签的 **scope=“singleton|prototype”**

-   `singleton`：每一个 IoC 容器只会创建⼀次简单对象，默认值；

```markdown
1. 容器在创建成功后就会把对象创建出来
2. 一个bean标签，对应一个singleton对象，不管被多少个getBean()调用，获取的都是同一个对象
```

-   `prototype`：每⼀次都会创建新的对象

```markdown
1. 容器创建后不会创建prototype对象
2. 对象只有在getBean()调用后才会创建，并且不会留在IoC容器中，每次调用获取的对象都是不同的对象
```



### 6.2	复杂对象

-   实现FactoryBean的工厂通过

```java
public boolean isSingleton() {
		return true; // 只会创建⼀次
		// return false; // 每⼀次都会创建新的
}
```

-   实例工厂和静态工厂和简单对象一样，通过scope属性控制



### 6.3	为什么要控制创建对象的次数

-   好处：节约资源

-   适合单例的对象：重量级、可被共用、线程安全。。。

```markdown
SqlSessionFactory
DAO
Service
......
```

-   适合多例的对象：不能被共用、线程不安全。。。

```markdown
Connection
SqlSession | Session
Struts2 - Action
......
```













































