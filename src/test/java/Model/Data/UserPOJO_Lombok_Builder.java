package Model.Data;

import Model.RegisterUserPOJO_Lombok;
import helpers.SystemHelper;
import net.datafaker.Faker;

import java.util.Locale;

public class UserPOJO_Lombok_Builder {
    public static RegisterUserPOJO_Lombok getUserData() {

        Faker faker = new Faker(new Locale("vi"));
        String phoneNumber = faker.phoneNumber().cellPhone();
        phoneNumber = phoneNumber.replace(" ", "");

        return RegisterUserPOJO_Lombok.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .phone(phoneNumber)
                .email(faker.internet().emailAddress())
                .userStatus(1)
                .build();
    }

    public static RegisterUserPOJO_Lombok getUserDataUpdate() {

        Faker faker = new Faker(new Locale("vi"));
        String phoneNumber = faker.phoneNumber().cellPhone();
        phoneNumber = phoneNumber.replace(" ", "");

        return RegisterUserPOJO_Lombok.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .phone(phoneNumber)
                .email(faker.internet().emailAddress())
                .userStatus(1)
                .build();
    }

    public static RegisterUserPOJO_Lombok getUserDataCreate() {

        Faker faker = new Faker(new Locale("vi"));

        String phoneNumber = "0" + faker.number().digits(9);
        System.out.println(phoneNumber);

        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        String username = SystemHelper.removeAccent(firstname + "." + lastname);

        return RegisterUserPOJO_Lombok.builder()
                .username(username)
                .password("Demo@123")
                .firstName(firstname)
                .lastName(lastname)
                .phone(phoneNumber)
                .email(username + "@email.com")
                .userStatus(1)
                .build();
    }
}
