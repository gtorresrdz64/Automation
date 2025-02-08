package com.intercom.framework.automation.testsuites.firsttestsuite;

import org.springframework.boot.test.context.SpringBootTest;
import com.intercom.framework.automation.core.definition.testcase.TestComponentsManager;
import org.testng.annotations.Test;

@SpringBootTest
public class TestCase extends TestComponentsManager {

    @Test
    public void execute(){
        System.out.println("Debug");
        cmd.loadWebComponents("Page1");
        cmd.openWebPage("www.google.com");


    }



}
