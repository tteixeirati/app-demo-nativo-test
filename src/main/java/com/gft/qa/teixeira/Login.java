package com.gft.qa.teixeira;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import com.gft.qa.teixeira.LoginPage;

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