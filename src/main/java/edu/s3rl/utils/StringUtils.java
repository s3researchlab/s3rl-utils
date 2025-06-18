package edu.s3rl.utils;

public class StringUtils {

    /**
     * This private constructor is to prevent the instantiation of this class directly
     */
    private StringUtils() {

        // Throwing an exception to avoid the use java reflection to instantiate this class
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    public static boolean isBlank(String str) {

        return str == null || str.isBlank();
    }
}
