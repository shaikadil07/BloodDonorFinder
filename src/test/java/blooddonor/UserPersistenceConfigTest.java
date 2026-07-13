package blooddonor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserPersistenceConfigTest {

    @Autowired
    private Environment environment;

    @Test
    void shouldUsePersistentFileBasedH2DatabaseByDefault() {
        String datasourceUrl = environment.getProperty("spring.datasource.url");

        assertThat(datasourceUrl)
                .isNotNull()
                .contains("jdbc:h2:file:")
                .contains("MODE=PostgreSQL");
    }
}
