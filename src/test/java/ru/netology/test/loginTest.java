package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.dataBase.DataBase;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class loginTest {
    String login = "vasya";
    String password = "qwerty123";

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldBeAuthorized() throws SQLException {
        $("[data-test-id=login] input").setValue(login);
        $("[data-test-id=password] input").setValue(password);
        $("[class=button__content]").click();
        $("[data-test-id='code'] input").setValue(DataBase.getAuthCode(login));
        $("[class=button__text]").click();
    }
}
