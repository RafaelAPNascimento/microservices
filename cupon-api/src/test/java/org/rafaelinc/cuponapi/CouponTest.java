package org.rafaelinc.cuponapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rafaelinc.cuponapi.model.Coupon;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

public class CouponTest {

    private static final String BASE_URI = "http://localhost:8080/couponapi/coupons";

    @Test
    @DisplayName("should unauthorize due to failed login")
    public void shouldUnauthorize() {

        String body = "{\"code\":\"ABCD\",\"discount\":8.99,\"expireDate\":\"2022-05-21\"}";

        given().baseUri(BASE_URI)
            .request()
            //.auth().preemptive().basic("john@ferguson.com", "john")
            .auth().preemptive().basic("kkk@bailey.com", "doug")
            .contentType(JSON)
            .body(body)
            .log().all()
            .when()
            .post()
            .then().log().all()
            .assertThat().statusCode(SC_UNAUTHORIZED);
    }

    @Test
    @DisplayName("should forbid because user doesn't have permissions to create coupons")
    public void shouldForbid() {

        String body = "{\"code\":\"ABCD\",\"discount\":8.99,\"expireDate\":\"2022-05-21\"}";

        given().baseUri(BASE_URI)
                .request()
                .auth().preemptive().basic("john@ferguson.com", "john")
                .contentType(JSON)
                .body(body)
                .log().all()
                .when()
                .post()
                .then().log().all()
                .assertThat().statusCode(SC_FORBIDDEN);
    }

    @Test
    public void shouldSave() {

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

        String code = "SUPERSALE";
        String path = "/{code}";

        given().baseUri(BASE_URI)
                .basePath(path)
                .request()
                .auth().preemptive().basic("doug@bailey.com", "doug")
                .pathParam("code", code)
                .log().all()
                .when()
                .get()
                .then().log().all()
                .assertThat().statusCode(SC_OK);
    }

}
