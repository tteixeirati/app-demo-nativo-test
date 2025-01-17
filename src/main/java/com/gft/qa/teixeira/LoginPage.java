package com.gft.qa.teixeira;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class LoginPage {

    AndroidDriver driver;

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void openLoginPage() throws Exception {
        driver.findElement(By.id("android:id/content")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.TextView[@text='Login']")).click();
        takeScreenShot("openLoginPage");
        Thread.sleep(1000);
    }

    public void fillInEmail(String userEmail) throws InterruptedException, IOException {
        driver.findElement(By.id("android:id/content")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.EditText[@text='Email']")).sendKeys(userEmail);
        takeScreenShot("fillInEmail");
    }

    public void fillInPassword(String password) throws IOException {
        driver.findElement(By.xpath("//android.widget.EditText[@text='Password']")).sendKeys(password);
        takeScreenShot("fillInPassword");
    }

    public void clickLoginbutton() throws IOException {
        driver.findElement(By.xpath("//android.widget.TextView[@text='LOGIN']")).click();
        takeScreenShot("clickLoginbutton");
    }

    public String getLoginResponse() throws IOException {
        String respostaLogin = driver.findElement(By.id("android:id/message")).getText();
        takeScreenShot("getLoginResponse");
        return respostaLogin;
    }

    public void confirmLoginMessage() throws IOException {
        driver.findElement(By.id("android:id/button1")).click();
        takeScreenShot("confirmLoginMessage");
    }

    public boolean getInvalidEmailResponse() throws IOException {
        boolean invalidEmail = false;
        try {
            String invalidEmailResponse = driver.findElement(By.xpath("//android.widget.TextView[@text='Please enter a valid email address']")).getText();
            if (invalidEmailResponse.equals("Please enter a valid email address")) invalidEmail = true;
        } catch (Exception e) {
            takeScreenShot("getInvalidEmailResponse");
            throw new RuntimeException(e);
        }
        takeScreenShot("getInvalidEmailResponse");
        return invalidEmail;
    }

    public void takeScreenShot(String imageName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destination = new File("src/test/screenshot/" + imageName + ".png");
        FileUtils.copyFile(screenshot, destination);
    }

}