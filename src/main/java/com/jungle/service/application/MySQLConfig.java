package com.jungle.service.application;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 杨文军(132500)
 * @version Created on 2016/4/27.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.jungle.service.repository.mysql.jpa"}
)
@PropertySource(value = {"classpath:c3p0-config.properties"})
@MapperScan(basePackages = "com.*.*.repository.mysql.mybatis")
public class MySQLConfig {
    private static final Logger logger = LoggerFactory.getLogger(MySQLConfig.class);
    @Value("${c3p0.driverClass}")
    private String c3p0DriverClass;
    @Value("${c3p0.url}")
    private String c3p0Url;
    @Value("${c3p0.user}")
    private String c3p0User;
    @Value("${c3p0.password}")
    private String c3p0Password;
    private ComboPooledDataSource dataSource;
    @Bean
    public DataSource dataSource() {
        if(null == dataSource) {
            dataSource = new ComboPooledDataSource();
            try {
                dataSource.setDriverClass(c3p0DriverClass);
                dataSource.setJdbcUrl(c3p0Url);
                dataSource.setUser(c3p0User);
                dataSource.setPassword(c3p0Password);
                dataSource.setInitialPoolSize(10);
                dataSource.setMaxIdleTime(25000);
                dataSource.setMaxPoolSize(100);
                dataSource.setMinPoolSize(5);
                dataSource.setMaxStatements(10);
                dataSource.setMaxStatementsPerConnection(0);
                dataSource.setIdleConnectionTestPeriod(30);
                dataSource.setAutoCommitOnClose(false);
                dataSource.setAcquireIncrement(5);
                dataSource.setAcquireRetryDelay(1000);
                dataSource.setAcquireRetryAttempts(30);
                dataSource.setBreakAfterAcquireFailure(false);
            } catch (PropertyVetoException e) {
                logger.error("com.jungle.service.application.MySQLConfig#dataSource, mysql dataSource config property error");
            }
        }
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sqlSessionFactory.setConfigLocation(resolver.getResource("classpath:com/jungle/service/repository/mysql/mybatis/conf/mybatis-config.xml"));
            //多个地址逗号分隔
            sqlSessionFactory.setMapperLocations(resolver.getResources("classpath*:com/*/*/repository/mysql/mybatis/dto/*.xml"));
        } catch (IOException e) {
            logger.error("com.jungle.service.application.MySQLConfig#sqlSessionFactory, sqlSessionFactory set mapping path error.");
        }
        return sqlSessionFactory;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public JpaDialect jpaDialect() {
        HibernateJpaDialect hibernateJpaDialect = new HibernateJpaDialect();
        return hibernateJpaDialect;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan(
                "com.jungle.service.repository.mysql.jpa",
                "com.jungle.service.domain"
                );
        entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactory.setJpaDialect(jpaDialect());
        Map<String, Object> jpaPropertyMap = new HashMap<>();
        jpaPropertyMap.put("hibernate.query.substitutions", "true 1, false 0");
        jpaPropertyMap.put("hibernate.default_batch_fetch_size", 16);
        jpaPropertyMap.put("hibernate.max_fetch_depth", 2);
        jpaPropertyMap.put("hibernate.generate_statistics", true);
        jpaPropertyMap.put("hibernate.bytecode.use_reflection_optimizer", true);
        jpaPropertyMap.put("hibernate.cache.use_second_level_cache", false);
        jpaPropertyMap.put("hibernate.cache.use_query_cache", false);
        entityManagerFactory.setJpaPropertyMap(jpaPropertyMap);
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager jpaTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getNativeEntityManagerFactory());
        return transactionManager;
    }

    /**
     * property文件解析配置
     *
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
