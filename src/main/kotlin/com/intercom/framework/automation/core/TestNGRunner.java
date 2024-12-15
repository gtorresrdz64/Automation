package com.intercom.framework.automation.core;

import com.intercom.framework.automation.entity.model.RunnerTestsTimer;
import com.intercom.framework.automation.settings.TASSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TestNGRunner {

    private final String LOCATION= "target/classes";
    private final String TESTSUITE_PATH= "com.intercom.framework.automation.testsuites.";
    @Autowired
    private TASSettings settings;
    @Autowired
    private TestNGCore testNGCore;
    Logger logger = LoggerFactory.getLogger(TestNGRunner.class);


    public void runTests(String testSuiteName, List<String> testsList) {

        RunnerTestsTimer runnerTestsTimer = new RunnerTestsTimer();
        runnerTestsTimer.setStartTime(LocalDateTime.now());
        int testId = 0;


        List<Class> classes= testsList.stream().map((test)-> setClass(testSuiteName,test)).toList();
        classes.forEach((testClass)-> {
            try {
                testNGCore.runTestCase(testId,testClass);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        runnerTestsTimer.setEndTime(LocalDateTime.now());
        runnerTestsTimer.setDuration();
    }

    private Class setClass(String testSuiteName,String test) {
        try {
            ClassLoader cl = getTestLocation();
            return cl.loadClass(TESTSUITE_PATH+testSuiteName + "." + test);
        } catch (MalformedURLException | ClassNotFoundException e) {
            logger.info("Error: "+ getClass().getPackageName()+"Not Found");
            throw new RuntimeException(e);
        }
    }

    private ClassLoader getTestLocation() throws MalformedURLException {
        URL[] urls= new URL[]{new File(LOCATION).toURI().toURL()};
        logger.info("Location: "+urls[0]);
        return new URLClassLoader(urls);
    }
}