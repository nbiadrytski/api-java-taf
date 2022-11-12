package com.socks.ui.tests;

import static com.codeborne.selenide.Condition.text;
import com.socks.api.payloads.UserPayload;
import com.socks.api.services.UserApiService;
import static com.socks.api.conditions.Conditions.statusCode;

import com.socks.ui.LoggedUserPage;
import com.socks.ui.MainPage;
import org.testng.annotations.Test;


import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;


public class TestLogin extends BaseUITest {

    private final UserApiService userApiService = new UserApiService();

    @Test
    public void userCanLoginWithValidCredentials() {
        UserPayload userPayload = new UserPayload()
                .username(randomAlphanumeric(6))
                .email("test@mail.com")
                .password("test123");
        userApiService.registerUser(userPayload).shouldHave(statusCode(200));

        MainPage.open().loginAs(userPayload.username(), userPayload.password());

        LoggedUserPage loggedUserPage = at(LoggedUserPage.class);
        loggedUserPage.logoutBtn().shouldHave(text("Logout"));
        loggedUserPage.userName().shouldHave(text("Logged in as"));
    }
}
