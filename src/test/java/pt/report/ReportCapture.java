package pt.report;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportCapture implements TestReportListener {
    private List<Map<String, Object>> classes;
    private Map<String, Object> currentClass;
    private Map<String, Object> currentMethod;
    private int failures;
    private int successes;
    private int errors;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss.SSS");


    public void doInitReport() {
        classes = new ArrayList<Map<String, Object>>();
    }

    public void doTestClass(final String className) {
        currentClass = new HashMap<String, Object>() {{
            put("name", className);
            put("methods", new ArrayList<Map<String, Object>>());
        }};
        classes.add(currentClass);
    }

    public void doTestMethod(final String methodName) {
        successes++;
        currentMethod = new HashMap<String, Object>() {{
            put("name", methodName);
            put("state", TestActionType.ASSERT);
            put("actions", new ArrayList<Map<String, String>>());
        }};
        ((List<Map<String, Object>>) currentClass.get("methods")).add(currentMethod);
    }

    public void doTestAction(TestAction testAction) {
        Map<String, String> actionMap = new HashMap<String, String>();
        actionMap.put("state", testAction.getType().toString());
        actionMap.put("name", testAction.getName());
        actionMap.put("content", testAction.getContent());
        actionMap.put("time", format.format(testAction.getCalendar().getTime()));
        ((List<Map<String, String>>) currentMethod.get("actions")).add(actionMap);
        switch (testAction.getType()) {
            case ASSERT:
                return;
            case ERROR:
                errors++;
                break;
            case FAIL:
                failures++;
                break;
        }
        currentMethod.put("state", testAction.getType().toString());
        successes--;
    }

    private Map<String, Object> getModel() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("reportTitle","Welfare - tests");
        model.put("classes", classes);
        model.put("errors", errors);
        model.put("failures", failures);
        model.put("successes", successes);
        model.put("total", errors + failures + successes);
        return model;
    }

    public void doEndReport() {
        Configuration cfg = new Configuration();
        //Load template from source folder
        try {
            File pathTestReport = new File(getClass().getResource("/tests-report").toURI());
            cfg.setDirectoryForTemplateLoading(pathTestReport);
            Template template = cfg.getTemplate("index.ftl");

            Path path = Paths.get(pathTestReport.toURI()).resolve("index.html");
            File indexFile = path.toFile();
            indexFile.createNewFile();

            final FileWriter out = new FileWriter(indexFile);
            template.process(getModel(), out);
            out.flush();
            out.close();

        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (TemplateException e) {
            throw new IllegalStateException(e);
        }
    }
}
