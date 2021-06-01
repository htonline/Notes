# Spring

## 1.lib包

![Spring导入包lib](C:\Users\98634\Desktop\笔记\Spring\img\Spring导入包lib.png)

## 2.src目录下创建xml配置文件

![xml配置文件](C:\Users\98634\Desktop\笔记\Spring\img\xml配置文件.png)

## 3.获取bean的三种方法

```java
<!--创建IOC容器对象-->
ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");

【beans.xml	路径（因为直接在src目录下，所以直接写文件名)】
```

- 根据bean的id获取	【需要强转】

  ```java
  Book book = (Book) ioc.getBean("bookID");
  ```

- 根据bean的类型获取   【当一个类型有多个bean时，会报错】

  ```java
  Book book = ioc.getBean(Book.class);
  ```

- 根据Bean的id和类型 获取

  ```java
  Book book = ioc.getBean("bookID", Book.class);
  ```

## 4.基于xml方式管理Bean

```java
public class Book {
    private Integer id;
    private String title;
    private String author;
    private Double price;
    private Integer sales;
    
    无参、全参、部分参
    get和set方法
    toString方法
    
}
```

### 1.创建Bean

```xml
<bean id="bookID" class="com.atguigu.spring.beans.Book"></bean>
```

### 2.给Bean的属性赋值

#### setter注入

IOC容器一创建就调用**无参**构造器创建对象，然后调用属性对应的**set方法**给属性赋值。

```xml
<bean id="book1" class="com.atguigu.spring.beans.Book">
        <!--通过子标签property给属性赋值
      	通过name属性指定属性名，通过value属性或value子标签指定属性值
        -->
        <property name="id" value="1"></property>
        <property name="title">
            <value>三国演义</value>
        </property>
        <property name="author" value="罗贯中"></property>
        <property name="price" value="6.66"></property>
        <property name="sales" value="100"></property>
</bean>
```

#### **特殊值的处理**：

碰到需要转义的字符，可以用<![CDATA[]]>将其括起来，这样才可以显示出来。

```xml
<property name="title">
    <value><![CDATA[<史记>]]></value>
</property>
```

#### 构造器注入

IOC容器一创建就调用对应的**有参**构造器创建对象

按有参构造区顺序进行赋值。多个就看哪个匹配。

```xml
<!--使用子标签constructor-arg给属性赋值-->
<bean id="book2" class="com.atguigu.spring.beans.Book">
    <constructor-arg name="id" value="2"></constructor-arg>
    <constructor-arg value="水浒传"></constructor-arg>
    <constructor-arg value="施耐庵"></constructor-arg>
    <constructor-arg value="8.88"></constructor-arg>
    <constructor-arg value="200"></constructor-arg>
</bean>
```

```xml
<!--可以通过index属性来指定属性在构造器中的位置，索引从0开始-->
<bean id="book3" class="com.atguigu.spring.beans.Book">
    <constructor-arg value="3"></constructor-arg>
    <constructor-arg index="2" value="曹雪芹"></constructor-arg>
    <constructor-arg index="1" value="红楼梦"></constructor-arg>
    <constructor-arg value="10.00"></constructor-arg>
    <constructor-arg value="300"></constructor-arg>
</bean>
```

```xml
<!--还可以通过type属性来指定类型，对构造器进行匹配-->
<bean id="book4" class="com.atguigu.spring.beans.Book">
    <constructor-arg value="4"></constructor-arg>
    <constructor-arg value="西游记"></constructor-arg>
    <constructor-arg value="吴承恩"></constructor-arg>
    <constructor-arg value="40" type="java.lang.Double">		</constructor-arg>
</bean>
```

#### P空间配置Bean

```xml
<!--最上面的配置文件中，还需要加上这个-->
xmlns:p="http://www.springframework.org/schema/p"

-----------------------------------------------------------
<bean id="book6" class="com.atguigu.spring.beans.Book"
          p:id="6"
          p:title="聊斋志异"
          p:author="蒲松龄"
          p:price="9.88"
          p:sales="1000">
</bean>
```

#### 引入外部已声明的Bean【级联】

---CartItem属性中包含Book类的成员成员变量

---ref 属性

```xml
<bean class="com.atguigu.spring.beans.CartItem" id="cartItem3">
        <property name="book" ref="book1"></property>
        <!--给级联属性赋值：将book1中的title修改为新三国，作者修改为罗贯小中-->
        <property name="book.title" value="新三国"></property>
        <property name="book.author" value="罗贯小中"></property>
        <property name="count" value="1"></property>
        <property name="amount" value="6.66"></property>
</bean>
```

#### 内部Bean

```xml
<bean class="com.atguigu.spring.beans.CartItem" id="cartItem2">
  <property name="book">
    <!--内部bean不能被其他bean引用，所以可以不指定id-->
    <bean class="com.atguigu.spring.beans.Book">
        <property name="id" value="7"></property>
        <property name="title" value="西厢记"></property>
        <property name="author" value="王实甫"></property>
        <property name="price" value="7.77"></property>
        <property name="sales" value="700"></property>
     </bean>
   </property>
        <property name="count" value="10"></property>
        <property name="amount" value="77.7"></property>
    </bean>
```

#### 集合属性

```xml
<!--给集合属性赋值-->
    <bean class="com.atguigu.spring.beans.BookShop" id="bookShop">
        <property name="list">
            <!--给List类型的属性赋值使用list标签-->
<!--            <list>-->
<!--               <ref bean="book1"></ref>-->
<!--               <ref bean="book2"></ref>-->
<!--               <ref bean="book3"></ref>-->
<!--            </list>-->
            <!--直接引用List类型的bean-->
            <ref bean="listBean"></ref>
        </property>
        <property name="map">
            <!--给Map类型的属性赋值使用map标签-->
            <map>
                <entry key="bk01" value="图书1"></entry>
                <entry key="bk02" value="图书2"></entry>
                <entry key="bk03" value="图书3"></entry>
            </map>
        </property>
        <property name="props">
            <!--给Properties类型的属性赋值使用props标签-->
            <props>
                <prop key="username">root</prop>
                <prop key="password">root</prop>
                <prop key="url">jdbc:mysql://localhost:3306/test</prop>
                <prop key="driverClassName">com.mysql.jdbc.Driver</prop>
            </props>
        </property>
    </bean>
```

```xml
<!--配置集合类型的bean，需要引入util名称空间-->
    <util:list id="listBean">
        <bean class="com.atguigu.spring.beans.Book">
            <property name="id" value="9"/>
            <property name="title" value="解忧杂货店"/>
            <property name="author" value="东野圭吾"/>
            <property name="price" value="1.11"/>
            <property name="sales" value="1000"/>
        </bean>
        <bean class="com.atguigu.spring.beans.Book">
            <property name="id" value="10"/>
            <property name="title" value="三体"/>
            <property name="author" value="刘慈欣"/>
            <property name="price" value="2.22"/>
            <property name="sales" value="10000"/>
        </bean>
    </util:list>
```

#### 自动装配

```xml
<!--基于XML形式的自动装配（依赖注入）
    通过autowire属性配置自动装配的规则：
       no或default：不装配
       byName：根据当前bean的属性名和IOC容器中bean的名称（id属性值）实现自动装配
            如果找到则装配成功；如果找不到则不装配
        byType：根据当前bean的属性的类型和IOC容器中bean的类型实现自动装配
        如果找到一个则装配成功；如果找到多个则抛出异常；如果找不到则不装配
    -->
    <bean class="com.atguigu.spring.beans.CartItem" id="cartItem4" autowire="byName">
        <property name="count" value="1"></property>
        <property name="amount" value="10.00"></property>
    </bean>
```

#### 配置数据库

引入Druid的jar包和MySQl的驱动包

![Druid和MySQL包](C:\Users\98634\Desktop\笔记\Spring\img\Druid和MySQL包.png)

##### 内部配置

```xml
<bean class="com.alibaba.druid.pool.DruidDataSource" id="dataSource">
    <property name="username" value="root"></property>
    <property name="password" value="root"></property>
    <property name="url" value="jdbc:mysql://localhost:3306/test"></property>
    <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
    <property name="initialSize" value="5"></property>
    <property name="maxActive" value="10"></property>
    <property name="maxWait" value="1000"></property>
</bean>
```

##### 引入外部文件drui.properties 进行配置

```xml
 <!--1.引入外部的属性文件-->
<context:property-placeholder location="classpath:druid.properties"></context:property-placeholder>

 <!--2.读取属性文件中的值配置数据源-->
 <bean class="com.alibaba.druid.pool.DruidDataSource" id="dataSource">
	<property name="username" value="${jdbc.username}"></property>
    <property name="password" value="${jdbc.password}"></property>
    <property name="url" value="${jdbc.url}"></property>
    <property name="driverClassName" value="${jdbc.driverClassName}"></property>
    <property name="initialSize" value="${jdbc.initialSize}"></property>
    <property name="maxActive" value="${jdbc.maxActive}">		</property>
    <property name="maxWait" value="${jdbc.maxWait}">			</property>
</bean>
```

```xml
<!--druid.properties-->
#key=value
jdbc.username=root
jdbc.password=root
jdbc.url=jdbc:mysql://localhost:3306/test
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.initialSize=5
jdbc.maxActive=10
jdbc.maxWait=1000
```

