package com.test.config;

import com.test.bean.Car;
import com.test.bean.Cat;
import com.test.bean.Color;
import com.test.bean.Person;
import com.test.bean.Red;
import com.test.controller.PersonController;
import com.test.dao.PersonDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

// 配置类==配置文件，告诉Spring这是一个配置类
@Configuration

@ComponentScan(value = "com.test")

//@ComponentScan(value = "com.test", includeFilters = {@Filter(type = FilterType.ANNOTATION, classes = {Controller.class})}, useDefaultFilters = false)
//@ComponentScan(value = "com.test", excludeFilters = {@Filter(type = FilterType.ANNOTATION, classes = {Controller.class})})

//@ComponentScans(value = {
//    @ComponentScan(value = "com.test", includeFilters = {@Filter(type = FilterType.ANNOTATION, classes = {Controller.class})}, useDefaultFilters = false),
//    @ComponentScan(value = "com.test", excludeFilters = {@Filter(type = FilterType.ANNOTATION, classes = {Controller.class})})
//})

//@ComponentScan(value = "com.test", includeFilters = {@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {PersonController.class})}, useDefaultFilters = false)

//@ComponentScan(value = "com.test", includeFilters = {@Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})}, useDefaultFilters = false)

//FilterType.ANNOTATION：按照注解
//FilterType.ASSIGNABLE_TYPE：按照给定的类型
//FilterType.ASPECTJ：使用ASPECTJ表达式
//FilterType.REGEX：使用正则指定
//FilterType.CUSTOM：使用自定义规则

@Import(value = {Color.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})

//使用@PropertySource读取外部配置文件中的k/v保存到运行的环境变量中;加载完外部的配置文件以后使用${}取出配置文件的值
@PropertySource(value = {"classpath:person.properties"})
public class MainConfig {

    /**
     * 给容器中注册一个Bean，类型为返回值的类型，id默认是用方法名
     *
     * 单例时，容器创建好就调用该方法，也可以用@Lazy，需要时才调用
     * 多例时，需要该对象时才地调用
     *
     */
    @Bean("person01")
    //@Scope("prototype")
    //@Lazy
    public Person person() {
        System.out.println("@bean01");
        return new Person(1, "a");
    }

    @Bean
    public Person person02() {
        System.out.println("@bean02");
        return new Person(2, "b");
    }

    @Bean
    @Conditional(value = {ConditionOnMissingBean.class})
    public Person person03() {
        System.out.println("@bean03");
        return new Person(3, "c");
    }

    @Bean
    public Person person04() {
        System.out.println("@bean04");
        return new Person();
    }

    /**
     * 给容器中注册组件
     * 1）、包扫描+组件标注注解（@Controller/@Service/@Repository/@Component）[自己写的类]
     * 2）、@Bean[导入的第三方包里面的组件]
     * 3）、@Import[快速给容器中导入一个组件]
     * 		1）、@Import(要导入到容器中的组件)；容器中就会自动注册这个组件，id默认是全类名
     * 		2）、ImportSelector:返回需要导入的组件的全类名数组；
     * 		3）、ImportBeanDefinitionRegistrar:手动注册bean到容器中
     * 4）、使用Spring提供的FactoryBean（工厂Bean）
     * 		1）、默认获取到的是工厂bean调用getObject创建的对象
     * 		2）、要获取工厂Bean本身，我们需要给id前面加一个&
     * 			&colorFactoryBean
     */
    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }

    /**
     * bean的生命周期：
     * 容器管理bean的生命周期；
     *
     * 1.创建
     * 		单实例：在容器启动的时候创建对象
     * 		多实例：在每次获取的时候创建对象
     *
     * 2.初始化：
     *      我们可以自定义初始化和销毁方法；容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁方法
     *
     * populateBean //给bean进行属性赋值
     *
     * initializeBean {
     *      applyBeanPostProcessorsBeforeInitialization {
     *          // 遍历得到容器中所有的BeanPostProcessor，挨个执行postProcessBeforeInitialization，
     *          // 一但返回null，跳出for循环，不会执行后面的postProcessorsBeforeInitialization
     *          BeanPostProcessor.postProcessBeforeInitialization
     *      }
     *      invokeInitMethods {
     *          afterPropertiesSet
     *      }
     *      applyBeanPostProcessorsAfterInitialization {
     *          // 遍历得到容器中所有的BeanPostProcessor，挨个执行postProcessAfterInitialization，
     *          // 一但返回null，跳出for循环，不会执行后面的postProcessAfterInitialization
     *          BeanPostProcessor.postProcessAfterInitialization
     *      }
     * }
     *
     * 3.销毁：
     * 		单实例：容器关闭的时候
     * 		多实例：容器不会管理这个bean；容器不会调用销毁方法；
     *
     *
     * 1）、指定初始化和销毁方法
     * 		通过@Bean指定
     * 	        init-method
     * 	        destroy-method
     * 2）、通过让Bean实现
     *          InitializingBean.afterPropertiesSet()
     * 			DisposableBean.destroy()
     * 3）、可以使用JSR250
     * 		@PostConstruct： 在bean创建完成并且属性赋值完成；来执行初始化方法
     * 		@PreDestroy： 在容器销毁bean之前通知我们进行清理工作
     * 4）、BeanPostProcessor：bean的后置处理器，可以处理容器中所有bean
     * 		在bean初始化前后进行一些处理工作；
     * 		postProcessBeforeInitialization:在初始化之前工作
     * 		postProcessAfterInitialization:在初始化之后工作
     *
     * Spring底层对 BeanPostProcessor 的使用；
     * 		bean赋值，注入其他组件，@Autowired，生命周期注解功能，@Async
     *
     */
    //@Scope("prototype")
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Car car() {
        return new Car();
    }

    /**
     * 自动装配
     * 		Spring利用依赖注入（DI），完成对IOC容器中中各个组件的依赖关系赋值；
     *
     * 1）、@Autowired：自动注入：
     * 		1）、默认优先按照“类型”去容器中找对应的组件:applicationContext.getBean(BookDao.class);找到就赋值
     * 		2）、如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找applicationContext.getBean("bookDao")
     * 		3）、@Qualifier("bookDao")：使用@Qualifier指定需要装配的组件的id，而不是使用属性名
     * 		4）、自动装配默认一定要将属性赋值好，没有就会报错；
     * 			可以使用@Autowired(required=false);
     * 		5）、@Primary：让Spring进行自动装配的时候，默认使用首选的bean；@Qualifier比他优先级高
     * 		BookService{
     * 			@Autowired
     * 			BookDao  bookDao;
     * 		}
     *
     * 2）、Spring还支持使用@Resource(JSR250)和@Inject(JSR330)[java规范的注解]
     * 		@Resource:
     * 			默认是按照“组件名称"进行装配的，不支持@Primary，不支持reqiured=false
     * 		@Inject:
     * 			需要导入javax.inject的包，支持@Primary，不支持required=false
     *
     * AutowiredAnnotationBeanPostProcessor:解析完成自动装配功能；
     *
     * 3）、 @Autowired:构造方法，方法，方法参数，属性；都是从容器中获取参数组件的值
     * 		1）、[标注在方法位置]：@Bean+方法参数；参数从容器中获取;默认不写@Autowired效果是一样的；都能自动装配
     * 		2）、[标在构造器上]：如果组件只有一个有参构造器，这个有参构造器的@Autowired可以省略，参数位置的组件还是可以自动从容器中获取
     * 		3）、放在参数位置：
     *
     * 4）、自定义组件想要使用Spring容器底层的一些组件（ApplicationContext，BeanFactory，xxx）；
     * 		自定义组件实现xxxAware；在创建对象的时候，会调用接口规定的方法注入相关组件；Aware；
     * 		把Spring底层一些组件注入到自定义的Bean中；
     * 		xxxAware：功能使用xxxProcessor；
     * 			ApplicationContextAware==》ApplicationContextAwareProcessor；
     *
     */
    @Bean
    @Primary
    public PersonDao personDao(){
        return new PersonDao();
    }

    /**
     * @Bean 标注的方法创建对象的时候，方法参数的值从容器中获取
     */
    @Bean
    public Color color(Car car){
        Color color = new Color();
        color.setCar(car);
        return color;
    }

}
