package com.apitest;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;

import com.base.UtilityClass;

import io.restassured.http.ContentType;

public class Git extends UtilityClass {

	@BeforeMethod
	public void beforeMethod() throws IOException {
		baseURI = getPropertyFileValue("baseURI");
	}

	@Test(priority = 0)
	public void getUser() {
		given().get("users/SAral0027").then().assertThat().statusCode(200).body("id", isA(Integer.class)).log().all();
	}

	@Test(priority = 1)
	public void getRepo() {
		given().get("users/SAral0027/repos").then().assertThat().statusCode(200).body("[1].name", equalTo("Demo")).log()
				.all();
	}

	@Test(priority = 2)
	public void createRepo() throws IOException {
		given().header("Authorization", "Bearer " + getPropertyFileValue("token"))
				.header("Content-Type", "application/json").body(new File(getPropertyFileValue("jsonPathOfCreateRepo")))
				.when().post("user/SAral0027/repos").then().assertThat().statusCode(201).body("name", equalTo("REST")).log()
				.all();
	}

	@Test(priority = 3)
	public void updateRepo() throws IOException {
		given().header("Authorization", "Bearer " + getPropertyFileValue("token"))
				.header("Content-Type", "application/json").body(new File(getPropertyFileValue("jsonPathOfUpdateRepo")))
				.when().patch("repos/REST").then().assertThat().statusCode(200)
				.body("name", equalTo("RESTAssured")).log().all();
	}

	@Test(priority = 4)
	public void deleteRepo() throws IOException {
		given().header("Authorization", "Bearer " + getPropertyFileValue("token")).when()
				.delete("repos/SAral0027/RESTAssured").then().assertThat().statusCode(204).log().all();
	}

}
