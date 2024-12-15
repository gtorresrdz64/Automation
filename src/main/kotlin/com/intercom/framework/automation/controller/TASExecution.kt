package com.intercom.framework.automation.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TASExecution {


    @GetMapping("/goodbye")
    fun sayGoodBye(): String = "Good Bye"
}