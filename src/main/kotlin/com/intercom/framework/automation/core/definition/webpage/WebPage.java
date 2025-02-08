package com.intercom.framework.automation.core.definition.webpage;

import lombok.Setter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Component
public class WebPage {

    protected WebDriver driver;

    protected Map<String, List<String>> locators;

    protected List<String> webComponentsOfThePage;

    private WebComponent component;

    @Setter
    private long waitTime = 0;


    public void setPageWebComponents(WebDriver driver, Map locators){
        this.driver= driver;
        this.locators= locators;
        this.component= new WebComponent(driver);
    }

    public void loadWebComponents(String webPageName){
        webComponentsOfThePage= locators.get(webPageName);
    }

    public void openWebPage(String domain){
        driver.get(domain);
    }

    public void WaitForPageLoad(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(waitTime));
    }

    /*public WebComponent Component(String name){

        this.component.setComponentData(name,)
    }*/

    protected String seleniumFoundElementBy(String findby) {

        String locatorType = switch (findby) {
            case ("CSS") -> "css=";
            case ("XPATH") -> "xpath=";
            case ("CLASSNAME") -> "className=";
            case ("LINKTEXT") -> "linkText=";
            case ("TAGNAME") -> "tagName=";
            case ("NAME") -> "name=";
            case ("ID") -> "id=";
            case ("PARTIALLINKTEXT") -> "partialLinkText=";
            default -> throw new NoSuchElementException("Option Selected was Invalid.");
        };
        return locatorType;
    }
}
