package com.intercom.framework.automation.entity.model.reports;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class TestNGData {

    private int testId;
    private String testName;
    private String statusResults;
    private String remarks;

}
