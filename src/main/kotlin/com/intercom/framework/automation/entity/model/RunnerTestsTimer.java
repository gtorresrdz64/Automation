package com.intercom.framework.automation.entity.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
public class RunnerTestsTimer {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Duration duration;

    public void setDuration(){
        duration= Duration.between(startTime,endTime);
    }
}
