package com.arkus.listeners;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(RealTimeReports.class)
public class TestReport {
    @Test
    public void testOne(){
        Assert.assertTrue(true);
    }

    @Test
    public void testTwo(){
        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = "testTwo")
    public void testThree(){
        Assert.assertTrue(true);
    }
}
