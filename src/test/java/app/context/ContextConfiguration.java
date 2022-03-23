package app.context;

import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContextConfiguration {
    @PersistenceContext
    private final EntityManager em;
    private Logger logger;

    @Autowired
    public ContextConfiguration(EntityManager em, Logger logger) {
        this.em = em;
        this.logger = logger;
    }

    @Before
    @Transactional
    public void truncateDB() {
        List<Object> tables = this.em.createNativeQuery("show tables").getResultList();
        tables.stream().filter(t -> !t.equals("hibernate_sequence")).forEach( o -> {
            String query = "truncate table %s".formatted(o.toString());
            this.logger.info(query);
            this.em.createNativeQuery(query).executeUpdate();
        });
    }
}
