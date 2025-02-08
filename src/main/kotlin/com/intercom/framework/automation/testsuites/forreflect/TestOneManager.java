package com.intercom.framework.automation.testsuites.forreflect;

import com.intercom.framework.automation.core.definition.testcase.TestComponentsManager;
import com.intercom.framework.automation.core.definition.testcase.AutomationTest;
import org.junit.Test;

@AutomationTest
public class TestOneManager extends TestComponentsManager {


    @Test
    public void execute(){
        System.out.println("Hello World");
    }
}
