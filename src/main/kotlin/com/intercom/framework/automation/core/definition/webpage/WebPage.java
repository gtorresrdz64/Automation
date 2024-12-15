package com.intercom.framework.automation.core.definition.webpage;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class WebPage {

    protected WebDriver driver;

    protected Map<String, List<String>> locators;

    protected List<String> webComponentsOfThePage;


    public void setPageWebComponents(WebDriver driver, Map locators){
        this.driver= driver;
        this.locators= locators;
    }

    protected void Name(String webPageName){
        webComponentsOfThePage= locators.get(webPageName);
    }

    public void openWebPage(String domain){
            driver.get(domain);
    }

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
