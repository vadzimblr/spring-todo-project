package com.example.springlearnpr1.config;

import com.example.springlearnpr1.repositories.UserRepository;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;


@Configuration
@EnableJdbcRepositories(basePackages = "com.example.springlearnpr1.repositories")
public class DBconfig extends AbstractJdbcConfiguration {
    @Bean
    public DataSource dataSource(){
        PGPoolingDataSource source = new PGPoolingDataSource();
        source.setServerNames(new String[] {
                "localhost"
        });
        source.setDatabaseName("spring_pr1");
        source.setUser("spring_root");
        source.setPassword("root");
        return source;
    }

    @Bean
    public NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public TransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
