package com.chaseTest.automation.ui.qa.steps;

import com.chaseTest.automation.ui.qa.steplib.JPWebPageStepLib;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class JPWebPageScenarioSteps {

    @Steps
    JPWebPageStepLib jpWebPageStepLib;

    @Given("I am on the Google search page")
    public void i_am_on_the_google_search_page() {
        jpWebPageStepLib.openJPWebPage();
    }

    @When("I search {string} using search bar")
    public void i_search_using_search_bar(String string) {
        jpWebPageStepLib.searchEnteredText(string);
    }

    @When("I click on the first result")
    public void i_click_on_the_first_result() {
        jpWebPageStepLib.clickOnFirstResultLink();
    }

    @Then("I navigated to the J.P. Morgan website")
    public void i_navigated_to_the_j_p_morgan_website() {
        jpWebPageStepLib.verifyJPWebPageURL();
    }

    @Then("I verify J.P. Morgan logo is displayed")
    public void i_verify_j_p_morgan_logo_is_displayed() {
        jpWebPageStepLib.verifyThatLogoIsDisplayed(true);
    }
}
