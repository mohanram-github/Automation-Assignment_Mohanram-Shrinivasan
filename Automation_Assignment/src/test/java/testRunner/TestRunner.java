package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="D:\\Eclipse\\Automation_Assignment\\Features\\WindowHandling.feature",
				glue="stepDefinitions",
				dryRun=false,
				monochrome=true,
				plugin={"pretty","html:testReport"}
				)

public class TestRunner {

}
