package com.intercom.framework.automation.core.definition.testcase;

import com.intercom.framework.automation.core.TestFilesManager;
import com.intercom.framework.automation.core.definition.webpage.WebPage;
import com.intercom.framework.automation.services.drivers.DriversManager;
import com.intercom.framework.automation.services.exceptions.HandleExceptions;
import com.intercom.framework.automation.settings.TASSettings;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class TestComponentsManager extends AbstractTestNGSpringContextTests {


    @Autowired
    private DriversManager driversManager;

    @Autowired
    private HandleExceptions handleExceptions;

    @Autowired
    private TASSettings tasSettings;


    @Autowired
    protected WebPage cmd;

    @Autowired
    protected WebDriver driver;

    public TestComponentsManager(){

    }

    @BeforeTest
    public void BeforeMethod() throws Exception{
        setTestSettings();
    }

    @AfterTest
    public void AfterMethod() throws Exception{

    }

    @Settings
    public void setTestSettings() throws IOException {
        driver= driversManager.getDriver();
        handleExceptions.initErrorsStorage();
        cmd.setPageWebComponents(driver, TestFilesManager.getLocators());
        cmd.setWaitTime(tasSettings.getWaitTime());
    }

    protected void setFlowType(HandleExceptions.Flow flow){
        handleExceptions.setFlowtype(flow);
    }
}
