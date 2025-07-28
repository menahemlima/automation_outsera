package com.automation.web.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {
        "com.automation.web.stepDefinitions",
        "com.automation.web.hooks" },
    tags = "@regression", // @login, @purchase
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber-html-report.html",
        "json:target/cucumber-reports/cucumber.json",
        "junit:target/cucumber-reports/cucumber.xml",
        "rerun:target/failed_scenarios.txt" },
    snippets = CucumberOptions.SnippetType.CAMELCASE,
    dryRun = false,
    monochrome = true
)
public class TestRunner {
    
}