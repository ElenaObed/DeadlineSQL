package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;


public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));
    private DataHelper() {
    }
    public static RegisteredUser getRegisteredUser() {
        return new RegisteredUser("vasya", "qwerty123");
    }

   private static String getRandomLogin(){
        return faker.name().username();
   }
   public static String getRandomPassword() {
       return faker.internet().password();
      }

    public static RegisteredUser getRandomUser() {
        return new RegisteredUser(getRandomLogin(), getRandomPassword());
    }

    public static VerificationCode getRandomVerificationCode() {
        return new VerificationCode(faker.numerify("####"));
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class RegisteredUser {
        String login;
        String password;
    }
}