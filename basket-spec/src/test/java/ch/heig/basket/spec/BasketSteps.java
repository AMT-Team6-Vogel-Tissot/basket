package ch.heig.basket.spec;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.api.PlayerEndPointApi;
import org.openapitools.client.api.TeamEndPointApi;

import org.openapitools.client.model.PlayerID;
import org.openapitools.client.model.PlayerPatch;
import org.openapitools.client.model.Team;
import org.openapitools.client.model.Trophy;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class BasketSteps {

    private final PlayerEndPointApi apiPlayer = new PlayerEndPointApi();
    private final TeamEndPointApi apiTeam = new TeamEndPointApi();

    private PlayerID playerId;

    private int statusCode;
    private int teamId;

    private String teamName;

    private PlayerPatch playerpatch;




    @Given("I have an player payload")
    public void iHaveAnPlayerPayload() {
        playerId = new PlayerID();
        playerId.setName("Olivier");
        playerId.setSurname("Tissot");
        Integer teamid = 2;
        playerId.setTeamId(teamid);

    }

    @When("I POST it to the \\/players endpoint")
    public void iPOSTItToThePlayersEndpoint() {
        try {
            ApiResponse<Void> response = apiPlayer.addPlayerWithHttpInfo(playerId);
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
            Team response = apiTeam.getTeam(teamId);
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
        playerpatch = new PlayerPatch();
        playerpatch.setId(arg0);
        playerpatch.setName("Maelle");
        playerpatch.setSurname("Vogel");

    }

    @When("I PATCH it to the \\/players endpoint")
    public void iPatchItToThePlayersIdEndpoint() {
        try {
            ApiResponse<Void> response = apiPlayer.patchPlayerWithHttpInfo(playerpatch);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }


}
