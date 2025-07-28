package com.automation.mobile.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.automation.mobile.stepDefinitions",
    		"com.automation.mobile.hooks"},
    tags = "@regression", // @login, @form
    plugin = {"pretty",
    		"html:target/cucumber-reports/cucumber-report.html", 
    		"json:target/cucumber-reports/cucumber.json",
            "junit:target/cucumber-reports/cucumber.xml",
            "rerun:target/failed_scenarios.txt" },
    monochrome = true,
    snippets = CucumberOptions.SnippetType.CAMELCASE,
    dryRun = false
)

public class TestRunner {

}