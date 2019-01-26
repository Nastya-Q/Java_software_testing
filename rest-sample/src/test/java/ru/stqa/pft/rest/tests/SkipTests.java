package ru.stqa.pft.rest.tests;

import org.testng.annotations.Test;

public class SkipTests extends TestBase{

    @Test
    //bug #681 is open
    public void skipTestWithOpenBug() {
        skipIfNotFixed(681);
        System.out.println("Bug 681 is open, this test shouldn't run");
    }

    @Test
    //bug #678 is closed
    public void runTestWithClosedBug() {
        skipIfNotFixed(678);
        System.out.println("Bug 678 closed, this test should run");
    }

    @Test
    public void testGetIssueStatus() {
        String result = app.rest().getIssueStatus(680);
        System.out.println(result);
    }

}
