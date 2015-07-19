package pt.report;

public interface TestReportListener {
    void doInitReport();
    void doTestClass(String className);
    void doTestMethod(String methodName);
    void doTestAction(TestAction testAction);
    void doEndReport();
}
