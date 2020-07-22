package org.goaler.spring.lifecycle;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 *
 * PostConstruct、afterPropertiesSet、init-method是同一个功能的不同实现方式
 *
 * PostConstruct
 * - 属性被初始化后调用
 *
 * initializingBean.afterPropertiesSet()
 * - 这个方法将在所有的属性被初始化后调用，可以做一些检查或者初始化
 *
 * init-method
 * - afterPropertiesSet的另一种实现，在afterPropertiesSet调用，通过反射实现。
 * - afterPropertiesSet效率比init-method高，但是init-method消除了对spring的依赖
 *
 *
 */
public class LifecycleBean implements InitializingBean {


    LifecycleBean(){
        System.out.println("构造函数：LifecycleBean");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("postConstruct");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("preDestroy");
    }

    public void init() {
        System.out.println("init");
    }

    public void destroy(){
        System.out.println("destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }
}
