package com.reqres.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.reqres.config.TestContext;
import io.cucumber.java.en.When;

public class DeleteUserStepDefinitions {

	private static final Logger logger = LogManager.getLogger(DeleteUserStepDefinitions.class);

	private TestContext testContext;

	public DeleteUserStepDefinitions(TestContext testContext) {
		this.testContext = testContext;
	}

	@When("I try to delete a user with id {string}")
	public void iTryToDeleteAUserWithId(String user_id) {
		logger.info("Sending request to delete a user");
		testContext.response = testContext.reqresClient.deleteUser(user_id);
	}

}
