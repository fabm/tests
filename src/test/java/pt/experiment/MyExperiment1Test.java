package pt.experiment;

import org.junit.Rule;
import org.junit.Test;
import pt.report.ReporterRule;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static pt.Equivalent.equivalent;

public class MyExperiment1Test {
    @Rule
    public ReporterRule myRule = ReporterRule.get();

    @Test
    public void equivalentsTest1() throws URISyntaxException, IOException {
        myRule.getTestMethod().addAssertAction("assert-equivalent","I know the 3 is not 2");
        assertThat("2.0", equivalent(2));
        assertThat("2", equivalent(2));
        assertThat("3", equivalent(2));
    }
}
