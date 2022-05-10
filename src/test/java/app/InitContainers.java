package app;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class InitContainers implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Container
    private MySQLContainer mysqlContainer;
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment env = applicationContext.getEnvironment();
        String dbName = env.getProperty("db.name");
        String dbPassword = env.getProperty("spring.datasource.password");
        mysqlContainer = new MySQLContainer<>("mysql:8")
                .withDatabaseName(dbName)
                .withPassword(dbPassword)
                .withReuse(true);

        mysqlContainer.start();

        TestPropertyValues values = TestPropertyValues.of(
                "db.host=" + mysqlContainer.getHost(),
                "db.port=" + mysqlContainer.getFirstMappedPort()
        );

        values.applyTo(applicationContext);
    }
}
