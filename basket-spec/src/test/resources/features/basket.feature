 Feature: Application basket

Scenario: Register a new player
  Given I have an player payload
  When I POST it to the /players endpoint
  Then I receive a 201 status code

  Scenario: Get team name
    Given I have the team id 2
    When I GET it to the /teams/id endpoint
    Then I receive "nets"

  Scenario: Modify player info
    Given I have the player id 42 and a playload
    When I PATCH it to the /players/{id} endpoint
    Then I receive a 200 status code
