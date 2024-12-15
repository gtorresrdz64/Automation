package com.intercom.framework.automation.testsuites.firsttestsuite;

import com.intercom.framework.automation.core.definition.testcase.AbstractTestCase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase extends AbstractTestCase {

    private WebDriver driver;

    @BeforeTest
    public void BeforeMethod() throws Exception{
       setTestSettings();
    }

    @Test
    public void testLogic(){

    }

    @AfterTest
    public void AfterMethod() throws Exception{

    }

}
