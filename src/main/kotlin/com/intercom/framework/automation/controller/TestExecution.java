package com.intercom.framework.automation.controller;


import com.intercom.framework.automation.core.TestManagerCenter;
import com.intercom.framework.automation.entity.model.TestLoaderPayload;
import com.intercom.framework.automation.core.TestNGRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class TestExecution {

    Logger logger = LoggerFactory.getLogger(TestExecution.class);

    @Autowired
    private TestNGRunner testNGRunner;


    private TestManagerCenter testManagerCenter= new TestManagerCenter();

    @GetMapping("/hello")
    public String getHello(){
        return "Hello World: this project is alive";
    }

    @PostMapping("/execute")
    public void executeTests(@RequestBody TestLoaderPayload testLoaderPayload) throws MalformedURLException, ClassNotFoundException {

        String testSuiteName= testLoaderPayload.getTestSuiteName();
        List<String> testsList= testLoaderPayload.getTestSuiteList();

        logger.info("TestNG execution starts...");
        logger.info("TestSuite Name: "+testSuiteName);
        logger.info("Test List: "+testsList);
        testNGRunner.runTests(testSuiteName,testsList);
    }
}
