package com.example;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class SelenideTest {

    @BeforeAll
    public static void setupAll() {
        Configuration.headless = System.getProperty("selenide.headless", "true").equals("true");
    }

    @Test
    public void testGoogleSearch() {
        open("https://www.google.com");
        $("input[name='q']").setValue("Selenide").pressEnter();
        $("h3").shouldHave(text("Selenide: concise UI tests in Java"));
    }
}
