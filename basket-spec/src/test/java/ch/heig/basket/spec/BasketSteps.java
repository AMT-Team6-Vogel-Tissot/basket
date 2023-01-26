package ch.heig.basket.spec;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.api.BasketEndPointApi;
import org.openapitools.client.model.Player;
import org.openapitools.client.model.Team;
import org.openapitools.client.model.Trophy;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BasketSteps {

    private final BasketEndPointApi api = new BasketEndPointApi();
    private Player player;
    private int statusCode;


    @When("I POST it to the \\/quotes endpoint")
    public void i_POST_it_to_the_quotes_endpoint() throws Throwable {
        //    try {
        //     ApiResponse response = api.addQuoteWithHttpInfo(quote);
        //    statusCode = response.getStatusCode();
        //  } catch (ApiException e) {
        //  statusCode = e.getCode();
        //  }
    }

    @Given("I have an player payload")
    public void iHaveAnPlayerPayload() {
        player = new Player();
        player.setId(42);
        player.setName("Olivier");
        player.setSurname("Tissot");
    }

    @When("I POST it to the \\/players endpoint")
    public void iPOSTItToThePlayersEndpoint() {
        try {
            ApiResponse response = api.addPlayerWithHttpInfo(player);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @Then("I receive a {int} status code")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, statusCode);
    }
}
