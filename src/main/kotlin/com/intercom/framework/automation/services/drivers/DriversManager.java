package com.intercom.framework.automation.services.drivers;

import com.intercom.framework.automation.settings.TASSettings;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class DriversManager {

    @Autowired
    private TASSettings settings;

    private WebDriver driver;

    private String browserSelected;

    public WebDriver getDriver(){
        this.driver= selectDriver(settings.getBrowser());
        this.browserSelected=settings.getBrowser();
        return driver;
    }

    private WebDriver selectDriver(String browser){

        WebDriver driver= switch (browser) {
            case ("Chrome") -> getChromeDriver();
            case ("Internet Explorer") -> getInternetExplorerDriver();
            case ("Firefox") -> getFirefox();
            default -> throw new IllegalStateException("The browser selected is invalid. Browser: "+browser);
        };

        return driver;
    }

    private WebDriver getChromeDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--dns-prefetch-disable");
        driver= new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        setDriversResolution(driver);

        return driver;
    }

    private WebDriver getInternetExplorerDriver(){
        //System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\IEDriverServer.exe");

        InternetExplorerOptions ieOptions = new InternetExplorerOptions()
                .destructivelyEnsureCleanSession();
        ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        ieOptions.setCapability("ie.ensureCleanSession", true);
        driver= new InternetExplorerDriver(ieOptions);
        setDriversResolution(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    private WebDriver getFirefox(){
        driver= new FirefoxDriver();
        setDriversResolution(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    private void setDriversResolution(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        String resolution= settings.getBrowser();
        if(resolution.equals("Default")){
            driver.manage().window().maximize();
        }else{
            String[] size= resolution.split("x");
            int width= Integer.parseInt(size[0]);
            int height= Integer.parseInt(size[1]);
            driver.manage().window().setSize(new Dimension(width,height));
        }
    }

    public String getBrowserDriverSelected(){
        return browserSelected;
    }
}
