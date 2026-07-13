package blooddonor;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DatabaseConfig {

    @Autowired
    private Environment environment;

    @Bean
    @Primary
    public DataSource dataSource() {
        String url = firstNonBlank(
                environment.getProperty("SPRING_DATASOURCE_URL"),
                environment.getProperty("DATABASE_URL"),
                "jdbc:h2:file:./data/blooddonor;DB_CLOSE_DELAY=-1;MODE=PostgreSQL"
        );

        String username = firstNonBlank(
                environment.getProperty("SPRING_DATASOURCE_USERNAME"),
                environment.getProperty("POSTGRES_USER"),
                "sa"
        );

        String password = firstNonBlank(
                environment.getProperty("SPRING_DATASOURCE_PASSWORD"),
                environment.getProperty("POSTGRES_PASSWORD"),
                ""
        );

        String jdbcUrl = toJdbcUrl(url);
        String driverClassName = jdbcUrl != null && jdbcUrl.startsWith("jdbc:postgres")
                ? "org.postgresql.Driver"
                : "org.h2.Driver";

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    private String firstNonBlank(String... values) {
        for (String value : values) {
            if (value != null && !value.isBlank()) {
                return value;
            }
        }
        return null;
    }

    public static String toJdbcUrl(String databaseUrl) {
        if (databaseUrl == null || databaseUrl.isBlank()) {
            return null;
        }

        if (databaseUrl.startsWith("jdbc:")) {
            return databaseUrl;
        }

        try {
            URI uri = new URI(databaseUrl);
            String scheme = uri.getScheme();
            if (!"postgresql".equalsIgnoreCase(scheme) && !"postgres".equalsIgnoreCase(scheme)) {
                return databaseUrl;
            }

            String host = uri.getHost();
            int port = uri.getPort();
            String path = uri.getPath();
            String query = uri.getRawQuery();
            StringBuilder jdbcUrl = new StringBuilder();
            jdbcUrl.append("jdbc:").append(scheme).append("://").append(host);
            if (port != -1) {
                jdbcUrl.append(":").append(port);
            }
            if (path != null && !path.isEmpty()) {
                jdbcUrl.append(path);
            }
            if (query != null && !query.isEmpty()) {
                jdbcUrl.append("?").append(query);
            }
            return jdbcUrl.toString();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid database URL", e);
        }
    }
}
