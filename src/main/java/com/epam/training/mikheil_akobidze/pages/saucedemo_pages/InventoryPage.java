package com.epam.training.mikheil_akobidze.pages.saucedemo_pages;

import com.epam.training.mikheil_akobidze.pages.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
public class InventoryPage extends BasePage {

    private final By productHeader = By.xpath("//span[text()='Products']");
    private final By shoppingCart = By.id("shopping_cart_container");

    public boolean isProductHeaderDisplayed() {
        return find(productHeader).isDisplayed();
    }

    @Step("Adding products to cart: {paths}")
    public void addProductsToCart(String... paths) {
        for (String path : paths) {
            find(By.id(path)).click();
        }
    }

    public CartPage goToShoppingCart() {
        click(shoppingCart);
        return new CartPage();
    }
}
