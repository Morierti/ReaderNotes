package readernotes.test.core;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class CoreTestRunner {
    public static void main(String[] args) {
        BookTestRunner();
        SinteseTestRunner();
        ShellTestRunner();
    }

    private static void BookTestRunner() {
        Result result = JUnitCore.runClasses(BookTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }

    private static void SinteseTestRunner() {
        Result result = JUnitCore.runClasses(SinteseTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }

    private static void ShellTestRunner() {
        Result result = JUnitCore.runClasses(ShellTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}