package com.epam.training.mikheil_akobidze.pages.saucedemo_pages;

import com.epam.training.mikheil_akobidze.pages.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static utilities.Utility.parseDoubleFromString;
import static utilities.Utility.sumOfDoublesFromString;

public class CheckoutFinishPage extends BasePage {

    private final By finishHeader = By.xpath("//span[text()='Checkout: Overview']");
    private final By priceList = By.xpath("//div[@class='cart_list']//div[@class='inventory_item_price']");
    private final By totalPrice = By.xpath("//div[@class='summary_subtotal_label']");
    private final By finishButton = By.id("finish");

    public boolean isFinishHeaderDisplayed() {
        return driver.findElement(finishHeader).isDisplayed();
    }

    private String[] getPriceListText() {
        List<WebElement> list = findListOfElements(priceList);
        String[] priceText = new String[list.size()];

       for(int i = 0; i < list.size(); i++) {
           String text =  list.get(i).getText();
           priceText[i] = text;
       }
       return priceText;
    }


    private double getSumOfItemsPrice() {
        // Actual sum of Items prices
        return sumOfDoublesFromString(getPriceListText());
    }

    private double getTotalPrice() {
        // Total price provided on Finish Page
        return parseDoubleFromString(find(totalPrice).getText());
    }

    @Step("Validate final price equals the sum of both product prices")
    public boolean isPricesEqual(){
        return getSumOfItemsPrice() == getTotalPrice();
    }

    public CompletePage finish() {
        click(finishButton);
        return new CompletePage();
    }
}
