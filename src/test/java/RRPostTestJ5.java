import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RRPostTestJ5 {
/*
    @Before  // NO funciona @BeforeEach -> JU5 ó @Before -> JU4
    public void setup(){
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(JSON)
                .build();
    }
 */

    //JUnit 5 + Hamcrest  -> junit.jupiter
    @Test
    public void POST(){
        RestAssured
                .given()
                .contentType(JSON)
                .body("{\"email\": \"eve.holt@reqres.in\",\n" +
                        "\"password\": \"cityslicka\"}")
            .when()
                .post("https://reqres.in/api/login")
            .then()
                .statusCode(HttpStatus.SC_OK)  // 200
                .body("token", notNullValue());  // Afirmación con Hamcrest
    }

    @Test
    public void GET(){
        RestAssured
            .given()
                .contentType(JSON)
            .when()
                .get("https://reqres.in/api/users/2")
            .then()
                .statusCode(HttpStatus.SC_OK) // 200
                .body("data.id.", equalTo(2));  // Afirmación con Hamcrest
    }

    @Test
    public void DELETE(){
        RestAssured
            .given()
                .contentType(JSON)
            .when()
                .delete("https://reqres.in/api/users/2")
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT); // 204
                //.body("data.id.", equalTo(2));  // Afirmación con Hamcrest
    }


    @Test
    public void PATCH(){
        String nameUpdated = given()
                .contentType(JSON)
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
            .when()
                .patch("https://reqres.in/api/users/2")
            .then()
                .statusCode(HttpStatus.SC_OK) // 200
                .extract()
                .jsonPath()
                .getString("name");

        assertThat(nameUpdated,
                equalTo("morpheus")); //Afirmación con Hamcrest
    }


    @Test
    public void PUT(){
        String jobUpdated = given()
                .contentType(JSON)
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
            .when()
                .put("https://reqres.in/api/users/2")
            .then()
                .statusCode(HttpStatus.SC_OK) // 200
                .extract()
                .jsonPath()
                .getString("job");

        assertThat(jobUpdated,
                equalTo("zion resident")); //Afirmación con Hamcrest
    }
}
