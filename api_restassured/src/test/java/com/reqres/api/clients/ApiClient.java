package com.reqres.api.clients;

import com.reqres.api.endpoints.ReqresEndpoints;
import com.reqres.api.pojos.CreateNewUserRequest;
import com.reqres.api.pojos.UpdateUserRequest;
import com.reqres.config.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ApiClient {
	
	String apiKey = ConfigurationManager.getProperty("apiKey");
	String apiValue = ConfigurationManager.getProperty("apiValue");

	public ApiClient() {
		RestAssured.baseURI = ConfigurationManager.getProperty("reqres.base.url");
	}
	
	public Response postCreateNewUser(String name, String job) {
		CreateNewUserRequest userPayload = new CreateNewUserRequest(name, job);
		String path = ReqresEndpoints.CREATE_USER;
		return given()
				.header(apiKey,apiValue)
				.contentType("application/json")
				.body(userPayload)
				.when()
				.post(path);
	}

	public Response getListUserById(String id_user) {
		String path = ReqresEndpoints.LIST_USER.replace("{id_user}", id_user);
		return given().header(apiKey,apiValue)
				.when()
				.get(path);
	}	

	public Response putUpdateUser(String id_user, String name, String job) {
		UpdateUserRequest userPayload = new UpdateUserRequest(name, job);
		String path = ReqresEndpoints.UPDATE_USER.replace("{id_user}", id_user);
		return given()
				.header(apiKey,apiValue)
				.contentType("application/json")
				.body(userPayload)
				.when()
				.put(path);
	}
	
	public Response deleteUser(String id_user) {
		String path = ReqresEndpoints.DELETE_USER.replace("{id_user}", id_user);
		return given()
				.header(apiKey,apiValue)
				.when()
				.delete(path);
	}
}