package com.reqres.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertEquals;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.reqres.config.TestContext;

public class CommonsStepsDefinitions {

	private static final Logger logger = LogManager.getLogger(CommonsStepsDefinitions.class);

	private TestContext testContext;

	public CommonsStepsDefinitions(TestContext testContext) {
		this.testContext = testContext;
	}

	@Given("that the Reqres API is available")
	public void thatTheReqresApiIsAvailable() {
		logger.info("Checking availability of Reqres API endpoint!");
	}

	@Then("the response should match with status code {int}")
	public void theResponseShouldMatchWithStatusCode(int status_code) {
		assertEquals(status_code, testContext.response.getStatusCode());
	}

	@Then("the user should not be listed with status code {int}")
	public void theUserShouldNotBeListedWithStatusCode(int status_code) {
		assertEquals(status_code, testContext.response.getStatusCode());
	}

}
