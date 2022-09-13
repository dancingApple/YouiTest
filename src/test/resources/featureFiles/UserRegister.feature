Feature: User Login

  Background:
    Given The Environment is set as "demowebshop"
    And The application is launched in "Chrome"

  @test
  Scenario: User register to demo web shop
    Given user "try to register" to the demo web shop
    When user fill in all the required fields of the register form and click on the register btn
    Then user successfully register as a new user

