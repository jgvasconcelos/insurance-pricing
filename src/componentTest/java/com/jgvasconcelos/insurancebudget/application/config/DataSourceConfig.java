package com.jgvasconcelos.insurancebudget.application.config;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.io.IOException;

@Component
@Configuration
@ActiveProfiles("component-test")
public class DataSourceConfig {
    @Value("${spring.datasource.port}")
    private Integer dataSourcePort;

    @Bean
    public DataSource createDataSource() throws IOException {
        return EmbeddedPostgres.builder()
                .setPort(dataSourcePort)
                .setLocaleConfig("locale", "en-US.UTF-8")
                .setLocaleConfig("lc-messages", "en-US.UTF-8")
                .setCleanDataDirectory(true)
                .start()
                .getPostgresDatabase();
    }
}
