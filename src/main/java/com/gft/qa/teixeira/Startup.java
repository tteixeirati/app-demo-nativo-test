package com.gft.qa.teixeira;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.DesiredCapabilities.*;

public class Startup {

    public AndroidDriver driver;

    public AndroidDriver startApp() throws Exception {

        String currentDir = System.getProperty("user.dir");
        String apkPath = currentDir + "\\android.wdio.native.app.v1.0.8.apk";
//        URL appiumServerUrl = new URL("http://172.30.208.1:4723/");
        URL appiumServerUrl = new URL("https://hub-cloud.browserstack.com/wd/hub");
        String userName = System.getenv("BROWSERSTACK_USERNAME");
        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

        UiAutomator2Options options = new UiAutomator2Options();
//        options.setDeviceName("emulator-5554");
        options.setAppPackage("com.wdiodemoapp");
        options.setAppActivity("com.wdiodemoapp/.MainActivity");
        options.setAutomationName("UiAutomator2");
        options.setPlatformName("Android");
        options.setPlatformVersion("12.0");
        options.setNewCommandTimeout(Duration.ofSeconds(60));

//        options.setCapability("platformName", "Android"); // or "iOS"
        options.setCapability("deviceName", "Samsung Galaxy S22 Ultra"); // Specify device
        options.setCapability("app", "bs://0b3702a6ab69ccd0f740dd899056284cf3a876f7.app"); // Specify app ID in BrowserStack
        options.setCapability("browserstack.user", userName); // Set BrowserStack username
        options.setCapability("browserstack.key", accessKey); // Set BrowserStack access key

        driver = new AndroidDriver(appiumServerUrl, options);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        if (!driver.isAppInstalled("com.wdiodemoapp")) {
//            driver.installApp(apkPath);
            driver.installApp("bs://0b3702a6ab69ccd0f740dd899056284cf3a876f7.app");
        }
        Thread.sleep(3000);
        return driver;

    }

}