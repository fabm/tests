package pt.report;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;

public class TestAction {

    private final Calendar calendar;
    private final String content;
    private final TestActionType type;
    private final String name;

    public TestAction(TestActionType type, String name, String content) {
        this.type = type;
        this.name = name;
        this.calendar = Calendar.getInstance();
        this.content = content;
    }

    public TestAction(TestActionType type, Throwable throwable) {
        this.type = type;
        this.calendar = Calendar.getInstance();
        this.name = "";
        switch (type) {
            case FAIL:
                this.content = throwable.getMessage();
                break;
            case ERROR:
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                throwable.printStackTrace(printWriter);
                this.content = stringWriter.toString();
                break;
            default:
                throw new IllegalStateException("type "+type.toString()+" unexpected");
        }
    }

    public TestAction(String name, String content) {
        this.type = TestActionType.ASSERT;
        this.calendar = Calendar.getInstance();
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public String getContent() {
        return content;
    }

    public TestActionType getType() {
        return type;
    }
}
