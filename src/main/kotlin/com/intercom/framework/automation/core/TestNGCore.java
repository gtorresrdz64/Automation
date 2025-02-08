package com.intercom.framework.automation.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.intercom.framework.automation.entity.model.reports.TestNGData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

@Component
public class TestNGCore {

    @Autowired
    private TestFilesManager testFilesManager;

    private List<String> errors;
    public Object[][] inputTestData;

    public void runTestCase(int testId,Class testcase) throws IOException {
        String path= testcase.getPackage().toString().replace("package ","");
        testFilesManager.setAnnexedFiles(path,testcase.getSimpleName());//classname used to know to which test case and the annexed files
        //Call the file of paramaters testData
        testNGCore(testId,testcase);

    }

    private void testNGCore(int testId, Class testcase) throws IOException {
        //errors= new ArrayList<>(); // Array used to collect information about which web components failed
        TestListenerAdapter listener = new TestListenerAdapter();
        TestNG testng = new TestNG();
        testng.setUseDefaultListeners(false);
        testng.setTestClasses(new Class[]{testcase});
        testng.addListener(listener);
        testng.run();


        List<TestNGData> testNGResultsStorage= new ArrayList<>();
        collectResults(testNGResultsStorage,listener.getPassedTests(),testId,"PASSED","<b> Input TestData: </b>" + inputTestData);
        collectResults(testNGResultsStorage,listener.getFailedTests(),testId,"FAILED","<b> Input TestData: </b>" + inputTestData);
    }


    private void collectResults(List<TestNGData> testNGResultsStorage,List<ITestResult> listenerResults,int testId, String status, String details){
        if(!CollectionUtils.isEmpty(listenerResults)) {
             listenerResults.stream().forEach((testResult)->{
                 testNGResultsStorage.add(new TestNGData(testId,testResult.getInstanceName().toString(),status,formatResultDetails(testResult,details)));
             });
        }
    }

    private String formatResultDetails(ITestResult testResult,String details){
        if(StringUtils.hasText(testResult.getThrowable().getMessage())){
            return testResult.getThrowable().getMessage()+details;
        }
        return details;
    }
}
