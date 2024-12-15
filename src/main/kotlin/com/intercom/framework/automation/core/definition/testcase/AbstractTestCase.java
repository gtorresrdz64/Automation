package com.intercom.framework.automation.core.definition.testcase;

import com.intercom.framework.automation.core.TestManagerCenter;
import com.intercom.framework.automation.core.definition.webpage.WebPage;
import com.intercom.framework.automation.services.drivers.DriversManager;
import com.intercom.framework.automation.services.exceptions.HandleExceptions;
import com.intercom.framework.automation.settings.TASSettings;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class AbstractTestCase implements ITestCase{

    @Autowired
    private DriversManager driversManager;

    @Autowired
    private HandleExceptions handleExceptions;

    @Autowired
    private TASSettings tasSettings;

    @Autowired
    private WebPage page;

    protected WebDriver driver;

    public AbstractTestCase(){

    }

    @Override
    public void setTestSettings() throws IOException {
        driver= driversManager.getDriver();
        handleExceptions.initErrorsStorage();
        page.setPageWebComponents(driver, TestManagerCenter.getLocators());
    }

    protected void setFlowType(HandleExceptions.Flow flow){
        handleExceptions.setFlowtype(flow);
    }
}
