package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
/**
 * Created by Kartik Shah
 */
public class PostsAssertionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public static void start() {
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("size()", equalTo(20));
    }

    //2. Verify the if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupti cohaero libero.”
    @Test
    public void test002() {
//        response.body("findAll{it.[0] == 139915 }.title", equalTo("Defaeco in carbo decet audeo volutabrum corroboro."));
        response
                .body("[0]", hasEntry("title", "Defaeco in carbo decet audeo volutabrum corroboro."))
                .body("[0]", hasEntry("id", 139915));
    }

    //3. Check the single user_id in the Array list (5914249)
    @Test
    public void test003() {
        //response.body("[4].user_id", equalTo(5914249));
        response.body("user_id", hasItem(7015124));
    }

    //4. Check the multiple ids in the ArrayList (5914243, 5914202, 5914199)
    @Test
    public void test004() {
        response.body("id", hasItems(139915, 139914, 139909));
    }

    @Test
    public void test005() {
        response.body("[0].body", equalTo("Ultio cattus patrocinor. Sint cubitum vapulus. Valetudo tertius excepturi. Convoco delego sollers. Supellex antepono admoveo. Culpa appello deleniti. Aro dolores certo. Avaritia testimonium degero. Vir culpa temeritas. Vel modi theca. Voluptas vado error. Abduco sulum desipio. Suffoco quibusdam spiritus. Ea convoco velit."));
    }
}
