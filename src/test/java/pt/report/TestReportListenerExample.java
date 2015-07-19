package pt.report;

import java.text.SimpleDateFormat;

public class TestReportListenerExample implements TestReportListener{
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss.SSS");

    public void doInitReport() {
        System.out.println("inicio do report");
    }

    public void doTestClass(String className) {
        System.out.println("--"+className+"--");
    }

    public void doTestMethod(String methodName) {
        System.out.println("---"+methodName+"---");
    }

    public void doTestAction(TestAction testAction) {
        System.out.println("---:"+testAction.getType()+":---");

        System.out.println("at "+format.format(testAction.getCalendar().getTime())+"");
        System.out.println(testAction.getContent());
    }

    public void doEndReport() {
        System.out.println("fim do report");
    }
}
