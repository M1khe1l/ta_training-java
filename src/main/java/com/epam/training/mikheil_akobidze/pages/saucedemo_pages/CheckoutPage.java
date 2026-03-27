package com.epam.training.mikheil_akobidze.pages.saucedemo_pages;

import com.epam.training.mikheil_akobidze.pages.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CheckoutPage extends BasePage {


    private final By checkoutHeader = By.xpath("//span[text()='Checkout: Your Information']");
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");

    public boolean isCheckoutHeaderDisplayed() {
        return find(checkoutHeader).isDisplayed();
    }

    @Step("Fill checkout information form")
    public void fillInputFields(String  firstName, String lastName, String zipCode) {
        set(firstNameField, firstName);
        set(lastNameField, lastName);
        set(postalCodeField, zipCode);
    }

    public CheckoutFinishPage goToFinishPage() {
        click(continueButton);
        return new CheckoutFinishPage();
    }
}
