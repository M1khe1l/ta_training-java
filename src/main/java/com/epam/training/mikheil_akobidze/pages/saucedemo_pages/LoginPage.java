package com.epam.training.mikheil_akobidze.pages.saucedemo_pages;

import com.epam.training.mikheil_akobidze.pages.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");

    public void setUsername(String username) {
        set(usernameField, username);
    }

    public void setPassword(String password) {
        set(passwordField, password);
    }

    @Step("Login with username: {username} and password: {password}")
    public InventoryPage logIntoApplication(String username, String password) {
        setUsername(username);
        setPassword(password);
        click(loginButton);
        return new InventoryPage();
    }
}
