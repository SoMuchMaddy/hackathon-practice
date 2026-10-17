package huskyfinance;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource source =
                new DriverManagerDataSource();

        source.setDriverClassName("org.sqlite.JDBC");
        source.setUrl("jdbc:sqlite:huskyfinance.db");

        return source;
    }
}