 Feature: Application basket

Scenario: Register a new player
Given I have an player payload
When I POST it to the /players endpoint
Then I receive a 201 status code
