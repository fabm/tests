package pt.experiment.inner;

import org.junit.Rule;
import org.junit.Test;
import pt.report.ReporterRule;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static pt.Equivalent.equivalent;

public class MyExperiment1Test {
    @Rule
    public ReporterRule myRule = ReporterRule.get();

    @Test
    public void equivalentsInnerTest() throws URISyntaxException, IOException {
        myRule.getTestMethod().addAssertAction("assert-equivalent","2.0 with 2");
        assertThat("2.0", equivalent(2));
        assertThat("2", equivalent(2));
        assertThat("3", not(equivalent(2)));
    }
}
