package com.intercom.framework.automation.settings;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TASSettings {

    @Value("${reports.path}")
    private String reportPath;
    @Value("${parameters.path}")
    private String parametersPath;
    @Value("${locators.path}")
    private String locatorsPath;
    @Value("${snapshots}")
    private boolean snapshots;
    @Value("${browser}")
    private String browser;
    @Value("${resolution}")
    private String resolution;
    @Value("${email.notifications}")
    private boolean emailNotification;
    @Value("${global.wait.time}")
    private int waitTime;
}
