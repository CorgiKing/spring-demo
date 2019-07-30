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
public class WriteDataSourceConfig {

    @Bean(initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties("spring.datasource.write")
    public DataSource writeDataSource() {
//        return DataSourceBuilder.create().build();
        return new DruidDataSource();
    }

    @Bean
    public SqlSessionFactory writeSqlSessionFactory(@Qualifier("writeDataSource") DataSource writeDataSource) throws Exception {
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setDataSource(writeDataSource);
        ssfb.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mybatis/mapper/writedb/*.xml"));
        return ssfb.getObject();
    }

    @Bean
    public SqlSessionTemplate writeSqlSessionTemplate(@Qualifier("writeSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sst = new SqlSessionTemplate(sqlSessionFactory);
        return sst;
    }

    @Bean
    public MapperScannerConfigurer writeMapperScannerConfigurer() {
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setSqlSessionFactoryBeanName("writeSqlSessionFactory");
        msc.setBasePackage("org.goaler.springbootdemo.dao.db.write.mapper");
        return msc;
    }

    @Bean
    public DataSourceTransactionManager writeTransaction(@Qualifier("writeDataSource") DataSource writeDataSource) {
        DataSourceTransactionManager dstm = new DataSourceTransactionManager();
        dstm.setDataSource(writeDataSource);
        return dstm;
    }

}
