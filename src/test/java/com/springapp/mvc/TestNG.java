package com.springapp.mvc;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Иван on 26.09.2015.
 */
public class TestNG {

    @BeforeMethod
    public void setUp() throws Exception {
        System.out.println("BeforeMethod");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        System.out.println("AfterMethod");
    }

    @Test
    public void test() {
        System.out.println("Test");
    }
}