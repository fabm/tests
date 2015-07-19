package pt.report;

import java.util.HashMap;
import java.util.Map;

public class TestClass {
    private final Map<String, TestMethod> testMethods;

    public TestClass() {
        testMethods = new HashMap<String, TestMethod>();
    }

    public Map<String, TestMethod> getTestMethods() {
        return testMethods;
    }
}
