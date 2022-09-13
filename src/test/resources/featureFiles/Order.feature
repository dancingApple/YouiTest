Feature: Order

  Background:
    Given The Environment is set as "demowebshop"
    And The application is launched in "Chrome"
  @test
  Scenario Outline: User order items on demo web shop
    Given user "login" to the demo web shop
    When user go to the <category> page and add <items> into the Cart
    And user go to the cart to check if the orders are correct
    Then user fill in the shipping address as <state>,<country> with post code <postcode> to complete the order successfully
    Examples:
      | category | items                              | state | country   | postcode |
      | Books    | Computing and Internet,Health Book | NSW   | Australia | 2193     |