package org.rafaelinc.cuponapi;

import org.junit.jupiter.api.Test;

import org.rafaelinc.cuponapi.model.Coupon;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_OK;
import org.mockito.MockedStatic;

public class CouponTest {

    private static final String BASE_URI = "http://localhost:8080/coupon/api/coupon";

    @Test
    public void shouldSave() {

        //Coupon coupon = new Coupon("SUPERSALE", 10, "2020-05-27");
        String body = "{\"code\":\"ABCD\",\"discount\":8.99,\"expireDate\":\"2022-05-21\"}";

        Coupon coupon =
                given().baseUri(BASE_URI)
                        .request()
                        .auth().preemptive().basic("doug@bailey.com", "doug")
                        .contentType(JSON)
                        .body(body)
                        .log().all()
                        .when()
                        .post()
                        .then().log().all()
                        .assertThat().statusCode(SC_OK)
                        .extract().response().body().as(Coupon.class);
    }

    @Test
    public void shouldReturnCoupon() {

        String code = "ABCD";
        String path = "/{code}";

        given().baseUri(BASE_URI)
                .basePath(path)
                .request()
                .auth().preemptive().basic("doug@bailey.com", "123456")
                .pathParam("code", code)
                .log().all()
                .when()
                .get()
                .then().log().all()
                .assertThat().statusCode(SC_OK);
    }

}
