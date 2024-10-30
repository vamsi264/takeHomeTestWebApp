package com.chaseTest.automation.ui.qa.steplib;

import com.chaseTest.automation.ui.qa.pages.JPWebPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.junit.Assert;

import static org.assertj.core.api.Assertions.assertThat;


public class JPWebPageStepLib extends ScenarioSteps {

    private JPWebPage jpWebPage;

    @Step
    public void openJPWebPage() {
        jpWebPage.open_google();
    }

    @Step
    public void searchEnteredText(String searchText) {
        jpWebPage.enter_and_search_the_text_using_search_field(searchText);
    }

    @Step
    public void clickOnFirstResultLink() {
        jpWebPage.click_on_first_result_link();
    }

    @Step
    public void verifyJPWebPageURL() {
        jpWebPage.verify_JP_Web_Page();
    }

    @Step
    public void verifyThatLogoIsDisplayed(boolean expected) {
        assertThat(jpWebPage.verify_JP_logo()).isEqualTo(expected);
    }

}
