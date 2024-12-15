package com.intercom.framework.automation.services.exceptions;

import com.intercom.framework.automation.core.TestNGRunner;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HandleExceptions {

    Logger logger = LoggerFactory.getLogger(HandleExceptions.class);
    private Flow flowtype;
    private List<String> errors;

    public enum Flow {
        CONTINIUS, INTERUPTED;
    }

    public void initErrorsStorage(){
        errors= new ArrayList<>();
    }

    public void errorException(String message,Exception ex,String locator){

        if(flowtype.equals(Flow.CONTINIUS)){
            exceptionWithoutThrow(message,locator,ex);
        }

        exceptionWithThrow(message,locator,ex);
    }

    private void exceptionWithoutThrow(String message,String locator,Exception ex){
        messageDefinition(message, locator,ex);
    }

    private void exceptionWithThrow(String message,String locator,Exception ex){
        messageDefinition(message, locator,ex);
        throw new WebDriverException(message,ex);
    }

    private void messageDefinition(String message,String locator,Exception ex){
        errors.add(message+" for element: "+locator);
        logger.info (message+" for element: "+locator+" "+ex.getMessage());
    }

    public void setFlowtype(Flow flowtype) {
        this.flowtype = flowtype;
    }
}
