package com.reqres.stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.reqres.config.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class ListByUserStepDefinitions {

	private static final Logger logger = LogManager.getLogger(ListByUserStepDefinitions.class);
	private TestContext testContext;

	public ListByUserStepDefinitions(TestContext testContext) {
		this.testContext = testContext;
	}

	@When("I list a user with id {string}")
	public void iListAUserWithId(String user_id) {
		logger.info("Sending request to list a user");
		testContext.response = testContext.reqresClient.getListUserById(user_id);
	}

	@And("all information should be listed correctly {string}, {string}, {string}, {string}")
	public void allInformationShouldBeListedCorrectly(String email, String first_name, String last_name,
			String avatar) {
		assertEquals(email, testContext.response.jsonPath().getString("data.email"));
		assertThat(testContext.response.jsonPath().getString("data.email"), notNullValue());
		assertThat(testContext.response.jsonPath().getString("data.email"), not(emptyString()));

		assertEquals(first_name, testContext.response.jsonPath().getString("data.first_name"));
		assertThat(testContext.response.jsonPath().getString("data.first_name"), notNullValue());
		assertThat(testContext.response.jsonPath().getString("data.first_name"), not(emptyString()));

		assertEquals(last_name, testContext.response.jsonPath().getString("data.last_name"));
		assertThat(testContext.response.jsonPath().getString("data.last_name"), notNullValue());
		assertThat(testContext.response.jsonPath().getString("data.last_name"), not(emptyString()));

		assertEquals(avatar, testContext.response.jsonPath().getString("data.avatar"));
		assertThat(testContext.response.jsonPath().getString("data.avatar"), notNullValue());
		assertThat(testContext.response.jsonPath().getString("data.avatar"), not(emptyString()));
	}

	@And("all information should not be visible")
	public void allInformationShouldNotBeVisible() {
		String bodyAsString = testContext.response.getBody().asString().trim();
		assertEquals("{}", bodyAsString);
	}

}
