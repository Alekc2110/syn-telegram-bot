package com.example.mysynergybot.scheduler.config;

import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfig {
    @Bean
    public MapperFactory mapperFactory() {
        return new DefaultMapperFactory.Builder()
                .mapNulls(Boolean.FALSE)
                .build();
    }

    @Bean
    public MapperFacade mapperFacade(MapperFactory mapperFactory) {
        return mapperFactory.getMapperFacade();
    }

    private <A, B> Mapper<A, B> toRegisteredMapper(ClassMapBuilder<A, B> builder) {
        builder.register();
        return builder.toClassMap().getCustomizedMapper();
    }
}
