SpringBoot AOP 记录日志实例

![image.png](http://upload-images.jianshu.io/upload_images/1233356-6f89f8e53d42aa03.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![image.png](http://upload-images.jianshu.io/upload_images/1233356-7bc54181d9e06be6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![image.png](http://upload-images.jianshu.io/upload_images/1233356-55564f77eac1ec52.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![image.png](http://upload-images.jianshu.io/upload_images/1233356-8c81b3818525e80c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


> 散布于应用中多处的功能（日志、安全、事务管理等）被称为横切关注点。把横切关注点与业务逻辑分离是AOP要解决的问题。

在运行时，动态地将代码切入到类的指定方法、指定位置上.

这样的编程思想就是面向切面的编程。

面向切面编程（AOP是Aspect Oriented Program的首字母缩写） ，我们知道，面向对象的特点是继承、多态和封装。而封装就要求将功能分散到不同的对象中去，这在软件设计中往往称为职责分配。

实际上也就是说，让不同的类设计不同的方法。这样代码就分散到一个个的类中去了。这样做的好处是降低了代码的复杂程度，使类可重用。

但是人们也发现，在分散代码的同时，也增加了代码的重复性。什么意思呢？比如说，我们在两个类中，可能都需要在每个方法中做日志。按面向对象的设计方法，我们就必须在两个类的方法中都加入日志的内容。也许他们是完全相同的，但就是因为面向对象的设计让类与类之间无法联系，而不能将这些重复的代码统一起来。

也许有人会说，那好办啊，我们可以将这段代码写在一个独立的类独立的方法里，然后再在这两个类中调用。但是，这样一来，这两个类跟我们上面提到的独立的类就有耦合了，它的改变会影响这两个类。那么，有没有什么办法，能让我们在需要的时候，随意地加入代码呢？这种在运行时，动态地将代码切入到类的指定方法、指定位置上的编程思想就是面向切面的编程。

一般而言，我们管切入到指定类指定方法的代码片段称为切面，而切入到哪些类、哪些方法则叫切入点。有了AOP，我们就可以把几个类共有的代码，抽取到一个切片中，等到需要时再切入对象中去，从而改变其原有的行为。这样看来，AOP其实只是OOP的补充而已。

OOP从横向上区分出一个个的类来，而AOP则从纵向上向对象中加入特定的代码。有了AOP，OOP变得立体了。如果加上时间维度，AOP使OOP由原来的二维变为三维了，由平面变成立体了。从技术上来说，AOP基本上是通过代理机制实现的。

 AOP在编程历史上可以说是里程碑式的，对OOP编程是一种十分有益的补充。


最常见的一些横切行为如下面这些：

日志记录，跟踪，优化和监控
事务的处理
持久化
性能的优化
资源池，如数据库连接池的管理
系统统一的认证、权限管理等
应用系统的异常捕捉及处理
针对具体行业应用的横切行为


在软件业，AOP为Aspect Oriented Programming的缩写，意为：面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。AOP是OOP的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容，是函数式编程的一种衍生范型。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。

在Spring中提供了面向切面编程的丰富支持，允许通过分离应用的业务逻辑与系统级服务（例如审计（auditing）和事务（transaction）管理）进行内聚性的开发。

![image.png](http://upload-images.jianshu.io/upload_images/1233356-d224836e9bd48eb6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![image.png](http://upload-images.jianshu.io/upload_images/1233356-f5cc3ef5f411acd7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



主要功能:

日志记录，性能统计，安全控制，事务处理，异常处理等等。


OOP引入封装、继承、多态等概念来建立一种对象层次结构，用于模拟公共行为的一个集合。不过OOP允许开发者定义纵向的关系，但并不适合定义横向的关系，例如日志功能。日志代码往往横向地散布在所有对象层次中，而与它对应的对象的核心功能毫无关系对于其他类型的代码，如安全性、异常处理和透明的持续性也都是如此，这种散布在各处的无关的代码被称为横切（cross cutting），在OOP设计中，它导致了大量代码的重复，而不利于各个模块的重用。

AOP技术恰恰相反，它利用一种称为"横切"的技术，剖解开封装的对象内部，并将那些影响了多个类的公共行为封装到一个可重用模块，并将其命名为"Aspect"，即切面。所谓"切面"，简单说就是那些与业务无关，却为业务模块所共同调用的逻辑或责任封装起来，便于减少系统的重复代码，降低模块之间的耦合度，并有利于未来的可操作性和可维护性。

使用"横切"技术，AOP把软件系统分为两个部分：核心关注点和横切关注点。业务处理的主要流程是核心关注点，与之关系不大的部分是横切关注点。横切关注点的一个特点是，他们经常发生在核心关注点的多处，而各处基本相似，比如权限认证、日志、事物。AOP的作用在于分离系统中的各种关注点，将核心关注点和横切关注点分离开来。



AOP核心概念

1、横切关注点

对哪些方法进行拦截，拦截后怎么处理，这些关注点称之为横切关注点

2、切面（aspect）

类是对物体特征的抽象，切面就是对横切关注点的抽象

3、连接点（joinpoint）

被拦截到的点，因为Spring只支持方法类型的连接点，所以在Spring中连接点指的就是被拦截到的方法，实际上连接点还可以是字段或者构造器

4、切入点（pointcut）

对连接点进行拦截的定义

5、通知（advice）

所谓通知指的就是指拦截到连接点之后要执行的代码，通知分为前置、后置、异常、最终、环绕通知五类

6、目标对象

代理的目标对象

7、织入（weave）

将切面应用到目标对象并导致代理对象创建的过程

8、引入（introduction）

在不修改代码的前提下，引入可以在运行期为类动态地添加一些方法或字段



Spring对AOP的支持

Spring中AOP代理由Spring的IOC容器负责生成、管理，其依赖关系也由IOC容器负责管理。因此，AOP代理可以直接使用容器中的其它bean实例作为目标，这种关系可由IOC容器的依赖注入提供。Spring创建代理的规则为：

1、默认使用Java动态代理来创建AOP代理，这样就可以为任何接口实例创建代理了

2、当需要代理的类不是代理接口的时候，Spring会切换为使用CGLIB代理，也可强制使用CGLIB

AOP编程其实是很简单的事情，纵观AOP编程，程序员只需要参与三个部分：

1、定义普通业务组件

2、定义切入点，一个切入点可能横切多个业务组件

3、定义增强处理，增强处理就是在AOP框架为普通业务组件织入的处理动作

所以进行AOP编程的关键就是定义切入点和定义增强处理，一旦定义了合适的切入点和增强处理，AOP框架将自动生成AOP代理，即：代理对象的方法=增强处理+被代理对象的方法。



重温 AspectJ 中几个必须要了解的概念：

>Aspect： Aspect 声明类似于 Java 中的类声明，在 Aspect 中会包含着一些 Pointcut 以及相应的 Advice。
Joint point：表示在程序中明确定义的点，典型的包括方法调用，对类成员的访问以及异常处理程序块的执行等等，它自身还可以嵌套其它 joint point。
Pointcut：表示一组 joint point，这些 joint point 或是通过逻辑关系组合起来，或是通过通配、正则表达式等方式集中起来，它定义了相应的 Advice 将要发生的地方。
Advice：Advice 定义了在 pointcut 里面定义的程序点具体要做的操作，它通过 before、after 和 around 来区别是在每个 joint point 之前、之后还是代替执行的代码。

下面要讨论的这些问题，也许正是接触了 AOP 之后所困惑的。

AOP 帮助我们解决了新的问题没有？

AOP 并没有帮助我们解决任何新的问题，它只是提供了一种更好的办法，能够用更少的工作量来解决现有的一些问题，并且使得系统更加健壮，可维护性更好。同时，它让我们在进行系统架构和模块设计的时候多了新的选择和新的思路。



实现AOP的切面主要有以下几个要素：

@Aspect： 将一个java类定义为切面类
@Pointcut：定义一个切入点（规则表达式）

根据需要在切入点不同位置的切入内容

@Before：在切入点开始处切入内容
@After：在切入点结尾处切入内容
@AfterReturning：在切入点return之后切入内容（返回值回调，可以用来对处理返回值做一些加工处理）
@Around：在切入点前后切入内容，并自己控制何时执行切入点自身的内容
@AfterThrowing：用来处理当切入内容部分抛出异常之后的处理逻辑



![image.png](http://upload-images.jianshu.io/upload_images/1233356-53cea810194c1154.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)




![image.png](http://upload-images.jianshu.io/upload_images/1233356-619a70251743830e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)






![image.png](http://upload-images.jianshu.io/upload_images/1233356-eebc3dfdaed72227.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![image.png](http://upload-images.jianshu.io/upload_images/1233356-fca3f3fc39d0a433.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![image.png](http://upload-images.jianshu.io/upload_images/1233356-238c010af29e00fb.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



![image.png](http://upload-images.jianshu.io/upload_images/1233356-cacdbf7794f2e071.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Spring AOP 依赖树：

![image.png](http://upload-images.jianshu.io/upload_images/1233356-91954c581d52008b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


工程完整目录：
```
~/KotlinSpringBoot/demo2_aop_logging$ tree
.
├── build
│   ├── kotlin
│   │   ├── compileKotlin
│   │   └── compileTestKotlin
│   └── kotlin-build
│       └── version.txt
├── build.gradle
├── demo2_aop_logging.iml
├── demo2_aop_logging.ipr
├── demo2_aop_logging.iws
├── demo2_aop_logging_main.iml
├── demo2_aop_logging_test.iml
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
└── src
    ├── main
    │   ├── java
    │   ├── kotlin
    │   │   └── com
    │   │       └── easy
    │   │           └── springboot
    │   │               └── demo2_aop_logging
    │   │                   └── Demo2AopLoggingApplication.kt
    │   └── resources
    │       ├── application.properties
    │       ├── static
    │       └── templates
    └── test
        ├── java
        ├── kotlin
        │   └── com
        │       └── easy
        │           └── springboot
        │               └── demo2_aop_logging
        │                   └── Demo2AopLoggingApplicationTests.kt
        └── resources

26 directories, 14 files

```

写一个测试的Http 接口 Controller

```kotlin
package com.easy.springboot.demo2_aop_logging.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloAopController {

    @GetMapping("hello")
    fun hello(): World {
        return World(name = "AOP", id = "1002")
    }

    data class World(var name: String, var id: String)

}
```

响应

```json
// 20180101203831
// http://127.0.0.1:8080/hello

{
  "name": "AOP",
  "id": "1002"
}
```



除了引入spring框架dist目录下的org.springframework.aop  jar之外，还需要自己下载第三方依赖包：

>aspectjweaver.jar


在 build.gradle 中添加依赖 spring-boot-starter-aop

```
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-aop
	compile group: 'org.springframework.boot', name: 'spring-boot-starter-aop', version: '2.0.0.M7'
```
引入以上jar包之后，就可以通过@Aspect等注解方式进行AOP编程了.


![image.png](http://upload-images.jianshu.io/upload_images/1233356-f88aaa41b737523b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

切面逻辑实现:

LogAspect.kt

```kotlin
package com.easy.springboot.demo2_aop_logging.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*


@Component
@Aspect
class LogAspect {
    private val LOG = LoggerFactory.getLogger(LogAspect::class.java)

    @Pointcut("execution(public * com.easy.springboot.demo2_aop_logging.controller.*.*(..))") //.*.*代表所有子目录下的所有方法，最后括号里(..)的两个..代表所有参数
    fun logPointCut() {
    }

    @Before("logPointCut()")
    @Throws(Throwable::class)
    fun doBefore(joinPoint: JoinPoint) {
        // 接收到请求，记录请求内容
        val attributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
        val request = attributes.request

        // 记录下请求内容
        LOG.info("请求地址 : " + request.requestURL.toString())
        LOG.info("HTTP METHOD : " + request.method)
        LOG.info("IP : " + request.remoteAddr)
        LOG.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName())
        LOG.info("参数 : " + Arrays.toString(joinPoint.getArgs()))

    }

    @AfterReturning(returning = "ret", pointcut = "logPointCut()")// returning的值和doAfterReturning的参数名一致
    @Throws(Throwable::class)
    fun doAfterReturning(ret: Any) {
        // 处理完请求，返回内容
        LOG.info("返回值 : " + ret)
    }

    @Around("logPointCut()")
    @Throws(Throwable::class)
    fun doAround(pjp: ProceedingJoinPoint): Any {
        val startTime = System.currentTimeMillis()
        val ob = pjp.proceed()// ob 为方法的返回值
        LOG.info("耗时 : " + (System.currentTimeMillis() - startTime))
        return ob
    }

}
```

其中 execution 用于匹配方法执行的连接点:

         within：用于匹配指定类型内的方法执行；

         this：用于匹配当前AOP代理对象类型的执行方法；注意是AOP代理对象的类型匹配，这样就可能包括引入接口也类型匹配；

         target：用于匹配当前目标对象类型的执行方法；注意是目标对象的类型匹配，这样就不包括引入接口也类型匹配；

         args：用于匹配当前执行的方法传入的参数为指定类型的执行方法；

         @within：用于匹配所以持有指定注解类型内的方法；

         @target：用于匹配当前目标对象类型的执行方法，其中目标对象持有指定的注解；

         @args：用于匹配当前执行的方法传入的参数持有指定注解的执行；

         @annotation：用于匹配当前执行方法持有指定注解的方法；

         bean：Spring AOP扩展的，AspectJ没有对于指示符，用于匹配特定名称的Bean对象的执行方法；

         reference pointcut：表示引用其他命名切入点，只有@ApectJ风格支持，Schema风格不支持。

       AspectJ切入点支持的切入点指示符还有： call、get、set、preinitialization、staticinitialization、initialization、handler、adviceexecution、withincode、cflow、cflowbelow、if、@this、@withincode；但Spring AOP目前不支持这些指示符，使用这些指示符将抛出IllegalArgumentException异常。

类型匹配语法:

AspectJ类型匹配的通配符：

         *：匹配任何数量字符；

         ..：匹配任何数量字符的重复，如在类型模式中匹配任何数量子包；而在方法参数模式中匹配任何数量参数。

         +：匹配指定类型的子类型；仅能作为后缀放在类型模式后边。



再次请求

```json
// 20180101212902
// http://127.0.0.1:8080/hello

{
  "name": "AOP",
  "id": "1002"
}
```

我们可以看到后台的日志输出:

![image.png](http://upload-images.jianshu.io/upload_images/1233356-692a6a86970c2375.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



完整的文本如下:

```
2018-01-01 21:29:01.138  INFO 36572 --- [nio-8080-exec-1] c.e.s.demo2_aop_logging.aop.LogAspect    : 请求地址 : http://127.0.0.1:8080/hello
2018-01-01 21:29:01.138  INFO 36572 --- [nio-8080-exec-1] c.e.s.demo2_aop_logging.aop.LogAspect    : HTTP METHOD : GET
2018-01-01 21:29:01.138  INFO 36572 --- [nio-8080-exec-1] c.e.s.demo2_aop_logging.aop.LogAspect    : IP : 127.0.0.1
2018-01-01 21:29:01.141  INFO 36572 --- [nio-8080-exec-1] c.e.s.demo2_aop_logging.aop.LogAspect    : CLASS_METHOD : com.easy.springboot.demo2_aop_logging.controller.HelloAopController.hello
2018-01-01 21:29:01.141  INFO 36572 --- [nio-8080-exec-1] c.e.s.demo2_aop_logging.aop.LogAspect    : 参数 : []
2018-01-01 21:29:01.147  INFO 36572 --- [nio-8080-exec-1] c.e.s.demo2_aop_logging.aop.LogAspect    : 耗时 : 10
2018-01-01 21:29:01.147  INFO 36572 --- [nio-8080-exec-1] c.e.s.demo2_aop_logging.aop.LogAspect    : 返回值 : World(name=AOP, id=1002)
```








关于AspectJ 中的pointcut 语法
    这两天忙着看AspectJ in Action 为了补一下AOP知识。看了Spring 2.0的规范，其中AOP部分已经基本融合了AspectJ，看来有必要看一看AspectJ了。
   看了很多AOP的文章了，AOP这两年发展的很慢，没有什么新意，现在到处都是SOA，SCA了，不过研究了一下，觉得还是很有帮助的。尤其是增加系统的契约性和模块的独立性来说，很有帮助。
   当然，学东西，基础很重要。下面就说说AspectJ中的基本语法，有兴趣的可以看看AspectJ in Action。
   先来说说pointcut，从字面的意思说的是切面的意思。也就是横切的时候，会有哪些执行点会被识别。只有先识别了，才能执行相应的Advice。
   基本的定义如下：

   public pointcut accountOperations:call(* Account.*(..))

   1.通配符和pointcut 操作符
   *  表示任何数量的字符，除了(.)
    .. 表示任何数量的字符包括任何数量的(.)
       + 描述指定类型的任何子类或者子接口
    同java一样，提供了一元和二元的条件表达操作符。
    一元操作符：!
      二元操作符：||和&&
      优先权同java

      2.签名语法

  类型签名样式

  主要的例子:
  Account                              类型Account
    *Account                             使用Account名称结束的类型，如SavingsAccount和CheckingAccount
    java.*.Date                         类型Date在任何直接的java子包中，如java.util.Date和java.sql.Date
    java..*                                 任何在java包或者所有子包中的类型，如java.awt和java.util或者java.awt.event 和java.util.logging
    javax..*Model+                   所有javax包或者子包中以Model结尾的类型和其所有子类，如TableModel,TreeModel。
  ！vector                             所有除了Vector的类型
  Vector|| Hashtable              Vector或者Hashtable类型
  java.util.RandomAccess+    实现RandomAccess和List的所有子类
   && java.util.List+

   方法和构造器签名模式

  public void Collection.clear()：
  在Collection中同样签名的clear方法

  public void Account.debit(float) throws InsufficientBalanceException：
  Account中同样签名的debit方法

  public void Account.set*(*)
  Account中以set开头，并且只有一个参数类型的方法

  public void Account.*()
  Account中所有的没有参数的public void 方法

  public * Account.*()
  Account中所有没有参数的public 方法

  public * Account.*(..)
  Account中所有的public 方法

  * Account.*(..)
  Account中的所有方法，包括private方法

  !public * Account.*(..)
  所有的非public 方法

   * Account+.*(..)
   所有的方法，包括子类的方法

   * java.io.Reader.read(..)
   所有的read方法

   * java.io.Reader.read(char[],..)
   所有以read(char[])开始的方法，包括read(char[])和read(char[],int,int)

   * javax..*.add*Listener(EventListener+)
   命名以add开始，以Listener结尾的方法，参数中为EventListener或子类

   * *.*(..) throws RemoteException
   抛出RemoteException的所有方法

   构造器，同上面
   public Account.new()
   没有参数的构造器方法

    属性签名模式
    同方法一样，属性也查不多
    * Account.*
    所有的Account属性

    !public static * banking..*.*
    所有的非public static 属性，在banking的包或者子包中

    3.主要的pointcuts类型

    分类pointcuts
    遵循特定的语法用于捕获每一个种类的可使用连接点。
    主要的种类：
方法执行：execution(MethodSignature)
方法调用：call(MethodSignature)
构造器执行：execution(ConstructorSignature)
构造器调用：call(ConstructorSignature)
类初始化：staticinitialization(TypeSignature)
属性读操作：get(FieldSignature)
属性写操作：set(FieldSignature)
例外处理执行：handler(TypeSignature)
对象初始化：initialization(ConstructorSignature)
对象预先初始化：preinitialization(ConstructorSignature)
Advice执行：adviceexecution()
   基于控制流的pointcuts
   主要包括两种类型的控制流：
   cflow(Pointcut)，捕获所有的连接点在指定的方法执行中，包括执行方法本身。
   cflowbelow(Pointcut)，捕获所有的连接点在指定的方法执行中，除了执行方法本身。

   如以下的例子：
   cflow(call(* Account.debit(..))
   所有的debit方法中的连接点，包括debit方法本身
   cflowbelow(call(* Account.debit(..))
   所有debit方法中的连接点，除了debit方法本身
   cflow(transactedOperations())
   所有由transactedOperations捕获的连接点
   cflowbelow(execution(Account.new(..))
   所有在Account 构造器中执行的连接点

   基于词汇结构的连接点
   源代码片断。，如within()和withincode()
   within ：捕获在指定类或者方面中的程序体中的所有连接点，包括内部类。
   Withincode：用于捕获在构造器或者方法中的所有连接点，包括在其中的本地类

   执行对象连接点
   匹配this,和target对象，作为方法被调用的对象。
   this(Account)，所有Account的实例的执行点，匹配所有的连接点，如方法调用，属性设置，当前的执行对象为Account，或者其子类。
   target(Account)：匹配所有的连接点，目标对象为Account或其子类。

   必须执行相应的类型，不能使用*,或者..通配符。当前静态方法，不能被匹配。

   在within()和this()中的区别：
   一个是程序体，而另一个为对象执行。

   参数pointcuts
   用于捕获参数类型的连接点。
   args(String,..,int)
   args(RemoteException)

   条件检测pointcuts
   if(System.currentTimeMillis()>triggerTime)







本章示例工程源代码:


https://github.com/KotlinSpringBoot/demo2_aop_logging