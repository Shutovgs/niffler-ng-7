package guru.qa.niffler.utils;

import com.github.javafaker.Faker;

public class DataGenerator {

    private static final Faker faker = new Faker();

    public static String randomUserName() {
        return faker.name().lastName().toLowerCase() + faker.random().nextInt(1, 100000);
    }

    public static String randomCategory() {
        return "Category_" + faker.random().nextInt(1, 100000);
    }
}
