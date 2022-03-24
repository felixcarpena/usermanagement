package app.context;

import app.application.command.CreateUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import shared.domain.bus.Bus;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UserContext {
    private final TestRestTemplate template;
    private final Bus bus;
    private ResponseEntity<String> response;

    @Autowired
    public UserContext(TestRestTemplate testRestTemplate, Bus bus) {
        this.template = testRestTemplate;
        this.bus = bus;
    }

    @Given("I send a {string} request to {string} with body:")
    public void iSendAPatchRequestToWith(String method, String path, String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        this.response = template.exchange(path, HttpMethod.resolve(method.toUpperCase()), entity, String.class);
    }

    @Then("the response status should be {int} with body:")
    public void theResponseStatusAndBodyShouldBe(int status, String body) throws JsonProcessingException {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.resolve(status));
        ObjectMapper mapper = new ObjectMapper();
        assertThat(mapper.readTree(response.getBody())).isEqualTo(mapper.readTree(body));
    }

    @Given("Users with the following data exists:")
    public void usersWithTheFollowingDataExists(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        rows.forEach(row -> this.bus.dispatch(new CreateUser(row.get("id"), row.get("email"))));
    }
}
