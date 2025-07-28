package com.reqres.stepDefinitions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.reqres.config.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class UpdateUserStepDefinitions {

	private static final Logger logger = LogManager.getLogger(UpdateUserStepDefinitions.class);
	private TestContext testContext;
	private String name;
	private String job;

	public UpdateUserStepDefinitions(TestContext testContext) {
		this.testContext = testContext;
	}

	@When("I update a user with id {string}, {string}, {string}")
	public void iUpdateAUserWithId(String user_id, String name, String job) {
		this.name = name;
		this.job = job;
		logger.info("Sending request to update a user");
		testContext.response = testContext.reqresClient.putUpdateUser(user_id, name, job);
	}

	@And("all information should be updated correctly")
	public void allInformationShouldBeUpdatedCorrectly() {
		assertEquals(name, testContext.response.jsonPath().getString("name"));
		assertThat(testContext.response.jsonPath().getString("name"), notNullValue());
		assertThat(testContext.response.jsonPath().getString("name"), not(emptyString()));

		assertEquals(job, testContext.response.jsonPath().getString("job"));
		assertThat(testContext.response.jsonPath().getString("job"), notNullValue());
		assertThat(testContext.response.jsonPath().getString("job"), not(emptyString()));

		assertThat(testContext.response.jsonPath().getString("updatedAt"), notNullValue());
		assertThat(testContext.response.jsonPath().getString("updatedAt"), not(emptyString()));
	}

}
