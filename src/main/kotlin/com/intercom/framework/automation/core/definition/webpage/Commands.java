package com.intercom.framework.automation.core.definition.webpage;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Commands {

    private WebDriver driver;

    private WebElement component1;

    private WebElement component2;

    private String localorA;

    private String locatorB;

    private String findByA;

    private String findByB;


    public Commands Commands (WebDriver driver, String localorA, String findByA){
        this.driver= driver;
        this.localorA= localorA;
        this.findByA= findByA;

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
