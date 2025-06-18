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

    /**
     * Replace all whitespaces characters (including spaces, tabs,
     * form feeds, line feeds, and other Unicode whitespace
     * characters) by " ".
     *
     * @param str the input string to be minified; may be null
     * @return a trimmed string with normalized spacing, or null if input is null
     */
    public static String minify(String str) {

        if (str == null) {
            return null;
        }

        return str.replaceAll("\\s+", " ").trim();
    }
}
