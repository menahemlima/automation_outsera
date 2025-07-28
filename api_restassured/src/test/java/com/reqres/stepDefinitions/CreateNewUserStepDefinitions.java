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

public class CreateNewUserStepDefinitions {

	private static final Logger logger = LogManager.getLogger(CreateNewUserStepDefinitions.class);
	private TestContext testContext;
	private String name;
	private String job;

	public CreateNewUserStepDefinitions(TestContext testContext) {
		this.testContext = testContext;
	}

	@When("I create a user with name {string} and job {string}")
	public void iCreateAUserWithNameAndJob(String name, String job) {
		this.name = name;
		this.job = job;

		logger.info("Sending request to create a new user");
		testContext.response = testContext.reqresClient.postCreateNewUser(name, job);
	}

	@And("all data should be correctly")
	public void allDataShouldBeCorrectly() {
		assertEquals(name, testContext.response.jsonPath().getString("name"));
		assertThat(testContext.response.jsonPath().getString("name"), notNullValue());
		assertThat(testContext.response.jsonPath().getString("name"), not(emptyString()));

		assertEquals(job, testContext.response.jsonPath().getString("job"));
		assertThat(testContext.response.jsonPath().getString("job"), notNullValue());
		assertThat(testContext.response.jsonPath().getString("job"), not(emptyString()));

		assertThat(testContext.response.jsonPath().getString("createdAt"), notNullValue());
		assertThat(testContext.response.jsonPath().getString("createdAt"), not(emptyString()));
	}

}
