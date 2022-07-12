package com.socks.tests;

import com.github.javafaker.Faker;
import com.socks.api.ProjectConfig;
import com.socks.api.payloads.UserPayload;
import com.socks.api.responses.UserGenerationResponse;
import com.socks.api.services.UserApiService;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Locale;

import static com.socks.api.conditions.Conditions.statusCode;
import static com.socks.api.conditions.Conditions.bodyField;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.IsEmptyString.emptyOrNullString;


public class UsersTest {

    private final UserApiService userApiService = new UserApiService();
    private Faker faker;

    @BeforeClass
    public void setUp() {
        ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
        faker = new Faker(new Locale(config.locale()));
        RestAssured.baseURI = config.baseUrl();
    }

    @Test
    public void canRegisterNewUser() {
        // given
        UserPayload user = new UserPayload()
                .username(faker.name().username())
                .email("test@mail.com")
                .password("test123");

        // expect
        UserGenerationResponse response = userApiService.registerUser(user)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", not(is(emptyOrNullString()))))
                .asPojo(UserGenerationResponse.class);
        String userId = response.getId();
        System.out.println(userId);
    }

    @Test
    public void canNotRegisterUserTwice() {
        UserPayload user = new UserPayload()
                .username(faker.name().username())
                .email("test@mail.com")
                .password("test123");

        userApiService.registerUser(user)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", not(is(emptyOrNullString()))));

        userApiService.registerUser(user)
                .shouldHave(statusCode(500));
    }
}
