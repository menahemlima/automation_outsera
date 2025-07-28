package com.reqres.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Utils {

	private static final Logger logger = LogManager.getLogger(Utils.class);

	public static void validateSchema(Response response, String nameFileSchema) {
		response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/" + nameFileSchema));
		logger.warn("Schema was validate successfully", nameFileSchema);
	}
}