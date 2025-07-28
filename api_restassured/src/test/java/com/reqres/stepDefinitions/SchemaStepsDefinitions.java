package com.reqres.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.reqres.config.TestContext;
import com.reqres.utils.Utils;
import io.cucumber.java.en.Then;

public class SchemaStepsDefinitions {

	private static final Logger logger = LogManager.getLogger(SchemaStepsDefinitions.class);

	private TestContext testContext;

	public SchemaStepsDefinitions(TestContext testContext) {
		this.testContext = testContext;
	}

	@Then("the schema should be equal {string}")
	public void theSchemaShouldBeEqual(String schemaFileName) {
		logger.info("Validating schema with file: {}", schemaFileName);
		Utils.validateSchema(testContext.response, schemaFileName);
	}
}
