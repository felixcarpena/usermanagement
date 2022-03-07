package dev.carpena.usermanagement;

import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserSteps {
    protected TestRestTemplate template;

    @Autowired
    public UserSteps(TestRestTemplate testRestTemplate) {
        this.template = testRestTemplate;
    }

    @Given("I subscribe a new user")
    public void iSubscribeAnUser() throws Throwable {
        ResponseEntity<String> response = template.getForEntity("/user", String.class);
        assertThat(response.getBody()).isEqualTo("I'm alive!");
    }
}
