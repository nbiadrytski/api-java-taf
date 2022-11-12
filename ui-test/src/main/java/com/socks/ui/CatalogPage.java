package com.socks.ui;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

public class CatalogPage {

    public static CatalogPage open() {
        Selenide.open("category.html");
        return page(CatalogPage.class);
    }

    public CatalogPage addItemByIndex(int index) {
        $$("#products .product .buttons a.btn-primary").get(index).click();
        return this;
    }

    public void goToCart() {
        $("#numItemsInCart").click();
    }
}
