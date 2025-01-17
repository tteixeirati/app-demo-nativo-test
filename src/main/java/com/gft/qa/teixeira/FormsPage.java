package com.gft.qa.teixeira;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class FormsPage {

    AndroidDriver driver;

    public FormsPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void openFormsPagePage() throws Exception {

        driver.findElement(By.id("android:id/content")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.TextView[@text='Forms']")).click();
        Thread.sleep(1000);
        takeScreenShot("openFormsPagePage");
    }

    public void fillInInputFieldAndValidateYouHaveTyped(String typeSomething) throws InterruptedException, IOException {

        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.EditText[@text='Type something']")).sendKeys(typeSomething);
        if (!validateYouHaveTyped(typeSomething)) throw new RuntimeException();
        takeScreenShot("fillInInputFieldAndYouHaveTyped");
    }

    public boolean validateYouHaveTyped(String InputFieldValue) throws IOException {
        String youHaveTypedValue = driver.findElement(By.xpath("//android.widget.TextView[@content-desc='input-text-result']")).getText();
        takeScreenShot("validateYouHaveTyped");
        return youHaveTypedValue.equals(InputFieldValue);
    }

    public void clickAndValidateSwitch() throws IOException {
        driver.findElement(By.xpath("//android.widget.Switch[@content-desc='switch']")).click();
        validateSwitch();
        takeScreenShot("clickAndValidateSwitch");
    }

    public void validateSwitch() throws IOException {
        String switchValue = driver.findElement(By.xpath("//android.widget.TextView[@content-desc='switch-text']")).getText();
        if (!switchValue.equals("Click to turn the switch OFF")) throw new RuntimeException();
        takeScreenShot("validateSwitch");
    }

    public void validateDropdown() throws InterruptedException, IOException {

        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.EditText[@text='Select an item...']")).click();
        driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Appium is awesome']")).click();
        Thread.sleep(1000);
        String valueSelected = driver.findElement(By.xpath("//android.widget.EditText[@resource-id='text_input']")).getText();
        if (!valueSelected.equals("Appium is awesome")) throw new RuntimeException();
        takeScreenShot("validateDropdown");
    }

    public void takeScreenShot(String imageName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destination = new File("src/test/screenshot/" + imageName + ".png");
        FileUtils.copyFile(screenshot, destination);
    }

}