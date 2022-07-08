package org.example.app.configuration;


import com.google.gson.Gson;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean(destroyMethod = "close")
    public HikariDataSource dataSource() {
        final HikariConfig configuration = new HikariConfig();
        configuration.setJdbcUrl("jdbc:postgresql://localhost:5432/db?user=app&password=password");
        configuration.setDriverClassName("org.postgresql.Driver");
        return new HikariDataSource(configuration);
    }

    @Bean
    public NamedParameterJdbcOperations namedParameterJdbcOperations(final DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}