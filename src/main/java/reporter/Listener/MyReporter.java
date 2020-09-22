package reporter.Listener;

import com.aventstack.extentreports.ExtentTest;

/**
 **
 * @Auther: mazhaoyang
 * @Date: 2020/9/22 20:26
 * @Description:
 */
public class MyReporter {
    public static ExtentTest report;
    private static String testName;

    public static String getTestName() {
        return testName;
    }

    public static void setTestName(String testName) {
        MyReporter.testName = testName;
    }
}
