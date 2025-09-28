package com.testapp.playercontroller.basetest;

import com.testapp.utils.Listener.ProjectListener;
import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;

public class BaseTest {

    ProjectListener projectListener = new ProjectListener();

    @AfterClass
    public void closeSession() {
        RestAssured.reset();
    }

    @AfterSuite
    public void generateReport() {
        projectListener.saveSummaryResultInFile();
    }
}
