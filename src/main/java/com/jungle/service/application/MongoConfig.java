/*
 * Copyright (c) 2016.  ND.
 * Jungle.
 */

package com.jungle.service.application;

import com.mongodb.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

/**
 * mongo配置
 *
 * @author Jungle
 * @version Created on 2016/1/25.
 */
@Configuration
@EnableMongoRepositories(basePackages = {
        "com.jungle.service.demo.repository.mongo"
})
@PropertySource(value = {"classpath:mongo.properties", "classpath:config.properties"})
public class MongoConfig extends AbstractMongoConfiguration {
    /**
     * 副本集配置
     */
    @Value("${spring.data.mongodb.uri}")
    private String mongoUri = "";

    @Value("${deploy.environment}")
    private String deployEnv = "";
    /**
     * 单点配置
     */
    @Value("${spring.data.mongodb.host}")
    private String mongoHost = "";
    @Value("${spring.data.mongodb.port}")
    private String mongoPort = "";
    @Value("${spring.data.mongodb.db.name}")
    private String mongoDbName = "";
    @Value("${spring.data.mongodb.db.account}")
    private String mongoDbAccount = "";
    @Value("${spring.data.mongodb.db.password}")
    private String mongoDbPassword = "";

    @Override
    protected String getDatabaseName() {
        if (StringUtils.equals("development", deployEnv)) {
            return mongoDbName;
        } else {
            return new MongoClientURI(mongoUri).getDatabase();
        }
    }

    @Override
    public Mongo mongo() throws Exception {
        MongoClient client;
        if (StringUtils.equals("development", deployEnv)) {
            int port = Integer.parseInt(mongoPort);
            ServerAddress serverAddress = new ServerAddress(mongoHost, port);
            MongoCredential credential = MongoCredential.createCredential(mongoDbAccount, mongoDbName,
                    mongoDbPassword.toCharArray());
            client = new MongoClient(serverAddress, Arrays.asList(credential));
        } else {
            MongoClientURI mongoClientURI = new MongoClientURI(mongoUri);
            client = new MongoClient(mongoClientURI);
        }
        return client;
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.jungle.service";
    }

    @Override
    public MappingMongoConverter mappingMongoConverter() throws Exception {
        MappingMongoConverter converter = super.mappingMongoConverter();
        converter.setMapKeyDotReplacement("\\$\\$_DOT_\\$\\$");
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
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
