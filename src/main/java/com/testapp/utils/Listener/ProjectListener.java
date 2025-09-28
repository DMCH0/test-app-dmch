package com.testapp.utils.Listener;

import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ProjectListener implements ITestListener {

    public static Set<ITestResult> testResults = new HashSet<>();

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        testResults.add(iTestResult);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        testResults.add(iTestResult);
    }


    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        testResults.add(iTestResult);
    }

    public void saveSummaryResultInFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("summaryResult.txt"));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

