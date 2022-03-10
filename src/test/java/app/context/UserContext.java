package app.context;

import app.domain.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserContext {
    private final TestRestTemplate template;
    private ResponseEntity<String> response;

    @Autowired
    public UserContext(TestRestTemplate testRestTemplate, UserRepository userRepository) {
        this.template = testRestTemplate;
    }

    @Given("I send a post request to {string} with body:")
    public void iSendARequestToWith(String path, String body){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        this.response = template.postForEntity(path, entity, String.class);
    }

    @Then("the response status should be {int} with body:")
    public void theResponseStatusAndBodyShouldBe(int status, String body) throws JsonProcessingException {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.resolve(status));
        ObjectMapper mapper = new ObjectMapper();
        assertThat(mapper.readTree(body)).isEqualTo(mapper.readTree(response.getBody()));
    }
}
