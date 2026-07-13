package blooddonor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DatabaseConfigTest {

    @Test
    void shouldConvertRailwayPostgresUrlToJdbcUrl() {
        String jdbcUrl = DatabaseConfig.toJdbcUrl("postgresql://postgres:secret@containers-us-west-123.pg.local:5432/railway");

        assertThat(jdbcUrl).isEqualTo("jdbc:postgresql://containers-us-west-123.pg.local:5432/railway");
    }
}
