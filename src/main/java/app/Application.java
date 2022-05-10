package app;

import co.elastic.apm.attach.ElasticApmAttacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"app", "shared"})
@EntityScan({"app", "shared"})
@EnableJpaRepositories({"app", "shared"})
public class Application {
    public static void main(String[] args) {
        ElasticApmAttacher.attach();
        SpringApplication.run(Application.class, args);
    }
}
