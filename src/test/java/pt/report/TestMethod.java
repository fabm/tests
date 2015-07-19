package pt.report;

import java.util.ArrayList;
import java.util.List;

public class TestMethod {
    private final List<TestAction> testActions;

    public TestMethod() {
        testActions = new ArrayList<TestAction>();
    }

    public List<TestAction> getTestActions() {
        return testActions;
    }

    public void addAssertAction(String name,String content){
        TestAction testAction = new TestAction(name,content);
        testActions.add(testAction);
    }
}
