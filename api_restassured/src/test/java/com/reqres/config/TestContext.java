package com.reqres.config;

import com.reqres.api.clients.ApiClient;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;

public class TestContext {

	public Scenario scenario;
	public ApiClient reqresClient;
	public Response response;

	public TestContext() {
		this.reqresClient = new ApiClient();
	}

}
