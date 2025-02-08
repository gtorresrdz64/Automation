package com.intercom.framework.automation.core;

import com.intercom.framework.automation.core.definition.testcase.AutomationTest;
import com.intercom.framework.automation.entity.model.RunnerTestsTimer;
import com.intercom.framework.automation.settings.TASSettings;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class TestRunner {

    Logger logger = LoggerFactory.getLogger(TestRunner.class);
    private final String TESTSUITE_PATH= "com.intercom.framework.automation.testsuites.";
    private final String LOCATION = "C:\\Users\\gtorr\\OneDrive\\Documentos\\Proyectos\\Automation\\src\\test\\kotlin\\com\\intercom\\framework\\automation\\tests\\";

    public void runTests(String testSuiteName, List<String> testsList) throws Exception {

        List<Class> classes = testsList.stream().map((test) -> setClass(testSuiteName, test)).toList();


        int testId = 0;

        if (classes.isEmpty()) {
            throw new Exception("Not Test Found");
        }

        RunnerTestsTimer runnerTestsTimer = new RunnerTestsTimer();
        runnerTestsTimer.setStartTime(LocalDateTime.now());

        classes.forEach(x-> {
            try {
                consumeTestClass(x);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });


        runnerTestsTimer.setEndTime(LocalDateTime.now());
        runnerTestsTimer.setDuration();

    }

    private void consumeTestClass(Class aClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        TestListenerAdapter listener = new TestListenerAdapter();
        TestNG testng = new TestNG();
        testng.setUseDefaultListeners(false);
        testng.setTestClasses(new Class[]{aClass});
        testng.addListener(listener);
        testng.run();
       /* if (aClass.isAnnotationPresent(AutomationTest.class)) {
            Object classOfTest = aClass.getDeclaredConstructor().newInstance();
            if(classOfTest.getClass().isAnnotationPresent(Test.class) ||
                    classOfTest.getClass().isAnnotationPresent(org.testng.annotations.Test.class)){
                applicationContext.getAutowireCapableBeanFactory().autowireBean(classOfTest);

            }



            /*for (Method method : aClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Test.class) || method.isAnnotationPresent(org.testng.annotations.Test.class)) {
                    method.setAccessible(true);
                    method.invoke(classOfTest);
                }
            }*/
       // }
    }

    private Class setClass(String testSuiteName,String test) {
        try {
            ClassLoader cl = getTestLocation(testSuiteName);
            return cl.loadClass(TESTSUITE_PATH+testSuiteName + "." + test);
        } catch (MalformedURLException | ClassNotFoundException e) {
            logger.info("Error: "+ getClass().getPackageName()+"Not Found");
            throw new RuntimeException(e);
        }
    }

    private ClassLoader getTestLocation(String testSuiteName) throws MalformedURLException {
        URL[] urls= new URL[]{new File(LOCATION+"\\"+testSuiteName).toURI().toURL()};
        logger.info("Location: "+urls[0]);
        return new URLClassLoader(urls);
    }
}
