package com.spring01.reviews.config;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import converter.ZonedDateTimeReadConverter;
import converter.ZonedDateTimeWriteConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.spring01.reviews.repository")
public class MongoConfig extends AbstractMongoConfiguration {

    private final List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();


    @Override
    protected String getDatabaseName() {
        return "reviewsDb";
    }


    @Override
    public MongoCustomConversions customConversions() {
        converters.add(new ZonedDateTimeReadConverter());
        converters.add(new ZonedDateTimeWriteConverter());
        return new MongoCustomConversions(converters);
    }


    @Override
    public @Bean MongoClient mongoClient() {
        return new MongoClient("localhost");
    }
}