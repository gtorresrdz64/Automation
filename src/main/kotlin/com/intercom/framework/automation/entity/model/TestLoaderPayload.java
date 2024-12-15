package com.intercom.framework.automation.entity.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestLoaderPayload {

    private String testSuiteName;
    private List<String> testSuiteList;
}
