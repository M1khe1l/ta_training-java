package com.epam.training.mikheil_akobidze.saucedemo_task_2;

import com.epam.training.mikheil_akobidze.base.BaseTest;
import com.epam.training.mikheil_akobidze.base.TestData;
import com.epam.training.mikheil_akobidze.pages.saucedemo_pages.*;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Saucedemo E2E")
@Feature("Checkout Flow")
public class CheckoutSeveralItemTest extends BaseTest {

    @Test(dataProvider = "browserProvider")
    @Story("Task-2: Checkout several item")
    @Description("Login, add two products to cart, validate prices, complete checkout and validate success message")
    public void CheckoutSeveralItem(String browser) {
        setUp(browser);

        // GIVEN - user is on the login page
        LoginPage loginPage = new LoginPage();
        loginPage.setDriver(getDriver());

        // WHEN - user logs in with valid credentials
        InventoryPage inventoryPage =  loginPage.logIntoApplication(TestData.USERNAME, TestData.PASSWORD);
        inventoryPage.setDriver(getDriver());
        Assert.assertTrue(inventoryPage.isProductHeaderDisplayed(), "\nProduct header is not displayed");

        // AND - user adds two products to the cart
        inventoryPage.addProductsToCart(TestData.BACKPACK, TestData.BIKE_LIGHT);   // Add two items on cart

        // AND - user navigates to the cart
        CartPage cartPage = inventoryPage.goToShoppingCart();
        cartPage.setDriver(getDriver());

        // THEN - cart should contain both added products
        Assert.assertTrue(cartPage.isCartHeaderDisplayed(), "\nCart header is not displayed");
        Assert.assertTrue(cartPage.isItemsPresentInCart("Sauce Labs Backpack"), "\nBackpack is not present in cart");
        Assert.assertTrue(cartPage.isItemsPresentInCart("Sauce Labs Bike Light"), "\nBike Light is not present in cart");


        // WHEN - user proceeds to checkout and fills in the information form
        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
        checkoutPage.setDriver(getDriver());
        Assert.assertTrue(checkoutPage.isCheckoutHeaderDisplayed());
        checkoutPage.fillInputFields(TestData.FIRST_NAME, TestData.LAST_NAME, TestData.ZIP_CODE);

        // AND - user completes the checkout
        CheckoutFinishPage finishPage = checkoutPage.goToFinishPage();
        finishPage.setDriver(getDriver());
        Assert.assertTrue(finishPage.isFinishHeaderDisplayed());

        // THEN - final price should equal the sum of both product prices
        Assert.assertTrue(finishPage.isPricesEqual(), "\nFinal price is not matched to sum of items price");


        // AND - success message should be displayed
        CompletePage completePage = finishPage.finish();
        completePage.setDriver(getDriver());
        Assert.assertEquals(completePage.getCompleteTextBoxText(), "Thank you for your order!");

        completePage.goBack();
    }
}
