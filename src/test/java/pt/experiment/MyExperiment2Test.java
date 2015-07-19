package pt.experiment;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import pt.report.ReporterRule;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static pt.Equivalent.equivalent;

public class MyExperiment2Test {
    @Rule
    public ReporterRule myRule = ReporterRule.get();

    @Test
    public void equivalentsTest() throws URISyntaxException, IOException {
        myRule.getTestMethod().addAssertAction("try assert with equivalent matcher","try assert with equivalent matcher");
        assertThat("2.0", equivalent(2));
        assertThat("2", equivalent(2));
    }

    @Test
    public void nullPointerTest(){
        myRule.getTestMethod().addAssertAction("null","I know it's a null");
        List<String> list = null;
        Assert.assertEquals(list.get(0),"");
    }
}
