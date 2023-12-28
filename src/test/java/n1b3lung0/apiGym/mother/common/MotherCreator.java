package n1b3lung0.apiGym.mother.common;

import net.datafaker.Faker;

import java.util.Locale;

public final class MotherCreator {

    private static final Faker faker = new Faker(new Locale("es"));

    public static Faker random() {
        return faker;
    }
}
