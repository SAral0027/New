package com.apitest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
public class PostRequest {
	public static void main(String[] args) {
		RestAssured.baseURI = "https://reqres.in/api/users";

		Response response = RestAssured.given()
				.body("{\r\n" + "    \"name\": \"saral\",\r\n" + "    \"job\": \"engineer\"\r\n" + "}").when().post();

		response.then().log().all();
		System.out.println(response.getStatusCode());
//		System.out.println(response.asPrettyString());
//		
//		RestAssured
//		.given()
//			.body("")
//		.when()
//			.post()
//		.then()
//			.assertThat().body("", equalTo("")).log().all();

	}
}
