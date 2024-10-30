Feature: As a user
         I search J.P. Morgan on Google search
         and verify logo is displayed

  @VerifyLogo
  Scenario: I search for "J. P. Morgan" on Google and verify the logo
    Given I am on the Google search page
    When I search "JP Morgan" using search bar
    And I click on the first result
    Then I navigated to the J.P. Morgan website
    And I verify J.P. Morgan logo is displayed