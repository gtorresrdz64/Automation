package com.intercom.framework.automation.core.definition.webpage;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebComponent {

    private WebDriver driver;

    private WebElement component1;

    private WebElement component2;

    private String localorA;

    private String locatorB;

    private String findByA;

    private String findByB;


    public WebComponent (WebDriver driver){
        this.driver= driver;
    }

    public WebComponent setComponentData(String locator,String type){
        this.localorA= locator;
        this.findByA= type;
        return this;
    }


    protected String getFontFamily(String pfamily) {

        String family = pfamily.replace("\"", "");
        family = family.replace("\'", "");
        family = family.replace(" ", "");
        return family;
    }

    protected boolean isPresent() throws NoSuchElementException {
        return component1.isDisplayed();
    }



}
