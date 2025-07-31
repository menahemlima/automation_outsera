package com.automation.web.utils;

import com.github.javafaker.Faker;

public class FakeDataGenerator {

	private final Faker faker = new Faker();

	public String getName() {
		return faker.name().fullName();
	}
	
	public String getFirstName() {
		return faker.name().firstName();
	}
	
	public String getLastName() {
		return faker.name().lastName();
	}
	
	public String getZipCode() {
		return faker.address().zipCode();
	}	

	public String getPassword() {
		return faker.internet().password();
	}
}
