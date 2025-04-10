package com.qa.teixeira;

import io.appium.java_client.android.AndroidDriver;

public class Login {

    private AndroidDriver driver;
    private LoginPage loginPage;

    public Login(AndroidDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);

    }

    public void logOnApp(String userEmail, String password) throws Exception {

        loginPage.openLoginPage();
        loginPage.fillInEmail(userEmail);
        loginPage.fillInPassword(password);
        loginPage.clickLoginbutton();

    }

}