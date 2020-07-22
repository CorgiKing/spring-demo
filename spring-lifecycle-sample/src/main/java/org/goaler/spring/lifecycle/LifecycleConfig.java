package org.goaler.spring.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LifecycleConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public LifecycleBean lifecycleBean(){
        return new LifecycleBean();
    }

}
