package com.socks.ui;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;


public class MainPage {

    public static MainPage open() {
        Selenide.open("http://0.0.0.0/");
        return new MainPage();
    }

    public void loginAs(String username, String password) {
        $("#login > a").click();
        Selenide.sleep(2000);
        $("#username-modal").setValue(username);
        $("#password-modal").setValue(password);
        $("#login-modal p button").click();
    }
}
