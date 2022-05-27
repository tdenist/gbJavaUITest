import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources"},
        plugin = {"pretty",
                "json:target/cucumber-reports/report.html"},
        publish = true)


public class TestRunner {
}
