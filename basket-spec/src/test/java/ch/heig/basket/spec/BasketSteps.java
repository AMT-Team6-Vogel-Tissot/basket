package ch.heig.basket.spec;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.api.BasketEndPointApi;
import org.openapitools.client.model.Player;
import org.openapitools.client.model.Team;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BasketSteps {

    private final BasketEndPointApi api = new BasketEndPointApi();
    private Player player;

    private int playerId;
    private int statusCode;
    private int teamId;

    private String teamName;




    @Given("I have an player payload")
    public void iHaveAnPlayerPayload() {
        player = new Player();
        player.setId(42);
        player.setName("Olivier");
        player.setSurname("Tissot");
        player.setFkTeam(2);
    }

    @When("I POST it to the \\/players endpoint")
    public void iPOSTItToThePlayersEndpoint() {
        try {
            ApiResponse<Player> response = api.addPlayerWithHttpInfo(player);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @Then("I receive a {int} status code")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, statusCode);
    }

    @Given("I have the team id {int}")
    public void iHaveTheTeamId(int arg0) {
        teamId = arg0;
    }

    @When("I GET it to the \\/teams\\/id endpoint")
    public void iGETItToTheTeamsIdEndpoint() {
        try {
            Team response = api.getTeam(teamId);
            teamName = response.getName();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @Then("I receive {string}")
    public void iReceive(String arg0) {
        assertEquals(arg0, teamName);

    }

    @Given("I have the player id {int} and a playload")
    public void iHaveThePlayerIdAndAPlayload(int arg0) {
        playerId = arg0;
        player = new Player();
        player.setSurname("Tissot-Daguette");
    }

    @When("I PATCH it to the \\/players\\/\\{id} endpoint")
    public void iPATCHItToThePlayersIdEndpoint() {
        try {
            ApiResponse<Player> response = api.patchPlayerWithHttpInfo(playerId, player);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }
}
