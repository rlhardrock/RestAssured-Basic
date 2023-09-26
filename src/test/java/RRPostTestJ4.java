import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RRPostTestJ4 {
    // NO FUNCIONA  JUnit4 con configuracion J17
    @Test
    public void loginTestJ4() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"email\": \"eve.holt@reqres.in\",\n" +
                        "\"password\": \"cityslicka\"}")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(200)
                .body("token", notNullValue());
    }
}
