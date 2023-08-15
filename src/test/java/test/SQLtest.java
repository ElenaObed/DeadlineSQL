package test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import page.DashboardPage;
import page.LoginPage;
import page.VerificationPage;
import data.DataHelper;
import data.SQLHelper;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class SQLtest {


    @AfterAll
    static void clearBase() {
        SQLHelper.cleanDataBase();
    }

    @Test
    void shouldSuccessLoginUser() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var RegisteredUser = DataHelper.getRegisteredUser();
        var verificationPage = loginPage.validLogin(RegisteredUser);
        verificationPage.verifyVerificationPageVisiblity();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    void shouldGetErrorNotificationIfLoginRandom() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var RegisteredUser = DataHelper.getRandomUser();
        loginPage.validLogin(RegisteredUser);
        loginPage.verifyErrorNotificationVisiblity();

    }

    @Test
    void shouldErrorNotificationIfLoginWithExistUserAndRandomVerifCode(){
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var RegisteredUser = DataHelper.getRegisteredUser();
        var verificationPage = loginPage.validLogin(RegisteredUser);
        verificationPage.verifyVerificationPageVisiblity();
        var verificationCode = DataHelper.getRandomVerificationCode();
        verificationPage.verify(verificationCode.getCode());
        verificationPage.verifyErrorNotificationVisiblity();
    }
}
