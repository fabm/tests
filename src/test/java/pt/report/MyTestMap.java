package pt.report;

import com.metapossum.utils.scanner.reflect.ClassesInPackageScanner;
import pt.experiment.MyExperiment1Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MyTestMap {
    public static Set<String> getClassesName() {
        try {
            Set<Class<?>> classes = new ClassesInPackageScanner()
                .findImplementers(MyExperiment1Test.class.getPackage().getName(), Object.class);

            Set<String> classesSet = new HashSet<String>();
            for (Class<?> clazz:classes){
                classesSet.add(clazz.getName());
            }
            return classesSet;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
