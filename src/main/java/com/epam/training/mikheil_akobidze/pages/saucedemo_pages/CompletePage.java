package com.epam.training.mikheil_akobidze.pages.saucedemo_pages;

import com.epam.training.mikheil_akobidze.pages.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CompletePage extends BasePage {

    private final By completeTextBox = By.xpath("//div[@id='checkout_complete_container']// h2");
    private final By BackHome = By.id("back-to-products");

    @Step("Get success message text")
    public String getCompleteTextBoxText() {
        return find(completeTextBox).getText();
    }

    public void goBack() {
        click(BackHome);
    }
}
