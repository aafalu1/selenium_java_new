package org.aafalu.test.utilities;

public final class TestCredentials {

    private TestCredentials() {
    }

    public static String email() {
        return requiredValue("test.email", "TEST_EMAIL");
    }

    public static String password() {
        return requiredValue("test.password", "TEST_PASSWORD");
    }

    private static String requiredValue(String propertyName, String environmentName) {
        String value = System.getProperty(propertyName);
        if (value == null || value.isBlank()) {
            value = System.getenv(environmentName);
        }
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Set the " + environmentName + " environment variable or -D" + propertyName + " system property.");
        }
        return value;
    }
}
