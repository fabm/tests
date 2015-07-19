package pt.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Utils {
    public String throwableStackToString(Throwable throwable){
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}
