package com.intercom.framework.automation.testsuites.firsttestsuite;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FirstTest {

    @Test
    @Parameters({ "welcome", "thankyou" })
    public void testEasySamples(String welcome,String thankyou) {

        String title = "welcome";
        Assert.assertTrue(welcome.contains("Geeky people"));
        Assert.assertTrue(thankyou.contains("Geeky people"));
        Assert.assertTrue(title.contains("welcome"));
        Assert.assertTrue((1000 * 20) == 20000);
        Assert.assertTrue((1000 * 20) >= 2000);
        Assert.assertEquals(true, title.contains("welcome"));
        Assert.assertEquals(true, welcome.contains("Geeky people"));
        Assert.assertEquals(true, thankyou.contains("Geeky people"));
    }
}
