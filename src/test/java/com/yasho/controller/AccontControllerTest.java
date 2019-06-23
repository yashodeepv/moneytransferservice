package com.yasho.controller;

import com.yasho.Application;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;

// Specific numbered methods so that they can be sorted in ascending order
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccontControllerTest {


    @BeforeClass
    public static void setup() {
        Application.main(null);
    }


    @Test // testAccountsTransfer
    public void test_5() {
        given()
                .when()
                .body("{\n" +
                        "\t\"sourceAccountId\": \"account1\",\n" +
                        "\t\"destinationAccountId\": \"account2\",\n" +
                        "\t\"amount\": 10\n" +
                        "}")
                .post("http://localhost:8080/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(200)
                .assertThat()
                .body(CoreMatchers.is("{\"message\":\"Fund Transfer is Successful\"}"));
    }

    @Test // testAccountsPostEndpointSetUpSecondAccount
    public void test_4() {
        given()
                .when()
                .body("{\n" +
                        "\t\"accountState\": \"ACTIVE\",\n" +
                        "\t\"accountType\": \"DEPOSIT\",\n" +
                        "\t\"balance\": \"100\"\n" +
                        "}")
                .post("http://localhost:8080/accounts")
                .then()
                .assertThat()
                .statusCode(200)
                .assertThat()
                .body(CoreMatchers.is("{\"accountId\":\"account2\",\"accountState\":\"ACTIVE\",\"accountType\":\"DEPOSIT\",\"balance\":100}"));
    }

    @Test // testAccountGetEndpoint
    public void test_3() {
        given()
                .when()
                .get("http://localhost:8080/accounts/account1")
                .then()
                .assertThat()
                .statusCode(200)
                .assertThat()
                .body(CoreMatchers.is("{\"accountId\":\"account1\",\"accountState\":\"ACTIVE\",\"accountType\":\"DEPOSIT\",\"balance\":100}"));
    }

    @Test // testAllAccountsGetEndpoint
    public void test_2() {
        given().
                when().
                get("http://localhost:8080/accounts").
                then().
                assertThat().
                statusCode(200)
                .body(CoreMatchers.is("[{\"accountId\":\"account1\",\"accountState\":\"ACTIVE\",\"accountType\":\"DEPOSIT\",\"balance\":100}]"));
    }


    @Test //testAccountsPostEndpointSetUpFirstAccount
    public void test_1() {
        given()
                .when()
                .body("{\n" +
                        "\t\"accountState\": \"ACTIVE\",\n" +
                        "\t\"accountType\": \"DEPOSIT\",\n" +
                        "\t\"balance\": \"100\"\n" +
                        "}")
                .post("http://localhost:8080/accounts")
                .then()
                .assertThat()
                .statusCode(200)
                .assertThat()
                .body(CoreMatchers.is("{\"accountId\":\"account1\",\"accountState\":\"ACTIVE\",\"accountType\":\"DEPOSIT\",\"balance\":100}"));
    }
}
