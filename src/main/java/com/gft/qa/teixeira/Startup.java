package com.gft.qa.teixeira;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Startup {

    Properties appProps;

    public Startup() {
        appProps = new Properties();
        try {
            appProps.load(new FileInputStream(System.getProperty("user.dir")+"\\app.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public AndroidDriver startApp() throws Exception {

        String currentDir = System.getProperty("user.dir");
        String apkPath = currentDir + "\\android.wdio.native.app.v1.0.8.apk";
        URL appiumServerUrl = new URL(appProps.getProperty("appium_server_url"));

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        options.setAppPackage("com.wdiodemoapp");
        options.setAppActivity("com.wdiodemoapp/.MainActivity");
        options.setAutomationName("UiAutomator2");
        options.setPlatformName("Android");
        options.setPlatformVersion("15.0");
        options.setNewCommandTimeout(Duration.ofSeconds(60));

        AndroidDriver driver = new AndroidDriver(appiumServerUrl, options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        if (!driver.isAppInstalled("com.wdiodemoapp")) {
            driver.installApp(apkPath);
        }
        Thread.sleep(3000);
        return driver;

    }

}