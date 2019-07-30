package org.goaler.springbootdemo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class ReadDataSourceConfig {

    @Bean(initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties("spring.datasource.read")
    public DataSource readDataSource() {
//        return DataSourceBuilder.create().build();
        return new DruidDataSource();
    }

    @Bean
    public SqlSessionFactory readSqlSessionFactory(@Qualifier("readDataSource") DataSource readDataSource) throws Exception {
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setDataSource(readDataSource);
        ssfb.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mybatis/mapper/readdb/*.xml"));
        return ssfb.getObject();
    }

    @Bean
    public SqlSessionTemplate readSqlSessionTemplate(@Qualifier("readSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sst = new SqlSessionTemplate(sqlSessionFactory);
        return sst;
    }

    @Bean
    public MapperScannerConfigurer readMapperScannerConfigurer() {
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setSqlSessionFactoryBeanName("readSqlSessionFactory");
        msc.setBasePackage("org.goaler.springbootdemo.dao.db.read.mapper");
        return msc;
    }

    @Bean
    public DataSourceTransactionManager readTransaction(@Qualifier("readDataSource") DataSource readDataSource) {
        DataSourceTransactionManager dstm = new DataSourceTransactionManager();
        dstm.setDataSource(readDataSource);
        return dstm;
    }

}
