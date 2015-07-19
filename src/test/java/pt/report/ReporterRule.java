package pt.report;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReporterRule implements TestRule {

    private static ReporterRule testRule;

    public static ReporterRule get() {
        if (testRule == null) {
            testRule = new ReporterRule();
            testRule.testReportListener = new ReportCapture();
            testRule.classNames = MyTestMap.getClassesName();
            testRule.testClassMap = new HashMap<String, TestClass>();
        }

        String currentClassName = Thread.currentThread().getStackTrace()[2].getClassName();
        testRule.classNames.remove(currentClassName);
        testRule.count = 0;
        testRule.testClass = testRule.testClassMap.get(currentClassName);
        if (testRule.testClass == null) {
            testRule.testClass = new TestClass();
            testRule.testClassMap.put(currentClassName, testRule.testClass);
        }
        return testRule;
    }

    private Set<String> classNames;
    private Map<String, TestClass> testClassMap;
    private TestMethod testMethod;
    private TestClass testClass;
    private TestReportListener testReportListener;
    private int count;

    public TestMethod getTestMethod() {
        return testMethod;
    }

    public Statement apply(final Statement statement, final Description description) {
        testMethod = new TestMethod();
        testClass.getTestMethods().put(description.getMethodName(), testMethod);
        Statement evaluated = new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    statement.evaluate();
                } catch (AssertionError assertionError) {
                    TestAction fail = new TestAction(TestActionType.FAIL, assertionError);
                    testMethod.getTestActions().add(fail);
                    throw assertionError;
                } catch (Throwable throwable) {
                    TestAction error = new TestAction(TestActionType.ERROR, throwable);
                    testMethod.getTestActions().add(error);
                    throw throwable;
                } finally {
                    checkIsLast(description);
                }
            }
        };
        return evaluated;
    }

    private void checkIsLast(Description description) {
        if (description.testCount() == ++count) {
            lastTestMethod();
        }
    }

    private void lastTestMethod() {
        if (classNames.isEmpty()) {
            endTests();
        }
    }

    private void endTests() {
        testReportListener.doInitReport();
        for (Map.Entry<String, TestClass> testClassEntry : testClassMap.entrySet()) {
            testReportListener.doTestClass(testClassEntry.getKey());
            for (Map.Entry<String, TestMethod> testMethodEntry : testClassEntry.getValue().getTestMethods().entrySet()) {
                testReportListener.doTestMethod(testMethodEntry.getKey());
                for (TestAction testAction : testMethodEntry.getValue().getTestActions()) {
                    testReportListener.doTestAction(testAction);
                }
            }
        }
        testReportListener.doEndReport();
    }
}
