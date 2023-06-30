package com.apitest;

import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;

public class GetRequest {
	public static void main(String[] args) {
		RestAssured.baseURI = "https://reqres.in/api/users?page=2";

		Response response = RestAssured.given().when().get();
		System.out.println(response.getStatusCode()); 
//		System.out.println(response.asPrettyString());

		response.then().log().all();

		JsonPath jsonPath = response.jsonPath();
		Object object = jsonPath.get("data[3].first_name"); // System.out.println(object.toString());

		Assert.assertEquals("Eve", object);

		/*
		 * RestAssured .given() .when() .get() .then()
		 * .assertThat().body("data[3].first_name", equalTo("Eve")).log().all();
		 */
	}
}
