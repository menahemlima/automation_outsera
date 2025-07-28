package com.reqres.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
glue = { "com.reqres.stepDefinitions",
		"com.reqres.hooks" },
tags = "@regression", // @create, @update, @delete, @list, @schema
		plugin = { "pretty",
				"html:target/cucumber-reports/cucumber-html-report.html",
				"json:target/cucumber-reports/cucumber.json",
				"junit:target/cucumber-reports/cucumber.xml",
				"rerun:target/failed_scenarios.txt" },
		snippets = CucumberOptions.SnippetType.CAMELCASE,
		dryRun = false,
		monochrome = true)
public class TestRunner {

}