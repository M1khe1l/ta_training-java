package com.epam.training.mikheil_akobidze.pages.saucedemo_pages;

import com.epam.training.mikheil_akobidze.pages.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private final By cartHeader= By.xpath("//span[text()='Your Cart']");
    private final By checkoutButton= By.id("checkout");

    public boolean isCartHeaderDisplayed() {
        return find(cartHeader).isDisplayed();
    }

    @Step("Validate item is present in cart: {itemName}")
    public boolean isItemsPresentInCart(String itemName) {
        List<WebElement> list = findListOfElements(By.cssSelector(".inventory_item_name"));
        for(WebElement item : list) {
            if(item.getText().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public CheckoutPage goToCheckoutPage() {
        click(checkoutButton);
        return new CheckoutPage();
    }
}
